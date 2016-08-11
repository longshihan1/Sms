package com.longshihan.sms;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class HeadlessSmsSendService extends Service {
    public HeadlessSmsSendService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("cky","HeadlessSmsSendService: "+intent);
        return null;
    }
}
