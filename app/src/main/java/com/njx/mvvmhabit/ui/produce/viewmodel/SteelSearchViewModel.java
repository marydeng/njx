package com.njx.mvvmhabit.ui.produce.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.app.AppApplication;
import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.data.source.http.service.DemoApiService;
import com.njx.mvvmhabit.entity.MenuEntity;
import com.njx.mvvmhabit.entity.OrderEntity;
import com.njx.mvvmhabit.entity.UserEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.depot.StorageOperateFragment;
import com.njx.mvvmhabit.ui.main.bean.MenuBean;
import com.njx.mvvmhabit.ui.produce.SteelOperateFragment;
import com.njx.mvvmhabit.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class SteelSearchViewModel extends ToolbarViewModel {
    public ObservableField<String> SMTTypeTxt = new ObservableField<>("");
    public ObservableField<String> orderID = new ObservableField<>("");
    public ObservableField<String> stationID = new ObservableField<>("");
    private String partNO;
    private String lineClass;


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
            bundle.putString(SteelOperateFragment.Extra_PART_NO, partNO);
            startContainerActivity(SteelOperateFragment.class.getCanonicalName(), bundle);

        }
    });

    public BindingCommand<Boolean> onOrderFocusChange = new BindingCommand<Boolean>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean hasFocus) {
            if (hasFocus) {
                if (SMTTypeTxt.get().equals("上机台")) {
                    if (TextUtils.isEmpty(orderID.get())) {
                        ToastUtils.showShort("请输入工单号！");
                    } else {
                        queryOrder();
                    }
                }
            }
        }
    });

    //根据工单号查询信息
    public void queryOrder() {
        //网络API服务
        DemoApiService apiService = RetrofitClient.getInstance().create(DemoApiService.class);
        apiService.queryOrder(orderID.get())
                .compose(RxUtils.<BaseResponse<UserEntity>>bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                })
                .subscribe(new Consumer<BaseResponse<OrderEntity>>() {
                               @Override
                               public void accept(BaseResponse<OrderEntity> response) throws Exception {
                                   //请求成功
                                   if (response.getCode() == Constant.Ret_SUCCESS) {
                                       OrderEntity orderEntity = response.getResult();
                                       if (orderEntity != null) {
                                           partNO = orderEntity.getPartno();
                                           lineClass = orderEntity.getPresetLine();
                                           stationID.set(lineClass);
                                       } else {
                                           ToastUtils.showShort("工单信息失败");
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
