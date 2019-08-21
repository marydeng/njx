package com.njx.mvvmhabit.ui.produce.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.login.LoginViewModel;
import com.njx.mvvmhabit.ui.produce.FQCScanFragment;

import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class FQCConfigViewModel extends ToolbarViewModel {
    //栈板号
    public ObservableField<String> zhanbanId=new ObservableField<>("");
    //数量
    public ObservableField<String> num=new ObservableField<>("");
    //检验结果
    public String testType="NG";

    public FQCConfigViewModel(@NonNull Application application) {
        super(application);
    }


    public UIChangeObservable uc = new UIChangeObservable();

    public class UIChangeObservable {
        //重置单选框
        public SingleLiveEvent<Boolean> resetRadioBtn = new SingleLiveEvent<>();
    }


    public void initToolBar() {
        setTitleText("FQC配置");
    }

    public BindingCommand<String> onTestTypeCheckedChange=new BindingCommand<String>(new BindingConsumer<String>() {
        @Override
        public void call(String type) {
            testType=type;
        }
    });

    public BindingCommand onNextCommand=new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (TextUtils.isEmpty(zhanbanId.get())) {
                ToastUtils.showShort("栈板号不能为空");
                return;
            }
            if (TextUtils.isEmpty(num.get())) {
                ToastUtils.showShort("数量不能为空");
                return;
            }


            //携带参数，跳转页面
            Bundle bundle = new Bundle();
            bundle.putString(FQCScanFragment.Extra_zhanban_id, zhanbanId.get());
            bundle.putString(FQCScanFragment.Extra_num, num.get());
            bundle.putString(FQCScanFragment.Extra_test_type, testType);
            startContainerActivity(FQCScanFragment.class.getCanonicalName(), bundle);
            //清空配置页面
            zhanbanId.set("");
            num.set("");
            testType="NG";
            uc.resetRadioBtn.call();
        }
    });
}
