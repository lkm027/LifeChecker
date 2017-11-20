package com.lucas.lifechecker.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.lucas.lifechecker.R;

/**
 * Created by Lucas on 11/19/2017.
 */

public class LifeCheckerTitle extends Fragment {

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        rootView = inflater.inflate( R.layout.fragment_title, container, false );

        return rootView;
    }

    public void removeBackButtonView()
    {
        View view = rootView.findViewById( R.id.button_return_to_parent );
        rootView.findViewById( R.id.button_return_to_parent ).setVisibility( View.INVISIBLE );

    }

}
