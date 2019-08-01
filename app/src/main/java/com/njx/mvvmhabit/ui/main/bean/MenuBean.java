package com.njx.mvvmhabit.ui.main.bean;

/**
 * Created by Administrator on 2018/7/25.
 */

public class MenuBean {
    private int resourse;
    private String name;
    private int function;

    public MenuBean() {
    }

    public MenuBean(int resourse, String name, int function) {
        this.resourse = resourse;
        this.name = name;
        this.function = function;
    }

    public int getResourse() {
        return resourse;
    }

    public void setResourse(int resourse) {
        this.resourse = resourse;
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
