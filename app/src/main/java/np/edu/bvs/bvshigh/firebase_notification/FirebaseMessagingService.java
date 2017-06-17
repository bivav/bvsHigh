package np.edu.bvs.bvshigh.firebase_notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;

import np.edu.bvs.bvshigh.general.Constants;
import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.general.fragment_alerts;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static android.content.ContentValues.TAG;


public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{

    public void onMessageReceived(RemoteMessage remoteMessage) {

        //Log data to Log Cat
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification_Message_Body: " + remoteMessage.getNotification().getTitle()
                + " -- " + remoteMessage.getNotification().getBody());

        //create notification
        createNotification(remoteMessage.getNotification().getBody());

        //save title and message into server
        saveNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());

    }

    private void createNotification(String messageBody) {

        Intent intent = new Intent( this , fragment_alerts.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent resultIntent = PendingIntent.getActivity(this , 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.bvs_logo_small)
                .setContentTitle("From Admin")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, mNotificationBuilder.build());
    }

    public void saveNotification(String mTitles, String mNotifications) {

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("titles", mTitles)
                .add("notifications", mNotifications)
                .build();

        Request request = new Request.Builder()
                .url(Constants.URL_SAVE_NOTIFICATIONS)
                .post(requestBody)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}