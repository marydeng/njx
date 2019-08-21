package com.njx.mvvmhabit.ui.produce;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentSteelSearchBinding;
import com.njx.mvvmhabit.ui.produce.viewmodel.SteelSearchViewModel;
import com.njx.mvvmhabit.ui.widget.spinner.bean.SpinnearBean;
import com.njx.mvvmhabit.ui.widget.spinner.listener.OnSpinnerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseFragment;

public class StealSearchFragment extends BaseFragment<FragmentSteelSearchBinding, SteelSearchViewModel> {
    private ArrayList<SpinnearBean> typeList;
    private ArrayList<SpinnearBean> lineList;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_steel_search;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initToolBar();
        typeList = new ArrayList<>();
        SpinnearBean sp1 = new SpinnearBean("上机台", "1");
        SpinnearBean sp2 = new SpinnearBean("下机台", "2");
        typeList.add(sp1);
        typeList.add(sp2);

        binding.steelTypeSpinner.setData(typeList);
        binding.steelTypeSpinner.setSelectedIndexAndText(0);
        viewModel.type.set("上机台");
        binding.steelTypeSpinner.setOnSpinnerItemClickListener(new OnSpinnerItemClickListener() {
            @Override
            public void OnFinished(int position) {
                if (position == 0) {
                    binding.orderLiner.setVisibility(View.VISIBLE);
                    binding.stationLinear.setVisibility(View.GONE);
                    viewModel.type.set("上机台");
                } else {
                    binding.orderLiner.setVisibility(View.GONE);
                    binding.stationLinear.setVisibility(View.VISIBLE);
                    viewModel.type.set("下机台");
                    if (lineList == null || lineList.size() == 0) {
                        viewModel.queryLine();
                    }
                }
            }
        });

        lineList = new ArrayList<>();

        binding.lineTypeSpinner.setData(lineList);
        binding.lineTypeSpinner.setHint("请输入线体");
        binding.lineTypeSpinner.setOnSpinnerItemClickListener(new OnSpinnerItemClickListener() {
            @Override
            public void OnFinished(int position) {
                viewModel.stationID.set(lineList.get(position).getParaName());
            }
        });
//        binding.lineTypeSpinner.setOnSpinnerClickListener(new OnSpinnerClickListener() {
//            @Override
//            public void OnFinished() {
//                if (lineList == null || lineList.size() == 0) {
//                    viewModel.queryLine();
//                } else {
//                    binding.lineTypeSpinner.PopupListDialog();
//                }
//            }
//        });


    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.showLineList.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> lineNameList) {
                lineList.clear();
                for (String lineName : lineNameList) {
                    SpinnearBean spinnearBean = new SpinnearBean(lineName, lineName);
                    lineList.add(spinnearBean);
                }
                binding.lineTypeSpinner.setData(lineList);
            }
        });
    }
}
