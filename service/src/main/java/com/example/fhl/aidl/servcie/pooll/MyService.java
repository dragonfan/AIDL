package com.example.fhl.aidl.servcie.pooll;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new BinderPooll();
    }
}
