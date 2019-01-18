package com.example.fhl.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.fhl.aidl.IModel1;
import com.example.fhl.aidl.inouts.IPresonManager;
import com.example.fhl.aidl.inouts.Preson;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button bt1,bt2,bt3,bt4,bt5;

//    private IMyService myService;
    private IPresonManager mIPresonManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt5 = findViewById(R.id.bt5);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                bindService();
                break;
            case R.id.bt2:
                Preson presonin = new Preson();
                presonin.setName("Preson in");
                presonin.setAge(11);
                try {
                    Preson returnPreson = mIPresonManager.addPresonIn(presonin);
                    Log.e("MainActivity", "returnPreson="+returnPreson.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Log.e("MainActivity", "preson="+presonin.toString());
                break;
            case R.id.bt3:
                Preson presonout = new Preson();
                presonout.setName("Preson in");
                presonout.setAge(11);
                try {
                    Preson returnPreson = mIPresonManager.addPresonOut(presonout);
                    Log.e("MainActivity", "returnPreson="+returnPreson.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Log.e("MainActivity", "preson="+presonout.toString());
                break;
            case R.id.bt4:
                Preson presoninout = new Preson();
                presoninout.setName("Preson in");
                presoninout.setAge(11);
                try {
                    Preson returnPreson = mIPresonManager.addPresonIn(presoninout);
                    Log.e("MainActivity", "returnPreson="+returnPreson.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Log.e("MainActivity", "preson="+presoninout.toString());
                break;
            case R.id.bt5:
                try {
                    List<Preson> list = mIPresonManager.getPresons();
                    Log.e("MainActivity", "return list="+list.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                bindService();
                break;
        }
    }

    private void mode1(){
        IBinder iBinder = BinderPooll.getInstance(this).queryIBinder(1);
        IModel1 iModel1 = IModel1.Stub.asInterface(iBinder);
        try {
            iModel1.getName();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    private void bindService(){
        Intent intent = new Intent();
//        intent.setAction("com.example.fhl.aidl.servcie.easy.MyService");
        intent.setAction("com.example.fhl.aidl.servcie.inouts.InoutService");
        intent.setPackage("com.example.fhl.aidl");
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
//            myService = IMyService.Stub.asInterface(iBinder);
//            try {
//                String name = myService.getName();
//                System.out.println(name);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
            mIPresonManager = IPresonManager.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
//            myService = null;
            mIPresonManager = null;
        }
    };
}
