package com.lucas.lifechecker.Custom;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.IBinder;

/**
 * Created by Lucas on 12/10/2017.
 */

public class OnScreenOn extends Service {

    int ID = 1331;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId )
    {
        startForeground( ID, new Notification());
        BroadcastReceiver receiver = new OnScreenOnReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction( Intent.ACTION_SCREEN_ON );
        this.registerReceiver( receiver, filter );
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind( Intent intent )
    {
        // for communication return Ibinder implementation
        return null;
    }

}
