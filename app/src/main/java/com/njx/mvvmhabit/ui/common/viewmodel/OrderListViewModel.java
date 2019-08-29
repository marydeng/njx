package com.njx.mvvmhabit.ui.common.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.data.source.http.service.DemoApiService;
import com.njx.mvvmhabit.entity.OrderListEntity;
import com.njx.mvvmhabit.entity.UserEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.utils.RetrofitClient;

import java.util.ArrayList;
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

public class OrderListViewModel extends ToolbarViewModel {
    public String lineClass;
    private DemoApiService apiService;
    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //显示线体列表
        public SingleLiveEvent<List<String>> showLineList = new SingleLiveEvent<>();
        //显示工单列表
        public SingleLiveEvent<List<OrderListEntity.WorkEntity>> showOrderList = new SingleLiveEvent<>();
    }

    public OrderListViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("查询工单");
        apiService = RetrofitClient.getInstance().create(DemoApiService.class);
    }

    //查询所有线体
    public void queryLine() {
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

    public void queryOrderList() {
        apiService.queryWorkItem(lineClass,mStartPage,PAGE_SIZE)
                .compose(RxUtils.<BaseResponse<UserEntity>>bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                })
                .subscribe(new Consumer<BaseResponse<OrderListEntity>>() {
                               @Override
                               public void accept(BaseResponse<OrderListEntity> response) throws Exception {
                                   //请求成功
                                   if (response.getCode() == Constant.Ret_SUCCESS) {
                                       if (response.getResult() != null) {
                                           totalSize=response.getResult().getTotal();
                                           uc.showOrderList.setValue(response.getResult().getRows());

                                       } else {
                                           uc.showOrderList.setValue(new ArrayList<OrderListEntity.WorkEntity>());
                                       }
                                   } else {
                                       ToastUtils.showShort(response.getMsg());
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

    public static final int PAGE_SIZE=10;
    public boolean isRefresh = true;
    public boolean isLoad=false;
    public int mStartPage = 1;
    public int totalSize;
    public BindingCommand onLoadMoreCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isRefresh = false;
            isLoad=true;
            queryOrderList();

        }
    });

    public BindingCommand onRefreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isRefresh = true;
            mStartPage = 1;
            queryOrderList();
        }
    });


}
