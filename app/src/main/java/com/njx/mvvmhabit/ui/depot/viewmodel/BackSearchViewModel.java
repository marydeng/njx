package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.depot.BackOperateFragment;
import com.njx.mvvmhabit.ui.depot.StorageOperateFragment;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class BackSearchViewModel extends ToolbarViewModel {
    public ObservableField<String> orderTxt=new ObservableField<>("");
    public ObservableField<String> deptTxt=new ObservableField<>("");

    public BackSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("退库管理");
    }

    public BindingCommand onSearchCommand=new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(orderTxt.get())) {
                ToastUtils.showShort("请输入工单号！");
                return;
            }

            if (TextUtils.isEmpty(deptTxt.get())) {
                ToastUtils.showShort("请输入仓库号！");
                return;
            }
            Bundle bundle=new Bundle();
            bundle.putString(BackOperateFragment.Extra_Order,orderTxt.get());
            bundle.putString(BackOperateFragment.Extra_Dept,deptTxt.get());
            startContainerActivity(BackOperateFragment.class.getCanonicalName(),bundle);

        }
    });

}
