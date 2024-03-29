package com.njx.mvvmhabit.ui.produce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.njx.mvvmhabit.BR;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentSmtInputConfigBinding;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTInputConfigViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

public class SMTInputConfigFragment extends BaseFragment<FragmentSmtInputConfigBinding, SMTInputConfigViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_smt_input_config;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initToolBar();
        binding.lastOrderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yes_radiobtn) {
                    binding.lastOrderLinear.setVisibility(View.VISIBLE);
                    viewModel.isConnectOrder=true;
                } else {
                    binding.lastOrderLinear.setVisibility(View.INVISIBLE);
                    viewModel.isConnectOrder=false;
                    viewModel.lastOrderID.set("");
                }
            }
        });
    }
}
