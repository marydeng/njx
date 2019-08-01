package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 出库
 */
public class OutEntity extends BaseObservable implements Parcelable {
    private String orderID;
    private String depotID;
    private String materialsDesc;
    private String materialsID;
    private int needsNum;
    private int outNum;

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

    public int getNeedsNum() {
        return needsNum;
    }

    public void setNeedsNum(int needsNum) {
        this.needsNum = needsNum;
    }

    public int getOutNum() {
        return outNum;
    }

    public void setOutNum(int outNum) {
        this.outNum = outNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public OutEntity() {
    }

    public OutEntity(Parcel in) {
        orderID = in.readString();
        depotID = in.readString();
        materialsDesc = in.readString();
        materialsID = in.readString();
        needsNum = in.readInt();
        outNum = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderID);
        dest.writeString(depotID);
        dest.writeString(materialsDesc);
        dest.writeString(materialsID);
        dest.writeInt(needsNum);
        dest.writeInt(outNum);
    }

    public static final Creator<OutEntity> CREATOR = new Creator<OutEntity>() {
        @Override
        public OutEntity createFromParcel(Parcel source) {
            return new OutEntity(source);
        }

        @Override
        public OutEntity[] newArray(int size) {
            return new OutEntity[size];
        }
    };
}
