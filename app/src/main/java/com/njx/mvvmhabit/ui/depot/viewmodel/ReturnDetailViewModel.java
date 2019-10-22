package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.entity.BackEntity;
import com.njx.mvvmhabit.entity.ReturnEntity;
import com.njx.mvvmhabit.entity.ReturnPartRecordEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class ReturnDetailViewModel extends ToolbarViewModel {
    public ReturnPartRecordEntity entity;
    public ObservableField<String> needNum=new ObservableField<>("");
    public ObservableField<String> returnNum=new ObservableField<>("");
    public ObservableField<String> notReturnNum=new ObservableField<>();
    public ReturnDetailViewModel(@NonNull Application application) {
        super(application);
    }
    public void initToolBar() {
        setTitleText("查看退货物料详情");
    }

    public void setEntity(ReturnPartRecordEntity entity) {
        this.entity = entity;
        notReturnNum.set(String.valueOf(entity.getQuantity()-entity.getReturnQuantity()));
        needNum.set(String.valueOf(entity.getQuantity()));
        returnNum.set(String.valueOf(entity.getReturnQuantity()));
    }
}
