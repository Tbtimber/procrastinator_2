package com.procrastinator.isen.procrastinator_2.imdbRetrieval;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by isen on 21/01/2016.
 */
public class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

    // The ImageView in which to display the image
    private final ImageView mImageView;

    // The cache object


    public DownloadImageAsyncTask (ImageView imageView){
        mImageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        // Retrieve the image url in params
        if ((null != params) && (params.length > 0)){
            final String imageUrl = params[0];
            try {
                final Bitmap bitmap = IMDbSearchHelper.getSearchResultImage(imageUrl);

                // Add to cache
                ImageMemoryCache.addBitmapToMemoryCache(imageUrl, bitmap);


                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if ((null != result) && (null != mImageView)){
            mImageView.setImageBitmap(result);
        }
    }
}