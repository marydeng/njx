package com.njx.mvvmhabit.ui.depot.viewmodel;

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
import com.njx.mvvmhabit.ui.depot.BackDepotListFragment;
import com.njx.mvvmhabit.ui.depot.BackOperateFragment;
import com.njx.mvvmhabit.ui.depot.StorageOperateFragment;
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

public class BackSearchViewModel extends ToolbarViewModel {
    public ObservableField<String> orderTxt=new ObservableField<>("");
    public ObservableField<String> deptTxt=new ObservableField<>("");

    public BackSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("退库管理");
        apiService = RetrofitClient.getInstance().create(DemoApiService.class);
        Messenger.getDefault().register(this, Constant.TOKEN__Receive_Back_Depot, String.class, new BindingConsumer<String>() {
            @Override
            public void call(String depotName) {
                deptTxt.set(depotName);
            }
        });
    }

    public BindingCommand onSearchCommand=new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(orderTxt.get())) {
                ToastUtils.showShort("请输入工单号！");
                return;
            }

            if (TextUtils.isEmpty(deptTxt.get())) {
                ToastUtils.showShort("请输入仓库号！");
                return;
            }
            Bundle bundle=new Bundle();
            bundle.putString(BackOperateFragment.Extra_Order,orderTxt.get());
            bundle.putString(BackOperateFragment.Extra_Dept,deptTxt.get());
            startContainerActivity(BackOperateFragment.class.getCanonicalName(),bundle);

        }
    });
    public BindingCommand onQuery = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startContainerActivity(BackDepotListFragment.class.getCanonicalName());
        }
    });

    public UIChangeObservable uc = new UIChangeObservable();
    public class UIChangeObservable {
        //设置订单数据
        public SingleLiveEvent<List<String>> setOderData = new SingleLiveEvent<>();
    }
    private DemoApiService apiService;
    //查询订单号
    public void queryOrder() {
        //网络API服务
        apiService.queryBackOrderList()
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
                                       List<String> dataList = response.getResult();
                                       if (dataList != null) {
                                           if (dataList != null && dataList.size() > 0) {
                                               uc.setOderData.setValue(dataList);
                                           } else {
                                               ToastUtils.showShort("查询退库单号失败");
                                           }
                                       } else {
                                           ToastUtils.showShort("查询退库单号失败");
                                       }
                                   }
                               }
                           }
                        , new Consumer<ResponseThrowable>() {
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


}
