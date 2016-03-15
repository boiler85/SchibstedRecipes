package boiler.com.schibrecipe.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

// A singleton for loading images from web
public class ImageLoaderSingleton {

    private static ImageLoaderSingleton mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mContext;

    private ImageLoaderSingleton(Context context) {

        mContext = context;
        mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        LruBitmapCache cache = new LruBitmapCache(LruBitmapCache.getCacheSize(mContext));
        mImageLoader = new ImageLoader(mRequestQueue, cache);
    }

    public static synchronized ImageLoaderSingleton getInstance(Context ctx) {

        if (mInstance == null) {
            mInstance = new ImageLoaderSingleton(ctx);
        }
        return mInstance;
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
