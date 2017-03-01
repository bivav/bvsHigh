package np.edu.bvs.bvshigh;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

class Request_Queue_Handler{

    private static Request_Queue_Handler mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private Request_Queue_Handler(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    static synchronized Request_Queue_Handler getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Request_Queue_Handler(context);
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
