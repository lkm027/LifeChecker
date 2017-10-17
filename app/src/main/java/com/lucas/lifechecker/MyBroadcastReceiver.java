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
    private static int LOGINS = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        CounterDbHelper db = new CounterDbHelper( context );

        int num = db.increaseCounter();
        db.testDate();

        StringBuilder sb = new StringBuilder();
        LOGINS++;
        sb.append( num );
        String log = sb.toString();
        Log.d( TAG, log );
        Toast.makeText( context, log, Toast.LENGTH_LONG ).show();
    }
}
