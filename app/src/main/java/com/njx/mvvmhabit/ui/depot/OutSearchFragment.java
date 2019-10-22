package com.njx.mvvmhabit.ui.depot;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentOutSearchBinding;
import com.njx.mvvmhabit.entity.OutOrderEntity;
import com.njx.mvvmhabit.ui.depot.viewmodel.OutSearchViewModel;
import com.njx.mvvmhabit.ui.widget.spinner.bean.SpinnearBean;
import com.njx.mvvmhabit.ui.widget.spinner.listener.OnSpinnerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseFragment;

public class OutSearchFragment extends BaseFragment<FragmentOutSearchBinding, OutSearchViewModel> {
    private String[] invoiceList = {"A78458294", "A78458295", "A78458296", "A78458297", "A78458298", "A78458299", "A78458300", "A78458301"};
    private String[] deptList = {"ck1", "ck2", "ck3", "ck4", "ck5", "ck6", "ck7", "ck8"};

    private ArrayList<SpinnearBean> orderSpiList;
    private List<OutOrderEntity> outOrderEntityList;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_out_search;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.setOderData.observe(this, new Observer<List<OutOrderEntity>>() {
            @Override
            public void onChanged(@Nullable List<OutOrderEntity> outOrderEntities) {
                outOrderEntityList.clear();
                outOrderEntityList.addAll(outOrderEntities);

                orderSpiList.clear();
                for (OutOrderEntity outOrderEntity : outOrderEntities) {
                    SpinnearBean spinnearBean=new SpinnearBean(outOrderEntity.getWorkorderNumber(),outOrderEntity.getWorkorderNumber());
                    orderSpiList.add(spinnearBean);
                }

                binding.outOrderSpinner.setData(orderSpiList);
            }
        });

    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initToolBar();

        orderSpiList =new ArrayList<>();
        outOrderEntityList=new ArrayList<>();

        binding.outOrderSpinner.setData(orderSpiList);
        binding.outOrderSpinner.setHint("请输入出库单号");
        binding.outOrderSpinner.setOnSpinnerItemClickListener(new OnSpinnerItemClickListener() {
            @Override
            public void OnFinished(int position) {
                binding.outDepatEd.setText(outOrderEntityList.get(position).getWarehouseName());
                viewModel.orderTxt.set(outOrderEntityList.get(position).getWorkorderNumber());
                viewModel.deptTxt.set(outOrderEntityList.get(position).getWarehouseName());
            }
        });

        viewModel.queryAllOutOrder();

    }

}
