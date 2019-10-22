package com.njx.mvvmhabit.ui.depot;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentBackSearchBinding;
import com.njx.mvvmhabit.databinding.FragmentOutSearchBinding;
import com.njx.mvvmhabit.ui.depot.viewmodel.BackSearchViewModel;
import com.njx.mvvmhabit.ui.depot.viewmodel.OutSearchViewModel;
import com.njx.mvvmhabit.ui.widget.spinner.adapter.MySpinnerPopListArrayAdapter;
import com.njx.mvvmhabit.ui.widget.spinner.bean.SpinnearBean;
import com.njx.mvvmhabit.ui.widget.spinner.listener.OnSpinnerItemClickListener;
import com.njx.mvvmhabit.ui.widget.spinner.util.PopWindowUtil;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseFragment;

public class BackSearchFragment extends BaseFragment<FragmentBackSearchBinding, BackSearchViewModel> {
    private String[] invoiceList = {"A78458294", "A78458295", "A78458296", "A78458297", "A78458298", "A78458299", "A78458300", "A78458301"};
    private String[] deptList = {"ck1", "ck2", "ck3", "ck4", "ck5", "ck6", "ck7", "ck8"};

    private ArrayList<SpinnearBean> orderSpinList;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_back_search;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initToolBar();

        orderSpinList = new ArrayList<>();

        binding.orderSpinner.setData(orderSpinList);
        binding.orderSpinner.setHint("请输入退库单号");
        binding.orderSpinner.setOnSpinnerItemClickListener(new OnSpinnerItemClickListener() {
            @Override
            public void OnFinished(int position) {
                viewModel.orderTxt.set(orderSpinList.get(position).getParaValue());
            }
        });

        viewModel.queryOrder();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.setOderData.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> dataList) {
                orderSpinList.clear();
                for (String orderId : dataList) {
                    SpinnearBean sb = new SpinnearBean(orderId, orderId);
                    orderSpinList.add(sb);
                }
                binding.orderSpinner.setData(orderSpinList);
            }
        });

    }
}
