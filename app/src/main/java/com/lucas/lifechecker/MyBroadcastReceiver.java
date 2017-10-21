package com.lucas.lifechecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.lucas.lifechecker.db.CounterContract;
import com.lucas.lifechecker.db.CounterDbHelper;

/**
 * Created by Lucas on 10/12/2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        CounterDbHelper db = new CounterDbHelper( context );

        int num = db.checkAndAddToCount();

        int weeknum = db.getWeekCount();

        StringBuilder sb = new StringBuilder();
        sb.append( num );
        String log = sb.toString();
        Log.d( TAG, log );
        Toast.makeText( context, log, Toast.LENGTH_LONG ).show();
    }
}
