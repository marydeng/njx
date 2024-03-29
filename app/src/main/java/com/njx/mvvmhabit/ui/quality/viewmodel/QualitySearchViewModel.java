package com.njx.mvvmhabit.ui.quality.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.common.OrderListFragment;
import com.njx.mvvmhabit.ui.produce.GunChangeOperateFragment;
import com.njx.mvvmhabit.ui.produce.MaterChangeOperateFragment;
import com.njx.mvvmhabit.ui.produce.SMTOperateFragment;
import com.njx.mvvmhabit.ui.quality.QualityOperateFragment;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.bus.Messenger;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class QualitySearchViewModel extends ToolbarViewModel {
    public ObservableField<String> SMTTypeTxt = new ObservableField<>("");
    public ObservableField<String> orderID = new ObservableField<>("");


    public QualitySearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("SMT对料");
        Messenger.getDefault().register(this, Constant.TOKEN__Receive_Work_Item, String.class, new BindingConsumer<String>() {
            @Override
            public void call(String workItem) {
                orderID.set(workItem);
            }
        });
    }

    public BindingCommand onSearchCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            if (TextUtils.isEmpty(orderID.get())) {
                ToastUtils.showShort("请输入工单号！");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(QualityOperateFragment.Extra_smt_type, "对料");
            bundle.putString(QualityOperateFragment.Extra_order_id, orderID.get());
            startContainerActivity(QualityOperateFragment.class.getCanonicalName(), bundle);

        }
    });
    public BindingCommand onQuery = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startContainerActivity(OrderListFragment.class.getCanonicalName());
        }
    });
}
