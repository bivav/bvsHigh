package np.edu.bvs.bvshigh.general;


import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

import np.edu.bvs.bvshigh.R;


public class fragment_alerts extends AppCompatActivity {

    String[] titles_alert = {"Results of Class 11 is out", "Routine for Class 12", "Come and Enjoy"};
    String[] description_alert =
            {
                    "Results for class 11 is out. Please check results tab and refresh to download the result",
                    "Routine has been updated for grade 12. Kindly update.",
                    "Welcome to BVS to all students!! Hope you have a good time."
            };

    ListView alerts_display;
    TextView alerts, sub_text;
    public static String INTENT_ACTION_NOTIFICATION = "np.edu.bvs.bvshigh.general.notification";

    private MyReceiver myReceiver;
    String notificationTitle;
    CharSequence notificationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_alerts_customlist_view);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        alerts = (TextView)findViewById(R.id.title_alerts_new);
        sub_text = (TextView)findViewById(R.id.sub_text_new);

        alerts_display = (ListView)findViewById(R.id.alerts_display);
        alertsDisplay alerts = new alertsDisplay(getApplicationContext(), titles_alert, description_alert);
        alerts_display.setAdapter(alerts);

        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, new IntentFilter(INTENT_ACTION_NOTIFICATION));

    }

    private class alertsDisplay extends ArrayAdapter {

        String[] titles_alert;
        String[] description_alert;

        alertsDisplay(Context context, String[] mtitles, String[] mDescription) {
            //noinspection unchecked
            super(context, R.layout.fragment_alerts, R.id.title_alerts, mtitles);

            this.titles_alert = mtitles;
            this.description_alert = mDescription;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.fragment_alerts, parent, false);

            String date = DateFormat.getDateInstance().format(new Date());

            TextView alert_date = (TextView)view.findViewById(R.id.alert_date);
            alert_date.setText(date);

            TextView titles = (TextView)view.findViewById(R.id.title_alerts);
            TextView description = (TextView)view.findViewById(R.id.alert_description);

            titles.setText(titles_alert[position]);
            description.setText(description_alert[position]);

            return view;
        }
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent != null) {
                Bundle extras = intent.getExtras();
                notificationTitle = extras.getString(Notification.EXTRA_TITLE);
                notificationText = extras.getCharSequence(Notification.EXTRA_TEXT);

                Log.i("testingNotification", notificationTitle + "___" + notificationText);

                alerts.setText(notificationTitle);
                sub_text.setText(notificationText);
            } else {
                Log.i("elseResult", notificationTitle + " ___ " + notificationText);

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (myReceiver == null) myReceiver = new MyReceiver();
        registerReceiver(myReceiver, new IntentFilter(INTENT_ACTION_NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("testingNotification", notificationTitle + " ___ " + notificationText);
        unregisterReceiver(myReceiver);
    }

}