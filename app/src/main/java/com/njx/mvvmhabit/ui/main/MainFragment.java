package com.njx.mvvmhabit.ui.main;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.njx.mvvmhabit.R;
import com.njx.mvvmhabit.app.AppApplication;
import com.njx.mvvmhabit.data.source.http.service.DemoApiService;
import com.njx.mvvmhabit.databinding.FragmentMainBinding;
import com.njx.mvvmhabit.entity.UserEntity;
import com.njx.mvvmhabit.ui.business.ProductOutFragment;
import com.njx.mvvmhabit.ui.business.ProductOutSearchFragment;
import com.njx.mvvmhabit.ui.business.ProductStorageFragment;
import com.njx.mvvmhabit.ui.depot.BackSearchFragment;
import com.njx.mvvmhabit.ui.depot.OutSearchFragment;
import com.njx.mvvmhabit.ui.depot.ReturnSearchFragment;
import com.njx.mvvmhabit.ui.depot.StorageSearchFragment;
import com.njx.mvvmhabit.ui.depot.TransferSearchFragment;
import com.njx.mvvmhabit.ui.main.adapter.MenuAdapter;
import com.njx.mvvmhabit.ui.main.bean.MenuBean;
import com.njx.mvvmhabit.ui.main.viewmodel.MainViewModel;
import com.njx.mvvmhabit.ui.produce.FQCConfigFragment;
import com.njx.mvvmhabit.ui.produce.FQCScanFragment;
import com.njx.mvvmhabit.ui.produce.SMTClearFragment;
import com.njx.mvvmhabit.ui.produce.SMTInputConfigFragment;
import com.njx.mvvmhabit.ui.produce.SMTInputScanFragment;
import com.njx.mvvmhabit.ui.produce.SMTSearchFragment;
import com.njx.mvvmhabit.ui.produce.StealSearchFragment;
import com.njx.mvvmhabit.ui.quality.QualitySearchFragment;
import com.njx.mvvmhabit.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.utils.RxUtils;

public class MainFragment extends BaseFragment<FragmentMainBinding, MainViewModel> {

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_main;
    }

    @Override
    public int initVariableId() {
        return com.njx.mvvmhabit.BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.initToolBar();
        viewModel.getMenuList();


        viewModel.uc.menuListEvent.observe(this, new Observer<List<MenuBean>>() {
            @Override
            public void onChanged(@Nullable final List<MenuBean> menuBeans) {
                MenuAdapter menuAdapter = new MenuAdapter(menuBeans, getContext());
                binding.gvHome.setAdapter(menuAdapter);
                binding.gvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (menuBeans.get(position).getName().equals("入库")) {
                            startContainerActivity(StorageSearchFragment.class.getCanonicalName());
                        } else if (menuBeans.get(position).getName().equals("出库")) {
                            startContainerActivity(OutSearchFragment.class.getCanonicalName());
                        } else if (menuBeans.get(position).getName().equals("退库")) {
                            startContainerActivity(BackSearchFragment.class.getCanonicalName());
                        } else if (menuBeans.get(position).getName().equals("退货")) {
                            startContainerActivity(ReturnSearchFragment.class.getCanonicalName());
                        } else if (menuBeans.get(position).getName().equals("调拨")) {
                            startContainerActivity(TransferSearchFragment.class.getCanonicalName());
                        } else if (menuBeans.get(position).getName().equals("上料")) {
                            startContainerActivity(SMTSearchFragment.class.getCanonicalName());
                        } else if (menuBeans.get(position).getName().equals("对料")) {
                            startContainerActivity(QualitySearchFragment.class.getCanonicalName());
                        } else if (menuBeans.get(position).getName().equals("钢板刮刀")) {
                            startContainerActivity(StealSearchFragment.class.getCanonicalName());
                        } else if (menuBeans.get(position).getName().equals("清除")) {
                            startContainerActivity(SMTClearFragment.class.getCanonicalName());
                        } else if (menuBeans.get(position).getName().equals("FQC")) {
                            startContainerActivity(FQCScanFragment.class.getCanonicalName());
                        } else if (menuBeans.get(position).getName().equals("SMTInput")) {
                            startContainerActivity(SMTInputConfigFragment.class.getCanonicalName());
                        } else if (menuBeans.get(position).getName().equals("成品入库")) {
                            startContainerActivity(ProductStorageFragment.class.getCanonicalName());
                        } else if (menuBeans.get(position).getName().equals("成品出库")) {
                            startContainerActivity(ProductOutSearchFragment.class.getCanonicalName());
                        }
                    }
                });
            }
        });


    }
}
