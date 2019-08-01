package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class ReturnOperateViewModel extends ToolbarViewModel {
    public ObservableField<String> orderTxt =new ObservableField<>();


    public ReturnOperateViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("退货管理");
    }



}
