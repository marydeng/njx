package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 上料
 */
//站位，生成数量，料号，料站号，料枪号，REEL料卷号，REEL料卷数量，料卷日期
public class FeedingEntity extends BaseObservable implements Parcelable {
    private String orderID;
    private String SMTtype;
    private String stationID;
    private String materialsID;
    private int produceNum;
    private String materialsStaID;
    private String materialsGunID;
    private String REELID;
    private String materialRollDate;
    private int REELnum;

    public interface SMTType{
        String feeding="111";
        String changeMater="112";
        String changeGun="113";

    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getSMTtype() {
        return SMTtype;
    }

    public void setSMTtype(String SMTtype) {
        this.SMTtype = SMTtype;
    }

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getMaterialsID() {
        return materialsID;
    }

    public void setMaterialsID(String materialsID) {
        this.materialsID = materialsID;
    }

    public int getProduceNum() {
        return produceNum;
    }

    public void setProduceNum(int produceNum) {
        this.produceNum = produceNum;
    }

    public int getREELnum() {
        return REELnum;
    }

    public void setREELnum(int REELnum) {
        this.REELnum = REELnum;
    }



    public String getMaterialsStaID() {
        return materialsStaID;
    }

    public void setMaterialsStaID(String materialsStaID) {
        this.materialsStaID = materialsStaID;
    }

    public String getMaterialsGunID() {
        return materialsGunID;
    }

    public void setMaterialsGunID(String materialsGunID) {
        this.materialsGunID = materialsGunID;
    }

    public String getREELID() {
        return REELID;
    }

    public void setREELID(String REELID) {
        this.REELID = REELID;
    }

    public String getMaterialRollDate() {
        return materialRollDate;
    }

    public void setMaterialRollDate(String materialRollDate) {
        this.materialRollDate = materialRollDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }




    public FeedingEntity() {

    }

    public FeedingEntity(String materialsStaID, String materialsGunID, String REELID) {
        this.materialsStaID = materialsStaID;
        this.materialsGunID = materialsGunID;
        this.REELID = REELID;
    }

    public FeedingEntity(Parcel in) {
        this.orderID = in.readString();
        this.SMTtype = in.readString();
        this.stationID = in.readString();
        this.materialsID = in.readString();
        this.produceNum = in.readInt();
        this.REELnum = in.readInt();
        this.materialsStaID=in.readString();
        this.materialsGunID=in.readString();
        this.REELID=in.readString();
        this.materialRollDate=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderID);
        dest.writeString(SMTtype);
        dest.writeString(stationID);
        dest.writeString(materialsID);
        dest.writeInt(produceNum);
        dest.writeInt(REELnum);
        dest.writeString(materialsStaID);
        dest.writeString(materialsGunID);
        dest.writeString(REELID);
        dest.writeString(materialRollDate);
    }

    public static final Creator<FeedingEntity> CREATOR = new Creator<FeedingEntity>() {
        @Override
        public FeedingEntity createFromParcel(Parcel source) {
            return new FeedingEntity(source);
        }

        @Override
        public FeedingEntity[] newArray(int size) {
            return new FeedingEntity[size];
        }
    };
}
