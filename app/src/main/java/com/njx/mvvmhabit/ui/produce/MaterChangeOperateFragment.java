package com.njx.mvvmhabit.ui.produce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentGunChangeOperateBinding;
import com.njx.mvvmhabit.databinding.FragmentMaterChangeOperateBinding;
import com.njx.mvvmhabit.entity.FeedingEntity;
import com.njx.mvvmhabit.ui.base.fragment.BaseScanFragment;
import com.njx.mvvmhabit.ui.produce.adapter.MaterChangeAdapter;
import com.njx.mvvmhabit.ui.produce.adapter.SMTAdapter;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTOperateViewModel;

import java.util.ArrayList;
import java.util.List;

public class MaterChangeOperateFragment extends BaseScanFragment<FragmentMaterChangeOperateBinding, SMTOperateViewModel> {
    public static final String Extra_order_id = "SMTOperateFragment.order.id";
    public static final String Extra_smt_type = "SMTOperateFragment.smt.type";
    private List<FeedingEntity> feedingEntityList;
    private String orderID ="";
    private String smtType ="";

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_mater_change_operate;
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
            orderID =bundle.getString(Extra_order_id);
            smtType =bundle.getString(Extra_smt_type);
        }
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.orderId.set(orderID);
        viewModel.smtType.set(smtType);

        viewModel.initToolBar();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        createData();
        MaterChangeAdapter smtAdapter = new MaterChangeAdapter(getContext(), feedingEntityList);
        smtAdapter.setOnItemClickListener(new MaterChangeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(SMTDetailFragment.Extra_Entity, feedingEntityList.get(position));
                startContainerActivity(SMTDetailFragment.class.getCanonicalName(), bundle);
            }
        });
        binding.recyclerview.setAdapter(smtAdapter);



    }

    private void createData() {
        feedingEntityList = new ArrayList<>();

        FeedingEntity feedingEntity1=new FeedingEntity("HSK2087908923HS00","HK2","OAH897NH55667788");
        FeedingEntity feedingEntity2=new FeedingEntity("HSK2087908923HS01","HK3","OAH897NH55667788");
        FeedingEntity feedingEntity3=new FeedingEntity("HSK2087908923HS02","HK3","OAH897NH55667788");
        FeedingEntity feedingEntity4=new FeedingEntity("HSK2087908923HS03","HK3","OAH897NH55667788");
        FeedingEntity feedingEntity5=new FeedingEntity("HSK2087908923HS04","HK3","OAH897NH55667788");
        FeedingEntity feedingEntity6=new FeedingEntity("HSK2087908923HS05","HK3","OAH897NH55667788");
        FeedingEntity feedingEntity7=new FeedingEntity("HSK2087908923HS06","HK3","OAH897NH55667788");
        FeedingEntity feedingEntity8=new FeedingEntity("HSK2087908923HS07","HK3","OAH897NH55667788");

        feedingEntityList.add(feedingEntity1);
        feedingEntityList.add(feedingEntity2);
        feedingEntityList.add(feedingEntity3);
        feedingEntityList.add(feedingEntity4);
        feedingEntityList.add(feedingEntity5);
        feedingEntityList.add(feedingEntity6);
        feedingEntityList.add(feedingEntity7);
        feedingEntityList.add(feedingEntity8);
    }


}
