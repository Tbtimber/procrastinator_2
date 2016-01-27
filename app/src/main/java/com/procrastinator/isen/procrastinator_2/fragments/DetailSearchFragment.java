package com.procrastinator.isen.procrastinator_2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.procrastinator.isen.procrastinator_2.R;
import com.procrastinator.isen.procrastinator_2.interfaces.MainActivityListener;

/**
 * Created by Matthieu on 25/01/2016.
 */
public class DetailSearchFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private View mView;
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
        View rootView = inflater.inflate(R.layout.detail_search_fragment, container, false);
        Button searchB = (Button) rootView.findViewById(R.id.detail_search_b);
        searchB.setOnClickListener(this);
        mView = rootView;
        return rootView;
    }

    @Override
    public void onClick(View v) {
        String s = "";
        if (((CheckBox)mView.findViewById(R.id.checkBox_movie)).isChecked()){
            s = "http://api.themoviedb.org/3/discover/movie?api_key=70bf5590ea8b078fe3130b64859b479e";
            int length = s.length();
            if (((CheckBox)mView.findViewById(R.id.checkBox_action)).isChecked()){
                if (s.length() == length) {
                    s += "&with_genres=";
                    s += "28,";
                } else {
                    s += "28,";
                }
            }
            if (((CheckBox)mView.findViewById(R.id.checkBox_adventure)).isChecked()){
                if (s.length() == length) {
                    s += "&with_genres=";
                    s += "12,";
                } else {
                    s += "12,";
                }

            }
            if (((CheckBox)mView.findViewById(R.id.checkBox_comedy)).isChecked()){
                if (s.length() == length) {
                    s += "&with_genres=";
                    s += "35,";
                } else {
                    s += "35,";
                }
            }
            if (((CheckBox)mView.findViewById(R.id.checkBox_documentary)).isChecked()){
                if (s.length() == length) {
                    s += "with_genres=";
                    s += "99,";
                } else {
                    s += "99,";
                }
            }
            if (((CheckBox)mView.findViewById(R.id.checkBox_drama)).isChecked()){
                if (s.length() == length) {
                    s += "&with_genres=";
                    s += "18,";
                } else {
                    s += "18,";
                }
            }
            if (((CheckBox)mView.findViewById(R.id.checkBox_horror)).isChecked()){
                if (s.length() == length) {
                    s += "&with_genres=";
                    s += "27,";
                } else {
                    s += "27,";
                }
            }
            if (((CheckBox)mView.findViewById(R.id.checkBox_romance)).isChecked()){
                if (s.length() == length) {
                    s += "&with_genres=";
                    s += "10749,";
                } else {
                    s += "10749,";
                }
            }
            if (((CheckBox)mView.findViewById(R.id.checkBox_sci_fi)).isChecked()){
                if (s.length() == length) {
                    s += "&with_genres=";
                    s += "878,";
                } else {
                    s += "878,";
                }
            }

            else if (((CheckBox)mView.findViewById(R.id.checkBox_TV_show)).isChecked()){
                s = "http://api.themoviedb.org/3/discover/tv?api_key=70bf5590ea8b078fe3130b64859b479e";
                length = s.length();
                if (((CheckBox)mView.findViewById(R.id.checkBox_action)).isChecked()){
                    if (s.length() == length) {
                        s += "&with_genres=";
                        s += "28,";
                    } else {
                        s += "28,";
                    }
                }
                if (((CheckBox)mView.findViewById(R.id.checkBox_adventure)).isChecked()){
                    if (s.length() == length) {
                        s += "&with_genres=";
                        s += "12,";
                    } else {
                        s += "12,";
                    }

                }
                if (((CheckBox)mView.findViewById(R.id.checkBox_comedy)).isChecked()){
                    if (s.length() == length) {
                        s += "&with_genres=";
                        s += "35,";
                    } else {
                        s += "35,";
                    }
                }
                if (((CheckBox)mView.findViewById(R.id.checkBox_documentary)).isChecked()){
                    if (s.length() == length) {
                        s += "with_genres=";
                        s += "99,";
                    } else {
                        s += "99,";
                    }
                }
                if (((CheckBox)mView.findViewById(R.id.checkBox_drama)).isChecked()){
                    if (s.length() == length) {
                        s += "&with_genres=";
                        s += "18,";
                    } else {
                        s += "18,";
                    }
                }
                if (((CheckBox)mView.findViewById(R.id.checkBox_horror)).isChecked()){
                    if (s.length() == length) {
                        s += "&with_genres=";
                        s += "27,";
                    } else {
                        s += "27,";
                    }
                }
                if (((CheckBox)mView.findViewById(R.id.checkBox_romance)).isChecked()){
                    if (s.length() == length) {
                        s += "&with_genres=";
                        s += "10749,";
                    } else {
                        s += "10749,";
                    }
                }
                if (((CheckBox)mView.findViewById(R.id.checkBox_sci_fi)).isChecked()){
                    if (s.length() == length) {
                        s += "&with_genres=";
                        s += "878,";
                    } else {
                        s += "878,";
                    }
                }
            }

            mainActivityListener.searchWithDetail(s);

        }
    }
}
