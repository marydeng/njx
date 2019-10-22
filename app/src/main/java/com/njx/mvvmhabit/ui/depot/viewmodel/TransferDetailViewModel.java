package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.entity.ReturnEntity;
import com.njx.mvvmhabit.entity.TransferEntity;
import com.njx.mvvmhabit.entity.TransferPartRecordEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class TransferDetailViewModel extends ToolbarViewModel {
    public TransferPartRecordEntity entity;
    public ObservableField<String> notTransferTxt = new ObservableField<>("");
    public ObservableField<String> needNum = new ObservableField<>("");
    public ObservableField<String> transferNum = new ObservableField<>("");

    public TransferDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("查看调拨物料详情");
    }

    public void setEntity(TransferPartRecordEntity entity) {
        this.entity = entity;
        notTransferTxt.set(String.valueOf(entity.getQuantity() - entity.getTransferredQuantity()));
        needNum.set(String.valueOf(entity.getQuantity()));
        transferNum.set(String.valueOf(entity.getTransferredQuantity()));
    }
}
