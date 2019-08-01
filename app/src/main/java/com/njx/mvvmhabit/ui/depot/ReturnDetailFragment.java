package com.njx.mvvmhabit.ui.depot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentBackDetailBinding;
import com.njx.mvvmhabit.databinding.FragmentReturnDetailBinding;
import com.njx.mvvmhabit.entity.BackEntity;
import com.njx.mvvmhabit.entity.ReturnEntity;
import com.njx.mvvmhabit.ui.depot.viewmodel.BackDetailViewModel;
import com.njx.mvvmhabit.ui.depot.viewmodel.ReturnDetailViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

public class ReturnDetailFragment extends BaseFragment<FragmentReturnDetailBinding, ReturnDetailViewModel> {
    public static final String Extra_Entity = "BackDetailFragment.storageEntity";
    private ReturnEntity entity = new ReturnEntity();

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_return_detail;
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
