package com.njx.mvvmhabit.ui.produce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentSmtDetailBinding;
import com.njx.mvvmhabit.databinding.FragmentSteelDetailBinding;
import com.njx.mvvmhabit.entity.FeedingEntity;
import com.njx.mvvmhabit.entity.SteelEntity;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTDetailViewModel;
import com.njx.mvvmhabit.ui.produce.viewmodel.SteelDetailViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

public class SteelDetailFragment extends BaseFragment<FragmentSteelDetailBinding, SteelDetailViewModel> {
    public static final String Extra_Entity = "SteelDetailFragment.storageEntity";
    private SteelEntity entity = new SteelEntity();

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_steel_detail;
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
