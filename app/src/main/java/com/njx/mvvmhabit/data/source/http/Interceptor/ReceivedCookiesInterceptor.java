package com.njx.mvvmhabit.data.source.http.Interceptor;

import com.google.gson.Gson;
import com.njx.mvvmhabit.app.Constant;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.utils.SPUtils;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 首次请求从response中获取cookie
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {


        Response originalResponse = chain.proceed(chain.request());
        if (chain.request().url().url().getPath().contains("loginByUserName")) {
            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                List<String> cookies = new ArrayList<>();
                for (String header : originalResponse.headers("Set-Cookie")) {
                    cookies.add(header);
                }
                String cookieStr = new Gson().toJson(cookies);
                SPUtils.getInstance().put(Constant.SP_Coonkie_Key, cookieStr);
            }
        }
        return originalResponse;
    }
}
