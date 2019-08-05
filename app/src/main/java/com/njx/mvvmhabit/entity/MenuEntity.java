package com.njx.mvvmhabit.entity;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

public class MenuEntity extends BaseObservable implements Parcelable {
    private String icon;
    private String menuName;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.icon);
        parcel.writeString(this.menuName);
    }

    public MenuEntity(Parcel in) {
        this.icon = in.readString();
        this.menuName = in.readString();
    }

    public static final Creator<MenuEntity> CREATOR = new Creator<MenuEntity>() {
        @Override
        public MenuEntity createFromParcel(Parcel parcel) {
            return new MenuEntity(parcel);
        }

        @Override
        public MenuEntity[] newArray(int i) {
            return new MenuEntity[i];
        }
    };
}
