package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.entity.BackEntity;
import com.njx.mvvmhabit.entity.StorageEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class BackDetailViewModel extends ToolbarViewModel {
    public BackEntity entity;
    public BackDetailViewModel(@NonNull Application application) {
        super(application);
    }
    public void initToolBar() {
        setTitleText("查看物料详情");
    }

    public void setEntity(BackEntity entity) {
        this.entity = entity;
    }
}
