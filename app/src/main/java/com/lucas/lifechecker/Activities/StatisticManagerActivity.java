package com.lucas.lifechecker.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lucas.lifechecker.Adapters.PagerAdapter;
import com.lucas.lifechecker.Custom.NonSwipeableViewPager;
import com.lucas.lifechecker.Fragments.*;
import com.lucas.lifechecker.Fragments.DailyAverages;
import com.lucas.lifechecker.R;


/**
 * Created by Lucas on 11/18/2017.
 */

public class StatisticManagerActivity extends FragmentActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_statistic_manager);

        NonSwipeableViewPager viewPager = ( NonSwipeableViewPager ) findViewById( R.id.pager );

        setupViewPager( viewPager );

        TabLayout tabLayout = ( TabLayout ) findViewById( R.id.tabLayout );
        tabLayout.setupWithViewPager( viewPager );
    }

    private void setupViewPager( ViewPager viewPager )
    {
        PagerAdapter adapter = new PagerAdapter( getSupportFragmentManager(), 0 );
        adapter.addFragment(new DailyAverages(), getString( R.string.tab_daily_averages ) );
        adapter.addFragment(new WeekCounter(), getString( R.string.tab_weekly_count ) );
        viewPager.setAdapter( adapter );
    }

    public void returnToParentActivity( View view ) {
        finish();
    }
}
