package com.example.fhl.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fhl.aidl.IModel1;
import com.example.fhl.aidl.IMyService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button bt1;

    private IMyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = findViewById(R.id.bt1);
        bt1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                bindSer();
                IBinder iBinder = BinderPooll.getInstance(this).queryIBinder(1);
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
    private void bindSer(){
        Intent intent = new Intent();
        intent.setAction("com.example.fhl.aidl.servcie.easy.MyService");
        intent.setPackage("com.example.fhl.aidl");
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myService = IMyService.Stub.asInterface(iBinder);
            try {
                String name = myService.getName();
                System.out.println(name);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myService = null;
        }
    };
}
