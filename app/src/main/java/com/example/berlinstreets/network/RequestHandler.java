package com.example.berlinstreets.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestHandler {

    private static RequestHandler mInstance;
    private RequestQueue mRequestQueue;
    private Context mContext;

    /**
     * private constructor ensures that object of that
     * class can't be created somewhere else
     * (Singleton-Pattern)
     */
    private RequestHandler(Context context) {
        this.mContext = context;
        this.mRequestQueue = getRequestQueue();

    }

    public static synchronized RequestHandler getInstance(Context mContext) {
        if (mInstance == null) {
            mInstance = new RequestHandler(mContext);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
