package com.njx.mvvmhabit.ui.produce;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.afollestad.materialdialogs.MaterialDialog;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentSmtSearchBinding;
import com.njx.mvvmhabit.databinding.FragmentTransferSearchBinding;
import com.njx.mvvmhabit.ui.depot.viewmodel.TransferSearchViewModel;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTSearchViewModel;
import com.njx.mvvmhabit.ui.widget.spinner.bean.SpinnearBean;
import com.njx.mvvmhabit.ui.widget.spinner.listener.OnSpinnerItemClickListener;
import com.njx.mvvmhabit.ui.widget.spinner.util.PopWindowUtil;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.utils.MaterialDialogUtils;

public class SMTSearchFragment extends BaseFragment<FragmentSmtSearchBinding, SMTSearchViewModel> {
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
        SpinnearBean sp3 = new SpinnearBean("料枪变更", "3");
        typeList.add(sp1);
        typeList.add(sp2);
        typeList.add(sp3);

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
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.showConfirmDialog.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> dataList) {
                if (dataList == null || dataList.size() == 0) {
                    MaterialDialog.Builder builder = MaterialDialogUtils.showBasicDialogNoCancel(getActivity(), "上料结果确认", "上料成功");
                    builder.show();
                } else {
                    MaterialDialog.Builder builder=MaterialDialogUtils.showBasicListDialog(getActivity(),"上料失败，以下料站未上料",dataList);
                    builder.show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        PopWindowUtil.closePopupWindows();
    }
}
