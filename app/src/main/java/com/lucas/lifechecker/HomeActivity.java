package com.lucas.lifechecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lucas.lifechecker.db.CounterDbHelper;

public class HomeActivity extends AppCompatActivity {

    CounterDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = new CounterDbHelper( getApplicationContext() );
        populateScreen();
        db.close();
    }

    private void populateScreen() {
        TextView todayCount_textview = (TextView) findViewById( R.id.txtVW_homeScreen_todayCount );
        int currentDayCount = db.getWeekCount();
        todayCount_textview.setText( String.valueOf( currentDayCount ) );
    }
}
