package com.procrastinator.isen.procrastinator_2.interfaces;

import com.procrastinator.isen.procrastinator_2.imdbRetrieval.SearchResult;

/**
 * Created by Matthieu on 27/01/2016.
 */
public interface TrackedListener {
    void untracked(String title);
    void showDetail(SearchResult result);
}
