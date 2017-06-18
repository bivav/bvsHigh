package np.edu.bvs.bvshigh.students.firebase_notification;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;

import np.edu.bvs.bvshigh.general.fragment_alerts;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class FirebaseNotificationListener extends NotificationListenerService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {

            Notification notification = sbn.getNotification();
            if (notification != null) {
                Bundle extras;
                extras = notification.extras;
                Intent intent = new Intent(fragment_alerts.INTENT_ACTION_NOTIFICATION);
                intent.putExtras(extras);
                sendBroadcast(intent);
            }
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }
}
