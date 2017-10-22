package com.lucas.lifechecker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lucas.lifechecker.db.CounterDbHelper;

/**
 * Created by Lucas on 10/22/2017.
 */

public class UnlockedPhoneReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        CounterDbHelper db = new CounterDbHelper( context );

        db.checkAndAddToCount();
    }
}
