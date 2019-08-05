package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

public class RoleEntity extends BaseObservable implements Parcelable {
    private String menuIds;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.menuIds);
    }

    public RoleEntity(Parcel in) {
        this.menuIds=in.readString();
    }

    public static final Creator<RoleEntity> CREATOR = new Creator<RoleEntity>() {
        @Override
        public RoleEntity createFromParcel(Parcel parcel) {
            return new RoleEntity(parcel);
        }

        @Override
        public RoleEntity[] newArray(int i) {
            return new RoleEntity[i];
        }
    };
}
