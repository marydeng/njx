package com.njx.mvvmhabit.ui.main.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class MainViewModel extends ToolbarViewModel {
    public MainViewModel(@NonNull Application application) {
        super(application);
    }
    public void initToolBar() {
        setTitleText("南极星");
    }

   }
