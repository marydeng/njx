package com.njx.mvvmhabit.ui.produce.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.entity.FeedingEntity;
import com.njx.mvvmhabit.entity.SteelEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class SteelDetailViewModel extends ToolbarViewModel {
    public SteelEntity entity;
    public SteelDetailViewModel(@NonNull Application application) {
        super(application);
    }
    public void initToolBar() {
        setTitleText("查看钢板详情");
    }

    public void setEntity(SteelEntity entity) {
        this.entity = entity;
    }
}
