package com.procrastinator.isen.procrastinator_2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.procrastinator.isen.procrastinator_2.R;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.DownloadImageAsyncTask;
import com.procrastinator.isen.procrastinator_2.interfaces.MainActivityListener;
import com.procrastinator.isen.procrastinator_2.util.Proc_Constants;

/**
 * Created by Matthieu on 25/01/2016.
 */
public class DetailViewFragment extends android.support.v4.app.Fragment {
    private MainActivityListener mainActivityListener;
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


        return rootView;
    }
}
