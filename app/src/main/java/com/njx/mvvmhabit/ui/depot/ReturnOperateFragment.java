package com.njx.mvvmhabit.ui.depot;

import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentBackOperateBinding;
import com.njx.mvvmhabit.databinding.FragmentReturnOperateBinding;
import com.njx.mvvmhabit.entity.ReturnPartRecordEntity;
import com.njx.mvvmhabit.entity.StorageEntity;
import com.njx.mvvmhabit.entity.TransferPartRecordEntity;
import com.njx.mvvmhabit.ui.base.fragment.BaseScanFragment;
import com.njx.mvvmhabit.ui.depot.adapter.StorageAdapter;
import com.njx.mvvmhabit.ui.depot.viewmodel.BackOperateViewModel;
import com.njx.mvvmhabit.ui.depot.viewmodel.ReturnOperateViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.utils.MaterialDialogUtils;

public class ReturnOperateFragment extends BaseScanFragment<FragmentReturnOperateBinding, ReturnOperateViewModel> {
    public static final String Extra_Order = "ReturnOperateFragment.order";
    private String order = "";

    private List<ReturnPartRecordEntity> returnPartRecordEntityList;
    private BaseQuickAdapter<ReturnPartRecordEntity, BaseViewHolder> adapter;


    private boolean isShowErrorDialog = false;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_return_operate;
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
            order = bundle.getString(Extra_Order);
        }
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.listChangeEvent.observe(this, new Observer<List<ReturnPartRecordEntity>>() {
            @Override
            public void onChanged(@Nullable List<ReturnPartRecordEntity> dataList) {
                returnPartRecordEntityList.clear();
                if (dataList != null && dataList.size() > 0) {
                    returnPartRecordEntityList.addAll(dataList);
                }
                adapter.notifyDataSetChanged();
            }
        });
        viewModel.uc.onCommitSuccess.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                binding.rollEdit.requestFocus();
            }
        });

        viewModel.uc.showErrorDialog.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                isShowErrorDialog = true;
                MaterialDialogUtils.showErrorDialog(getContext(), "报警", s, new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        isShowErrorDialog = false;
                    }
                });
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.orderTxt.set(order);
        viewModel.initToolBar();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        returnPartRecordEntityList = new ArrayList<>();
        adapter = new BaseQuickAdapter<ReturnPartRecordEntity, BaseViewHolder>(R.layout.item_storage_operate, returnPartRecordEntityList) {
            @Override
            protected void convert(BaseViewHolder helper, final ReturnPartRecordEntity item) {
                helper.setText(R.id.mater_txt, item.getMaterialNum());

                StringBuilder sb = new StringBuilder();
                sb.append("退库需求量:");
                sb.append(item.getReturnQuantity());
                sb.append("\n");
                sb.append("已退库量：");
                sb.append(item.getQuantity());
//                sb.append("\n");
//                sb.append("未退库量：");
//                sb.append(item.getQuantity() - item.getReturnQuantity());
                helper.setText(R.id.num_txt, sb.toString());

                helper.setOnClickListener(R.id.storage_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString(ReturnDetailFragment.Extra_Entity, new Gson().toJson(item));
                        startContainerActivity(ReturnDetailFragment.class.getCanonicalName(), bundle);
                    }
                });
            }
        };
        binding.recyclerview.setAdapter(adapter);
        viewModel.queryRecordList();


    }

    @Override
    protected void onGetScanCode(String scanCode) {
        super.onGetScanCode(scanCode);
        if (!isShowErrorDialog) {
            viewModel.rollTxt.set(scanCode);
            viewModel.onCommit.execute();
        }
    }
}
