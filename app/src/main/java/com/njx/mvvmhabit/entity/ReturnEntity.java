package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 退货
 */
public class ReturnEntity extends BaseObservable implements Parcelable {
    private String orderID;
    private String materialsDesc;
    private String materialsID;
    private int boxID;

    private int returnNum;
    private int alreadReturnNum;
    private int notReturnNum;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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


    public int getBoxID() {
        return boxID;
    }

    public void setBoxID(int boxID) {
        this.boxID = boxID;
    }

    public int getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(int returnNum) {
        this.returnNum = returnNum;
    }

    public int getAlreadReturnNum() {
        return alreadReturnNum;
    }

    public void setAlreadReturnNum(int alreadReturnNum) {
        this.alreadReturnNum = alreadReturnNum;
    }

    public int getNotReturnNum() {
        return notReturnNum;
    }

    public void setNotReturnNum(int notReturnNum) {
        this.notReturnNum = notReturnNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public ReturnEntity() {

    }

    public ReturnEntity(Parcel in) {
        this.orderID = in.readString();
        this.materialsDesc = in.readString();
        this.materialsID = in.readString();
        this.boxID = in.readInt();
        this.returnNum = in.readInt();
        this.alreadReturnNum = in.readInt();
        this.notReturnNum = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderID);
        dest.writeString(materialsDesc);
        dest.writeString(materialsID);
        dest.writeInt(boxID);
        dest.writeInt(returnNum);
        dest.writeInt(alreadReturnNum);
        dest.writeInt(notReturnNum);
    }

    public static final Creator<ReturnEntity> CREATOR = new Creator<ReturnEntity>() {
        @Override
        public ReturnEntity createFromParcel(Parcel source) {
            return new ReturnEntity(source);
        }

        @Override
        public ReturnEntity[] newArray(int size) {
            return new ReturnEntity[size];
        }
    };
}
