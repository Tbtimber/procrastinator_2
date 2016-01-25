package com.procrastinator.isen.procrastinator_2.interfaces;

import com.procrastinator.isen.procrastinator_2.imdbRetrieval.SearchResult;

import java.util.List;

/**
 * Created by Matthieu on 25/01/2016.
 */
public interface SelectionListener {
    void onSelectionRetrieved(List<SearchResult> results);
}
