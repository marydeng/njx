package com.njx.mvvmhabit.ui.business.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.data.source.http.service.DemoApiService;
import com.njx.mvvmhabit.entity.UserEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.business.OutOrderListFragment;
import com.njx.mvvmhabit.ui.business.ProductOutFragment;
import com.njx.mvvmhabit.ui.depot.BackDepotListFragment;
import com.njx.mvvmhabit.ui.depot.ReturnOperateFragment;
import com.njx.mvvmhabit.utils.RetrofitClient;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.bus.Messenger;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class OutSearchViewModel extends ToolbarViewModel {
    public ObservableField<String> orderTxt = new ObservableField<>("");


    public OutSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("成品出库管理");
        Messenger.getDefault().register(this, Constant.TOKEN__Receive_Busi_Order, String.class, new BindingConsumer<String>() {
            @Override
            public void call(String orderId) {
                orderTxt.set(orderId);
            }
        });
    }

    public BindingCommand onSearchCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(orderTxt.get())) {
                ToastUtils.showShort("请输入成品出库单号！");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(ProductOutFragment.Extra_Order, orderTxt.get());
            startContainerActivity(ProductOutFragment.class.getCanonicalName(), bundle);
        }
    });

    public BindingCommand onQuery = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startContainerActivity(OutOrderListFragment.class.getCanonicalName());
        }
    });
}
