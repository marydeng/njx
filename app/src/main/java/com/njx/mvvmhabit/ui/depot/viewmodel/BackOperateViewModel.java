package com.njx.mvvmhabit.ui.depot.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.data.source.http.service.DemoApiService;
import com.njx.mvvmhabit.entity.BackPartRecordEntity;
import com.njx.mvvmhabit.entity.BackRecordResultEntity;
import com.njx.mvvmhabit.entity.TransferPartRecordEntity;
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

public class BackOperateViewModel extends ToolbarViewModel {
    public ObservableField<String> orderTxt = new ObservableField<>();
    public ObservableField<String> deptTxt = new ObservableField<>();
    public ObservableField<String> rollTxt = new ObservableField<>();
    public ObservableField<String> numTxt = new ObservableField<>();
    public String location;

    public BackOperateViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("退库管理");
        apiService = RetrofitClient.getInstance().create(DemoApiService.class);
    }


    //封装一个界面发生改变的观察者
    public UIChangeObsevable uc = new UIChangeObsevable();

    public class UIChangeObsevable {
        public SingleLiveEvent<List<BackPartRecordEntity>> listChangeEvent = new SingleLiveEvent<>();
        public SingleLiveEvent<List<String>> locationChangeEvent = new SingleLiveEvent<>();
        public SingleLiveEvent onCommitSuccess = new SingleLiveEvent();
        public SingleLiveEvent<String> showErrorDialog = new SingleLiveEvent<>();
    }

    private DemoApiService apiService;


    public void queryLocationList() {
        apiService.queryBackLocation(deptTxt.get())
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
                            uc.locationChangeEvent.setValue(response.getResult());
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

    public void queryRecordList() {
        apiService.queryBackRecordList(orderTxt.get())
                .compose(RxUtils.<BaseResponse<UserEntity>>bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                })
                .subscribe(new Consumer<BaseResponse<BackRecordResultEntity>>() {
                    @Override
                    public void accept(BaseResponse<BackRecordResultEntity> response) throws Exception {
                        //请求成功
                        if (response.getCode() == Constant.Ret_SUCCESS) {
                            if (response.getResult() != null) {
                                uc.listChangeEvent.setValue(response.getResult().getRows());
                            } else {
                                uc.listChangeEvent.setValue(new ArrayList<BackPartRecordEntity>());
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

    public void uploadRecord() {
        apiService.backScan(orderTxt.get(), rollTxt.get(), numTxt.get(), deptTxt.get(),location)
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
                            numTxt.set("");
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
            if (TextUtils.isEmpty(location)) {
                ToastUtils.showShort("储位不能为空");
                return;
            }
            if (TextUtils.isEmpty(rollTxt.get())) {
                ToastUtils.showShort("料卷号不能为空");
                return;
            }

            if (TextUtils.isEmpty(numTxt.get())) {
                ToastUtils.showShort("数量不能为空");
                return;
            }
            uploadRecord();
        }
    });


}
