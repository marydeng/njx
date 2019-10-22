package com.njx.mvvmhabit.ui.depot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentReturnDetailBinding;
import com.njx.mvvmhabit.databinding.FragmentTransferDetailBinding;
import com.njx.mvvmhabit.entity.ReturnEntity;
import com.njx.mvvmhabit.entity.TransferEntity;
import com.njx.mvvmhabit.entity.TransferPartRecordEntity;
import com.njx.mvvmhabit.ui.depot.viewmodel.ReturnDetailViewModel;
import com.njx.mvvmhabit.ui.depot.viewmodel.TransferDetailViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

public class TransferDetailFragment extends BaseFragment<FragmentTransferDetailBinding, TransferDetailViewModel> {
    public static final String Extra_Entity = "TransferDetailFragment.storageEntity";
    private TransferPartRecordEntity entity = new TransferPartRecordEntity();

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_transfer_detail;
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
            String jsonStr = bundle.getString(Extra_Entity);
            if(!TextUtils.isEmpty(jsonStr)){
                entity=new Gson().fromJson(jsonStr,TransferPartRecordEntity.class);
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
