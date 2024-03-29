package com.njx.mvvmhabit.ui.produce.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.data.source.http.service.DemoApiService;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.common.OrderListFragment;
import com.njx.mvvmhabit.ui.login.LoginViewModel;
import com.njx.mvvmhabit.ui.produce.GunChangeOperateFragment;
import com.njx.mvvmhabit.ui.produce.MaterChangeOperateFragment;
import com.njx.mvvmhabit.ui.produce.SMTOperateFragment;
import com.njx.mvvmhabit.utils.RetrofitClient;

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

public class SMTClearViewModel extends ToolbarViewModel {
    public ObservableField<String> SMTTypeTxt = new ObservableField<>("");
    public ObservableField<String> orderID = new ObservableField<>("");
    private DemoApiService apiService;

    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //弹出对话框
        public SingleLiveEvent<Boolean> showDialogEvent = new SingleLiveEvent<>();
    }
    public SMTClearViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("清除工单");
        apiService = RetrofitClient.getInstance().create(DemoApiService.class);
        Messenger.getDefault().register(this, Constant.TOKEN__Receive_Work_Item, String.class, new BindingConsumer<String>() {
            @Override
            public void call(String workItem) {
                orderID.set(workItem);
            }
        });
    }

    public BindingCommand onClearCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            if (TextUtils.isEmpty(orderID.get())) {
                ToastUtils.showShort("请输入工单号！");
                return;
            }

            uc.showDialogEvent.call();

        }
    });

    public void clearWorkItem(){
        apiService.clearWorkItem(orderID.get(),"下线")
                .compose(RxUtils.<BaseResponse<Object>>bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                })
                .subscribe(new Consumer<BaseResponse<Object>>() {
                               @Override
                               public void accept(BaseResponse<Object> response) throws Exception {
                                   //请求成功
                                   if (response.getCode() == Constant.Ret_SUCCESS) {
                                       ToastUtils.showShort("清除工单成功");
                                       } else {
                                           ToastUtils.showShort("清除工单失败");
                                       }
                                   }}
                        ,new Consumer<ResponseThrowable>() {
                            @Override
                            public void accept(ResponseThrowable throwable) throws Exception {
                                dismissDialog();
                                ToastUtils.showShort(throwable.message);
                            }
                        }, new

                                Action() {
                                    @Override
                                    public void run() throws Exception {
                                        dismissDialog();
                                    }
                                });
    }

    public BindingCommand onQuery = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startContainerActivity(OrderListFragment.class.getCanonicalName());
        }
    });

}
