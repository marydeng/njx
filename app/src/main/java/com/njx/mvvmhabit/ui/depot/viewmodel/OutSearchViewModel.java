package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.data.source.http.service.DemoApiService;
import com.njx.mvvmhabit.entity.OrderEntity;
import com.njx.mvvmhabit.entity.OutOrderEntity;
import com.njx.mvvmhabit.entity.UserEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.depot.OutOperateFragment;
import com.njx.mvvmhabit.ui.depot.StorageOperateFragment;
import com.njx.mvvmhabit.ui.produce.SteelOperateFragment;
import com.njx.mvvmhabit.ui.produce.viewmodel.SteelSearchViewModel;
import com.njx.mvvmhabit.utils.RetrofitClient;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class OutSearchViewModel extends ToolbarViewModel {
    public ObservableField<String> orderTxt=new ObservableField<>("");
    public ObservableField<String> deptTxt=new ObservableField<>("");

    public OutSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("出库管理");
    }

    public BindingCommand onSearchCommand=new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(orderTxt.get())) {
                ToastUtils.showShort("请输入出库单号！");
                return;
            }

            if (TextUtils.isEmpty(deptTxt.get())) {
                ToastUtils.showShort("请输入仓库号！");
                return;
            }
            Bundle bundle=new Bundle();
            bundle.putString(OutOperateFragment.Extra_Order,orderTxt.get());
            bundle.putString(OutOperateFragment.Extra_Dept,deptTxt.get());
            startContainerActivity(OutOperateFragment.class.getCanonicalName(),bundle);

        }
    });

    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //设置订单数据
        public SingleLiveEvent<List<OutOrderEntity>> setOderData = new SingleLiveEvent<>();
    }

    //查询出库订单号
    public void queryAllOutOrder(){
        //网络API服务
        DemoApiService apiService = RetrofitClient.getInstance().create(DemoApiService.class);
        apiService.queryOutOrder()
                .compose(RxUtils.<BaseResponse<UserEntity>>bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                })
                .subscribe(new Consumer<BaseResponse<List<OutOrderEntity>>>() {
                               @Override
                               public void accept(BaseResponse<List<OutOrderEntity>> response) throws Exception {
                                   //请求成功
                                   if (response.getCode() == Constant.Ret_SUCCESS) {
                                       List<OutOrderEntity> dataList = response.getResult();
                                       if (dataList != null) {
                                           if (dataList!=null && dataList.size()>0) {
                                               uc.setOderData.setValue(dataList);
                                           } else {
                                               ToastUtils.showShort("查询出库订单失败");
                                           }
                                       } else {
                                           ToastUtils.showShort("查询出库订单失败");
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
