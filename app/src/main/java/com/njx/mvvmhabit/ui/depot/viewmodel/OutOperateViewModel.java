package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class OutOperateViewModel extends ToolbarViewModel {
    public ObservableField<String> orderTxt =new ObservableField<>();
    public ObservableField<String> deptTxt=new ObservableField<>();

    public OutOperateViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("出库管理");
    }



}
