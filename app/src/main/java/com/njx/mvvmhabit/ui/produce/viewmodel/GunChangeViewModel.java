package com.njx.mvvmhabit.ui.produce.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.app.AppApplication;
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

public class GunChangeViewModel extends ToolbarViewModel {
    public ObservableField<String> orderId = new ObservableField<>();
    public ObservableField<String> smtType = new ObservableField<>();
    public ObservableField<String> newGunTxt = new ObservableField<>();
    public ObservableField<String> oldGunTxt = new ObservableField<>();
    public ObservableField<String> stationTxt = new ObservableField<>();
    private DemoApiService apiService;

    public GunChangeViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("料枪变更管理");
        apiService = RetrofitClient.getInstance().create(DemoApiService.class);
    }

    public void uploadRecord() {
//        apiService.uploadGunChangeScanRecord(smtType.get(),"上线", orderId.get(), oldGunTxt.get(),newGunTxt.get(), "", stationTxt.get(), AppApplication.getInstance().userEntity.getUserName())
        apiService.uploadGunChangeScanRecord(smtType.get(),"上线", orderId.get(), oldGunTxt.get(),newGunTxt.get(), "", "", AppApplication.getInstance().userEntity.getUserName())
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
                            newGunTxt.set("");
                            oldGunTxt.set("");
                            stationTxt.set("");
                            uc.clearEdit.call();
                            queryRecordList();
                        } else {
                            uc.showErrorDialog.setValue(response.getMsg());
                        }
                    }
                }, new Consumer<ResponseThrowable>() {
                    @Override
                    public void accept(ResponseThrowable throwable) throws Exception {
                        dismissDialog();
                        uc.showErrorDialog.setValue(throwable.message);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        dismissDialog();
                    }
                });
    }

    public void checkStatus(String materialGun, String materialRoll, String materialStation) {
        apiService.checkSMTStatus(materialGun, materialRoll, materialStation)
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
                            ToastUtils.showShort("OK");
                        } else {
                            uc.showErrorDialog.setValue(response.getMsg());
                            dataClear();
                        }
                    }
                }, new Consumer<ResponseThrowable>() {
                    @Override
                    public void accept(ResponseThrowable throwable) throws Exception {
                        dismissDialog();
                        uc.showErrorDialog.setValue(throwable.getMessage());
                        dataClear();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        dismissDialog();
                    }
                });
    }

    private void dataClear(){
        newGunTxt.set("");
        uc.clearEdit.call();
    }

    //封装一个界面发生改变的观察者
    public UIChangeObsevable uc = new UIChangeObsevable();

    public class UIChangeObsevable {
        public SingleLiveEvent<List<SMTRecordEntity>> listChangeEvent = new SingleLiveEvent<>();
        public SingleLiveEvent clearEdit=new SingleLiveEvent();
        public SingleLiveEvent<String> showErrorDialog = new SingleLiveEvent<>();
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
            if(TextUtils.isEmpty(newGunTxt.get())){
                ToastUtils.showShort("新料枪不能为空");
                return;
            }
            if(TextUtils.isEmpty(oldGunTxt.get())){
                ToastUtils.showShort("旧料枪不能为空");
                return;
            }

//            if(TextUtils.isEmpty(stationTxt.get())){
//                ToastUtils.showShort("料站不能为空");
//                return;
//            }

            uploadRecord();
        }
    });


}
