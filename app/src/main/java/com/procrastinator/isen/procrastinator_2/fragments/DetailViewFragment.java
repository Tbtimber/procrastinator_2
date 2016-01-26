package com.procrastinator.isen.procrastinator_2.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.procrastinator.isen.procrastinator_2.ProcrastinatorApplication;
import com.procrastinator.isen.procrastinator_2.R;
import com.procrastinator.isen.procrastinator_2.database.ProcrastinatorDataBaseHelper;
import com.procrastinator.isen.procrastinator_2.database.ProcrastinatorDatabaseContract;
import com.procrastinator.isen.procrastinator_2.database.ProcrastinatorDatabaseManager;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.DownloadImageAsyncTask;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.Movie;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.SearchResult;
import com.procrastinator.isen.procrastinator_2.interfaces.MainActivityListener;
import com.procrastinator.isen.procrastinator_2.util.Proc_Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthieu on 25/01/2016.
 */
public class DetailViewFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private MainActivityListener mainActivityListener;
    private SearchResult searchResult;
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
        View rootView = inflater.inflate(R.layout.detail_view_fragment, container, false);

        rootView.findViewById(R.id.transparentBackground).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainActivityListener != null)
                    mainActivityListener.dropDetail();
            }
        });

        String url = getArguments().getString(Proc_Constants.IMAGE_URL);
        String overview = getArguments().getString(Proc_Constants.OVERVIEW);
        String title = getArguments().getString(Proc_Constants.DETAIL_TITLE);

        searchResult = new Movie();
        searchResult.poster_path = url;
        searchResult.setTitle(title);
        searchResult.overview = overview;

        if(url != null) {
            ImageView imgV = (ImageView) rootView.findViewById(R.id.imagePoster);
            new DownloadImageAsyncTask(imgV).execute(url);
        }
        if(overview != null) {
            TextView ovTV = (TextView) rootView.findViewById(R.id.textSummary);
            ovTV.setText(overview);
        }
        if(overview != null) {
            TextView tTV = (TextView) rootView.findViewById(R.id.textTitle);
            tTV.setText(title);
        }

        Button trackedButton = (Button) rootView.findViewById(R.id.buttonTrack);
        trackedButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        //mainActivityListener.hasTracked(searchResult.getTitle());
        //TODO insert searchResult To DB
        //List<SearchResult> list = new ArrayList<>();
        //list.add(searchResult);
        final ProcrastinatorDataBaseHelper dbHelper = new ProcrastinatorDataBaseHelper(getContext());
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(ProcrastinatorDatabaseContract.TABLE_MOVIES,"", ProcrastinatorDatabaseManager.movieToContentValues(searchResult));
    }

}
