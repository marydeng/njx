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

import java.util.ArrayList;

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
        viewModel.uc.showConfirmDialog.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                String msg;
                if (aBoolean) {
                    msg = viewModel.SMTTypeTxt.get() + "成功";
                } else {
                    msg = viewModel.SMTTypeTxt.get() + "失败";
                }
                MaterialDialog.Builder builder = MaterialDialogUtils.showBasicDialogNoCancel(getActivity(), "上料结果确认", msg);
                builder.show();

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
