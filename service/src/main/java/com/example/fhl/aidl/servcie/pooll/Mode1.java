package com.example.fhl.aidl.servcie.pooll;

import android.os.RemoteException;

import com.example.fhl.aidl.IModel1;

/**
 * <br/>
 * <li>Author hailong.fan
 * <li>Email fanhailong@vargo.com.cn
 * <li>Date 2019/1/16 18:22
 */
public class Mode1 extends IModel1.Stub {
    @Override
    public String getName() throws RemoteException {
        return "我是服务端";
    }
}
