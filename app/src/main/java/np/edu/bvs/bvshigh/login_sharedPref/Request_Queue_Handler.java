package np.edu.bvs.bvshigh.login_sharedPref;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

class Request_Queue_Handler{

    @SuppressLint("StaticFieldLeak")
    private static Request_Queue_Handler mInstance;
    private RequestQueue mRequestQueue;
    private Context mCtx;

    private Request_Queue_Handler(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized Request_Queue_Handler getInstance(Context context) {
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

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
