package com.njx.mvvmhabit.ui.main.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.njx.mvvmhabit.app.AppApplication;
import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.data.source.http.service.DemoApiService;
import com.njx.mvvmhabit.entity.MenuEntity;
import com.njx.mvvmhabit.entity.MenuListEntity;
import com.njx.mvvmhabit.entity.UserEntity;
import com.njx.mvvmhabit.ui.base.viewmodel.ToolbarViewModel;
import com.njx.mvvmhabit.ui.login.LoginViewModel;
import com.njx.mvvmhabit.ui.main.MainActivity;
import com.njx.mvvmhabit.ui.main.bean.MenuBean;
import com.njx.mvvmhabit.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class MainViewModel extends ToolbarViewModel {
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void initToolBar() {
        setTitleText("南极星");
    }

    //封装一个界面发生改变的观察者
    public UIChangeObsevable uc = new UIChangeObsevable();

    public class UIChangeObsevable{
        public SingleLiveEvent<List<MenuBean>> menuListEvent=new SingleLiveEvent<>();

    }

    public void getMenuList() {
        //网络API服务
        DemoApiService apiService = RetrofitClient.getInstance().create(DemoApiService.class);
        apiService.getMenuList(AppApplication.getInstance().userEntity.getUserId())
                .compose(RxUtils.<BaseResponse<UserEntity>>bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.exceptionTransformer())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog();
                    }
                })
                .subscribe(new Consumer<BaseResponse<List<MenuEntity>>>() {
                    @Override
                    public void accept(BaseResponse<List<MenuEntity>> response) throws Exception {
                        //请求成功
                        if (response.getCode() == Constant.Ret_SUCCESS) {
                            List<MenuEntity> menuListEntity = response.getResult();
                            if (menuListEntity != null) {
                                List<MenuBean> menuBeanList = new ArrayList<>();
                                for (int i = 0; i < menuListEntity.size(); i++) {
                                    MenuEntity menuEntity = menuListEntity.get(i);
                                    MenuBean menuBean = new MenuBean(menuEntity.getIcon(), menuEntity.getMenuName(), i);
                                    menuBeanList.add(menuBean);
                                }
                                if (menuBeanList.size() != 0) {
                                    uc.menuListEvent.setValue(menuBeanList);
                                }
                            } else {
                                ToastUtils.showShort("获取菜单失败");
                            }
                        }}}
    ,new Consumer<ResponseThrowable>() {
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

                }
