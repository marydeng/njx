package com.njx.mvvmhabit.ui.depot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentStorageDetailBinding;
import com.njx.mvvmhabit.entity.StorageEntity;
import com.njx.mvvmhabit.ui.depot.viewmodel.StorageDetailViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

public class StorageDetailFragment extends BaseFragment<FragmentStorageDetailBinding, StorageDetailViewModel> {
    public static final String Extra_Entity="storageEntity";
    private StorageEntity storageEntity=new StorageEntity();
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_storage_detail;
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
            storageEntity=bundle.getParcelable(Extra_Entity);
        }
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.setStorageEntity(storageEntity);
        viewModel.initToolBar();
    }
}
