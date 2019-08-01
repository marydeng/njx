package com.njx.mvvmhabit.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.njx.mvvmhabit.app.AppApplication;
import com.njx.mvvmhabit.app.Constant;

import me.goldze.mvvmhabit.bus.Messenger;
import me.goldze.mvvmhabit.utils.KLog;

public class ScannerResultReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        KLog.d(ScannerResultReceiver.class.getSimpleName(), "intent.getAction()-->" + intent.getAction());//


        final String scanResult = intent.getStringExtra("value");


        if (intent.getAction().equals(Constant.RES_ACTION)) {

            if (scanResult.length() > 0) { //如果条码长度>0，解码成功。如果条码长度等于0解码失败。
                Messenger.getDefault().send(scanResult, Constant.TOKEN_SCAN_CONTENT_REC);
            } else {
                Toast.makeText(AppApplication.getInstance(), "解码失败！", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
