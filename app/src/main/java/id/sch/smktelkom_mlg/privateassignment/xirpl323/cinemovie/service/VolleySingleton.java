package id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie.service;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie.ComingSoonFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie.NowPlayingFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl323.cinemovie.PopularFragment;

/**
 * Created by Nabila Basharahil on 5/13/2017.
 */

public class VolleySingleton {

    private static volatile VolleySingleton mInstance;
    private static NowPlayingFragment mCtx;
    private RequestQueue mRequestQueue;

    /*private VolleySingleton(Context context)
    {
        if (mInstance != null)
        {
            throw new RuntimeException(
                    "Use getInstance() method to get the single instance of this class");
        }
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }*/

    private VolleySingleton(NowPlayingFragment context) {
        if (mInstance != null) {
            throw new RuntimeException(
                    "Use getInstance() method to get the single instance of this class");
        }
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

   /* private VolleySingleton(PopularFragment context1) {
        if (mInstance != null) {
            throw new RuntimeException(
                    "Use getInstance() method to get the single instance of this class");
        }
        mCtx2 = context1;
        mRequestQueue = getRequestQueue();
    }

    private VolleySingleton(ComingSoonFragment context) {
        if (mInstance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class");
        }
        mCtx3 = context;
        mRequestQueue = getRequestQueue();
    } */


    public static VolleySingleton getInstance(NowPlayingFragment context) {
        if (mInstance == null) {
            synchronized (VolleySingleton.class) {
                if (mInstance == null) mInstance = new VolleySingleton(context);
            }
        }
        return mInstance;
    }

   public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getActivity());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}


   /* public static VolleySingleton getInstance(PopularFragment context1) {
        if (mInstance == null) {
            synchronized (VolleySingleton.class) {
                if (mInstance == null) mInstance = new VolleySingleton(context1);
            }
        }
        return mInstance;
    }

    public static VolleySingleton getInstance(ComingSoonFragment context2) {
        if (mInstance == null) {
            synchronized (VolleySingleton.class) {
                if (mInstance == null) mInstance = new VolleySingleton(context2);
            }
        }
        return mInstance;
    }*/

