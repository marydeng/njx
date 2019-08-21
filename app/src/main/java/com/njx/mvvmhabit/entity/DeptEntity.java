package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;

public class DeptEntity extends BaseObservable {
    private String deptName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
