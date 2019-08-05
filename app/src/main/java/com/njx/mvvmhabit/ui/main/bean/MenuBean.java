package com.njx.mvvmhabit.ui.main.bean;

/**
 * Created by Administrator on 2018/7/25.
 */

public class MenuBean {
    private String url;
    private String name;
    private int function;

    public MenuBean() {
    }

    public MenuBean(String resourse, String name, int function) {
        this.url = resourse;
        this.name = name;
        this.function = function;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFunction() {
        return function;
    }

    public void setFunction(int function) {
        this.function = function;
    }
}
