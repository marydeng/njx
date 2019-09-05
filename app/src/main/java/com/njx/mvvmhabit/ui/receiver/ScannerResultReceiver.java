package com.njx.mvvmhabit.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.device.ScanManager;
import android.util.Log;
import android.widget.Toast;

import com.njx.mvvmhabit.app.AppApplication;
import com.njx.mvvmhabit.app.Constant;

import me.goldze.mvvmhabit.bus.Messenger;
import me.goldze.mvvmhabit.utils.KLog;

public class ScannerResultReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        KLog.d(ScannerResultReceiver.class.getSimpleName(), "intent.getAction()-->" + intent.getAction());//


         String scanResult ;


        if (intent.getAction().equals(Constant.RES_ACTION)) {
            scanResult = intent.getStringExtra("value");
        }else {
            byte[] barcode=intent.getByteArrayExtra(ScanManager.DECODE_DATA_TAG);
            int barcodelen=intent.getIntExtra(ScanManager.BARCODE_LENGTH_TAG,0);
            byte temp=intent.getByteExtra(ScanManager.BARCODE_TYPE_TAG,(byte)0);
            Log.i("ScannerResultReceiver","-------codetype--"+temp);
            scanResult=new String(barcode,0,barcodelen);
        }

        if (scanResult.length() > 0) { //如果条码长度>0，解码成功。如果条码长度等于0解码失败。
            Messenger.getDefault().send(scanResult, Constant.TOKEN_SCAN_CONTENT_REC);
        } else {
            Toast.makeText(AppApplication.getInstance(), "解码失败！", Toast.LENGTH_SHORT).show();
        }
    }

}
