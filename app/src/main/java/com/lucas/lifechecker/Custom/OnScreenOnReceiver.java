package com.lucas.lifechecker.Custom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.lucas.lifechecker.Activities.HomeActivity;
import com.lucas.lifechecker.db.CounterDbHelper;

/**
 * Created by Lucas on 12/10/2017.
 */

public class OnScreenOnReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast toast = Toast.makeText( context, "Screen on", Toast.LENGTH_LONG );
        toast.show();
    }
}
