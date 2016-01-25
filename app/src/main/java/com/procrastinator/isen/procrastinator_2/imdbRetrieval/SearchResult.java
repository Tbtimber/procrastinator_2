package com.procrastinator.isen.procrastinator_2.imdbRetrieval;

import com.google.gson.annotations.SerializedName;

/**
 * Created by isen on 25/01/2016.
 */
public abstract class SearchResult {
    @SerializedName("poster_path")
    public String poster_path;

    @SerializedName("overview")
    public String overview;

    @SerializedName("genre_ids")
    public String[] genre_ids;

    @SerializedName("id")
    public String id;

    @SerializedName("backdrop_path")
    public String backdrop_path;

    public abstract String getTitle();
}