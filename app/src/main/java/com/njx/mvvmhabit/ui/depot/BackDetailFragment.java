package com.njx.mvvmhabit.ui.depot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentBackDetailBinding;
import com.njx.mvvmhabit.databinding.FragmentOutDetailBinding;
import com.njx.mvvmhabit.entity.BackEntity;
import com.njx.mvvmhabit.entity.BackPartRecordEntity;
import com.njx.mvvmhabit.entity.OutEntity;
import com.njx.mvvmhabit.ui.depot.viewmodel.BackDetailViewModel;
import com.njx.mvvmhabit.ui.depot.viewmodel.OutDetailViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

public class BackDetailFragment extends BaseFragment<FragmentBackDetailBinding, BackDetailViewModel> {
    public static final String Extra_Entity="BackDetailFragment.storageEntity";
    private BackPartRecordEntity entity =new BackPartRecordEntity();
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_back_detail;
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
            String jsonStr =bundle.getString(Extra_Entity);
            if(!TextUtils.isEmpty(jsonStr)){
                entity=new Gson().fromJson(jsonStr,BackPartRecordEntity.class);
            }
        }
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.setEntity(entity);
        viewModel.initToolBar();
    }
}
