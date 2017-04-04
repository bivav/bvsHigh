package np.edu.bvs.bvshigh;

import android.os.Build;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIDService";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + token);

        String deviceName = String.valueOf(Build.MODEL);
        registerToken(deviceName, token);

    }

    public void registerToken(String device, String token) {

        // Using OKHTTPClient to connect to remote database and fetching data
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("Device", device)
                .add("Token",token)
                .build();

        Request request = new Request.Builder().url("http://192.168.43.59/notification_bvs/register.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
