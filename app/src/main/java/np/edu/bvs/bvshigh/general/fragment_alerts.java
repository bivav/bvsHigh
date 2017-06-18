package np.edu.bvs.bvshigh.general;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

import np.edu.bvs.bvshigh.R;


public class fragment_alerts extends AppCompatActivity {

    private ListView alerts_display;
    public static String INTENT_ACTION_NOTIFICATION = "np.edu.bvs.bvshigh.general.notification";
    private String[] notification_titles, notification_message;
    private String TAG = "notificationData";
    private alertsDisplay alertsDisplay;
    private ProgressDialog dialog;
    private SwipeRefreshLayout swipeRefreshLayout;

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

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.update_notifications);

        dialog = new ProgressDialog(fragment_alerts.this);
        dialog.setIndeterminate(false);
        dialog.setMessage("Please wait");
        dialog.setCancelable(false);
        dialog.show();

        alerts_display = (ListView)findViewById(R.id.alerts_display);

        pullNotification();

        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.google_red),
                ContextCompat.getColor(this, R.color.google_yellow),
                ContextCompat.getColor(this, R.color.google_blue),
                ContextCompat.getColor(this, R.color.google_green)
        );

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullNotification();
            }
        });

    }

    private void pullNotification(){

        StringRequest request = new StringRequest(Request.Method.POST,
                Constants.URL_NOTIFICATIONS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject;

                            notification_titles = new String[jsonArray.length()];
                            notification_message = new String[jsonArray.length()];

                            for (int i = 0; i<jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);
                                notification_titles[i] = jsonObject.getString("titles");
                                notification_message[i] = jsonObject.getString("notifications");
                                Log.i(TAG, notification_titles[i] + " --- " + notification_message[i]);

                            }

                            alertsDisplay = new alertsDisplay(getApplicationContext(),
                                    notification_titles,
                                    notification_message);

                            alerts_display.setAdapter(alertsDisplay);

                            dialog.dismiss();
                            swipeRefreshLayout.setRefreshing(false);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "NO Notifications are available");

            }
        });

        Request_Queue_Handler.getInstance(this).addToRequestQueue(request);
    }

    private class alertsDisplay extends ArrayAdapter {

        String[] titles_alert;
        String[] description_alert;

        alertsDisplay(Context context, String[] mTitles, String[] mDescription) {
            //noinspection unchecked
            super(context, R.layout.fragment_alerts, R.id.title_alerts, mTitles);

            this.titles_alert = mTitles;
            this.description_alert = mDescription;
        }

        class ViewHolder {
            protected TextView titles;
            TextView description;

        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            ViewHolder viewHolder;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.fragment_alerts, parent, false);

                viewHolder = new ViewHolder();

                String date = DateFormat.getDateInstance().format(new Date());

                TextView alert_date = (TextView) convertView.findViewById(R.id.alert_date);
                alert_date.setText(date);

                viewHolder.titles = (TextView) convertView.findViewById(R.id.title_alerts);
                viewHolder.description = (TextView) convertView.findViewById(R.id.alert_description);

                convertView.setTag(viewHolder);
                convertView.setTag(R.id.title_alerts, viewHolder.titles);
                convertView.setTag(R.id.alert_description, viewHolder.description);

            } else {
                viewHolder = (ViewHolder)convertView.getTag();
            }

            viewHolder.titles.setTag(position);
            viewHolder.titles.setText(titles_alert[position]);
            viewHolder.description.setTag(position);
            viewHolder.description.setText(description_alert[position]);

            Log.i("alerts are as follows", titles_alert[position] + " --- " + description_alert[position]);

            return convertView;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}