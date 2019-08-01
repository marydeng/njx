package com.njx.mvvmhabit.ui.depot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentOutSearchBinding;
import com.njx.mvvmhabit.databinding.FragmentTransferSearchBinding;
import com.njx.mvvmhabit.ui.depot.viewmodel.OutSearchViewModel;
import com.njx.mvvmhabit.ui.depot.viewmodel.TransferSearchViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

public class TransferSearchFragment extends BaseFragment<FragmentTransferSearchBinding, TransferSearchViewModel> {
    private String[] invoiceList = {"A78458294", "A78458295", "A78458296", "A78458297", "A78458298", "A78458299", "A78458300", "A78458301"};
    private String[] deptList = {"ck1", "ck2", "ck3", "ck4", "ck5", "ck6", "ck7", "ck8"};

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_transfer_search;
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
