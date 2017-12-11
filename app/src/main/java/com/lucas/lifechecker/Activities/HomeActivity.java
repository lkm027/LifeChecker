package com.lucas.lifechecker.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lucas.lifechecker.Custom.OnScreenOn;
import com.lucas.lifechecker.Fragments.LifeCheckerTitle;
import com.lucas.lifechecker.R;
import com.lucas.lifechecker.db.CounterDbHelper;

public class HomeActivity extends FragmentActivity {

    CounterDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ((LifeCheckerTitle) getSupportFragmentManager().findFragmentById( R.id.fragment_title_StatisticManagerActivity)).removeBackButtonView();

        db = new CounterDbHelper( getApplicationContext() );
        populateScreen();
//        int dayOfWeek = db.getDayofWeek();
//        int[] averages = db.getDayAverages();
//        int day = averages[0];
//        Toast toast = Toast.makeText( getApplicationContext(), String.valueOf( dayOfWeek ), Toast.LENGTH_LONG );
//        toast.show();
        db.close();
    }

    private void populateScreen() {
        TextView todayCount_textview = (TextView) findViewById( R.id.txtVW_home_dailyCount );
        int currentDayCount = db.getTodayCount();
        todayCount_textview.setText( String.valueOf( currentDayCount ) );
    }

    public void refreshCount( View view ) {
        populateScreen();
    }

    public void showWeeklyChart( View view ) {
        Intent intent = new Intent(this, StatisticManagerActivity.class );
        startActivity( intent );
    }
}
