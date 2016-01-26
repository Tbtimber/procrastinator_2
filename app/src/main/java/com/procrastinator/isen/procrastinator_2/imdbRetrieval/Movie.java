package com.procrastinator.isen.procrastinator_2.imdbRetrieval;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tristan on 1/21/2016.
 */

public class Movie extends SearchResult {

    public Movie() {
    }

    @SerializedName("adult")
    public String adult;

    @SerializedName("release_date")
    public String release_date;

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @SerializedName("original_title")
    public String original_title;

    @SerializedName("original_language")
    public String original_language;

    @SerializedName("title")
    public String title;

    @SerializedName("popularity")
    public String popularity;

    @SerializedName("vote_count")
    public String vote_count;

    @SerializedName("video")
    public String video;

    @SerializedName("vote_average")
    public String vote_average;


}