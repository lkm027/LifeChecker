package com.lucas.lifechecker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.lucas.lifechecker.db.CounterDbHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.dayOfWeekTextAppearance;
import static android.R.attr.entries;

/**
 * Created by Lucas on 11/5/2017.
 */

public class DailyAverages extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_averages);

        CounterDbHelper db = new CounterDbHelper( this );

        int[] dayaverages = db.getDayAverages();




        BarChart barchart = (BarChart) findViewById( R.id.chart_average_barchart );

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
    }

    public void returnToParentActivity( View view ) {
        finish();
    }
}
