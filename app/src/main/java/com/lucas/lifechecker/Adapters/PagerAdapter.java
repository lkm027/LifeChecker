package com.lucas.lifechecker.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.lucas.lifechecker.Fragments.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 11/18/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();

    public PagerAdapter(FragmentManager manager, int numOfTabs )
    {
        super( manager );
    }

    @Override
    public Fragment getItem( int position )
    {
        return fragmentList.get( position );

    }

    @Override
    public int getCount()
    {
        return fragmentList.size();
    }

    public void addFragment( Fragment fragment, String title )
    {
        fragmentList.add( fragment );
        fragmentTitles.add( title );
    }

    @Override
    public CharSequence getPageTitle( int position )
    {
        return fragmentTitles.get( position );
    }
}
