package com.njx.mvvmhabit.ui.business;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentBackSearchBinding;
import com.njx.mvvmhabit.databinding.FragmentBusiOutSearchBinding;
import com.njx.mvvmhabit.ui.business.viewmodel.OutSearchViewModel;
import com.njx.mvvmhabit.ui.depot.viewmodel.BackSearchViewModel;
import com.njx.mvvmhabit.ui.widget.spinner.bean.SpinnearBean;
import com.njx.mvvmhabit.ui.widget.spinner.listener.OnSpinnerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseFragment;

public class ProductOutSearchFragment extends BaseFragment<FragmentBusiOutSearchBinding, OutSearchViewModel> {

    private ArrayList<SpinnearBean> orderSpinList;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_busi_out_search;
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
