package com.njx.mvvmhabit.ui.produce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentSmtDetailBinding;
import com.njx.mvvmhabit.databinding.FragmentTransferDetailBinding;
import com.njx.mvvmhabit.entity.FeedingEntity;
import com.njx.mvvmhabit.entity.TransferEntity;
import com.njx.mvvmhabit.ui.depot.viewmodel.TransferDetailViewModel;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTDetailViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

public class SMTDetailFragment extends BaseFragment<FragmentSmtDetailBinding, SMTDetailViewModel> {
    public static final String Extra_Entity = "TransferDetailFragment.storageEntity";
    private FeedingEntity entity = new FeedingEntity();

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_smt_detail;
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
            entity = bundle.getParcelable(Extra_Entity);
        }
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.setEntity(entity);
        viewModel.initToolBar();
    }
}
