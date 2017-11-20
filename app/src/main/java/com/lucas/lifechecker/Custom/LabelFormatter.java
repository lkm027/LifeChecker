package com.lucas.lifechecker.Custom;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by Lucas on 11/5/2017.
 */

public class LabelFormatter implements IAxisValueFormatter {
    private final String[] mLabels;

    public LabelFormatter( String[] labels ) {
        mLabels = labels;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis ) {
        return mLabels[(int) value];
    }
}
