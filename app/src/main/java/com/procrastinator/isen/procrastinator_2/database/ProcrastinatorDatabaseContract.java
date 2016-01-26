package com.procrastinator.isen.procrastinator_2.database;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Théophane MARTEAU on 21/01/2016.
 */
public class ProcrastinatorDatabaseContract implements BaseColumns {

    // Field names
    public static final String RELEASE_DATE = "releaseDate";
    public static final String PLOT = "plot";
    public static final String TITLE = "title";
    public static final String MOVIE_PICTURE = "moviePicture";
    public static final String GENRE = "genre";
    public static final String TYPE = "type";
    public static final String DIRECTOR = "director";
    public static final String WRITER = "writer";
    public static final String ACTORS = "actors";
    public static final String RUNTIME = "runtime";
    public static final String COUNTRY = "country";


    // db name
    public static final String TABLE_MOVIES = "movies";

    // Table scripts creation
    private static final String TABLE_GENERIC_CREATE_SCRIPT_PREFIX = "CREATE TABLE IF NOT EXISTS ";
    private static final String TABLE_IMAGES_CREATE_SCRIPT_SUFFIX = "(" + _ID + " INTEGER PRIMARY KEY, " +
            //RELEASE_DATE + " TEXT , " +
            PLOT + " TEXT NOT NULL, "+
            TITLE + " TEXT NOT NULL, "+
            MOVIE_PICTURE + " TEXT NOT NULL "+
            //GENRE + " TEXT , "+
            //TYPE + " TEXT , "+
            //DIRECTOR + " TEXT , "+
            //WRITER + " TEXT , "+
            //ACTORS + " TEXT , "+
            //RUNTIME + " TEXT , "+
            /*COUNTRY + " TEXT */")";

    public static final String TABLE_MOVIES_CREATE_SCRIPT = TABLE_GENERIC_CREATE_SCRIPT_PREFIX +
            TABLE_MOVIES + TABLE_IMAGES_CREATE_SCRIPT_SUFFIX;

    // The projections
    public static final String[] PROJECTION_FULL = new String[]{
            _ID,
            //RELEASE_DATE,
            PLOT,
            TITLE,
            MOVIE_PICTURE,
            //GENRE,
            //TYPE,
            //DIRECTOR,
            //WRITER,
            //ACTORS,
            //RUNTIME,
            //COUNTRY
    };

    // Selections
    public static final String SELECTION_BY_ID = _ID + "=?";
    public static final String SELECTION_BY_RELEASE_DATE = RELEASE_DATE + "=?";
    public static final String SELECTION_BY_TITLE = TITLE + "=?";
    public static final String SELECTION_BY_GENRE = GENRE + "=?";
    public static final String SELECTION_BY_TYPE = TYPE + "=?";
    public static final String SELECTION_BY_DIRECTOR = DIRECTOR + "=?";
    public static final String SELECTION_BY_ACTORS = ACTORS + "=?";
    public static final String SELECTION_BY_COUNTRY = COUNTRY + "=?";

    // Content provider
    public static final String CONTENT_PROVIDER_MOVIES_AUTHORITY = "com.procrastinator.isen.procrastinator_2.databaseAuthority";
    public static final Uri MOVIES_URI = Uri.parse("content://" + CONTENT_PROVIDER_MOVIES_AUTHORITY + "/" + TABLE_MOVIES);
    public static final String MOVIES_CONTENT_TYPE = "vnd.android.cursor.dir/vnd.wltwitter.tweets";
}
