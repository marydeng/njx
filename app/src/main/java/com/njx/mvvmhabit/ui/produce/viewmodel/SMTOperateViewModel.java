package com.njx.mvvmhabit.ui.produce.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class SMTOperateViewModel extends ToolbarViewModel {
    public ObservableField<String> orderId =new ObservableField<>();
    public ObservableField<String> smtType =new ObservableField<>();

    public SMTOperateViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("上料管理");
    }



}
