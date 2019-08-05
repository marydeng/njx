package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

public class SteelEntity extends BaseObservable implements Parcelable {
    private String stationID;
    private String operateType;
    private String orderID;
    private String type;
    private String id;
    private String lineID;
    private String status;
    private int nums;

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLineID() {
        return lineID;
    }

    public void setLineID(String lineID) {
        this.lineID = lineID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }



    public SteelEntity() {
    }

    public SteelEntity(String type, String id, String lineID) {
        this.type = type;
        this.id = id;
        this.lineID = lineID;
    }

    public SteelEntity(Parcel in) {
        this.stationID=in.readString();
        this.operateType=in.readString();
        this.orderID=in.readString();
        this.type=in.readString();
        this.id=in.readString();
        this.lineID=in.readString();
        this.status=in.readString();
        this.nums=in.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(stationID);
            dest.writeString(operateType);
            dest.writeString(orderID);
            dest.writeString(type);
            dest.writeString(id);
            dest.writeString(lineID);
            dest.writeString(status);
            dest.writeInt(nums);
    }

    public static final Creator<SteelEntity> CREATOR=new Creator<SteelEntity>() {
        @Override
        public SteelEntity createFromParcel(Parcel source) {
            return new SteelEntity(source);
        }

        @Override
        public SteelEntity[] newArray(int size) {
            return new SteelEntity[size];
        }
    };


}
