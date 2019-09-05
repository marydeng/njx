package com.njx.mvvmhabit.ui.base.fragment;

import android.databinding.ViewDataBinding;
import android.widget.EditText;

import com.njx.mvvmhabit.app.Constant;
import com.njx.mvvmhabit.utils.ScannerInterface;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.bus.Messenger;

public abstract class BaseScanFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment<V, VM> {

    @Override
    public void initData() {

        super.initData();
        Messenger.getDefault().register(getContext(), Constant.TOKEN_SCAN_CONTENT_REC, String.class, new BindingConsumer<String>() {
            @Override
            public void call(String scanResult) {
//                if (getActivity().getCurrentFocus() instanceof EditText) {
//                    EditText editText = (EditText) getActivity().getCurrentFocus();
//                    editText.setText(scanResult);
//
//                }
                onGetScanCode(scanResult);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ScannerInterface.getInstance().scan_start();
    }

    @Override
    public void onPause() {
        super.onPause();
        ScannerInterface.getInstance().scan_stop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Messenger.getDefault().unregister(getContext());
    }

    protected void onGetScanCode(String scanCode) {

    }
}
