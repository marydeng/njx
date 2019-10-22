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
import com.njx.mvvmhabit.databinding.FragmentTransferOperateBinding;
import com.njx.mvvmhabit.entity.StorageEntity;
import com.njx.mvvmhabit.entity.TransferPartRecordEntity;
import com.njx.mvvmhabit.ui.base.fragment.BaseScanFragment;
import com.njx.mvvmhabit.ui.depot.adapter.StorageAdapter;
import com.njx.mvvmhabit.ui.depot.viewmodel.TransferOperateViewModel;
import com.njx.mvvmhabit.ui.widget.spinner.bean.SpinnearBean;
import com.njx.mvvmhabit.ui.widget.spinner.listener.OnSpinnerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.utils.MaterialDialogUtils;

public class TransferOperateFragment extends BaseScanFragment<FragmentTransferOperateBinding, TransferOperateViewModel> {
    public static final String Extra_Transfer_Order = "TransferOperateFragment.order";
    public static final String Extra_Transfer_dept = "TransferOperateFragment.depot";
    private List<StorageEntity> storageEntityList;
    private String order = "";
    private String depot = "";

    private List<TransferPartRecordEntity> transferPartRecordEntityList;
    private BaseQuickAdapter adapter;

    private boolean isShowErrorDialog = false;

    private ArrayList<SpinnearBean> locationSpinList;

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
        Bundle bundle = getArguments();
        if (bundle != null) {
            order = bundle.getString(Extra_Transfer_Order);
            depot = bundle.getString(Extra_Transfer_dept);
        }
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.order.set(order);
        viewModel.depot.set(depot);

        viewModel.initToolBar();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerview.setLayoutManager(linearLayoutManager);
//        createData();
//        StorageAdapter storageAdapter = new StorageAdapter(getContext(), storageEntityList);
//        storageAdapter.setOnItemClickListener(new StorageAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, View view) {
//                Bundle bundle = new Bundle();
//                bundle.putParcelable(StorageDetailFragment.Extra_Entity, storageEntityList.get(position));
//                startContainerActivity(StorageDetailFragment.class.getCanonicalName(), bundle);
//            }
//        });
        transferPartRecordEntityList = new ArrayList<>();
        adapter = new BaseQuickAdapter<TransferPartRecordEntity, BaseViewHolder>(R.layout.item_storage_operate, transferPartRecordEntityList) {
            @Override
            protected void convert(BaseViewHolder helper, final TransferPartRecordEntity item) {
                helper.setText(R.id.mater_txt, item.getMaterialNum());

                StringBuilder sb = new StringBuilder();
                sb.append("目标量:");
                sb.append(item.getQuantity());
                sb.append("\n");
                sb.append("已转量：");
                sb.append(item.getTransferredQuantity());
//                sb.append("\n");
//                sb.append("未转量：");
//                sb.append(item.getQuantity() - item.getTransferredQuantity());
                helper.setText(R.id.num_txt, sb.toString());

                helper.setOnClickListener(R.id.storage_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString(TransferDetailFragment.Extra_Entity, new Gson().toJson(item));
                        startContainerActivity(TransferDetailFragment.class.getCanonicalName(), bundle);
                    }
                });
            }
        };
        binding.recyclerview.setAdapter(adapter);
        viewModel.queryRecordList();

        locationSpinList = new ArrayList<>();

        binding.locationSpinner.setData(locationSpinList);
        binding.locationSpinner.setHint("请输入储位");
        binding.locationSpinner.setOnSpinnerItemClickListener(new OnSpinnerItemClickListener() {
            @Override
            public void OnFinished(int position) {
                viewModel.location = locationSpinList.get(position).getParaValue();
            }
        });
        viewModel.queryLocationList();



    }


    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.listChangeEvent.observe(this, new Observer<List<TransferPartRecordEntity>>() {
            @Override
            public void onChanged(@Nullable List<TransferPartRecordEntity> dataList) {
                transferPartRecordEntityList.clear();
                if (null != dataList && dataList.size() > 0) {
                    transferPartRecordEntityList.addAll(dataList);
                }
                adapter.notifyDataSetChanged();
            }
        });

        viewModel.uc.locationChangeEvent.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> dataList) {
                locationSpinList.clear();
                if (dataList != null && dataList.size() > 0) {
                    for (String s : dataList) {
                        SpinnearBean sb = new SpinnearBean(s, s);
                        locationSpinList.add(sb);
                    }
                }
                binding.locationSpinner.setData(locationSpinList);
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
    protected void onGetScanCode(String scanCode) {
        super.onGetScanCode(scanCode);
        if (!isShowErrorDialog) {
            viewModel.rollTxt.set(scanCode);
            viewModel.onCommit.execute();
        }
    }
}
