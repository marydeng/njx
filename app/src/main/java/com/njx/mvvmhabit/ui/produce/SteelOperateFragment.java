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
import com.njx.mvvmhabit.entity.FeedingEntity;
import com.njx.mvvmhabit.entity.SteelEntity;
import com.njx.mvvmhabit.ui.base.fragment.BaseScanFragment;
import com.njx.mvvmhabit.ui.produce.adapter.SMTAdapter;
import com.njx.mvvmhabit.ui.produce.adapter.SteelAdapter;
import com.njx.mvvmhabit.ui.produce.viewmodel.SteelOperateViewModel;

import java.util.ArrayList;
import java.util.List;

public class SteelOperateFragment extends BaseScanFragment<FragmentSteelOperateBinding, SteelOperateViewModel> {
    public static final String Extra_Station_Id = "SteelOperateFragment.station.id";
    public static final String Extra_Steel_Type = "SMTOperateFragment.steel.type";
    public static final String Extra_PART_NO = "SMTOperateFragment.part.no";
    private List<SteelEntity> steelEntityList;
    private String stationId = "";
    private String type = "";
    private String partNO = "";

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
            stationId = bundle.getString(Extra_Station_Id);
            type = bundle.getString(Extra_Steel_Type);
            partNO = bundle.getString(Extra_PART_NO);
        }
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.stationId.set(stationId);
        viewModel.type.set(type);
        viewModel.partNO = partNO;

        viewModel.initToolBar();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerview.setLayoutManager(linearLayoutManager);
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

        viewModel.uc.steelListEvent.observe(this, new Observer<List<SteelEntity>>() {
            @Override
            public void onChanged(@Nullable List<SteelEntity> steelEntities) {
                steelEntityList = steelEntities;
                binding.recyclerview.notifyAll();
            }
        });


    }

    @Override
    protected void onGetScanCode(String scanCode) {
        if (viewModel.type.get().equals(scanCode)) {
            if (type.equals("上机台")) {
                viewModel.upLineSteel();
            } else if (type.equals("下机台")) {
                viewModel.downlineSteel();
            }
        }
    }

    public void onCommit(View view){
        if (type.equals("上机台")) {
            viewModel.upLineSteel();
        } else if (type.equals("下机台")) {
            viewModel.downlineSteel();
        }
    }

    private void createData() {
        steelEntityList = new ArrayList<>();

        SteelEntity steelEntity1 = new SteelEntity("钢板", "M-2-14", "L5");
        SteelEntity steelEntity2 = new SteelEntity("钢板", "M-2-15", "L5");
        SteelEntity steelEntity3 = new SteelEntity("钢板", "M-2-16", "L5");
        SteelEntity steelEntity4 = new SteelEntity("钢板", "M-2-17", "L5");
        SteelEntity steelEntity5 = new SteelEntity("钢板", "M-2-18", "L5");
        SteelEntity steelEntity6 = new SteelEntity("钢板", "M-2-19", "L5");
        SteelEntity steelEntity7 = new SteelEntity("钢板", "M-2-20", "L5");
        SteelEntity steelEntity8 = new SteelEntity("钢板", "M-2-21", "L5");

        steelEntityList.add(steelEntity1);
        steelEntityList.add(steelEntity2);
        steelEntityList.add(steelEntity3);
        steelEntityList.add(steelEntity4);
        steelEntityList.add(steelEntity5);
        steelEntityList.add(steelEntity6);
        steelEntityList.add(steelEntity7);
        steelEntityList.add(steelEntity8);


    }


}
