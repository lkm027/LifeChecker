package com.lucas.lifechecker.Custom;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Lucas on 11/18/2017.
 */

public class NonSwipeableViewPager extends ViewPager {

    public NonSwipeableViewPager( Context context, AttributeSet attrs )
    {
        super( context, attrs );
    }

    @Override
    public boolean onInterceptTouchEvent( MotionEvent event )
    {
        return false;
    }

    @Override
    public boolean onTouchEvent( MotionEvent event )
    {
        return false;
    }


}
