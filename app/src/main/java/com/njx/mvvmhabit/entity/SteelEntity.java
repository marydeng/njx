package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

public class SteelEntity extends BaseObservable implements Parcelable {
    private String stationID;
    private String steelPlateType;
    private String orderID;
    private String type;
    private String id;
    private String lineClass;
    private String status;
    private int useNum;
    private int usageNum;

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getSteelPlateType() {
        return steelPlateType;
    }

    public void setSteelPlateType(String steelPlateType) {
        this.steelPlateType = steelPlateType;
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

    public String getLineClass() {
        return lineClass;
    }

    public void setLineClass(String lineClass) {
        this.lineClass = lineClass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUseNum() {
        return useNum;
    }

    public void setUseNum(int useNum) {
        this.useNum = useNum;
    }



    public SteelEntity() {
    }

    public SteelEntity(String type, String id, String lineClass) {
        this.type = type;
        this.id = id;
        this.lineClass = lineClass;
    }

    public SteelEntity(Parcel in) {
        this.stationID=in.readString();
        this.steelPlateType =in.readString();
        this.orderID=in.readString();
        this.type =in.readString();
        this.id=in.readString();
        this.lineClass =in.readString();
        this.status=in.readString();
        this.useNum =in.readInt();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(stationID);
            dest.writeString(steelPlateType);
            dest.writeString(orderID);
            dest.writeString(type);
            dest.writeString(id);
            dest.writeString(lineClass);
            dest.writeString(status);
            dest.writeInt(useNum);
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
