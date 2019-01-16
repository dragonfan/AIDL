package com.example.fhl.aidl.servcie.easy;

import android.os.IBinder;
import android.os.RemoteException;

import com.example.fhl.aidl.IBinderPooll;

/**
 * <br/>
 * <li>Author hailong.fan
 * <li>Email fanhailong@vargo.com.cn
 * <li>Date 2019/1/16 17:58
 */
public class BinderPooll extends IBinderPooll.Stub{
    private static final int MODEL_1 = 1;
    private static final int MODEL_2 = 2;
    @Override
    public IBinder queryBinder(int code) throws RemoteException {
        IBinder iBinder = null;
        switch (code){
            case MODEL_1:
                iBinder = new Mode1();
                break;
            case MODEL_2:
                iBinder = new Mode2();
                break;
            default:
                break;
        }
        return iBinder;
    }
}
