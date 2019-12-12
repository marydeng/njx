package com.njx.mvvmhabit.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.njx.mvvmhabit.BR;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentMyBinding;
import com.njx.mvvmhabit.ui.main.viewmodel.MyViewModel;
import com.njx.mvvmhabit.utils.UBXScan;


import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.utils.MaterialDialogUtils;

import static com.njx.mvvmhabit.ui.main.MainActivity.UBX_PRE;

/**
 * Created by goldze on 2018/7/18.
 */

public class MyFragment extends BaseFragment<FragmentMyBinding, MyViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_my;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initToolBar();

        binding.quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog.Builder builder = MaterialDialogUtils.showBasicDialog(getContext(), "确认退出应用");
                builder.onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        getActivity().finish();
                    }
                });
                builder.show();
            }
        });

        if (Build.MODEL.startsWith(UBX_PRE)) {
            binding.scannerLightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    UBXScan.getInstance().setLight(checked);
                }
            });
        }
    }
}
