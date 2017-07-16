package study.sunning.study.demo.ipc.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    IAddService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setAction("android.sunning.demo.ipc.aidl");
        intent.setPackage("sunning.study.ipc.binder");
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                service = IAddService.Stub.asInterface(iBinder);
                Log.i("Client","Bind Success:" + service);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        }, Context.BIND_AUTO_CREATE);
        findViewById(R.id.click_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (service != null) {
                    try {
                        int result = service.add(100, 200);
                        Log.e("result","result:" + result);
                        ((TextView) findViewById(R.id.result)).setText("result:" + result);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}
