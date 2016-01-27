package com.procrastinator.isen.procrastinator_2.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.procrastinator.isen.procrastinator_2.R;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.SearchResult;
import com.procrastinator.isen.procrastinator_2.interfaces.MainActivityListener;
import com.procrastinator.isen.procrastinator_2.interfaces.SelectionListener;

/**
 * Created by Matthieu on 25/01/2016.
 */
public class SelectionsFragment extends android.support.v4.app.Fragment implements MainActivityListener{
    private android.support.v4.app.Fragment selectionAdaptedFrag;
    private android.support.v4.app.Fragment selectionTrendingFrag;

    private MainActivityListener mainActivityListener;


    @Override
    public void searchWithDetail(String s) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof MainActivityListener) {
            mainActivityListener = (MainActivityListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.selections_fragment, container, false);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selectionAdaptedFrag = new SelectionFragment();
        selectionTrendingFrag = new SelectionFragmentTrending();
        if(savedInstanceState == null) {
            android.support.v4.app.FragmentManager manager = getChildFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.selecteed_fragment_layout, selectionAdaptedFrag, "frag_selected");
            transaction.add(R.id.trending_fragment_layout, selectionTrendingFrag, "frag_trending");
            transaction.commit();
        }
    }

    @Override
    public void showDetail(SearchResult detail) {
        mainActivityListener.showDetail(detail);
    }

    @Override
    public void dropDetail() {
        mainActivityListener.dropDetail();
    }

    @Override
    public void hasTracked(String title) {

    }


}
