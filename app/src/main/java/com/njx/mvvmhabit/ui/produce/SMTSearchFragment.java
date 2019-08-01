package com.njx.mvvmhabit.ui.produce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentSmtSearchBinding;
import com.njx.mvvmhabit.databinding.FragmentTransferSearchBinding;
import com.njx.mvvmhabit.ui.depot.viewmodel.TransferSearchViewModel;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTSearchViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

public class SMTSearchFragment extends BaseFragment<FragmentSmtSearchBinding, SMTSearchViewModel> {
    private String[] invoiceList = {"上料","接料","换料","对料"};
    private String[] deptList = {"ck1", "ck2", "ck3", "ck4", "ck5", "ck6", "ck7", "ck8"};

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_smt_search;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initToolBar();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, invoiceList);
        binding.destDepotAuto.setAdapter(arrayAdapter);

        ArrayAdapter deptAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, deptList);
        binding.deptClassAuto.setAdapter(deptAdapter);
    }

}
