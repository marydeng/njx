package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.depot.StorageOperateFragment;
import com.njx.mvvmhabit.ui.depot.TransferOperateFragment;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class TransferSearchViewModel extends ToolbarViewModel {
    public ObservableField<String> destDepotTxt = new ObservableField<>("");
    public ObservableField<String> depotClassTxt = new ObservableField<>("");


    public TransferSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("调拨管理");
    }

    public BindingCommand onSearchCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(destDepotTxt.get())) {
                ToastUtils.showShort("请输入目标仓库号！");
                return;
            }

            if (TextUtils.isEmpty(depotClassTxt.get())) {
                ToastUtils.showShort("请输入仓别！");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(TransferOperateFragment.Extra_dest_depot, destDepotTxt.get());
            bundle.putString(TransferOperateFragment.Extra_depot_class, depotClassTxt.get());
            startContainerActivity(TransferOperateFragment.class.getCanonicalName(), bundle);

        }
    });

}
