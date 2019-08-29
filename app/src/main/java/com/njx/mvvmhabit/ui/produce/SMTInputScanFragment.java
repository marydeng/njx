package com.njx.mvvmhabit.ui.produce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentSmtInputConfigBinding;
import com.njx.mvvmhabit.databinding.FragmentSmtInputScanBinding;
import com.njx.mvvmhabit.ui.base.fragment.BaseScanFragment;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTInputConfigViewModel;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTInputScanViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

public class SMTInputScanFragment extends BaseScanFragment<FragmentSmtInputScanBinding, SMTInputScanViewModel> {
    public static final String Extra_order_id="SMTInputScanFragment.order.id";
    private String orderId;
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_smt_input_scan;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initParam() {
        super.initParam();
        Bundle bundle = getArguments();
        if (null != bundle) {
            orderId = bundle.getString(Extra_order_id);
        }

    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initToolBar();
        viewModel.orderID=orderId;
    }

    @Override
    protected void onGetScanCode(String scanCode) {
        super.onGetScanCode(scanCode);
        binding.produceCodeEdit.setText(scanCode);
        viewModel.onCommitCommand.execute();
    }
}
