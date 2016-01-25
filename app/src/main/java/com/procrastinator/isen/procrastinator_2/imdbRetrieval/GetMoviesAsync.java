package com.procrastinator.isen.procrastinator_2.imdbRetrieval;

import android.os.AsyncTask;

import com.procrastinator.isen.procrastinator_2.interfaces.SelectionListener;

import java.util.List;

/**
 * Created by Tristan on 1/21/2016.
 */
public class GetMoviesAsync extends AsyncTask<String, Void, List<SearchResult>> {
    private SelectionListener mListener;

    public GetMoviesAsync(SelectionListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected List<SearchResult> doInBackground(String... params) {
        return IMDbSearchHelper.getMovies(params[0], params[1]);
    }

    protected void onPostExecute(List<SearchResult> result){
        mListener.onSelectionRetrieved(result);
    }
}
