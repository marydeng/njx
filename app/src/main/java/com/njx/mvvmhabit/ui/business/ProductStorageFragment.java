package com.njx.mvvmhabit.ui.business;

import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentProductStorageBinding;
import com.njx.mvvmhabit.entity.BusiLocationEntity;
import com.njx.mvvmhabit.entity.BusinessStorageRecordEntity;
import com.njx.mvvmhabit.entity.TransferPartRecordEntity;
import com.njx.mvvmhabit.ui.base.fragment.BaseScanFragment;
import com.njx.mvvmhabit.ui.business.viewmodel.ProductStorageViewModel;
import com.njx.mvvmhabit.ui.widget.spinner.bean.SpinnearBean;
import com.njx.mvvmhabit.ui.widget.spinner.listener.OnSpinnerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.utils.MaterialDialogUtils;

public class ProductStorageFragment extends BaseScanFragment<FragmentProductStorageBinding, ProductStorageViewModel> {
    private List<BusinessStorageRecordEntity> recordList;
    private BaseQuickAdapter adapter;

    private boolean isShowErrorDialog = false;

    private ArrayList<SpinnearBean> locationSpinList;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_product_storage;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initToolBar();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        recordList = new ArrayList<>();
        adapter = new BaseQuickAdapter<BusinessStorageRecordEntity, BaseViewHolder>(R.layout.item_business_storage, recordList) {
            @Override
            protected void convert(BaseViewHolder helper, final BusinessStorageRecordEntity item) {
                helper.setText(R.id.location_txt, item.getProductLocation());
                helper.setText(R.id.box_txt, item.getBoxBarcode());
                helper.setText(R.id.num_txt, item.getNum());
                if (!TextUtils.isEmpty(item.getBoxBarcode()) && item.getBoxBarcode().length() > 12) {
                    String materialId = item.getBoxBarcode().substring(7, 12);
                    helper.setText(R.id.material_id, materialId);
                }

//                helper.setOnClickListener(R.id.storage_item, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Bundle bundle = new Bundle();
//                        bundle.putString(TransferDetailFragment.Extra_Entity, new Gson().toJson(item));
//                        startContainerActivity(TransferDetailFragment.class.getCanonicalName(), bundle);

//                    }
//                });
            }
        };
        binding.recyclerview.setAdapter(adapter);
        viewModel.queryRecordList();

        locationSpinList = new ArrayList<>();

        binding.locationSpinner.setData(locationSpinList);
        binding.locationSpinner.setHint("请输入库位");
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
        viewModel.uc.listChangeEvent.observe(this, new Observer<List<BusinessStorageRecordEntity>>() {
            @Override
            public void onChanged(@Nullable List<BusinessStorageRecordEntity> dataList) {
                recordList.clear();
                if (null != dataList && dataList.size() > 0) {
                    recordList.addAll(dataList);
                }
                adapter.notifyDataSetChanged();
            }
        });

        viewModel.uc.locationChangeEvent.observe(this, new Observer<List<BusiLocationEntity>>() {
            @Override
            public void onChanged(@Nullable List<BusiLocationEntity> dataList) {
                locationSpinList.clear();
                if (dataList != null && dataList.size() > 0) {
                    for (BusiLocationEntity entity : dataList) {
                        SpinnearBean sb = new SpinnearBean(entity.getProductLocationNumber(), entity.getProductLocationNumber());
                        locationSpinList.add(sb);
                    }
                }
                binding.locationSpinner.setData(locationSpinList);
            }
        });

        viewModel.uc.onCommitSuccess.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                binding.boxEdit.requestFocus();
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
            viewModel.boxId.set(scanCode);
            viewModel.onCommit.execute();
        }
    }
}
