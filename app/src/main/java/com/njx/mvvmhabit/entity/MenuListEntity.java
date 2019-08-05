package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MenuListEntity extends BaseObservable implements Parcelable {

    private List<MenuEntity> menuEntityList;


    public List<MenuEntity> getMenuEntityList() {
        return menuEntityList;
    }

    public void setMenuEntityList(List<MenuEntity> menuEntityList) {
        this.menuEntityList = menuEntityList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(menuEntityList);

    }

    public MenuListEntity(Parcel in) {
        this.menuEntityList = in.readArrayList(MenuEntity.class.getClassLoader());
    }

    public static final Creator<MenuListEntity> CREATOR = new Creator<MenuListEntity>() {
        @Override
        public MenuListEntity createFromParcel(Parcel parcel) {
            return new MenuListEntity(parcel);
        }

        @Override
        public MenuListEntity[] newArray(int i) {
            return new MenuListEntity[i];
        }
    };
}
