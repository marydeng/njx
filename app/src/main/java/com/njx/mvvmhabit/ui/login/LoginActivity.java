package com.njx.mvvmhabit.ui.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lcodecore.tkrefreshlayout.utils.LogUtil;
import com.njx.mvvmhabit.BR;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.app.AppViewModelFactory;
import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.databinding.ActivityLoginBinding;
import com.njx.mvvmhabit.utils.RetrofitClient;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.utils.MaterialDialogUtils;
import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * 一个MVVM模式的登陆界面
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    //ActivityLoginBinding类是databinding框架自定生成的,对应activity_login.xml
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public LoginViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(LoginViewModel.class);
    }

    @Override
    public void initViewObservable() {
        //监听ViewModel中pSwitchObservable的变化, 当ViewModel中执行【uc.pSwitchObservable.set(!uc.pSwitchObservable.get());】时会回调该方法
        viewModel.uc.pSwitchEvent.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                //pSwitchObservable是boolean类型的观察者,所以可以直接使用它的值改变密码开关的图标
                if (viewModel.uc.pSwitchEvent.getValue()) {
                    //密码可见
                    //在xml中定义id后,使用binding可以直接拿到这个view的引用,不再需要findViewById去找控件了
                    binding.ivSwichPasswrod.setImageResource(R.mipmap.show_psw);
                    binding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //密码不可见
                    binding.ivSwichPasswrod.setImageResource(R.mipmap.show_psw_press);
                    binding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        binding.configTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog.Builder builder = MaterialDialogUtils.showInputDialog(LoginActivity.this, "IP配置", "IP:");
                builder.onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        String ip = dialog.getInputEditText().getText().toString().trim();
                        if (TextUtils.isEmpty(ip)) {
                            ToastUtils.showShort("请输入服务器IP地址！");
                        } else {
                            if (ip.split("\\.").length != 4) {
                                ToastUtils.showShort("IP地址不符合规范！");
                            } else {
                                SPUtils.getInstance().put(Constant.SP_Base_URL_Key, dialog.getInputEditText().getText().toString().trim());
                                RetrofitClient.baseUrl = SPUtils.getInstance().getString(Constant.SP_Base_URL_Key);
                                RetrofitClient.reCreateClient();
                                LogUtil.i("ip 地址："+ip);
                            }
                        }
                    }
                });
                MaterialDialog dialog = builder.show();
                dialog.getInputEditText().setHint("");
                dialog.getInputEditText().setText(RetrofitClient.baseUrl);
            }
        });
    }
}
