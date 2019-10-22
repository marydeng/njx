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
import com.njx.mvvmhabit.ui.depot.TransferOperateFragment;
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

public class TransferSearchViewModel extends ToolbarViewModel {
    public ObservableField<String> destDepotTxt = new ObservableField<>("");
    public ObservableField<String> depotClassTxt = new ObservableField<>("");
    public String orderId;
    public String dept;
    public String serchCode;


    public TransferSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("调拨管理");
        apiService = RetrofitClient.getInstance().create(DemoApiService.class);

    }

    public BindingCommand onSearchCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(orderId)) {
                ToastUtils.showShort("请输入转仓单号！");
                return;
            }

            if (TextUtils.isEmpty(dept)) {
                ToastUtils.showShort("请输入目标仓库！");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(TransferOperateFragment.Extra_Transfer_Order, orderId);
            bundle.putString(TransferOperateFragment.Extra_Transfer_dept, dept);
            startContainerActivity(TransferOperateFragment.class.getCanonicalName(), bundle);

        }
    });
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //设置订单数据
        public SingleLiveEvent<List<String>> setOderData = new SingleLiveEvent<>();
        //设置目标仓库数据
        public SingleLiveEvent<List<String>> setDeptData = new SingleLiveEvent<>();
    }
    private DemoApiService apiService;

    //查询转仓订单号
    public void queryOrder(String code) {
        //网络API服务
        apiService.queryTransferOrder(code)
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
                                               ToastUtils.showShort("查询转仓单号失败");
                                           }
                                       } else {
                                           ToastUtils.showShort("查询转仓单号失败");
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

    //查询目标仓库
    public void queryDeptList() {
        //网络API服务
        apiService.queryTransferDepat(orderId)
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
                                               uc.setDeptData.setValue(dataList);
                                           } else {
                                               ToastUtils.showShort("查询目标仓库失败");
                                           }
                                       } else {
                                           ToastUtils.showShort("查询目标仓库失败");
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

    public BindingCommand onQuery = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            queryOrder(serchCode);
        }
    });

}
