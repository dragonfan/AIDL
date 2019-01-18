package com.example.fhl.aidl.servcie.remotecallbacklist;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.example.fhl.aidl.remotecallbacklist.ILisntener;
import com.example.fhl.aidl.remotecallbacklist.IManagerLisntener;
import com.example.fhl.aidl.servcie.pooll.BinderPooll;

public class RemoteCallBackListService extends Service {

    private RemoteCallbackList<ILisntener> mRemoteCallBackList = new RemoteCallbackList<>();
    @Override
    public IBinder onBind(Intent intent) {
        return new ManagerListener();
    }

    private class ManagerListener extends IManagerLisntener.Stub{

        @Override
        public void registerListener(ILisntener listener) throws RemoteException {
            if (listener != null) {
                mRemoteCallBackList.register(listener);
            }
        }

        @Override
        public void unregisterListener(ILisntener listener) throws RemoteException {
            if (listener != null) {
                mRemoteCallBackList.unregister(listener);
            }
        }
    }

    //给所有注册的监听回调
    private void returnCallBack(){
        int size = mRemoteCallBackList.beginBroadcast();
        for (int i = 0; i < size; i++) {
            ILisntener lisntener = mRemoteCallBackList.getBroadcastItem(i);
            if (lisntener != null) {
                try {
                    lisntener.onCallBack("hello");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mRemoteCallBackList.finishBroadcast();
    }

    @Override
    public void onDestroy() {
        if (mRemoteCallBackList != null) {
            mRemoteCallBackList.kill();
        }
        super.onDestroy();
    }
}
