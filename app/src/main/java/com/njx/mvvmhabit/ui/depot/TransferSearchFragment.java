package com.njx.mvvmhabit.ui.depot;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentTransferSearchBinding;
import com.njx.mvvmhabit.ui.depot.viewmodel.TransferSearchViewModel;
import com.njx.mvvmhabit.ui.widget.spinner.adapter.MySpinnerPopListArrayAdapter;
import com.njx.mvvmhabit.ui.widget.spinner.bean.SpinnearBean;
import com.njx.mvvmhabit.ui.widget.spinner.listener.OnSpinnerItemClickListener;
import com.njx.mvvmhabit.ui.widget.spinner.util.PopWindowUtil;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseFragment;

public class TransferSearchFragment extends BaseFragment<FragmentTransferSearchBinding, TransferSearchViewModel> {

    private List<String> orderList;
    private ArrayAdapter orderAdapter;
    private ArrayList<SpinnearBean> orderSpinList;

    private ArrayList<SpinnearBean> deptSpinList;


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
        orderList = new ArrayList<>();
        orderSpinList = new ArrayList<>();
//        orderAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, orderList.toArray(new String[orderList.size()]));
//        binding.orderAuto.setAdapter(orderAdapter);
//        binding.orderAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                viewModel.orderId =orderList.get(position);
//                viewModel.queryDeptList();
//            }
//        });
        binding.orderAuto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                String code=String.valueOf(s);
////                binding.orderAuto.setText("");
//                if(!TextUtils.isEmpty(code)){
//                    viewModel.queryOrder(code);
//                }
                deptSpinList.clear();
                binding.deptSpinner.setData(null);
                binding.deptSpinner.clearTitleTextView();
                viewModel.dept = "";


                viewModel.serchCode = String.valueOf(s);
            }
        });


        deptSpinList = new ArrayList<>();

        binding.deptSpinner.setData(deptSpinList);
        binding.deptSpinner.setHint("请输入目标仓库");
        binding.deptSpinner.setOnSpinnerItemClickListener(new OnSpinnerItemClickListener() {
            @Override
            public void OnFinished(int position) {
                viewModel.dept = deptSpinList.get(position).getParaValue();
            }
        });


    }


    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.setOderData.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> dataList) {
//                orderList.clear();
//                if(dataList!=null && dataList.size()>0){
//                    orderList.addAll(dataList);
//                }

//                orderAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, orderList.toArray(new String[orderList.size()]));
//                binding.orderAuto.setAdapter(orderAdapter);
//                binding.deptSpinner.setData(deptSpinList);

                orderSpinList.clear();
                for (String orderId : dataList) {
                    SpinnearBean sb = new SpinnearBean(orderId, orderId);
                    orderSpinList.add(sb);
                }
                PopWindowUtil.showPopupWindows(getContext(), binding.orderAuto, orderSpinList, new MySpinnerPopListArrayAdapter.OnMyItemClickListener() {
                    @Override
                    public void OnMyItemClick(int position) {
                        if (position != -1) {
                            viewModel.orderId = orderSpinList.get(position).getParaValue();
                            viewModel.queryDeptList();
                            PopWindowUtil.closePopupWindows();
                        }
                    }
                }, -1);

            }
        });

        viewModel.uc.setDeptData.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> dataList) {
                deptSpinList.clear();
                if (dataList != null && dataList.size() > 0) {
                    for (String s : dataList) {
                        SpinnearBean spinnearBean = new SpinnearBean(s, s);
                        deptSpinList.add(spinnearBean);
                    }
                }
                binding.deptSpinner.setData(deptSpinList);
            }
        });
    }
}
