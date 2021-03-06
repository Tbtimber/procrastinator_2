package com.procrastinator.isen.procrastinator_2.imdbRetrieval;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.util.Objects;

/**
 * Created by isen on 21/01/2016.
 */
public class ImageMemoryCache{
    // The cache object
    final static int maxMemory = (int) (Runtime.getRuntime().maxMemory())/1024;
    final static int cacheSize = maxMemory/16;
    private static LruCache<String, Bitmap> mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
            return (bitmap.getRowBytes() * bitmap.getHeight()) / 1024;
        }
    };

    public ImageMemoryCache(int maxCacheSize){
        mMemoryCache = new LruCache<String, Bitmap>(maxCacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return (bitmap.getRowBytes() * bitmap.getHeight()) / 1024;
            }
        };
    }

    public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public static Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
}