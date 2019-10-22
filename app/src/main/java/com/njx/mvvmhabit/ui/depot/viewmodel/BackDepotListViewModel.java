package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.data.source.http.service.DemoApiService;
import com.njx.mvvmhabit.entity.BackDepotEntity;
import com.njx.mvvmhabit.entity.BackDepotResultEntity;
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

public class BackDepotListViewModel extends ToolbarViewModel {


    public BackDepotListViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("退库仓库选择");
        apiService= RetrofitClient.getInstance().create(DemoApiService.class);
    }

    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //设置目标仓库数据
        public SingleLiveEvent<List<BackDepotEntity>> setDeptData = new SingleLiveEvent<>();
    }
    private DemoApiService apiService;

    public static final int PAGE_SIZE=10;
    public boolean isRefresh = true;
    public boolean isLoad=false;
    public int mStartPage = 1;
    public int totalSize;

    //查询目标仓库
    public void queryDeptList() {
        //网络API服务
        apiService.queryBackDepot(mStartPage,PAGE_SIZE)
                .compose(RxUtils.<BaseResponse<UserEntity>>bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                })
                .subscribe(new Consumer<BaseResponse<BackDepotResultEntity>>() {
                               @Override
                               public void accept(BaseResponse<BackDepotResultEntity> response) throws Exception {
                                   //请求成功
                                   if (response.getCode() == Constant.Ret_SUCCESS) {
                                       if (response.getResult() != null) {
                                           totalSize=response.getResult().getTotal();
                                           uc.setDeptData.setValue(response.getResult().getRows());

                                       } else {
                                           uc.setDeptData.setValue(new ArrayList<BackDepotEntity>());
                                       }
                                   }else {
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

    public BindingCommand onLoadMoreCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isRefresh = false;
            isLoad=true;
            queryDeptList();

        }
    });

    public BindingCommand onRefreshCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isRefresh = true;
            mStartPage = 1;
            queryDeptList();
        }
    });

}
