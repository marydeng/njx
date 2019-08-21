package com.njx.mvvmhabit.ui.business.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class ProductStorageViewModel extends ToolbarViewModel {
    //货位
    public ObservableField<String> location=new ObservableField<>("");
    //箱号
    public ObservableField<String> boxId=new ObservableField<>("");
    public ProductStorageViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("成品入库管理");
    }

    public BindingCommand onCommit=new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(location.get())) {
                ToastUtils.showShort("请输入货位");
                return;
            }

            if (TextUtils.isEmpty(boxId.get())) {
                ToastUtils.showShort("请输入箱号");
                return;
            }
        }
    });
}
