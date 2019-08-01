package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class StorageOperateViewModel extends ToolbarViewModel {
    public ObservableField<String> invoiceTxt=new ObservableField<>();
    public ObservableField<String> deptTxt=new ObservableField<>();

    public StorageOperateViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("入库管理");
    }



}
