package com.example.fhl.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.fhl.aidl.IBinderPooll;

import java.util.concurrent.CountDownLatch;

/**
 * <br/>
 * <li>Author hailong.fan
 * <li>Email fanhailong@vargo.com.cn
 * <li>Date 2019/1/16 18:37
 */
public class BinderPooll {
    private volatile static BinderPooll mBinderPooll;
    private IBinderPooll mIBinderPooll;
    private Context mContext;
    private CountDownLatch mCountDownLatch;//同步用

    private BinderPooll(Context context) {
        mContext = context.getApplicationContext();
        connBinderService();
    }

    public static BinderPooll getInstance(Context context){ //双重检测单例
        if (mBinderPooll == null) {
            synchronized(BinderPooll.class){
                if (mBinderPooll == null) {
                    mBinderPooll = new BinderPooll(context);
                }
            }
        }
        return mBinderPooll;
    }

    public IBinder queryIBinder(int code){
        IBinder iBinder = null;
        if (mIBinderPooll != null) {
            iBinder = mBinderPooll.queryIBinder(code);
        }
        return iBinder;
    }

    private synchronized void connBinderService(){
        mCountDownLatch = new CountDownLatch(1);
        Intent intent = new Intent();
        intent.setAction("com.example.fhl.aidl.servcie.easy.MyService");
        intent.setPackage("com.example.fhl.aidl");
        mContext.bindService(intent, conn, Context.BIND_AUTO_CREATE);
        try {
            mCountDownLatch.wait(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (mIBinderPooll == null) {
            throw new RuntimeException("mIBinderPooll is null");
        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mIBinderPooll = IBinderPooll.Stub.asInterface(iBinder);
            try {
                mIBinderPooll.asBinder().linkToDeath(deathRecipient, 0); //绑定死亡监听
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mCountDownLatch.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mIBinderPooll = null;
        }
    };

    //服务是否死亡监听
    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            mIBinderPooll.asBinder().unlinkToDeath(deathRecipient, 0); //取消死亡监听
            mIBinderPooll = null;
            connBinderService();
        }
    };
}
