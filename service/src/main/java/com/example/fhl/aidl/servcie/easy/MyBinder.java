package com.example.fhl.aidl.servcie.easy;

import android.os.RemoteException;

import com.example.fhl.aidl.IMyService;

/**
 * <br/>
 * <li>Author hailong.fan
 * <li>Email fanhailong@vargo.com.cn
 * <li>Date 2019/1/16 14:53
 */
public class MyBinder extends IMyService.Stub {

    @Override
    public String getName() throws RemoteException {
        return "我是服务进程";
    }

}
