package com.njx.mvvmhabit.ui.produce.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.app.AppApplication;
import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.data.source.http.service.DemoApiService;
import com.njx.mvvmhabit.entity.OrderEntity;
import com.njx.mvvmhabit.entity.UserEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.produce.SteelOperateFragment;
import com.njx.mvvmhabit.utils.RetrofitClient;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class FQCScanViewModel extends ToolbarViewModel {
    //生产条码
    public ObservableField<String> produceCode=new ObservableField<>("");
    //数量统计
    public ObservableField<String> numStatic=new ObservableField<>("");

    public String zhanbanId;
    public String num;
    public String testType="NG";

    public FQCScanViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("FQC扫码");
    }



    public BindingCommand onCommitCommand=new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(produceCode.get())) {
                ToastUtils.showShort("生产条码不能为空");
                return;
            }
            commitProduceCode();
        }
    });

    public void commitProduceCode(){
        //网络API服务
        DemoApiService apiService = RetrofitClient.getInstance().create(DemoApiService.class);
        apiService.commitProduceCode(testType,produceCode.get(), AppApplication.getInstance().userEntity.getUserName())
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
                                       ToastUtils.showShort("上传成功");
                                       String str = response.getResult();
                                       if (!TextUtils.isEmpty(str)) {
                                            numStatic.set(str+"/"+num);
                                       }
                                   }else{
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
    public BindingCommand<String> onTestTypeCheckedChange=new BindingCommand<String>(new BindingConsumer<String>() {
        @Override
        public void call(String type) {
            testType=type;
        }
    });

}
