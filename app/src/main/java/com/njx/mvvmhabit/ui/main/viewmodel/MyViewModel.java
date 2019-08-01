package com.njx.mvvmhabit.ui.main.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class MyViewModel extends ToolbarViewModel {
    public MyViewModel(@NonNull Application application) {
        super(application);
    }
    public void initToolBar() {
        setTitleText("个人信息");
    }

   }
