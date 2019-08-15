package com.njx.mvvmhabit.ui.produce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentSmtSearchBinding;
import com.njx.mvvmhabit.databinding.FragmentSteelSearchBinding;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTSearchViewModel;
import com.njx.mvvmhabit.ui.produce.viewmodel.SteelSearchViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

public class StealSearchFragment extends BaseFragment<FragmentSteelSearchBinding, SteelSearchViewModel> {
    private String[] invoiceList = {"上机台", "下机台"};
    private String[] stationList = {"ck1", "ck2", "ck3", "ck4", "ck5", "ck6", "ck7", "ck8"};

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
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, invoiceList);
        binding.destDepotAuto.setAdapter(arrayAdapter);


//        ArrayAdapter stationAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, stationList);
//        binding.steelStationAuto.setAdapter(stationAdapter);

        binding.destDepotAuto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.destDepotAuto.getText().equals("上机台")) {
                    binding.orderLiner.setVisibility(View.VISIBLE);
                } else if (binding.destDepotAuto.getText().equals("下机台")) {
                    binding.orderLiner.setVisibility(View.GONE);
                }
            }
        });
    }

}
