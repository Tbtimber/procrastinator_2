package com.procrastinator.isen.procrastinator_2.imdbRetrieval;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tristan on 1/21/2016.
 */

public class Serie extends SearchResult{

    public Serie() {
    }

    @Override
    public String getTitle() {
        return title;
    }

    @SerializedName("original_name")
    public String original_name;

    @SerializedName("original_language")
    public String original_language;

    @SerializedName("title")
    public String title;

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("popularity")
    public String popularity;

    @SerializedName("vote_count")
    public String vote_count;

    @SerializedName("video")
    public String video;

    @SerializedName("vote_average")
    public String vote_average;

    @SerializedName("first_air_date")
    public String first_air_date;

    @SerializedName("origin_country")
    public String[] origin_country;

    @SerializedName("name")
    public String name;
}