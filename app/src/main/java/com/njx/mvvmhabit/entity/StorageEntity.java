package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

public class StorageEntity extends BaseObservable implements Parcelable {
    private String invoiceNo;
    private String deptNo;
    private String materialsDesc;
    private String materialsNo;
    private String depatLocat;
    private String curentStor;
    private String needStor;
    private String alreadyStor;


    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getMaterialsDesc() {
        return materialsDesc;
    }

    public void setMaterialsDesc(String materialsDesc) {
        this.materialsDesc = materialsDesc;
    }

    public String getMaterialsNo() {
        return materialsNo;
    }

    public void setMaterialsNo(String materialsNo) {
        this.materialsNo = materialsNo;
    }

    public String getDepatLocat() {
        return depatLocat;
    }

    public void setDepatLocat(String depatLocat) {
        this.depatLocat = depatLocat;
    }

    public String getCurentStor() {
        return curentStor;
    }

    public void setCurentStor(String curentStor) {
        this.curentStor = curentStor;
    }

    public String getNeedStor() {
        return needStor;
    }

    public void setNeedStor(String needStor) {
        this.needStor = needStor;
    }

    public String getAlreadyStor() {
        return alreadyStor;
    }

    public void setAlreadyStor(String alreadyStor) {
        this.alreadyStor = alreadyStor;
    }



    public StorageEntity(String materialsNo, String curentStor, String needStor, String alreadyStor) {
        this.materialsNo = materialsNo;
        this.curentStor = curentStor;
        this.needStor = needStor;
        this.alreadyStor = alreadyStor;
    }

    public StorageEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public StorageEntity(Parcel in) {
        this.invoiceNo = in.readString();
        this.deptNo = in.readString();
        this.materialsDesc = in.readString();
        this.materialsNo = in.readString();
        this.depatLocat = in.readString();
        this.curentStor = in.readString();
        this.needStor = in.readString();
        this.alreadyStor = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.invoiceNo);
        dest.writeString(this.deptNo);
        dest.writeString(this.materialsDesc);
        dest.writeString(this.materialsNo);
        dest.writeString(this.depatLocat);
        dest.writeString(this.curentStor);
        dest.writeString(this.needStor);
        dest.writeString(this.alreadyStor);
    }

    public static final Creator<StorageEntity> CREATOR = new Creator<StorageEntity>() {
        @Override
        public StorageEntity createFromParcel(Parcel source) {
            return new StorageEntity(source);
        }

        @Override
        public StorageEntity[] newArray(int size) {
            return new StorageEntity[size];
        }
    };
}
