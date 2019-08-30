package com.njx.mvvmhabit.ui.produce.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.data.source.http.service.DemoApiService;
import com.njx.mvvmhabit.entity.UserEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.common.OrderListFragment;
import com.njx.mvvmhabit.ui.depot.StorageOperateFragment;
import com.njx.mvvmhabit.ui.produce.GunChangeOperateFragment;
import com.njx.mvvmhabit.ui.produce.MaterChangeOperateFragment;
import com.njx.mvvmhabit.ui.produce.SMTOperateFragment;
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

public class SMTSearchViewModel extends ToolbarViewModel {

    public ObservableField<String> SMTTypeTxt = new ObservableField<>("上料");
    public ObservableField<String> orderID = new ObservableField<>("");
    private DemoApiService apiService;
    //封装一个界面发生改变的观察者
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //密码开关观察者
        public SingleLiveEvent<Boolean> showConfirmDialog = new SingleLiveEvent<>();
    }

    public SMTSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("SMT上料");
        apiService = RetrofitClient.getInstance().create(DemoApiService.class);

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
            if (TextUtils.isEmpty(SMTTypeTxt.get())) {
                ToastUtils.showShort("请输入SMT类型！");
                return;
            }

            if (TextUtils.isEmpty(orderID.get())) {
                ToastUtils.showShort("请输入工单号！");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(SMTOperateFragment.Extra_smt_type, SMTTypeTxt.get());
            bundle.putString(SMTOperateFragment.Extra_order_id, orderID.get());
            if ("上料".equals(SMTTypeTxt.get())) {
                startContainerActivity(SMTOperateFragment.class.getCanonicalName(), bundle);
            } else if ("料枪变更".equals(SMTTypeTxt.get())) {
                startContainerActivity(GunChangeOperateFragment.class.getCanonicalName(), bundle);
            } else if ("接料".equals(SMTTypeTxt.get())) {
                startContainerActivity(MaterChangeOperateFragment.class.getCanonicalName(), bundle);
            }
        }
    });

    public BindingCommand onConfirmCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(orderID.get())) {
                ToastUtils.showShort("请输入工单号！");
                return;
            }

            confirmEnd();
        }
    });

    public void confirmEnd() {
        apiService.confirmEnd("上线", orderID.get())
                .compose(RxUtils.<BaseResponse<UserEntity>>bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                })
                .subscribe(new Consumer<BaseResponse<List<String>>>() {
                    @Override
                    public void accept(BaseResponse<List<String>> response) throws Exception {
                        //请求成功
                        if (response.getCode() == Constant.Ret_SUCCESS) {
                            if (response.getResult() == null || response.getResult().size() == 0) {
                                uc.showConfirmDialog.setValue(true);
                            } else {
                                uc.showConfirmDialog.setValue(false);
                            }
                        } else {
                            ToastUtils.showShort(response.getMsg());
                        }
                    }
                }, new Consumer<ResponseThrowable>() {
                    @Override
                    public void accept(ResponseThrowable throwable) throws Exception {
                        dismissDialog();
                        ToastUtils.showShort(throwable.message);
                    }
                }, new Action() {
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
