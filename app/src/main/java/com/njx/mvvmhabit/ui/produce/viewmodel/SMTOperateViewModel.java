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

public class SMTOperateViewModel extends ToolbarViewModel {
    public ObservableField<String> orderId = new ObservableField<>();
    public ObservableField<String> smtType = new ObservableField<>();
    public ObservableField<String> gunTxt = new ObservableField<>();
    public ObservableField<String> rollTxt = new ObservableField<>();
    public ObservableField<String> stationTxt = new ObservableField<>();
    private DemoApiService apiService;


    public SMTOperateViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("上料管理");
        apiService = RetrofitClient.getInstance().create(DemoApiService.class);
    }

    public void uploadRecord() {
        apiService.uploadScanRecord(smtType.get(), "上线", orderId.get(), gunTxt.get(), rollTxt.get(), stationTxt.get(), AppApplication.getInstance().userEntity.getUserName())
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
                            rollTxt.set("");
                            stationTxt.set("");
                            uc.gunEdit.call();
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

    private String scanType;
    public interface SCANTYPE{
        String gun="gun";
        String roll="roll";
        String station="station";
    }
    public void checkStatus(String materialGun, String materialRoll, String materialStation,String type) {
        scanType=type;
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
        if(SCANTYPE.gun.equals(scanType)){
            gunTxt.set("");
            uc.gunEdit.call();
        }else if(SCANTYPE.roll.equals(scanType)){
            rollTxt.set("");
            uc.rollEdit.call();
        }else {
            stationTxt.set("");
            uc.stationEdit.call();
        }
    }

    //封装一个界面发生改变的观察者
    public UIChangeObsevable uc = new UIChangeObsevable();

    public class UIChangeObsevable {
        public SingleLiveEvent<List<SMTRecordEntity>> listChangeEvent = new SingleLiveEvent<>();
        public SingleLiveEvent gunEdit = new SingleLiveEvent();
        public SingleLiveEvent rollEdit = new SingleLiveEvent();
        public SingleLiveEvent stationEdit = new SingleLiveEvent();
        public SingleLiveEvent<String> showErrorDialog = new SingleLiveEvent<>();


    }

    public void queryRecordList() {
        apiService.queryScanRecord(smtType.get(), orderId.get())
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

    public BindingCommand onCommit = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(gunTxt.get())) {
                ToastUtils.showShort("料枪不能为空");
                return;
            }
            if (TextUtils.isEmpty(rollTxt.get())) {
                ToastUtils.showShort("料卷不能为空");
                return;
            }

            if (TextUtils.isEmpty(stationTxt.get())) {
                ToastUtils.showShort("料站不能为空");
                return;
            }

            if(stationTxt.get().endsWith("L") && gunTxt.get().endsWith("R")){
                ToastUtils.showShort("料站与料枪放反");
                return;
            }

            if(stationTxt.get().endsWith("R") && gunTxt.get().endsWith("L")){
                ToastUtils.showShort("料站与料枪放反");
                return;
            }

            uploadRecord();
        }
    });


}
