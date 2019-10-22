package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.entity.BackEntity;
import com.njx.mvvmhabit.entity.BackPartRecordEntity;
import com.njx.mvvmhabit.entity.StorageEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class BackDetailViewModel extends ToolbarViewModel {
    public BackPartRecordEntity entity;
    public ObservableField<String> needNum = new ObservableField<>("");
    public ObservableField<String> backNum = new ObservableField<>("");

    public BackDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("查看退库物料详情");
    }

    public void setEntity(BackPartRecordEntity entity) {
        this.entity = entity;
        needNum.set(String.valueOf(entity.getReturnQuantity()));
        backNum.set(String.valueOf(entity.getQuantity()));
    }
}
