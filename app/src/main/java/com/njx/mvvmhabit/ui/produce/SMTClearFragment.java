package com.njx.mvvmhabit.ui.produce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentSmtClearBinding;
import com.njx.mvvmhabit.databinding.FragmentSmtSearchBinding;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTClearViewModel;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTSearchViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

public class SMTClearFragment extends BaseFragment<FragmentSmtClearBinding, SMTClearViewModel> {
    private String[] deptList = {"ck1", "ck2", "ck3", "ck4", "ck5", "ck6", "ck7", "ck8"};

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_smt_clear;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initToolBar();

        ArrayAdapter deptAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, deptList);
        binding.deptClassAuto.setAdapter(deptAdapter);


    }

}
