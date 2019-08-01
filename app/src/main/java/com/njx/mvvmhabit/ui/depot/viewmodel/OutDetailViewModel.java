package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.entity.OutEntity;
import com.njx.mvvmhabit.entity.StorageEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class OutDetailViewModel extends ToolbarViewModel {
    public OutEntity outEntity;
    public OutDetailViewModel(@NonNull Application application) {
        super(application);
    }
    public void initToolBar() {
        setTitleText("查看物料详情");
    }

    public void setEntity(OutEntity outEntity) {
        this.outEntity = outEntity;
    }
}
