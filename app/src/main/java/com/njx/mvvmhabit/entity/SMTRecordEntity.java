package com.njx.mvvmhabit.entity;

import java.util.Observable;

// SMT 扫码记录
public class SMTRecordEntity extends Observable {

    private String materialRoll;
    private String materialRollNew;
    private String materialStation;
    private String materialRack;
    private String materialRackNew;
    private String type;
    private String workItem;

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

    public String getMaterialRollNew() {
        return materialRollNew;
    }

    public void setMaterialRollNew(String materialRollNew) {
        this.materialRollNew = materialRollNew;
    }

    public String getMaterialRack() {
        return materialRack;
    }

    public void setMaterialRack(String materialRack) {
        this.materialRack = materialRack;
    }

    public String getMaterialRackNew() {
        return materialRackNew;
    }

    public void setMaterialRackNew(String materialRackNew) {
        this.materialRackNew = materialRackNew;
    }
}
