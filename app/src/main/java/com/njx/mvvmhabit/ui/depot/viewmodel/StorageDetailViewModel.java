package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.entity.StorageEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class StorageDetailViewModel extends ToolbarViewModel {
    public StorageEntity storageEntity;
    public StorageDetailViewModel(@NonNull Application application) {
        super(application);
    }
    public void initToolBar() {
        setTitleText("查看物料详情");
    }

    public void setStorageEntity(StorageEntity storageEntity) {
        this.storageEntity = storageEntity;
    }
}
