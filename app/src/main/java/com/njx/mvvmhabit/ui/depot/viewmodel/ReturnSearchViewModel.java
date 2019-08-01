package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.depot.StorageOperateFragment;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class ReturnSearchViewModel extends ToolbarViewModel {
    public ObservableField<String> orderTxt=new ObservableField<>("");


    public ReturnSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("退货管理");
    }

    public BindingCommand onSearchCommand=new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(orderTxt.get())) {
                ToastUtils.showShort("请输入退货单号！");
                return;
            }

            Bundle bundle=new Bundle();
            bundle.putString(StorageOperateFragment.Extra_Invoice,orderTxt.get());
            startContainerActivity(StorageOperateFragment.class.getCanonicalName(),bundle);

        }
    });

}
