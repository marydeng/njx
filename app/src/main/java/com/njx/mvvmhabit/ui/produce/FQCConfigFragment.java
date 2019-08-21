package com.njx.mvvmhabit.ui.produce;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentFqcConfigBinding;
import com.njx.mvvmhabit.ui.produce.viewmodel.FQCConfigViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

public class FQCConfigFragment extends BaseFragment<FragmentFqcConfigBinding, FQCConfigViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_fqc_config;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initToolBar();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.resetRadioBtn.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                binding.ngRadiobtn.setChecked(true);
                binding.okRadiobtn.setChecked(false);
            }
        });
    }
}
