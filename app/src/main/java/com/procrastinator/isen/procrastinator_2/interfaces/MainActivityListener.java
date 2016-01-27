package com.procrastinator.isen.procrastinator_2.interfaces;

import java.util.List;

import com.procrastinator.isen.procrastinator_2.imdbRetrieval.SearchResult;

/**
 * Created by Matthieu on 25/01/2016.
 */
public interface MainActivityListener {
    void showDetail(SearchResult detail);
    void dropDetail();
    void hasTracked(String title);
    void searchWithDetail(String s);
}

