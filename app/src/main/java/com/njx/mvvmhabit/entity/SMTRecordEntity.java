package com.njx.mvvmhabit.entity;

import java.util.Observable;

// SMT 扫码记录
public class SMTRecordEntity extends Observable {
    private String materialGun;
    private String materialRoll;
    private String materialStation;
    private String type;
    private String workItem;

    public String getMaterialGun() {
        return materialGun;
    }

    public void setMaterialGun(String materialGun) {
        this.materialGun = materialGun;
    }

    public String getMaterialRoll() {
        return materialRoll;
    }

    public void setMaterialRoll(String materialRoll) {
        this.materialRoll = materialRoll;
    }

    public String getMaterialStation() {
        return materialStation;
    }

    public void setMaterialStation(String materialStation) {
        this.materialStation = materialStation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWorkItem() {
        return workItem;
    }

    public void setWorkItem(String workItem) {
        this.workItem = workItem;
    }
}
