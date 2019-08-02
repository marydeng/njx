package com.njx.mvvmhabit.ui.depot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.databinding.FragmentOutOperateBinding;
import com.njx.mvvmhabit.entity.StorageEntity;
import com.njx.mvvmhabit.ui.base.fragment.BaseScanFragment;
import com.njx.mvvmhabit.ui.depot.adapter.StorageAdapter;
import com.njx.mvvmhabit.ui.depot.viewmodel.OutOperateViewModel;
import com.njx.mvvmhabit.utils.ScannerInterface;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.bus.Messenger;

public class OutOperateFragment extends BaseScanFragment<FragmentOutOperateBinding, OutOperateViewModel> {
    public static final String Extra_Order = "OutOperateFragment.order";
    public static final String Extra_Dept = "OutOperateFragment.dept";
    private List<StorageEntity> storageEntityList;
    private String order ="";
    private String dept="";



    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_out_operate;
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
            order =bundle.getString(Extra_Order);
            dept=bundle.getString(Extra_Dept);
        }
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.orderTxt.set(order);
        viewModel.deptTxt.set(dept);
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
