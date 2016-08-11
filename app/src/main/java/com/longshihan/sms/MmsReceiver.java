package com.longshihan.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MmsReceiver extends BroadcastReceiver {
    public MmsReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("cky","MmsReceiver: "+intent);
    }
}
