package com.njx.mvvmhabit.ui.produce.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class SteelOperateViewModel extends ToolbarViewModel {
    public ObservableField<String> stationId =new ObservableField<>();
    public ObservableField<String> type =new ObservableField<>();

    public SteelOperateViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("上料管理");
    }



}
