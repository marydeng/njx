package com.njx.mvvmhabit.ui.produce.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.entity.FeedingEntity;
import com.njx.mvvmhabit.entity.ReturnEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class SMTDetailViewModel extends ToolbarViewModel {
    public FeedingEntity entity;
    public SMTDetailViewModel(@NonNull Application application) {
        super(application);
    }
    public void initToolBar() {
        setTitleText("查看上料详情");
    }

    public void setEntity(FeedingEntity entity) {
        this.entity = entity;
    }
}
