package com.procrastinator.isen.procrastinator_2.fragments;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.procrastinator.isen.procrastinator_2.R;
import com.procrastinator.isen.procrastinator_2.adapters.GridViewAdapter;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.GetMoviesAsync;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.SearchResult;
import com.procrastinator.isen.procrastinator_2.interfaces.MainActivityListener;
import com.procrastinator.isen.procrastinator_2.interfaces.SelectionListener;
import com.procrastinator.isen.procrastinator_2.util.Proc_Constants;

import java.util.List;

/**
 * Created by Matthieu on 21/01/2016.
 */
public class ResultFragment extends android.support.v4.app.Fragment implements SelectionListener,AdapterView.OnItemClickListener {
    private String resultString;
    private GridView mGridView;
    private MainActivityListener mainActivityListener;
    private GetMoviesAsync moviesAsync;



    public void newSearch(String query) {
        if(!moviesAsync.isCancelled())
            moviesAsync.cancel(true);
        moviesAsync = new GetMoviesAsync(this);
        if(query == null) {
            query = "";
        }
        moviesAsync.execute(Proc_Constants.API_URL_MOVIE, query);
    }

    @Override
    public void onSelectionRetrieved(List<SearchResult> results) {
        if(results != null) {
            GridViewAdapter adapter = new GridViewAdapter(getActivity(), results);
            mGridView.setAdapter(adapter);
            mGridView.setOnItemClickListener(this);
            for (SearchResult r:results) {
                Log.v("GridView : " , r.getTitle());
            }
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivityListener) {
            mainActivityListener = (MainActivityListener) context;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        moviesAsync = new GetMoviesAsync(this);
        moviesAsync.execute(Proc_Constants.API_URL_MOVIE, resultString);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_result, container, false);

        resultString = getArguments().getString("search_string");
        if(resultString == null) {
            resultString = "test";
        }

        mGridView = (GridView) rootView.findViewById(R.id.result_grid_view);
        final ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        mGridView.setEmptyView(progressBar);
        return rootView;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(null != mainActivityListener) {
            final SearchResult result = (SearchResult) parent.getItemAtPosition((int)id);
            mainActivityListener.showDetail(result);
        }
    }
}
