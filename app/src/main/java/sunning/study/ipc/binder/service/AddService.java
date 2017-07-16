package sunning.study.ipc.binder.service;
/*
 * Created by sunning on 2017/7/16.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import sunning.study.ipc.binder.aidl.IAddService;

public class AddService extends Service {

    IAddService.Stub add = new IAddService.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            return (a + b) * 100;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return add;
    }
}
