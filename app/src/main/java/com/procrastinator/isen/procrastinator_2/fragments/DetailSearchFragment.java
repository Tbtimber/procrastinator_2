package com.procrastinator.isen.procrastinator_2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.procrastinator.isen.procrastinator_2.R;

/**
 * Created by Matthieu on 25/01/2016.
 */
public class DetailSearchFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_search_fragment, container, false);
        return rootView;
    }
}
