package com.procrastinator.isen.procrastinator_2.imdbRetrieval;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tristan on 1/21/2016.
 */
public class IMDbSearchHelper {

    static final String API_URL = "http://api.themoviedb.org/3/search/movie?api_key=70bf5590ea8b078fe3130b64859b479e&query=";

    public static List<SearchResult> getMovies(String url, String title) {
        String results = "";
        title = title.replace(" ", "+");
        List<SearchResult> resultList = new ArrayList<>();
        HttpURLConnection urlConnection= getHttpURLConnection(url + title);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            results = stringBuilder.toString();
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            JSONObject jo = new JSONObject(results);
            JSONArray ja = jo.getJSONArray("results");
            for (int i = 0; i < ja.length(); i++){
                resultList.add(new Gson().fromJson(ja.getJSONObject(i).toString(), Movie.class));
                resultList.get(i).poster_path = "https://image.tmdb.org/t/p/w185"+resultList.get(i).poster_path;
                resultList.get(i).backdrop_path = "https://image.tmdb.org/t/p/w185"+resultList.get(i).backdrop_path;
            }
            return resultList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<SearchResult> getSeries(String url, String title) {
        String results = "";
        title = title.replace(" ", "+");
        List<SearchResult> resultList = new ArrayList<>();
        HttpURLConnection urlConnection= getHttpURLConnection(url + title);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            results = stringBuilder.toString();
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            JSONObject jo = new JSONObject(results);
            JSONArray ja = jo.getJSONArray("results");
            for (int i = 0; i < ja.length(); i++){
                resultList.add(new Gson().fromJson(ja.getJSONObject(i).toString(), Serie.class));
                resultList.get(i).poster_path = "https://image.tmdb.org/t/p/w185"+resultList.get(i).poster_path;
                resultList.get(i).backdrop_path = "https://image.tmdb.org/t/p/w185"+resultList.get(i).backdrop_path;
            }
            return resultList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Movie getDetailedSearchResult(String title) {
        String result = "";
        title = title.replace(" ", "+");
        HttpURLConnection urlConnection = getHttpURLConnection(API_URL + title);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            result = stringBuilder.toString();
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            JSONObject jo = new JSONObject(result);
            JSONObject ja = jo.getJSONObject("results");
            Movie movie = new Gson().fromJson(ja.toString(), Movie.class);
            int i =2;
            String s = String.valueOf(i);
            return movie;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static HttpURLConnection getHttpURLConnection(String urlString) {

        try {
            URL url = new URL(urlString);
            return (HttpURLConnection) url.openConnection();
        }
        catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }
    public static Bitmap getSearchResultImage(String imageUrl) throws Exception {
        final HttpURLConnection connection = getHttpURLConnection(imageUrl);
        final int responseCode = connection.getResponseCode();

        // If success
        if (responseCode == 200) {
            final Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            return bitmap;
        }
        return null;
    }
}