package com.example.fhl.aidl.servcie.pooll;

import android.os.RemoteException;

import com.example.fhl.aidl.IModel2;

/**
 * <br/>
 * <li>Author hailong.fan
 * <li>Email fanhailong@vargo.com.cn
 * <li>Date 2019/1/16 18:22
 */
public class Mode2 extends IModel2.Stub {

    @Override
    public void setName(String name) throws RemoteException {

        System.out.println(name);
    }
}
