package com.njx.mvvmhabit.ui.depot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentOutDetailBinding;
import com.njx.mvvmhabit.entity.OutEntity;
import com.njx.mvvmhabit.entity.OutPartRecordEntity;
import com.njx.mvvmhabit.ui.depot.viewmodel.OutDetailViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

public class OutDetailFragment extends BaseFragment<FragmentOutDetailBinding, OutDetailViewModel> {
    public static final String Extra_Entity="OutDetailFragment.storageEntity";
    private OutPartRecordEntity entity =new OutPartRecordEntity();
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
            String jsonStr =bundle.getString(Extra_Entity);
            if(!TextUtils.isEmpty(jsonStr)){
                entity=new Gson().fromJson(jsonStr,OutPartRecordEntity.class);
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
