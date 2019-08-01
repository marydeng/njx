package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class TransferOperateViewModel extends ToolbarViewModel {
    public ObservableField<String> DestDepotTxt =new ObservableField<>();
    public ObservableField<String> DepotClassTxt=new ObservableField<>();

    public TransferOperateViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("调拨管理");
    }



}
