package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.app.AppApplication;
import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.data.source.http.service.DemoApiService;
import com.njx.mvvmhabit.entity.OutPartRecordEntity;
import com.njx.mvvmhabit.entity.SMTRecordEntity;
import com.njx.mvvmhabit.entity.UserEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTOperateViewModel;
import com.njx.mvvmhabit.utils.RetrofitClient;

import java.util.List;
import java.util.Observable;

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

public class OutOperateViewModel extends ToolbarViewModel {
    public ObservableField<String> orderTxt = new ObservableField<>();
    public ObservableField<String> deptTxt = new ObservableField<>();
    public ObservableField<String> rollTxt = new ObservableField<>();
    public ObservableField<String> providerRollTxt = new ObservableField<>();

    public OutOperateViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("出库管理");
        apiService = RetrofitClient.getInstance().create(DemoApiService.class);
    }


    //封装一个界面发生改变的观察者
    public UIChangeObsevable uc = new UIChangeObsevable();

    public class UIChangeObsevable {
        public SingleLiveEvent<List<OutPartRecordEntity>> listChangeEvent = new SingleLiveEvent<>();
        public SingleLiveEvent onCommitSuccess = new SingleLiveEvent();
        public SingleLiveEvent rollEdit = new SingleLiveEvent();
        public SingleLiveEvent stationEdit = new SingleLiveEvent();
        public SingleLiveEvent<String> showErrorDialog = new SingleLiveEvent<>();


    }

    private DemoApiService apiService;

    public void queryRecordList() {
        apiService.queryOutList(orderTxt.get(), deptTxt.get())
                .compose(RxUtils.<BaseResponse<UserEntity>>bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                })
                .subscribe(new Consumer<BaseResponse<List<OutPartRecordEntity>>>() {
                    @Override
                    public void accept(BaseResponse<List<OutPartRecordEntity>> response) throws Exception {
                        //请求成功
                        if (response.getCode() == Constant.Ret_SUCCESS) {
                            uc.listChangeEvent.setValue(response.getResult());
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

    public void uploadRecord() {
        apiService.outScan(orderTxt.get(), rollTxt.get(), AppApplication.getInstance().userEntity.getUserName(), providerRollTxt.get())
                .compose(RxUtils.<BaseResponse<UserEntity>>bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                })
                .subscribe(new Consumer<BaseResponse<String>>() {
                    @Override
                    public void accept(BaseResponse<String> response) throws Exception {
                        //请求成功
                        if (response.getCode() == Constant.Ret_SUCCESS) {
                            ToastUtils.showShort("提交记录成功");
                            rollTxt.set("");
                            providerRollTxt.set("");
                            uc.onCommitSuccess.call();
                            queryRecordList();
                        } else {
                            uc.showErrorDialog.setValue(response.getMsg());
                        }
                    }
                }, new Consumer<ResponseThrowable>() {
                    @Override
                    public void accept(ResponseThrowable throwable) throws Exception {
                        dismissDialog();
                        uc.showErrorDialog.setValue(throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        dismissDialog();
                    }
                });
    }

    public BindingCommand onCommit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(rollTxt.get())) {
                ToastUtils.showShort("料卷不能为空");
                return;
            }
            if (TextUtils.isEmpty(providerRollTxt.get())) {
                ToastUtils.showShort("供应商料卷不能为空");
                return;
            }
            uploadRecord();
        }
    });

}
