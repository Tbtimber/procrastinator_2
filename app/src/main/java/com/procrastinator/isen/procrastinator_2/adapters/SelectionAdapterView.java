package com.procrastinator.isen.procrastinator_2.adapters;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.procrastinator.isen.procrastinator_2.R;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.DownloadImageAsyncTask;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.ImageMemoryCache;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.SearchResult;
import com.procrastinator.isen.procrastinator_2.interfaces.MainActivityListener;

import java.util.List;

/**
 * Created by Matthieu on 21/01/2016.
 */
public class SelectionAdapterView extends RecyclerView.Adapter<SelectionAdapterView.ViewHolder> {
    private List<SearchResult> mMovies;
    private LayoutInflater mInflater;
    private MainActivityListener mainActivityListener;

    public void setMainActivityListener(MainActivityListener mainActivityListener) {
        this.mainActivityListener = mainActivityListener;
    }

    @Override
    public SelectionAdapterView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.selection_layout, parent, false);
        ViewHolder holder = new ViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SearchResult item = mMovies.get(position);

        final Bitmap bm = ImageMemoryCache.getBitmapFromMemCache(item.poster_path);
        if(null == bm) {
            new DownloadImageAsyncTask(holder.pic).execute(item.poster_path);
        } else {
            holder.pic.setImageBitmap(bm);
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public SelectionAdapterView(Context context, List<SearchResult> movies) {
        mMovies = movies;
        mInflater = LayoutInflater.from(context);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory())/1024;
        final int cacheSize = maxMemory/16;
    }



    public class ViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView pic;
        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.pic = (ImageView) view.findViewById(R.id.poster);
        }

        @Override
        public void onClick(View v) {
            if(null != mainActivityListener) {
                final SearchResult result = mMovies.get(getPosition());
                mainActivityListener.showDetail(result);
            }
        }
    }
}
