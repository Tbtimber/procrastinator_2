package com.procrastinator.isen.procrastinator_2.imdbRetrieval;

import android.os.AsyncTask;

import com.procrastinator.isen.procrastinator_2.interfaces.SelectionListener;

import java.util.List;

/**
 * Created by isen on 25/01/2016.
 */
public class GetSeriesAsync extends AsyncTask<String, Void, List<SearchResult>> {
    private SelectionListener mListener;

    public GetSeriesAsync(SelectionListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected List<SearchResult> doInBackground(String... params) {
        return IMDbSearchHelper.getSeries(params[0], params[1]);
    }

    protected void onPostExecute(List<SearchResult> result){
        mListener.onSelectionRetrieved(result);
    }
}
