package com.njx.mvvmhabit.ui.quality;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentSmtSearchBinding;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTSearchViewModel;
import com.njx.mvvmhabit.ui.quality.viewmodel.QualitySearchViewModel;
import com.njx.mvvmhabit.ui.widget.spinner.bean.SpinnearBean;
import com.njx.mvvmhabit.ui.widget.spinner.listener.OnSpinnerItemClickListener;

import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseFragment;

public class QualitySearchFragment extends BaseFragment<FragmentSmtSearchBinding, QualitySearchViewModel> {

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_quality_search;
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
