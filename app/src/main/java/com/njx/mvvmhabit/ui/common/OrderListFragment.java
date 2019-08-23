package com.njx.mvvmhabit.ui.common;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.njx.mvvmhabit.BR;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentOrderListBinding;
import com.njx.mvvmhabit.ui.common.viewmodel.OrderListViewModel;
import com.njx.mvvmhabit.ui.widget.spinner.bean.SpinnearBean;
import com.njx.mvvmhabit.ui.widget.spinner.listener.OnSpinnerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseFragment;

public class OrderListFragment extends BaseFragment<FragmentOrderListBinding, OrderListViewModel> {
    private ArrayList<SpinnearBean> lineList;
    private List<String> orderList;
    private ArrayAdapter orderAdapter;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_order_list;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.showLineList.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> lineNameList) {
                lineList.clear();
                if (lineNameList != null && lineNameList.size() > 0) {
                    for (String lineName : lineNameList) {
                        SpinnearBean spinnearBean = new SpinnearBean(lineName, lineName);
                        lineList.add(spinnearBean);
                    }
                }
                binding.lineTypeSpinner.setData(lineList);
            }
        });

        viewModel.uc.showOrderList.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> dataList) {
                orderList.clear();
                if (dataList != null && dataList.size() > 0) {
                    orderList.addAll(dataList);
                }
                orderAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        lineList = new ArrayList<>();

        binding.lineTypeSpinner.setData(lineList);
        binding.lineTypeSpinner.setHint("请输入线体");
        binding.lineTypeSpinner.setOnSpinnerItemClickListener(new OnSpinnerItemClickListener() {
            @Override
            public void OnFinished(int position) {
                viewModel.lineClass = lineList.get(position).getParaName();
                viewModel.queryOrderList();
            }
        });

        orderList = new ArrayList<>();
        orderAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, orderList);
        binding.listView.setAdapter(orderAdapter);

        viewModel.queryLine();
        viewModel.queryOrderList();
    }
}
