package com.njx.mvvmhabit.ui.produce.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.produce.GunChangeOperateFragment;
import com.njx.mvvmhabit.ui.produce.MaterChangeOperateFragment;
import com.njx.mvvmhabit.ui.produce.SMTOperateFragment;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class SMTClearViewModel extends ToolbarViewModel {
    public ObservableField<String> SMTTypeTxt = new ObservableField<>("");
    public ObservableField<String> orderID = new ObservableField<>("");


    public SMTClearViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("SMT换料");
    }

    public BindingCommand onSearchCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            if (TextUtils.isEmpty(orderID.get())) {
                ToastUtils.showShort("请输入工单号！");
                return;
            }
        }
    });

}
