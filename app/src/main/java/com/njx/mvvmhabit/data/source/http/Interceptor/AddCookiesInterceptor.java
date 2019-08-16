package com.njx.mvvmhabit.data.source.http.Interceptor;

import android.util.Log;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import com.njx.mvvmhabit.app.Constant;

import java.io.IOException;
import java.util.List;

import me.goldze.mvvmhabit.utils.SPUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 非首次请求，请求头添加cookie
 */
public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (!chain.request().url().url().getPath().contains("loginByUserName")) {
            String cookieStr = SPUtils.getInstance().getString(Constant.SP_Coonkie_Key, "");
            List<String> cookies = new Gson().fromJson(cookieStr, new TypeToken<List<String>>() {
            }.getType());
            if (cookies != null) {
                for (String cookie : cookies) {
                    builder.addHeader("Cookie",cookie);
//                    builder.addHeader("Cookie", "JSESSIONID=92497aef-c62a-4405-a3a2-6e1e83a9efe0; Path=/; HttpOnly");//Todo
                    Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
                }
            }

        }
        builder.addHeader("User-Agent","PostmanRuntime/7.15.2");
        builder.addHeader("Accept","*/*");
        builder.addHeader("Cache-Control","no-cache");
        builder.addHeader("Postman-Token","a2b06ec5-f002-4ee6-a062-11627c64f382");
        builder.addHeader("Host","192.168.1.137:8080");
        builder.addHeader("Accept-Encoding","gzip, deflate");
        builder.addHeader("Connection","keep-alive");
        return chain.proceed(builder.build());
    }
}
