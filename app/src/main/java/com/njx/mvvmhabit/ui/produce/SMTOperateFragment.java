package com.njx.mvvmhabit.ui.produce;

import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.databinding.FragmentSmtOperateBinding;
import com.njx.mvvmhabit.entity.FeedingEntity;
import com.njx.mvvmhabit.entity.SMTRecordEntity;
import com.njx.mvvmhabit.ui.base.fragment.BaseScanFragment;
import com.njx.mvvmhabit.ui.produce.adapter.SMTAdapter;
import com.njx.mvvmhabit.ui.produce.viewmodel.SMTOperateViewModel;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.utils.MaterialDialogUtils;

public class SMTOperateFragment extends BaseScanFragment<FragmentSmtOperateBinding, SMTOperateViewModel> {
    public static final String Extra_order_id = "SMTOperateFragment.order.id";
    public static final String Extra_smt_type = "SMTOperateFragment.smt.type";
    private List<FeedingEntity> feedingEntityList;
    private String orderID = "";
    private String smtType = "";
    private List<SMTRecordEntity> recordEntityList;
    private boolean isShowErrorDialog = false;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_smt_operate;
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
            orderID = bundle.getString(Extra_order_id);
            smtType = bundle.getString(Extra_smt_type);
        }
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.orderId.set(orderID);
        viewModel.smtType.set(smtType);

        viewModel.initToolBar();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        recordEntityList = new ArrayList<>();
        SMTAdapter smtAdapter = new SMTAdapter(getContext(), recordEntityList);
        smtAdapter.setOnItemClickListener(new SMTAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                //Todo 待接口提供
//                Bundle bundle = new Bundle();
//                bundle.putParcelable(SMTDetailFragment.Extra_Entity, feedingEntityList.get(position));
//                startContainerActivity(SMTDetailFragment.class.getCanonicalName(), bundle);
            }
        });
        binding.recyclerview.setAdapter(smtAdapter);

        binding.gunScanEdit.requestFocus();

        viewModel.queryRecordList();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.uc.listChangeEvent.observe(this, new Observer<List<SMTRecordEntity>>() {
            @Override
            public void onChanged(@Nullable List<SMTRecordEntity> dataList) {
                if (dataList == null) {
                    recordEntityList = new ArrayList<>();
                } else {
                    recordEntityList = dataList;
                }

                SMTAdapter smtAdapter = new SMTAdapter(getContext(), recordEntityList);
                smtAdapter.setOnItemClickListener(new SMTAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, View view) {
                        //Todo 待接口提供
//                        Bundle bundle = new Bundle();
//                        bundle.putParcelable(SMTDetailFragment.Extra_Entity, feedingEntityList.get(position));
//                        startContainerActivity(SMTDetailFragment.class.getCanonicalName(), bundle);
                    }
                });
                binding.recyclerview.setAdapter(smtAdapter);
            }
        });

        viewModel.uc.gunEdit.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                binding.gunScanEdit.requestFocus();
            }
        });
        viewModel.uc.rollEdit.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                binding.rollScanEdit.requestFocus();
            }
        });
        viewModel.uc.stationEdit.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                binding.stationScanEdit.requestFocus();
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
            if (TextUtils.isEmpty(viewModel.gunTxt.get())) {
                if (TextUtils.isEmpty(scanCode) || scanCode.length() > 18) {
                    showErrorDialog("料枪扫错");
                    return;
                }
                viewModel.checkStatus(scanCode, "", "", SMTOperateViewModel.SCANTYPE.gun);
                viewModel.gunTxt.set(scanCode);
                binding.rollScanEdit.requestFocus();
            } else if (TextUtils.isEmpty(viewModel.rollTxt.get())) {
                if (TextUtils.isEmpty(scanCode) || scanCode.length() < 18) {
                    showErrorDialog("料卷扫错");
                    return;
                }
                viewModel.checkStatus("", scanCode, "", SMTOperateViewModel.SCANTYPE.roll);
                viewModel.rollTxt.set(scanCode);
                binding.stationScanEdit.requestFocus();
            } else {
                if (TextUtils.isEmpty(scanCode) || scanCode.length() > 14) {
                    showErrorDialog("料站扫错");
                    return;
                }
                viewModel.stationTxt.set(scanCode);
                viewModel.onCommit.execute();
            }
        }
    }

    private void showErrorDialog(String msg) {
        isShowErrorDialog = true;
        MaterialDialog.Builder builder = MaterialDialogUtils.showBasicDialog(getContext(), "报警", msg);
        builder.show().setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                isShowErrorDialog = false;
            }
        });
    }


}
