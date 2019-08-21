package com.njx.mvvmhabit.ui.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentProductStorageBinding;
import com.njx.mvvmhabit.ui.base.fragment.BaseScanFragment;
import com.njx.mvvmhabit.ui.business.viewmodel.ProductStorageViewModel;

public class ProductStorageFragment extends BaseScanFragment<FragmentProductStorageBinding, ProductStorageViewModel> {

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_product_storage;
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
}
