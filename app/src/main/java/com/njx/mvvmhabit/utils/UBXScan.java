package com.njx.mvvmhabit.utils;

import android.content.Context;
import android.content.IntentFilter;
import android.device.ScanManager;
import android.device.scanner.configuration.PropertyID;

import com.njx.mvvmhabit.ui.receiver.ScannerResultReceiver;

public class UBXScan {
    private final static String SCAN_ACTION = ScanManager.ACTION_DECODE;
    private ScanManager mScanManager;
    private Context mContext;
    private ScannerResultReceiver mScanReceiver;

    public UBXScan() {
        initScan();
    }

    private void initScan() {
        mScanReceiver = new ScannerResultReceiver();
        mScanManager = new ScanManager();
        mScanManager.openScanner();
        mScanManager.switchOutputMode(0);
    }

    public void registerReceiver(Context context) {

        this.mContext = context;

        IntentFilter filter = new IntentFilter();
        int[] idbuf = new int[]{PropertyID.WEDGE_INTENT_ACTION_NAME, PropertyID.WEDGE_INTENT_DATA_STRING_TAG};
        String[] value_buf = mScanManager.getParameterString(idbuf);
        if (value_buf != null && value_buf[0] != null && !value_buf[0].equals("")) {
            filter.addAction(value_buf[0]);
        } else {
            filter.addAction(SCAN_ACTION);
        }
        mContext.registerReceiver(mScanReceiver, filter);
    }

    public void destroy() {
        if (mScanManager != null) {
            mScanManager.stopDecode();
        }

        if (mScanReceiver != null) {
            mContext.unregisterReceiver(mScanReceiver);
        }

    }

    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
