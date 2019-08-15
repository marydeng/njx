package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;

public class OrderEntity extends BaseObservable {
    private String id;
    private String workorderNumber;
    private String partno;
    private String presetLine;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkorderNumber() {
        return workorderNumber;
    }

    public void setWorkorderNumber(String workorderNumber) {
        this.workorderNumber = workorderNumber;
    }

    public String getPartno() {
        return partno;
    }

    public void setPartno(String partno) {
        this.partno = partno;
    }

    public String getPresetLine() {
        return presetLine;
    }

    public void setPresetLine(String presetLine) {
        this.presetLine = presetLine;
    }
}
