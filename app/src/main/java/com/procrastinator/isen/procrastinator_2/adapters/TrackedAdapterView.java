package com.procrastinator.isen.procrastinator_2.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.procrastinator.isen.procrastinator_2.R;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.DownloadImageAsyncTask;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.ImageMemoryCache;
import com.procrastinator.isen.procrastinator_2.imdbRetrieval.SearchResult;
import com.procrastinator.isen.procrastinator_2.interfaces.SelectionListener;

import java.util.List;

/**
 * TODO implement class + get tracked running ! (Need to look at sql)
 * Created by Matthieu on 26/01/2016.
 */
public class TrackedAdapterView extends BaseAdapter {
    private List<SearchResult> mMovies;
    private LayoutInflater mInflater;

    public TrackedAdapterView(Context context,List<SearchResult> searchResults) {
        this.mMovies = searchResults;
        mInflater = LayoutInflater.from(context);
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
            convertView = mInflater.inflate(R.layout.tracked_fragment_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final SearchResult item = (SearchResult) getItem(position);
        final Bitmap bm = ImageMemoryCache.getBitmapFromMemCache(item.poster_path);
        if(null == bm) {
            new DownloadImageAsyncTask(holder.pic).execute(item.poster_path);
        } else {
            holder.pic.setImageBitmap(bm);
        }
        holder.title.setText(item.getTitle());

        return convertView;

    }

    private class ViewHolder {
        private ImageView pic;
        private TextView title;

        public ViewHolder(View view) {

            this.pic = (ImageView) view.findViewById(R.id.image_tracked_Poster);
            this.title = (TextView) view.findViewById(R.id.text_tracked_Title);
        }
    }
}
