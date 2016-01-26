package com.procrastinator.isen.procrastinator_2.fragments;

import android.app.ActionBar;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.procrastinator.isen.procrastinator_2.ProcrastinatorApplication;
import com.procrastinator.isen.procrastinator_2.R;
import com.procrastinator.isen.procrastinator_2.adapters.TrackedAdapterView;
import com.procrastinator.isen.procrastinator_2.database.ProcrastinatorDataBaseHelper;
import com.procrastinator.isen.procrastinator_2.database.ProcrastinatorDatabaseContract;
import com.procrastinator.isen.procrastinator_2.database.ProcrastinatorDatabaseManager;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.SearchResult;
import com.procrastinator.isen.procrastinator_2.interfaces.MainActivityListener;
import com.procrastinator.isen.procrastinator_2.interfaces.SelectionListener;
import com.procrastinator.isen.procrastinator_2.providers.ProcrastinatorDatabaseProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthieu on 26/01/2016.
 */
public class TrackedFragment extends Fragment implements AdapterView.OnItemClickListener{
    private ListView mListView;
    private MainActivityListener mainActivityListener;
    private List<SearchResult> mList;
    private ProcrastinatorDatabaseProvider provider;
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivityListener) {
            mainActivityListener = (MainActivityListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tracked_fragment, container, false);


        mListView = (ListView) rootView.findViewById(R.id.tracked_listview);
        //mListView.setOnItemClickListener(this);
        /*final ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        mListView.setEmptyView(progressBar);*/
        final ProcrastinatorDataBaseHelper dbHelper = new ProcrastinatorDataBaseHelper(getContext());
        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(ProcrastinatorDatabaseContract.TABLE_MOVIES, ProcrastinatorDatabaseContract.PROJECTION_FULL, null, null, null, null, null);
        if(mList == null) {
            mList = new ArrayList<>();
        }
        //TODO améliorer la récupération des données plus manière de gérer la DB parce que la c'est bourrin (mais ça marche ...)
        if(null != cursor) {
            while (cursor.moveToNext()) {
                mList.add(ProcrastinatorDatabaseManager.movieFromCursor(cursor));
                Log.w("DB helper" , "Added one movie to mList");
            }
            if(!cursor.isClosed()) {
                cursor.close();
            }
        }
        TrackedAdapterView adapterView = new TrackedAdapterView(getContext(),mList);
        mListView.setAdapter(adapterView);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*if(null != mainActivityListener) {
            final SearchResult result = (SearchResult) parent.getItemAtPosition((int)id);
            mainActivityListener.showDetail(result);
        }*/
    }

}
