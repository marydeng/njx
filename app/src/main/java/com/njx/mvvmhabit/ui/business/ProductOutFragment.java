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
import com.njx.mvvmhabit.databinding.FragmentOutOperateBinding;
import com.njx.mvvmhabit.databinding.FragmentProductOutBinding;
import com.njx.mvvmhabit.entity.BusinessOutRecordEntity;
import com.njx.mvvmhabit.entity.TransferPartRecordEntity;
import com.njx.mvvmhabit.ui.base.fragment.BaseScanFragment;
import com.njx.mvvmhabit.ui.business.viewmodel.ProductOutViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.utils.MaterialDialogUtils;

public class ProductOutFragment extends BaseScanFragment<FragmentProductOutBinding, ProductOutViewModel> {
    public static final String Extra_Order = "ProductOutFragment.order";
    private String order;
    private List<BusinessOutRecordEntity> recordList;
    private BaseQuickAdapter adapter;

    private boolean isShowErrorDialog = false;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_product_out;
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
    public void initData() {
        super.initData();
        viewModel.orderId.set(order);
        viewModel.initToolBar();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        recordList = new ArrayList<>();
        adapter = new BaseQuickAdapter<BusinessOutRecordEntity, BaseViewHolder>(R.layout.item_smt_operate, recordList) {
            @Override
            protected void convert(BaseViewHolder helper, final BusinessOutRecordEntity item) {
                helper.setText(R.id.mater_gun, item.getBoxBarcode());
                if (!TextUtils.isEmpty(item.getBoxBarcode()) && item.getBoxBarcode().length() > 12) {
                    String materialId = item.getBoxBarcode().substring(7, 12);
                    helper.setText(R.id.mater_roll, materialId);
                }
                helper.setText(R.id.mater_station, String.valueOf(item.getNum()));
                helper.setOnClickListener(R.id.storage_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Bundle bundle = new Bundle();
//                        bundle.putString(TransferDetailFragment.Extra_Entity, new Gson().toJson(item));
//                        startContainerActivity(TransferDetailFragment.class.getCanonicalName(), bundle);
                        //Todo
                    }
                });
            }
        };
        binding.recyclerview.setAdapter(adapter);
        viewModel.queryRecordList();

    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.listChangeEvent.observe(this, new Observer<List<BusinessOutRecordEntity>>() {
            @Override
            public void onChanged(@Nullable List<BusinessOutRecordEntity> dataList) {
                recordList.clear();
                if (null != dataList && dataList.size() > 0) {
                    recordList.addAll(dataList);
                }
                adapter.notifyDataSetChanged();
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
