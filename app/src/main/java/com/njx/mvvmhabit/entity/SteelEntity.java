package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;
//            "searchValue": null,
//                    "createBy": null,
//                    "createTime": "2019-07-23 13:23:48",
//                    "updateBy": null,
//                    "updateTime": null,
//                    "remark": null,
//                    "params": {},
//                    "id": "dc1605ff-ad09-11e9-9975-484d7eac031f",
//                    "steelPlateType": "2",
//                    "describes": "2",
//                    "useNum": 6,
//                    "usageNum": 2,
//                    "status": "上机台",
//                    "testPoint": 5,
//                    "category": "钢板",
//                    "lineClass": "L5",
//                    "jobNum": null,
//                    "partNum": "6871L-5809B"
public class SteelEntity extends BaseObservable implements Parcelable {
    private String stationID;
    private String steelPlateType;
    private String orderID;
    private String type;
    private String id;
    private String lineClass;
    private String status;
    private String useNum;
    private String usageNum;
    private String category;
    private String jobNum;

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

    public String getUseNum() {
        return useNum;
    }

    public void setUseNum(String useNum) {
        this.useNum = useNum;
    }

    public String getUsageNum() {
        return usageNum;
    }

    public void setUsageNum(String usageNum) {
        this.usageNum = usageNum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
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
        this.useNum =in.readString();
        this.usageNum=in.readString();
        this.jobNum=in.readString();
        this.category=in.readString();

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
            dest.writeString(useNum);
            dest.writeString(usageNum);
            dest.writeString(jobNum);
            dest.writeString(category);
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
