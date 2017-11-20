package com.lucas.lifechecker.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.lucas.lifechecker.Custom.LabelFormatter;
import com.lucas.lifechecker.R;
import com.lucas.lifechecker.db.CounterDbHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lucas on 11/19/2017.
 */

public class DailyAverages extends Fragment {

    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        rootView = inflater.inflate( R.layout.daily_averages, container, false );

        CounterDbHelper db = new CounterDbHelper( this.getContext() );

        int[] dayaverages = db.getDayAverages();

        BarChart barchart = (BarChart) rootView.findViewById( R.id.chart_average_barchart );

        List<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < dayaverages.length; i++ ) {
            entries.add( new BarEntry( Float.valueOf(i), Float.valueOf( dayaverages[i] ) ) );
        }

        BarDataSet set = new BarDataSet( entries, "BarDataSet" );

        BarData data = new BarData( set );

        data.setValueTextSize(12);

        set.setColor(getResources().getColor(R.color.colorAccent));
        data.setBarWidth( 0.9f );
        barchart.setData( data );
        barchart.setFitBars( true );

        // remove gridlines
        barchart.getAxisLeft().setDrawGridLines(false);
        barchart.getAxisRight().setDrawGridLines(false);
        barchart.getXAxis().setDrawGridLines(false);

        // remove axis
//        barchart.getXAxis().setEnabled(false);
        barchart.getAxisLeft().setEnabled(false);
        barchart.getAxisRight().setEnabled(false);

        barchart.setTouchEnabled(false);
        Legend legend = barchart.getLegend();
        legend.setEnabled(false);
        barchart.getDescription().setEnabled(false);

        String[] labels = new String[] {"Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat" };

        barchart.getXAxis().setValueFormatter( new LabelFormatter( labels ));

        XAxis xAxis = barchart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        barchart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE
        );

//        barchart.isDrawBordersEnabled();

        barchart.invalidate();

//        PieChart chart = ( PieChart ) findViewById(R.id.chart);
//
//
//        List<PieEntry> entries = new ArrayList<>();
//
//        entries.add(new PieEntry(18.5f, "Green"));
//        entries.add(new PieEntry(26.7f, "Yellow"));
//        entries.add(new PieEntry(24.0f, "Red"));
//        entries.add(new PieEntry(30.8f, "Blue"));
//
//        PieDataSet set = new PieDataSet(entries, "Election Results");
//        PieData data = new PieData(set);
//        chart.setData(data);
//        set.setColors( new int[] { R.color.green, R.color.yellow, R.color.red, R.color.blue }, this);
//        chart.invalidate(); // refresh

        return rootView;
    }

}
