package com.njx.mvvmhabit.ui.main.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.app.AppApplication;
import com.njx.mvvmhabit.entity.UserEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

public class MyViewModel extends ToolbarViewModel {
    public UserEntity userEntity = AppApplication.getInstance().userEntity;
    public ObservableField<String> depatName = new ObservableField<>("");

    public MyViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("个人信息");
        if (userEntity != null && userEntity.getDept() != null && !TextUtils.isEmpty(userEntity.getDept().getDeptName())) {
            depatName.set(userEntity.getDept().getDeptName());
        }
    }

}
