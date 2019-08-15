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
import com.njx.mvvmhabit.ui.depot.BackSearchFragment;
import com.njx.mvvmhabit.ui.depot.OutSearchFragment;
import com.njx.mvvmhabit.ui.depot.ReturnSearchFragment;
import com.njx.mvvmhabit.ui.depot.StorageSearchFragment;
import com.njx.mvvmhabit.ui.depot.TransferSearchFragment;
import com.njx.mvvmhabit.ui.main.adapter.MenuAdapter;
import com.njx.mvvmhabit.ui.main.bean.MenuBean;
import com.njx.mvvmhabit.ui.main.viewmodel.MainViewModel;
import com.njx.mvvmhabit.ui.produce.SMTClearFragment;
import com.njx.mvvmhabit.ui.produce.SMTSearchFragment;
import com.njx.mvvmhabit.ui.produce.StealSearchFragment;
import com.njx.mvvmhabit.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.http.BaseResponse;
import me.goldze.mvvmhabit.utils.RxUtils;

public class MainFragment extends BaseFragment<FragmentMainBinding, MainViewModel> {
    private String[] menus={"入库","出库","退库","退货","调拨","盘点","上料","对料","钢板刮刀","清除","成品入库"};
    private int[]   icons={R.mipmap.depot_in,R.mipmap.depot_out,R.mipmap.depot_back,R.mipmap.depot_purchase_return,R.mipmap.depot_transfer,R.mipmap.depot_check,R.mipmap.produce_consume,R.mipmap.produce_compare,R.mipmap.produce_gangban,R.mipmap.produce_clear,R.mipmap.trade_depot_in};
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

//        List<MenuBean> menuItemList=new ArrayList<>();
//        for (int i = 0; i < menus.length; i++) {
//            MenuBean menuBean=new MenuBean(icons[i],menus[i],i) ;
//            menuItemList.add(menuBean);
//        }

        viewModel.uc.menuListEvent.observe(this, new Observer<List<MenuBean>>() {
            @Override
            public void onChanged(@Nullable List<MenuBean> menuBeans) {
                MenuAdapter menuAdapter=new MenuAdapter(menuBeans,getContext());
                binding.gvHome.setAdapter(menuAdapter);
                binding.gvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (menus[position].equals("入库")) {
                            startContainerActivity(StorageSearchFragment.class.getCanonicalName());
                        }else if(menus[position].equals("出库")){
                            startContainerActivity(OutSearchFragment.class.getCanonicalName());
                        }else if(menus[position].equals("退库")){
                            startContainerActivity(BackSearchFragment.class.getCanonicalName());
                        }else if(menus[position].equals("退货")){
                            startContainerActivity(ReturnSearchFragment.class.getCanonicalName());
                        }else if(menus[position].equals("调拨")){
                            startContainerActivity(TransferSearchFragment.class.getCanonicalName());
                        }else if(menus[position].equals("上料")){
                            startContainerActivity(SMTSearchFragment.class.getCanonicalName());
                        }else if(menus[position].equals("对料")){
                            startContainerActivity(SMTSearchFragment.class.getCanonicalName());
                        }else if(menus[position].equals("钢板刮刀")){
                            startContainerActivity(StealSearchFragment.class.getCanonicalName());
                        }else if(menus[position].equals("清除")){
                            startContainerActivity(SMTClearFragment.class.getCanonicalName());
                        }
                    }
                });
            }
        });




    }
}
