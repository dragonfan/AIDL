package com.example.fhl.aidl.servcie.inouts;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.fhl.aidl.inouts.IPresonManager;
import com.example.fhl.aidl.inouts.Preson;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InoutService extends Service {

    private CopyOnWriteArrayList<Preson> list = new CopyOnWriteArrayList<>();
    @Override
    public IBinder onBind(Intent intent) {
        return new PresonManager();
    }

    private class PresonManager extends IPresonManager.Stub{

        @Override
        public List<Preson> getPresons() throws RemoteException {
            return list;
        }

        @Override
        public Preson addPresonIn(Preson preson) throws RemoteException {
            if (preson == null) {
                Log.e("InoutService", "preson is null.");
                preson = new Preson();
            }
            preson.setAge(28);
            if (!list.contains(preson)) {
                list.add(preson);
            }
            Log.e("InoutService", "list: "+list.toString());
            return preson;
        }

        @Override
        public Preson addPresonOut(Preson preson) throws RemoteException {
            if (preson == null) {
                Log.e("InoutService", "preson is null.");
                preson = new Preson();
            }
            preson.setAge(28);
            if (!list.contains(preson)) {
                list.add(preson);
            }
            Log.e("InoutService", "list: "+list.toString());
            return preson;
        }

        @Override
        public Preson addPresonInOut(Preson preson) throws RemoteException {
            if (preson == null) {
                Log.e("InoutService", "preson is null.");
                preson = new Preson();
            }
            preson.setAge(28);
            if (!list.contains(preson)) {
                list.add(preson);
            }
            Log.e("InoutService", "list: "+list.toString());
            return preson;
        }
    }
}
