package com.procrastinator.isen.procrastinator_2.providers;

import android.content.ContentProvider;
import android.util.Log;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;


import com.procrastinator.isen.procrastinator_2.database.ProcrastinatorDataBaseHelper;
import com.procrastinator.isen.procrastinator_2.database.ProcrastinatorDatabaseContract;
import com.procrastinator.isen.procrastinator_2.util.Proc_Constants;


/**
 * Created by Théophane MARTEAU on 21/01/2016.
 */
public class ProcrastinatorDatabaseProvider extends ContentProvider { // à déclarer dans le manifeste
    private UriMatcher mUriMatcher;
    private ProcrastinatorDataBaseHelper mDBHelper;
    private static final int MOVIES_CORRECT_URI_CODE = 42;

    @Override
    public boolean onCreate() {
        mDBHelper = new ProcrastinatorDataBaseHelper(getContext());
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(ProcrastinatorDatabaseContract.CONTENT_PROVIDER_MOVIES_AUTHORITY,
                ProcrastinatorDatabaseContract.TABLE_MOVIES, MOVIES_CORRECT_URI_CODE);
        return true;
    }

    @Override
    public int delete(Uri arg0, String arg1, String[] arg2) {
        Log.e(Proc_Constants.LOG_TAG, "DELETE");
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        if (mUriMatcher.match(uri) == MOVIES_CORRECT_URI_CODE){
            return ProcrastinatorDatabaseContract.MOVIES_CONTENT_TYPE;
        }
        throw new IllegalArgumentException("Unknown URI " + uri);
    }

    @Override
    public Uri insert(Uri arg0, ContentValues arg1) {
        Log.i(Proc_Constants.LOG_TAG, "INSERT");
        return null;
    }

    @Override
    public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3, String arg4) {
        Log.w(Proc_Constants.LOG_TAG, "QUERY");
        return null;
    }

    @Override
    public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
        Log.d(Proc_Constants.LOG_TAG, "UPDATE");
        return 0;
    }
}
