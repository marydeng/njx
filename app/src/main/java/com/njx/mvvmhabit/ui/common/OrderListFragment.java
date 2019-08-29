package com.njx.mvvmhabit.ui.common;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.njx.mvvmhabit.BR;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.databinding.FragmentOrderListBinding;
import com.njx.mvvmhabit.entity.OrderListEntity;
import com.njx.mvvmhabit.ui.common.viewmodel.OrderListViewModel;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTSearchViewModel;
import com.njx.mvvmhabit.ui.widget.spinner.bean.SpinnearBean;
import com.njx.mvvmhabit.ui.widget.spinner.listener.OnSpinnerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.bus.Messenger;

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
                SpinnearBean allBean = new SpinnearBean("全部", "");
                lineList.add(allBean);
                if (lineNameList != null && lineNameList.size() > 0) {
                    for (String lineName : lineNameList) {
                        SpinnearBean spinnearBean = new SpinnearBean(lineName, lineName);
                        lineList.add(spinnearBean);
                    }
                }
                binding.lineTypeSpinner.setData(lineList);
            }
        });

        viewModel.uc.showOrderList.observe(this, new Observer<List<OrderListEntity.WorkEntity>>() {
            @Override
            public void onChanged(@Nullable List<OrderListEntity.WorkEntity> dataList) {
                if (!viewModel.isLoad) {
                    orderList.clear();
                }
                if (dataList != null && dataList.size() > 0) {
                    for (OrderListEntity.WorkEntity workEntity : dataList) {
                        orderList.add(workEntity.getWorkorderNumber());
                    }
                }

                if (viewModel.isLoad) {
                    viewModel.isLoad = false;
                    binding.twinklingRefreshLayout.finishLoadmore();
                }

                if (orderList.size() >= viewModel.totalSize) {
                    binding.twinklingRefreshLayout.setEnableLoadmore(false);
                } else {
                    binding.twinklingRefreshLayout.setEnableLoadmore(true);
                }
                viewModel.mStartPage++;
                orderAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initToolBar();

        binding.twinklingRefreshLayout.setEnableLoadmore(true);
        binding.twinklingRefreshLayout.setEnableRefresh(false);

        lineList = new ArrayList<>();

        binding.lineTypeSpinner.setData(lineList);
        binding.lineTypeSpinner.setHint("请输入线体");
        binding.lineTypeSpinner.setOnSpinnerItemClickListener(new OnSpinnerItemClickListener() {
            @Override
            public void OnFinished(int position) {
                viewModel.lineClass = lineList.get(position).getParaValue();
                viewModel.mStartPage=1;
                viewModel.queryOrderList();
            }
        });

        orderList = new ArrayList<>();
        orderAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, orderList);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Messenger.getDefault().send(orderList.get(position), Constant.TOKEN__Receive_Work_Item);
                getActivity().finish();
            }
        });
        binding.listView.setAdapter(orderAdapter);

        viewModel.queryLine();
        viewModel.queryOrderList();
    }
}
