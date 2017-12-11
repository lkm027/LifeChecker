package com.lucas.lifechecker.Custom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * Created by Lucas on 12/10/2017.
 */

public class PhoneBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent( context, OnScreenOn.class );
        context.startService( serviceIntent );
    }
}
