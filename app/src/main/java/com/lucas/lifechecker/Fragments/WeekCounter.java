package com.lucas.lifechecker.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucas.lifechecker.R;
import com.lucas.lifechecker.db.CounterDbHelper;

/**
 * Created by Lucas on 11/19/2017.
 */

public class WeekCounter extends Fragment {
    CounterDbHelper db;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        db = new CounterDbHelper( getContext() );

        rootView = inflater.inflate( R.layout.activity_home, container, false );
        populateScreen();
        db.close();

        removeViews();
        changeViewText();

        return rootView;
    }

    private void populateScreen() {
        TextView todayCount_textview = (TextView) rootView.findViewById(R.id.txtVW_home_dailyCount );
        int currentDayCount = db.getWeekCount();
        todayCount_textview.setText( String.valueOf( currentDayCount ) );
    }

    private void removeViews()
    {
        rootView.findViewById( R.id.fragment_title_StatisticManagerActivity).setVisibility( View.GONE );
        rootView.findViewById( R.id.button_home_Next ).setVisibility( View.GONE );
        rootView.findViewById( R.id.button_home_refreshCount ).setVisibility( View.GONE );
    }

    private void changeViewText()
    {
        ( ( TextView ) rootView.findViewById( R.id.txtVW_home_timesString ) ).setText( R.string.times_this_week);
    }


}
