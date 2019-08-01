package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.entity.BackEntity;
import com.njx.mvvmhabit.entity.ReturnEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class ReturnDetailViewModel extends ToolbarViewModel {
    public ReturnEntity entity;
    public ReturnDetailViewModel(@NonNull Application application) {
        super(application);
    }
    public void initToolBar() {
        setTitleText("查看物料详情");
    }

    public void setEntity(ReturnEntity entity) {
        this.entity = entity;
    }
}
