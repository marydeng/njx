package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

public class TransferEntity extends BaseObservable implements Parcelable {
    private String destDepot;
    private String destClass;
    private String materialDes;
    private String materialID;
    private String originDepot;
    private String originDepotClass;
    private int locationID;
    private int transferNums;
    private int alreadyTransferNum;
    private int notTransferNum;

    public String getDestDepot() {
        return destDepot;
    }

    public void setDestDepot(String destDepot) {
        this.destDepot = destDepot;
    }

    public String getDestClass() {
        return destClass;
    }

    public void setDestClass(String destClass) {
        this.destClass = destClass;
    }

    public String getMaterialDes() {
        return materialDes;
    }

    public void setMaterialDes(String materialDes) {
        this.materialDes = materialDes;
    }

    public String getMaterialID() {
        return materialID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public int getTransferNums() {
        return transferNums;
    }

    public void setTransferNums(int transferNums) {
        this.transferNums = transferNums;
    }

    public int getAlreadyTransferNum() {
        return alreadyTransferNum;
    }

    public void setAlreadyTransferNum(int alreadyTransferNum) {
        this.alreadyTransferNum = alreadyTransferNum;
    }

    public int getNotTransferNum() {
        return notTransferNum;
    }

    public void setNotTransferNum(int notTransferNum) {
        this.notTransferNum = notTransferNum;
    }

    public String getOriginDepot() {
        return originDepot;
    }

    public void setOriginDepot(String originDepot) {
        this.originDepot = originDepot;
    }

    public String getOriginDepotClass() {
        return originDepotClass;
    }

    public void setOriginDepotClass(String originDepotClass) {
        this.originDepotClass = originDepotClass;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public TransferEntity() {

    }

    public TransferEntity(Parcel in) {
        destDepot = in.readString();
        destClass = in.readString();
        materialDes = in.readString();
        materialID = in.readString();
        originDepot = in.readString();
        originDepotClass = in.readString();
        locationID = in.readInt();
        transferNums = in.readInt();
        alreadyTransferNum = in.readInt();
        notTransferNum = in.readInt();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(destDepot);
        dest.writeString(destClass);
        dest.writeString(materialDes);
        dest.writeString(materialID);
        dest.writeString(originDepot);
        dest.writeString(originDepotClass);
        dest.writeInt(locationID);
        dest.writeInt(transferNums);
        dest.writeInt(alreadyTransferNum);
        dest.writeInt(notTransferNum);
    }

    public static final Creator<TransferEntity> CREATOR = new Creator<TransferEntity>() {
        @Override
        public TransferEntity createFromParcel(Parcel source) {
            return new TransferEntity(source);
        }

        @Override
        public TransferEntity[] newArray(int size) {
            return new TransferEntity[size];
        }
    };
}
