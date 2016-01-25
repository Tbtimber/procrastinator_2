package com.procrastinator.isen.procrastinator_2.adapters;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.procrastinator.isen.procrastinator_2.R;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.DownloadImageAsyncTask;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.ImageMemoryCache;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.SearchResult;
import com.procrastinator.isen.procrastinator_2.interfaces.SelectionListener;

import java.util.List;

/**
 * Created by Matthieu on 21/01/2016.
 */
public class GridViewAdapter extends BaseAdapter {
    private List<SearchResult> mMovies;
    private ImageMemoryCache mMemoryCache;
    private LayoutInflater mInflater;

    public GridViewAdapter(Context context, List<SearchResult> mMovies) {
        this.mMovies = mMovies;
        mInflater = LayoutInflater.from(context);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory())/1024;
        final int cacheSize = maxMemory/16;
        mMemoryCache = new ImageMemoryCache(cacheSize);
    }

    @Override
    public int getCount() {
        return null != mMovies ? mMovies.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null != mMovies ? mMovies.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(null == convertView) {
            convertView = mInflater.inflate(R.layout.selection_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final SearchResult item = (SearchResult) getItem(position);
        final Bitmap bm = mMemoryCache.getBitmapFromMemCache(item.poster_path);
        if(null == bm) {
            new DownloadImageAsyncTask(holder.pic, mMemoryCache).execute(item.poster_path);
        } else {
            holder.pic.setImageBitmap(bm);
        }

        return convertView;
    }


    private class ViewHolder {
        private ImageView pic;

        public ViewHolder(View view) {
            this.pic = (ImageView) view.findViewById(R.id.poster);
        }
    }
}
