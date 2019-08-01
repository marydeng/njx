package com.njx.mvvmhabit.ui.depot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentStorageSearchBinding;
import com.njx.mvvmhabit.ui.depot.viewmodel.StorageSearchViewModel;
import com.njx.mvvmhabit.utils.ScannerInterface;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.utils.KLog;

public class StorageSearchFragment extends BaseFragment<FragmentStorageSearchBinding, StorageSearchViewModel> {
    private String[] invoiceList={"A78458294","A78458295","A78458296","A78458297","A78458298","A78458299","A78458300","A78458301"};
    private String[] deptList={"ck1","ck2","ck3","ck4","ck5","ck6","ck7","ck8"};
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_storage_search;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initToolBar();
        ArrayAdapter arrayAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_dropdown_item_1line,invoiceList);
        binding.invoiceAuto.setAdapter(arrayAdapter);

        ArrayAdapter deptAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_dropdown_item_1line,deptList);
        binding.deptAuto.setAdapter(deptAdapter);
        KLog.d("initData");
    }

    @Override
    public void onResume() {
        super.onResume();
        KLog.d("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        KLog.d("onPause");
        ScannerInterface.getInstance().scan_stop();
    }

    @Override
    public void onStop() {
        super.onStop();
        KLog.d("onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KLog.d("onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        KLog.d("onDestroyView");
    }
}
