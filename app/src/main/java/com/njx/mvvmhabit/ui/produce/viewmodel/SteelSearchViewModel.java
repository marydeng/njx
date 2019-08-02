package com.njx.mvvmhabit.ui.produce.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.depot.StorageOperateFragment;
import com.njx.mvvmhabit.ui.produce.SteelOperateFragment;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class SteelSearchViewModel extends ToolbarViewModel {
    public ObservableField<String> SMTTypeTxt = new ObservableField<>("");
    public ObservableField<String> orderID = new ObservableField<>("");
    public ObservableField<String> stationID = new ObservableField<>("");


    public SteelSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("钢板/刮刀");
    }

    public BindingCommand onSearchCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(SMTTypeTxt.get())) {
                ToastUtils.showShort("请输入SMT类型！");
                return;
            }

            if (SMTTypeTxt.get().equals("上机台")) {
                if (TextUtils.isEmpty(orderID.get())) {
                    ToastUtils.showShort("请输入工单号！");
                    return;
                }
            }
            if (TextUtils.isEmpty(stationID.get())) {
                ToastUtils.showShort("请输入工作站！");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(SteelOperateFragment.Extra_Station_Id, stationID.get());
            bundle.putString(SteelOperateFragment.Extra_Steel_Type, SMTTypeTxt.get());
            startContainerActivity(SteelOperateFragment.class.getCanonicalName(), bundle);

        }
    });

}
