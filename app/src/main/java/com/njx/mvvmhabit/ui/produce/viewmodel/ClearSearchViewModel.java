package com.njx.mvvmhabit.ui.produce.viewmodel;

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

public class ClearSearchViewModel extends ToolbarViewModel {
    public ObservableField<String> SMTTypeTxt =new ObservableField<>("");
    public ObservableField<String> orderID =new ObservableField<>("");


    public ClearSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("SMT换料");
    }

    public BindingCommand onSearchCommand=new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SMTTypeTxt.get())) {
                ToastUtils.showShort("请输入SMT类型！");
                return;
            }

            if (TextUtils.isEmpty(orderID.get())) {
                ToastUtils.showShort("请输入工单号！");
                return;
            }
            Bundle bundle=new Bundle();
            bundle.putString(StorageOperateFragment.Extra_Invoice, SMTTypeTxt.get());
            bundle.putString(StorageOperateFragment.Extra_Invoice, orderID.get());
            startContainerActivity(StorageOperateFragment.class.getCanonicalName(),bundle);

        }
    });

}
