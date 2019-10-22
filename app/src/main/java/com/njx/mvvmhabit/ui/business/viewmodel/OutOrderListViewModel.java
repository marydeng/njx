package com.njx.mvvmhabit.ui.business.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.data.source.http.service.DemoApiService;
import com.njx.mvvmhabit.entity.BackDepotEntity;
import com.njx.mvvmhabit.entity.BackDepotResultEntity;
import com.njx.mvvmhabit.entity.BusiOrderResultEntity;
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

public class OutOrderListViewModel extends ToolbarViewModel {


    public OutOrderListViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("成品出库单号选择");
        apiService= RetrofitClient.getInstance().create(DemoApiService.class);
    }

    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //设置订单数据
        public SingleLiveEvent<List<BusiOrderResultEntity.RowsBean>> onListDataChange = new SingleLiveEvent<>();
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
        apiService.queryBusinessOutOrder(mStartPage,PAGE_SIZE)
                .compose(RxUtils.<BaseResponse<UserEntity>>bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                })
                .subscribe(new Consumer<BaseResponse<BusiOrderResultEntity>>() {
                               @Override
                               public void accept(BaseResponse<BusiOrderResultEntity> response) throws Exception {
                                   //请求成功
                                   if (response.getCode() == Constant.Ret_SUCCESS) {
                                       if (response.getResult() != null) {
                                           totalSize=response.getResult().getTotal();
                                           uc.onListDataChange.setValue(response.getResult().getRows());

                                       } else {
                                           uc.onListDataChange.setValue(new ArrayList<BusiOrderResultEntity.RowsBean>());
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
