package com.njx.mvvmhabit.ui.produce;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentSteelOperateBinding;
import com.njx.mvvmhabit.entity.SteelEntity;
import com.njx.mvvmhabit.ui.base.fragment.BaseScanFragment;
import com.njx.mvvmhabit.ui.produce.adapter.SteelAdapter;
import com.njx.mvvmhabit.ui.produce.viewmodel.SteelOperateViewModel;

import java.util.ArrayList;
import java.util.List;

public class SteelOperateFragment extends BaseScanFragment<FragmentSteelOperateBinding, SteelOperateViewModel> {
    public static final String Extra_Line_Name = "SteelOperateFragment.station.id";
    public static final String Extra_Steel_Type = "SMTOperateFragment.steel.type";
    public static final String Extra_PART_NO = "SMTOperateFragment.part.no";
    public static final String Extra_Work_Id = "SMTOperateFragment.work.id";
    private List<SteelEntity> steelEntityList;
    private String lineName = "";
    private String type = "";
    private String partNO = "";
    private String workId = "";

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_steel_operate;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initParam() {
        super.initParam();
        Bundle bundle = getArguments();
        if (bundle != null) {
            lineName = bundle.getString(Extra_Line_Name);
            type = bundle.getString(Extra_Steel_Type);
            partNO = bundle.getString(Extra_PART_NO);
            workId = bundle.getString(Extra_Work_Id);
        }
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.stationId.set(lineName);
        viewModel.type.set(type);
        viewModel.partNO = partNO;
        viewModel.workId = workId;

        viewModel.initToolBar();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        steelEntityList = new ArrayList<>();
        SteelAdapter steelAdapter = new SteelAdapter(getContext(), steelEntityList);
        steelAdapter.setOnItemClickListener(new SteelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(SteelDetailFragment.Extra_Entity, steelEntityList.get(position));
                startContainerActivity(SteelDetailFragment.class.getCanonicalName(), bundle);
            }
        });
        binding.recyclerview.setAdapter(steelAdapter);


        viewModel.getSteelList();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.steelListEvent.observe(this, new Observer<List<SteelEntity>>() {
            @Override
            public void onChanged(@Nullable List<SteelEntity> steelEntities) {
                if (steelEntities == null) {
                    steelEntityList = new ArrayList<>();
                } else {
                    steelEntityList = steelEntities;
                }
                SteelAdapter steelAdapter = new SteelAdapter(getContext(), steelEntityList);
                steelAdapter.setOnItemClickListener(new SteelAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, View view) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(SteelDetailFragment.Extra_Entity, steelEntityList.get(position));
                        startContainerActivity(SteelDetailFragment.class.getCanonicalName(), bundle);
                    }
                });
                binding.recyclerview.setAdapter(steelAdapter);
            }
        });
    }

    @Override
    protected void onGetScanCode(String scanCode) {
        binding.steelCodeEdit.setText(scanCode);
        if (type.equals("上机台")) {
            viewModel.upLineSteel();
        } else if (type.equals("下机台")) {
            viewModel.downlineSteel();
        }

    }


}
