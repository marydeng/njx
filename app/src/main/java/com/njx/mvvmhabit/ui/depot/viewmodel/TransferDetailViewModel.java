package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.entity.ReturnEntity;
import com.njx.mvvmhabit.entity.TransferEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class TransferDetailViewModel extends ToolbarViewModel {
    public TransferEntity entity;
    public TransferDetailViewModel(@NonNull Application application) {
        super(application);
    }
    public void initToolBar() {
        setTitleText("查看物料详情");
    }

    public void setEntity(TransferEntity entity) {
        this.entity = entity;
    }
}
