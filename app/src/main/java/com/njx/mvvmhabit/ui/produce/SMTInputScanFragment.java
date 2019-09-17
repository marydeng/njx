package com.njx.mvvmhabit.ui.produce;

import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentSmtInputConfigBinding;
import com.njx.mvvmhabit.databinding.FragmentSmtInputScanBinding;
import com.njx.mvvmhabit.ui.base.fragment.BaseScanFragment;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTInputConfigViewModel;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTInputScanViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.utils.MaterialDialogUtils;

public class SMTInputScanFragment extends BaseScanFragment<FragmentSmtInputScanBinding, SMTInputScanViewModel> {
    public static final String Extra_order_id = "SMTInputScanFragment.order.id";
    public static final String Extra_last_order_id = "SMTInputScanFragment.last.order.id";
    private String orderId;
    private String lastWorkOrder;
    private boolean isShowErrorDialog = false;

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
            lastWorkOrder=bundle.getString(Extra_last_order_id);
        }

    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.showErrorDialog.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                isShowErrorDialog = true;
                MaterialDialog.Builder builder = MaterialDialogUtils.showBasicDialog(getContext(), "报警", s);
                builder.show().setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        isShowErrorDialog = false;
                    }
                });
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initToolBar();
        viewModel.orderID = orderId;
        viewModel.lastWorkOrder=lastWorkOrder;
    }

    @Override
    protected void onGetScanCode(String scanCode) {
        super.onGetScanCode(scanCode);
        if (!isShowErrorDialog) {
            binding.produceCodeEdit.setText(scanCode);
            viewModel.onCommitCommand.execute();
        }
    }
}
