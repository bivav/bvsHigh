package np.edu.bvs.bvshigh.firebase_notification;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;
import android.util.Log;

@SuppressLint("OverrideAbstract")
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class FirebaseNotificationListener extends NotificationListenerService {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            Bundle extras = sbn.getNotification().extras;

            if (extras.containsKey("android.text")) {
                if (extras.getCharSequence("android.text") != null) {
                    String text = extras.getCharSequence("android.text").toString();
                    Log.v("text_from_notification", "in onNotificationPosted(), Bundle.text != NULL, so here it is = " + text);
                }
            }

            if (extras.containsKey("android.text")) {
                if (extras.getCharSequence("android.text") != null) {
                    String textTitle = extras.getCharSequence("android.text").toString();
                    Log.v("text_from_notification", "in onNotificationPosted(), Bundle.text != NULL, so here it is = " + textTitle);
                }
            }

        }
    }
}
