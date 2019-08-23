package com.njx.mvvmhabit.ui.produce.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.data.source.http.service.DemoApiService;
import com.njx.mvvmhabit.entity.SMTRecordEntity;
import com.njx.mvvmhabit.entity.UserEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
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

public class MaterChangeViewModel extends ToolbarViewModel {
    public ObservableField<String> orderId = new ObservableField<>();
    public ObservableField<String> smtType = new ObservableField<>();
    public ObservableField<String> gunTxt = new ObservableField<>();
    public ObservableField<String> newRollTxt = new ObservableField<>();
    public ObservableField<String> oldRollTxt = new ObservableField<>();
    public ObservableField<String> stationTxt = new ObservableField<>();
    private DemoApiService apiService;

    public MaterChangeViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("换料管理");
        apiService = RetrofitClient.getInstance().create(DemoApiService.class);
    }

    public void uploadRecord() {
        String rolllist=newRollTxt.get()+";"+oldRollTxt.get();
        apiService.uploadScanRecord(smtType.get(), orderId.get(), gunTxt.get(), rolllist, stationTxt.get())
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
                            gunTxt.set("");
                            newRollTxt.set("");
                            oldRollTxt.set("");
                            stationTxt.set("");
                            uc.clearEdit.call();
                            queryRecordList();
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

    //封装一个界面发生改变的观察者
    public UIChangeObsevable uc = new UIChangeObsevable();

    public class UIChangeObsevable {
        public SingleLiveEvent<List<SMTRecordEntity>> listChangeEvent = new SingleLiveEvent<>();
        public SingleLiveEvent clearEdit=new SingleLiveEvent();

    }

    public void queryRecordList() {
        apiService.queryScanRecord(smtType.get(),orderId.get())
                .compose(RxUtils.<BaseResponse<UserEntity>>bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                })
                .subscribe(new Consumer<BaseResponse<List<SMTRecordEntity>>>() {
                    @Override
                    public void accept(BaseResponse<List<SMTRecordEntity>> response) throws Exception {
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

    public BindingCommand onCommit=new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if(TextUtils.isEmpty(gunTxt.get())){
                ToastUtils.showShort("料枪不能为空");
                return;
            }
            if(TextUtils.isEmpty(newRollTxt.get())){
                ToastUtils.showShort("新料卷不能为空");
                return;
            }
            if(TextUtils.isEmpty(oldRollTxt.get())){
                ToastUtils.showShort("旧料卷不能为空");
                return;
            }

            if(TextUtils.isEmpty(stationTxt.get())){
                ToastUtils.showShort("料站不能为空");
                return;
            }

            uploadRecord();
        }
    });


}
