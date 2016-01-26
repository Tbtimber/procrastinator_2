package com.procrastinator.isen.procrastinator_2.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.procrastinator.isen.procrastinator_2.ProcrastinatorApplication;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.Movie;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.SearchResult;
import com.procrastinator.isen.procrastinator_2.util.Proc_Constants;

import java.util.List;

/**
 * Created by Théophane MARTEAU on 21/01/2016.
 */
public class ProcrastinatorDatabaseManager {

    public static SearchResult movieFromCursor(Cursor c){ // crée un  curseur
        if (null != c){
            final SearchResult movie = new Movie();

            // Retrieve the date created
            /*if (c.getColumnIndex(ProcrastinatorDatabaseContract.RELEASE_DATE) >= 0){
                //movie.releaseDate = c.getString(c.getColumnIndex(ProcrastinatorDatabaseContract.RELEASE_DATE));
            }*/

            if (c.getColumnIndex(ProcrastinatorDatabaseContract.PLOT) >= 0){
                movie.overview = c.getString(c.getColumnIndex(ProcrastinatorDatabaseContract.PLOT));
            }

            if (c.getColumnIndex(ProcrastinatorDatabaseContract.TITLE) >= 0){
                movie.setTitle(c.getString(c.getColumnIndex(ProcrastinatorDatabaseContract.TITLE)));
            }

            if (c.getColumnIndex(ProcrastinatorDatabaseContract.MOVIE_PICTURE) >= 0){
                movie.poster_path = c.getString(c.getColumnIndex(ProcrastinatorDatabaseContract.MOVIE_PICTURE));
            }

            /*if (c.getColumnIndex(ProcrastinatorDatabaseContract.GENRE) >= 0){
                //movie.genre = c.getString(c.getColumnIndex(ProcrastinatorDatabaseContract.GENRE));
            }

            if (c.getColumnIndex(ProcrastinatorDatabaseContract.TYPE) >= 0){
                //movie.type = c.getString(c.getColumnIndex(ProcrastinatorDatabaseContract.TYPE));
            }

            if (c.getColumnIndex(ProcrastinatorDatabaseContract.DIRECTOR) >= 0){
                //movie.director = c.getString(c.getColumnIndex(ProcrastinatorDatabaseContract.DIRECTOR));
            }

            if (c.getColumnIndex(ProcrastinatorDatabaseContract.WRITER) >= 0){
                //movie.writer = c.getString(c.getColumnIndex(ProcrastinatorDatabaseContract.WRITER));
            }

            if (c.getColumnIndex(ProcrastinatorDatabaseContract.ACTORS) >= 0){
                //movie.actors = c.getString(c.getColumnIndex(ProcrastinatorDatabaseContract.ACTORS));
            }

            if (c.getColumnIndex(ProcrastinatorDatabaseContract.RUNTIME) >= 0){
                //movie.runtime = c.getString(c.getColumnIndex(ProcrastinatorDatabaseContract.RUNTIME));
            }

            if (c.getColumnIndex(ProcrastinatorDatabaseContract.COUNTRY) >= 0){
                //movie.country = c.getString(c.getColumnIndex(ProcrastinatorDatabaseContract.COUNTRY));
            }*/


            return movie;
        }
        return null;
    }

    public static ContentValues movieToContentValues(SearchResult movie){
        final ContentValues values = new ContentValues();

        if (!TextUtils.isEmpty(movie.overview)){
            values.put(ProcrastinatorDatabaseContract.PLOT, movie.overview);
        }
        if (!TextUtils.isEmpty(movie.getTitle())){
            values.put(ProcrastinatorDatabaseContract.TITLE, movie.getTitle());
        }

        if (!TextUtils.isEmpty(movie.poster_path)){
            values.put(ProcrastinatorDatabaseContract.MOVIE_PICTURE, movie.poster_path);
        }
        /*// Set the date created
        if (!TextUtils.isEmpty(movie.releaseDate)){
            values.put(ProcrastinatorDatabaseContract.RELEASE_DATE, movie.releaseDate);
        }



        if (!TextUtils.isEmpty(movie.genre)){
            values.put(ProcrastinatorDatabaseContract.GENRE, movie.genre);
        }

        if (!TextUtils.isEmpty(movie.type)){
            values.put(ProcrastinatorDatabaseContract.TYPE, movie.type);
        }

        if (!TextUtils.isEmpty(movie.director)){
            values.put(ProcrastinatorDatabaseContract.DIRECTOR, movie.director);
        }

        if (!TextUtils.isEmpty(movie.writer)){
            values.put(ProcrastinatorDatabaseContract.WRITER, movie.writer);
        }

        if (!TextUtils.isEmpty(movie.actors)){
            values.put(ProcrastinatorDatabaseContract.ACTORS, movie.actors);
        }

        if (!TextUtils.isEmpty(movie.runtime)){
            values.put(ProcrastinatorDatabaseContract.RUNTIME, movie.runtime);
        }

        if (!TextUtils.isEmpty(movie.country)){
            values.put(ProcrastinatorDatabaseContract.COUNTRY, movie.country);
        }*/

        return values;
    }

    public static void testDatabase(List<SearchResult> movies){
        final ProcrastinatorDataBaseHelper dbHelper = new ProcrastinatorDataBaseHelper(ProcrastinatorApplication.getContext());
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        // First insert all values in database
        for (SearchResult movie : movies){
            db.insert(ProcrastinatorDatabaseContract.TABLE_MOVIES, "", movieToContentValues(movie));
            Log.w(Proc_Constants.LOG_TAG, "movie stored");
            //Log.w(Proc_Constants.LOG_TAG, movie.toString());
            Log.w(Proc_Constants.LOG_TAG, "----------------------");
        }

        // Now that all values are stored in database, read them and log
        final Cursor cursor = db.query(ProcrastinatorDatabaseContract.TABLE_MOVIES,
                ProcrastinatorDatabaseContract.PROJECTION_FULL,
                null, null, null, null, null);
        if (null != cursor){
            while (cursor.moveToNext()){
                final SearchResult movie = movieFromCursor(cursor);
                Log.i(Proc_Constants.LOG_TAG, "Stored movie");
              //  Log.i(Proc_Constants.LOG_TAG, movie.toString());
                Log.i(Proc_Constants.LOG_TAG, "----------------------");
            }
        }
    }

    public static void testContentProvider(){
        // Test the query
        ProcrastinatorApplication.getContext().getContentResolver().query(
                ProcrastinatorDatabaseContract.MOVIES_URI, ProcrastinatorDatabaseContract.PROJECTION_FULL,
                null, null, null);

        // Test the insert
        ProcrastinatorApplication.getContext().getContentResolver().insert(
                ProcrastinatorDatabaseContract.MOVIES_URI, null);

        // Test the update
        ProcrastinatorApplication.getContext().getContentResolver().update(
                ProcrastinatorDatabaseContract.MOVIES_URI, null, null, null);

        // Test the delete
        ProcrastinatorApplication.getContext().getContentResolver().delete(
                ProcrastinatorDatabaseContract.MOVIES_URI, null, null);
    }
}
