package com.lucas.lifechecker.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
 * Created by Lucas on 11/5/2017.
 */

public class DailyAverages extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_daily_averages);

        CounterDbHelper db = new CounterDbHelper( this );

        int[] dayAverages = db.getDayAverages();
        createBarChart( dayAverages );


    }

    public void createBarChart( int[] dayAverages )
    {
        BarChart barchart = (BarChart) findViewById( R.id.chart_average_barchart );

        List<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < dayAverages.length; i++ ) {
            entries.add( new BarEntry( Float.valueOf(i), Float.valueOf( dayAverages[i] ) ) );
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
        barchart.getXAxis().setPosition( XAxis.XAxisPosition.BOTTOM_INSIDE );

        barchart.invalidate();
    }

}
