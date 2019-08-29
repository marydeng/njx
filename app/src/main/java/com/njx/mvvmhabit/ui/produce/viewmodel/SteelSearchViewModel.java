package com.njx.mvvmhabit.ui.produce.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.data.source.http.service.DemoApiService;
import com.njx.mvvmhabit.entity.OrderEntity;
import com.njx.mvvmhabit.entity.UserEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.common.OrderListFragment;
import com.njx.mvvmhabit.ui.produce.SteelOperateFragment;
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

public class SteelSearchViewModel extends ToolbarViewModel {
    public ObservableField<String> type = new ObservableField<>("");
    public ObservableField<String> orderID = new ObservableField<>("");
    public ObservableField<String> stationID = new ObservableField<>("");

    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //显示线体列表
        public SingleLiveEvent<List<String>> showLineList = new SingleLiveEvent<>();
    }


    public SteelSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("钢板/刮刀");
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
            if (type.get().equals("上机台")) {
                if (TextUtils.isEmpty(orderID.get())) {
                    ToastUtils.showShort("请输入工单号！");
                    return;
                }
                queryOrder();
            } else {
                if (TextUtils.isEmpty(stationID.get())) {
                    ToastUtils.showShort("请输入工作站！");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString(SteelOperateFragment.Extra_Line_Name, stationID.get());
                bundle.putString(SteelOperateFragment.Extra_Steel_Type, type.get());
                startContainerActivity(SteelOperateFragment.class.getCanonicalName(), bundle);
            }


        }
    });


    public BindingCommand<Boolean> onOrderFocusChange = new BindingCommand<Boolean>(new BindingConsumer<Boolean>() {
        @Override
        public void call(Boolean hasFocus) {
            if (hasFocus) {
                if (type.get().equals("上机台")) {
                    if (TextUtils.isEmpty(orderID.get())) {
                        ToastUtils.showShort("请输入工单号！");
                    } else {
//                        queryOrder();
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
                                           if (!TextUtils.isEmpty(orderEntity.getPartno()) && !TextUtils.isEmpty(orderEntity.getPresetLine())) {
                                               Bundle bundle = new Bundle();
                                               bundle.putString(SteelOperateFragment.Extra_Line_Name, orderEntity.getPresetLine());
                                               bundle.putString(SteelOperateFragment.Extra_Steel_Type, type.get());
                                               bundle.putString(SteelOperateFragment.Extra_PART_NO, orderEntity.getPartno());
                                               bundle.putString(SteelOperateFragment.Extra_Work_Id,orderID.get());
                                               startContainerActivity(SteelOperateFragment.class.getCanonicalName(), bundle);
                                           } else {
                                               ToastUtils.showShort("该工单不存在");
                                           }
                                       } else {
                                           ToastUtils.showShort("查询工单信息失败");
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

    //查询所有线体
    public void queryLine() {
        DemoApiService apiService = RetrofitClient.getInstance().create(DemoApiService.class);
        apiService.queryAllLine()
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
                                       List<String> lineList = response.getResult();
                                       if (lineList != null && lineList.size() > 0) {
                                           uc.showLineList.setValue(lineList);
                                       } else {
                                           ToastUtils.showShort("查询线体失败");
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
            startContainerActivity(OrderListFragment.class.getCanonicalName());
        }
    });

}
