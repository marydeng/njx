package com.njx.mvvmhabit.app;

public class Constant {
    //PDA 扫码监听广播
    public static final String RES_ACTION = "android.intent.action.SCANRESULT";
    //收到扫码内容 ，发送Messenger token
    public static final String TOKEN_SCAN_CONTENT_REC = "token_scan_content_rec";

    public static final int Ret_SUCCESS=0;
    public static final int Ret_NOTICE_ERROR=500;//Todo 需要提示的错误

    public static final String SP_Coonkie_Key="coonkie_key";
}
