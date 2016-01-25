package com.procrastinator.isen.procrastinator_2.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.procrastinator.isen.procrastinator_2.ProcrastinatorApplication;
import com.procrastinator.isen.procrastinator_2.R;
import com.procrastinator.isen.procrastinator_2.adapters.SelectionAdapterView;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.GetMoviesAsync;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.SearchResult;
import com.procrastinator.isen.procrastinator_2.interfaces.MainActivityListener;
import com.procrastinator.isen.procrastinator_2.interfaces.SelectionListener;
import com.procrastinator.isen.procrastinator_2.util.Proc_Constants;

import java.util.List;

/**
 * Created by Matthieu on 21/01/2016.
 */
public class SelectionFragmentTrending extends android.support.v4.app.Fragment implements SelectionListener {
    private RecyclerView mTrendingView;

    private GetMoviesAsync moviesAsync;
    private MainActivityListener mListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof MainActivityListener) {
            mListener = (MainActivityListener) context;
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        moviesAsync = new GetMoviesAsync(this);
        moviesAsync.execute(Proc_Constants.API_URL_MOVIE_POPULAR, "");
      //  mAsyncTask = new GetSearchResultsAsync(this);
       // mAsyncTask.execute("jones");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_selection_trending, container, false);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(ProcrastinatorApplication.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mTrendingView = (RecyclerView) rootView.findViewById(R.id.selectionTrendingListView);
        mTrendingView.setLayoutManager(layoutManager2);

        return rootView;
    }

    @Override
    public void onSelectionRetrieved(List<SearchResult> results) {
        if(results != null) {
            final SelectionAdapterView adapterView = new SelectionAdapterView(getActivity(), results);
            adapterView.setMainActivityListener(mListener);
            mTrendingView.setAdapter(adapterView);
        }
    }

}
