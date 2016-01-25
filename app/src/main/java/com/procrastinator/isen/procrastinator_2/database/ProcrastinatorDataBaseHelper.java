package com.procrastinator.isen.procrastinator_2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Théophane MARTEAU on 18/01/2016.
 */
public class ProcrastinatorDataBaseHelper extends SQLiteOpenHelper {

    // database version
    private static final int DATABASE_VERSION = 1;

    // database name
    private static final String DATABASE_NAME = "movies.db";

    public ProcrastinatorDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProcrastinatorDatabaseContract.TABLE_MOVIES_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ProcrastinatorDatabaseContract.TABLE_MOVIES);
        onCreate(db);
    }

}
