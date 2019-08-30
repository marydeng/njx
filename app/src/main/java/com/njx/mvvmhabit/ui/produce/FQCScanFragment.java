package com.njx.mvvmhabit.ui.produce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentFqcScanBinding;
import com.njx.mvvmhabit.ui.base.fragment.BaseScanFragment;
import com.njx.mvvmhabit.ui.produce.viewmodel.FQCScanViewModel;

public class FQCScanFragment extends BaseScanFragment<FragmentFqcScanBinding, FQCScanViewModel> {
    public static final String Extra_zhanban_id = "FQCScanFragment.zhanban.id";
    public static final String Extra_num = "FQCScanFragment.num";
    public static final String Extra_test_type = "FQCScanFragment.test.type";
    private String zhanbanId;
    private String num;
    private String testType;

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
        binding.produceCodeEdit.setText(scanCode);
        viewModel.onCommitCommand.execute();
    }
}
