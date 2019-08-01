package com.njx.mvvmhabit.ui.depot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentOutDetailBinding;
import com.njx.mvvmhabit.entity.OutEntity;
import com.njx.mvvmhabit.ui.depot.viewmodel.OutDetailViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

public class OutDetailFragment extends BaseFragment<FragmentOutDetailBinding, OutDetailViewModel> {
    public static final String Extra_Entity="OutDetailFragment.storageEntity";
    private OutEntity entity =new OutEntity();
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_out_detail;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initParam() {
        super.initParam();
        Bundle bundle=getArguments();
        if(null!=bundle){
            entity =bundle.getParcelable(Extra_Entity);
        }
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.setEntity(entity);
        viewModel.initToolBar();
    }
}
