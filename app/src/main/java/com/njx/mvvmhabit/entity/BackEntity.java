package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 退库
 */
public class BackEntity extends BaseObservable implements Parcelable {
    private String orderID;
    private String depotID;
    private String materialsDesc;
    private String materialsID;
    private int locationID;
    private int boxID;

    private int backNum;
    private int alreadBackNum;
    private int notBackNum;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDepotID() {
        return depotID;
    }

    public void setDepotID(String depotID) {
        this.depotID = depotID;
    }

    public String getMaterialsDesc() {
        return materialsDesc;
    }

    public void setMaterialsDesc(String materialsDesc) {
        this.materialsDesc = materialsDesc;
    }

    public String getMaterialsID() {
        return materialsID;
    }

    public void setMaterialsID(String materialsID) {
        this.materialsID = materialsID;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public int getBoxID() {
        return boxID;
    }

    public void setBoxID(int boxID) {
        this.boxID = boxID;
    }

    public int getBackNum() {
        return backNum;
    }

    public void setBackNum(int backNum) {
        this.backNum = backNum;
    }

    public int getAlreadBackNum() {
        return alreadBackNum;
    }

    public void setAlreadBackNum(int alreadBackNum) {
        this.alreadBackNum = alreadBackNum;
    }

    public int getNotBackNum() {
        return notBackNum;
    }

    public void setNotBackNum(int notBackNum) {
        this.notBackNum = notBackNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public BackEntity() {

    }

    public BackEntity(Parcel in) {
        this.orderID = in.readString();
        this.depotID = in.readString();
        this.materialsDesc = in.readString();
        this.materialsID = in.readString();
        this.locationID = in.readInt();
        this.boxID = in.readInt();
        this.backNum = in.readInt();
        this.alreadBackNum = in.readInt();
        this.notBackNum = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderID);
        dest.writeString(depotID);
        dest.writeString(materialsDesc);
        dest.writeString(materialsID);
        dest.writeInt(locationID);
        dest.writeInt(boxID);
        dest.writeInt(backNum);
        dest.writeInt(alreadBackNum);
        dest.writeInt(notBackNum);
    }

    public static final Creator<BackEntity> CREATOR = new Creator<BackEntity>() {
        @Override
        public BackEntity createFromParcel(Parcel source) {
            return new BackEntity(source);
        }

        @Override
        public BackEntity[] newArray(int size) {
            return new BackEntity[size];
        }
    };
}
