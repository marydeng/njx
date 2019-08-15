package me.goldze.mvvmhabit.http.cookie;


import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.http.cookie.store.CookieStore;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by goldze on 2017/5/13.
 */
public class CookieJarImpl implements CookieJar {

    private CookieStore cookieStore;

    public CookieJarImpl(CookieStore cookieStore) {
        if (cookieStore == null) {
            throw new IllegalArgumentException("cookieStore can not be null!");
        }
        this.cookieStore = cookieStore;
    }

    @Override
    public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            System.out.println("scookie Name:" + cookie.name());
            System.out.println("scookie Path:" + cookie.path());
        }
        cookieStore.saveCookie(url, cookies);
    }

    @Override
    public synchronized List<Cookie> loadForRequest(HttpUrl url) {
        if(url.url().getPath().contains("loginByUserName")){
            return new ArrayList<>();
        }
        List<Cookie> cookieList=cookieStore.loadCookie(url);
        for (Cookie cookie : cookieList) {
            System.out.println("lcookie Name:" + cookie.name());
            System.out.println("lcookie Path:" + cookie.path());
        }
        return cookieList;
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }
}