package com.njx.mvvmhabit.ui.produce;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentSmtSearchBinding;
import com.njx.mvvmhabit.databinding.FragmentTransferSearchBinding;
import com.njx.mvvmhabit.ui.depot.viewmodel.TransferSearchViewModel;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTSearchViewModel;
import com.njx.mvvmhabit.ui.widget.spinner.bean.SpinnearBean;
import com.njx.mvvmhabit.ui.widget.spinner.listener.OnSpinnerItemClickListener;

import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseFragment;

public class SMTSearchFragment extends BaseFragment<FragmentSmtSearchBinding, SMTSearchViewModel> {
    private String[] invoiceList = {"上料", "接料", "换料"};
    private String[] deptList = {"ck1", "ck2", "ck3", "ck4", "ck5", "ck6", "ck7", "ck8"};
    private ArrayList<SpinnearBean> typeList;

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
        typeList = new ArrayList<>();
        SpinnearBean sp1 = new SpinnearBean("上料", "1");
        SpinnearBean sp2 = new SpinnearBean("接料", "2");
        SpinnearBean sp3 = new SpinnearBean("换料枪", "3");
        SpinnearBean sp4 = new SpinnearBean("对料", "4");
        typeList.add(sp1);
        typeList.add(sp2);
        typeList.add(sp3);
        typeList.add(sp4);

        binding.smtTypeSpinner.setData(typeList);
        binding.smtTypeSpinner.setSelectedIndexAndText(0);
        binding.smtTypeSpinner.setOnSpinnerItemClickListener(new OnSpinnerItemClickListener() {
            @Override
            public void OnFinished(int position) {
                viewModel.SMTTypeTxt.set(typeList.get(position).getParaName());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
