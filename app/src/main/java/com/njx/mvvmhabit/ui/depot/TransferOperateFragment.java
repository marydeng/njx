package com.njx.mvvmhabit.ui.depot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentReturnOperateBinding;
import com.njx.mvvmhabit.databinding.FragmentTransferOperateBinding;
import com.njx.mvvmhabit.entity.StorageEntity;
import com.njx.mvvmhabit.ui.base.fragment.BaseScanFragment;
import com.njx.mvvmhabit.ui.depot.adapter.StorageAdapter;
import com.njx.mvvmhabit.ui.depot.viewmodel.ReturnOperateViewModel;
import com.njx.mvvmhabit.ui.depot.viewmodel.TransferOperateViewModel;

import java.util.ArrayList;
import java.util.List;

public class TransferOperateFragment extends BaseScanFragment<FragmentTransferOperateBinding, TransferOperateViewModel> {
    public static final String Extra_dest_depot = "TransferOperateFragment.dest.depot";
    public static final String Extra_depot_class = "TransferOperateFragment.depot.class";
    private List<StorageEntity> storageEntityList;
    private String destDepot ="";
    private String depotClass="";




    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_transfer_operate;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initParam() {
        super.initParam();
        Bundle bundle=getArguments();
        if (bundle != null) {
            destDepot =bundle.getString(Extra_dest_depot);
            depotClass=bundle.getString(Extra_depot_class);
        }
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.DestDepotTxt.set(destDepot);
        viewModel.DepotClassTxt.set(depotClass);

        viewModel.initToolBar();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        createData();
        StorageAdapter storageAdapter = new StorageAdapter(getContext(), storageEntityList);
        storageAdapter.setOnItemClickListener(new StorageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(StorageDetailFragment.Extra_Entity, storageEntityList.get(position));
                startContainerActivity(StorageDetailFragment.class.getCanonicalName(), bundle);
            }
        });
        binding.recyclerview.setAdapter(storageAdapter);



    }

    private void createData() {
        storageEntityList = new ArrayList<>();
        StorageEntity storageEntity1 = new StorageEntity("HSK123456789", "2000", "8000", "8000");
        StorageEntity storageEntity2 = new StorageEntity("HSK123456789", "2000", "8000", "7000");
        StorageEntity storageEntity3 = new StorageEntity("HSK123456789", "2000", "8000", "8000");
        StorageEntity storageEntity4 = new StorageEntity("HSK123456789", "2000", "8000", "8000");
        StorageEntity storageEntity5 = new StorageEntity("HSK123456789", "2000", "8000", "8000");
        StorageEntity storageEntity6 = new StorageEntity("HSK123456789", "2000", "8000", "8000");
        StorageEntity storageEntity7 = new StorageEntity("HSK123456789", "2000", "8000", "8000");
        StorageEntity storageEntity8 = new StorageEntity("HSK123456789", "2000", "8000", "8000");

        storageEntityList.add(storageEntity1);
        storageEntityList.add(storageEntity2);
        storageEntityList.add(storageEntity3);
        storageEntityList.add(storageEntity4);
        storageEntityList.add(storageEntity5);
        storageEntityList.add(storageEntity6);
        storageEntityList.add(storageEntity7);
        storageEntityList.add(storageEntity8);
    }


}
