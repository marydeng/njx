package com.njx.mvvmhabit.ui.produce.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.common.OrderListFragment;
import com.njx.mvvmhabit.ui.produce.FQCScanFragment;
import com.njx.mvvmhabit.ui.produce.SMTInputScanFragment;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.bus.Messenger;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class SMTInputConfigViewModel extends ToolbarViewModel {
    public ObservableField<String> orderID = new ObservableField<>("");
    public ObservableField<String> lastOrderID = new ObservableField<>("");
    private boolean isLastOrder = false;
    public boolean isConnectOrder=false;


    public SMTInputConfigViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("SMT input 配置");
        Messenger.getDefault().register(this, Constant.TOKEN__Receive_Work_Item, String.class, new BindingConsumer<String>() {
            @Override
            public void call(String workItem) {
                if (isLastOrder) {
                    lastOrderID.set(workItem);
                } else {
                    orderID.set(workItem);
                }
            }
        });
    }

    public BindingCommand onOKClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(orderID.get())) {
                ToastUtils.showShort("请输入工单号！");
                return;
            }
            if (isConnectOrder && TextUtils.isEmpty(lastOrderID.get())) {
                ToastUtils.showShort("请输入承接工单号！");
                return;
            }

            //携带参数，跳转页面
            Bundle bundle = new Bundle();
            bundle.putString(SMTInputScanFragment.Extra_order_id, orderID.get());
            bundle.putString(SMTInputScanFragment.Extra_last_order_id, lastOrderID.get());
            startContainerActivity(SMTInputScanFragment.class.getCanonicalName(), bundle);
            //清空配置页面
            orderID.set("");
            lastOrderID.set("");
        }
    });

    public BindingCommand onQuery = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isLastOrder = false;
            startContainerActivity(OrderListFragment.class.getCanonicalName());
        }
    });

    public BindingCommand onLastOrderQuery = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isLastOrder = true;
            startContainerActivity(OrderListFragment.class.getCanonicalName());
        }
    });

}
