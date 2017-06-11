package np.edu.bvs.bvshigh.firebase_notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

import np.edu.bvs.bvshigh.general.Main_Activity;
import np.edu.bvs.bvshigh.R;

import static android.content.ContentValues.TAG;


public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{


    public void onMessageReceived(RemoteMessage remoteMessage) {

        //Log data to Log Cat
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        //create notification
        createNotification(remoteMessage.getNotification().getBody());
//        showNotification(remoteMessage.getData().get("message"));
    }

    private void createNotification(String messageBody) {

        Intent intent = new Intent( this , Main_Activity.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultIntent = PendingIntent.getActivity(this , 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.bvs_logo_small)
                .setContentTitle("From Admin")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, mNotificationBuilder.build());
    }

}