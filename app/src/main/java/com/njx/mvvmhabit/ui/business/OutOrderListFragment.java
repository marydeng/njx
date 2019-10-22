package com.njx.mvvmhabit.ui.business;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.databinding.FragmentBackDepotListBinding;
import com.njx.mvvmhabit.databinding.FragmentBusiOutOrderBinding;
import com.njx.mvvmhabit.entity.BackDepotEntity;
import com.njx.mvvmhabit.entity.BusiOrderResultEntity;
import com.njx.mvvmhabit.ui.business.viewmodel.OutOrderListViewModel;
import com.njx.mvvmhabit.ui.depot.viewmodel.BackDepotListViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.bus.Messenger;

public class OutOrderListFragment extends BaseFragment<FragmentBusiOutOrderBinding, OutOrderListViewModel> {

    private List<BusiOrderResultEntity.RowsBean> depotEntityList;
    private BaseQuickAdapter<BusiOrderResultEntity.RowsBean, BaseViewHolder> adapter;
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_busi_out_order;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initToolBar();

        depotEntityList=new ArrayList<>();
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter=new BaseQuickAdapter<BusiOrderResultEntity.RowsBean, BaseViewHolder>(android.R.layout.simple_dropdown_item_1line,depotEntityList) {
            @Override
            protected void convert(BaseViewHolder helper, final BusiOrderResultEntity.RowsBean item) {
                helper.setText(android.R.id.text1,item.getOutNumber());
                helper.setOnClickListener(android.R.id.text1, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Messenger.getDefault().send(item.getOutNumber(), Constant.TOKEN__Receive_Busi_Order);
                        getActivity().finish();
                    }
                });
            }
        };
        binding.recyclerview.setAdapter(adapter);

        viewModel.queryDeptList();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.uc.onListDataChange.observe(this, new Observer<List<BusiOrderResultEntity.RowsBean>>() {
            @Override
            public void onChanged(@Nullable List<BusiOrderResultEntity.RowsBean> dataList) {
                if (!viewModel.isLoad) {
                    depotEntityList.clear();
                }
                if (dataList != null && dataList.size() > 0) {
                    depotEntityList.addAll(dataList);
                }

                if (viewModel.isLoad) {
                    viewModel.isLoad = false;
                    binding.twinklingRefreshLayout.finishLoadmore();
                }else{
                    binding.twinklingRefreshLayout.finishRefreshing();
                }

                if(depotEntityList.size()>=viewModel.totalSize){
                    binding.twinklingRefreshLayout.setEnableLoadmore(false);
                } else {
                    binding.twinklingRefreshLayout.setEnableLoadmore(true);
                }

                viewModel.mStartPage++;
                adapter.notifyDataSetChanged();

            }
        });
    }
}
