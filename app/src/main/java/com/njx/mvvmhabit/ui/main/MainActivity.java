package com.njx.mvvmhabit.ui.main;

import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import com.njx.mvvmhabit.BR;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.databinding.ActivityMainBinding;
import com.njx.mvvmhabit.ui.receiver.ScannerResultReceiver;
import com.njx.mvvmhabit.utils.ScannerInterface;
import com.njx.mvvmhabit.utils.UBXScan;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainActivity extends BaseActivity<ActivityMainBinding, BaseViewModel> {
    public static final String UBX_i6200S="i6200S";
    private List<BaseFragment> mFragments;
    private FragmentManager fragmentManager;
    private ScannerResultReceiver scannerResultReceiver;
    private UBXScan ubxScan;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initData() {
        //初始化Fragment
        initFragment();
        //初始化底部Button
        initBottomTab();

        ScannerInterface.getInstance().open();
        ScannerInterface.getInstance().setOutputMode(2);
        ScannerInterface.getInstance().enableFailurePlayBeep(true);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.RES_ACTION);

        scannerResultReceiver = new ScannerResultReceiver();
        registerReceiver(scannerResultReceiver, intentFilter);

        if (Build.MODEL.startsWith(UBX_i6200S)) {
            ubxScan = new UBXScan();
            ubxScan.registerReceiver(this);
        }
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        ft.add(R.id.frameLayout, mainFragment);
        mFragments.add(mainFragment);

        MyFragment myFragment = new MyFragment();
        ft.add(R.id.frameLayout, myFragment);
        mFragments.add(myFragment);
        ft.commit();
        selectFragment(0);
    }

    private void initBottomTab() {
        NavigationController navigationController = binding.pagerBottomTab.material()
                .addItem(R.mipmap.home, R.mipmap.home, "主页", ContextCompat.getColor(this, R.color.colorPrimary))
                .addItem(R.mipmap.my, R.mipmap.my, "个人", ContextCompat.getColor(this, R.color.colorPrimary))
                .setDefaultColor(ContextCompat.getColor(this, R.color.textColorVice))
                .build();
        navigationController.setSelect(0);
        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                selectFragment(index);
            }

            @Override
            public void onRepeat(int index) {
            }
        });
    }

    /**
     * 选中某个 Fragment
     *
     * @param index
     */
    private void selectFragment(int index) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            if (i == index) {
                ft.show(mFragments.get(i));
            } else {
                ft.hide(mFragments.get(i));
            }
        }
        ft.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ScannerInterface.getInstance().scan_stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScannerInterface.destroy();
        if (scannerResultReceiver != null) {
            unregisterReceiver(scannerResultReceiver);
        }

        if (Build.MODEL.startsWith(UBX_i6200S)) {
            ubxScan.destroy();
        }
    }
}
