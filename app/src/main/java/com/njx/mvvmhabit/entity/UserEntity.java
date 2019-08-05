package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class UserEntity extends BaseObservable implements Parcelable {
    private String userId;
    private List<RoleEntity> roleEntityList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<RoleEntity> getRoleEntityList() {
        return roleEntityList;
    }

    public void setRoleEntityList(List<RoleEntity> roleEntityList) {
        this.roleEntityList = roleEntityList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public UserEntity(Parcel in) {
        this.userId=in.readString();
        roleEntityList=in.readArrayList(RoleEntity.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userId);
        parcel.writeList(roleEntityList);
    }

    public static final Creator<UserEntity> CREATOR=new Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel parcel) {
            return new UserEntity(parcel);
        }

        @Override
        public UserEntity[] newArray(int i) {
            return new UserEntity[i];
        }
    };
}
