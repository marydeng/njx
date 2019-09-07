package com.njx.mvvmhabit.ui.produce;

import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentFqcScanBinding;
import com.njx.mvvmhabit.ui.base.fragment.BaseScanFragment;
import com.njx.mvvmhabit.ui.produce.viewmodel.FQCScanViewModel;

import me.goldze.mvvmhabit.utils.MaterialDialogUtils;

public class FQCScanFragment extends BaseScanFragment<FragmentFqcScanBinding, FQCScanViewModel> {
    public static final String Extra_zhanban_id = "FQCScanFragment.zhanban.id";
    public static final String Extra_num = "FQCScanFragment.num";
    public static final String Extra_test_type = "FQCScanFragment.test.type";
    private String zhanbanId;
    private String num;
    private String testType;
    private boolean isShowErrorDialog = false;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_fqc_scan;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initParam() {
        super.initParam();
//        Bundle bundle = getArguments();
//        if (null != bundle) {
//            zhanbanId = bundle.getString(Extra_zhanban_id);
//            num = bundle.getString(Extra_num);
//            testType = bundle.getString(Extra_test_type);
//        }

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
//        viewModel.zhanbanId = zhanbanId;
//        viewModel.num = num;
//        viewModel.testType = testType;
//        viewModel.numStatic.set("0/" + num);

        binding.endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }

    @Override
    protected void onGetScanCode(String scanCode) {
        if (!isShowErrorDialog) {
            binding.produceCodeEdit.setText(scanCode);
            viewModel.onCommitCommand.execute();
        }
    }
}
