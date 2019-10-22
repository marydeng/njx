package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.entity.OutEntity;
import com.njx.mvvmhabit.entity.OutPartRecordEntity;
import com.njx.mvvmhabit.entity.StorageEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class OutDetailViewModel extends ToolbarViewModel {
    public OutPartRecordEntity entity;
    public ObservableField<String> needNum = new ObservableField<>("");
    public ObservableField<String> outNum = new ObservableField<>("");

    public OutDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("查看出库物料详情");
    }

    public void setEntity(OutPartRecordEntity entity) {
        this.entity = entity;
        needNum.set(String.valueOf(entity.getAmount()));
        outNum.set(String.valueOf(entity.getOutgoingAmount()));
    }
}
