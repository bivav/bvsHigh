package np.edu.bvs.bvshigh.students;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.general.Constants;
import np.edu.bvs.bvshigh.general.Contact_Us;
import np.edu.bvs.bvshigh.general.Request_Queue_Handler;
import np.edu.bvs.bvshigh.general.Select_Category;
import np.edu.bvs.bvshigh.general.SharedPrefManager;
import np.edu.bvs.bvshigh.general.about_college;
import np.edu.bvs.bvshigh.general.fragment_alerts;
import np.edu.bvs.bvshigh.general.fragment_calendar;
import np.edu.bvs.bvshigh.general.fragment_events;
import np.edu.bvs.bvshigh.general.message_us_alert_box;
import np.edu.bvs.bvshigh.students.login_students.Login_Student;
import np.edu.bvs.bvshigh.students.routine_bvs_students.fragment_routine;
import np.edu.bvs.bvshigh.teachers.fragment_Teachers_Contact;
import np.edu.bvs.bvshigh.teachers.fragment_assignment;

public class Main_Activity_Students extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**Right Slide Alert Function Values**/
    private String[] notification_titles, notification_message;
    private String TAG = "notificationData";

    de.hdodenhof.circleimageview.CircleImageView imageView;
    ListView rightView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        // Initialize the Firebase messaging service by passing subscribeToTopic method
        FirebaseMessaging.getInstance().subscribeToTopic("message");
        FirebaseInstanceId.getInstance().getToken();

        // Displaying DEVICE TOKEN, some devices the token doesn't work so just checking
        String displayToken = FirebaseInstanceId.getInstance().getToken();
        if (FirebaseInstanceId.getInstance().getToken() == null) {
            Toast.makeText(this, "Please turn on Internet Connection to receive Notifications.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Welcome!: -> " + displayToken, Toast.LENGTH_LONG).show();
        }

        Log.d(TAG, "DEVICE TOKEN ------> " + displayToken);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // check if the user is not logged in -> get the user in login page
        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(), Login_Student.class));
        }

        // Main Homepage activity aka Dashboard
        Dashboard_Student fragment = new Dashboard_Student();
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_activity, fragment);
        transaction.commit();

        // Setting up Side Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Setting up username and email for navigationView Drawer Activity
        View headerView = navigationView.getHeaderView(0);

        TextView student_name = (TextView) headerView.findViewById(R.id.student_name);
        student_name.setText(SharedPrefManager.getInstance(this).getFullName());

        imageView = (de.hdodenhof.circleimageview.CircleImageView)headerView.findViewById(R.id.profile_image);

        TextView student_email = (TextView) headerView.findViewById(R.id.student_email);
        student_email.setText(getResources().getString(R.string.brihaspati));

        //loading the image saved in above directory
        String path = String.valueOf(getApplicationContext().getDir("imageDir", Context.MODE_PRIVATE));
        loadImageFromStorage(path);

        // Calling the alerts layout and implementing using custom list view
        rightView = (ListView)findViewById(R.id.right_slide);

        pullNotification();

    }

    public void loadImageFromStorage(String pathImg) {

        try {
            File f = new File(pathImg, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            imageView.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
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

                            alertsDisplay alerts = new alertsDisplay(getApplicationContext(), notification_titles, notification_message);

                            rightView.setAdapter(alerts);

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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.alerts:
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.END);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {

        // Handle navigation view item clicks here.
        switch (item.getItemId()){

            case R.id.nav_dashboard:
                Dashboard_Student fragment_dash = new Dashboard_Student();
                android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_activity, fragment_dash);
                Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
                toolbar.setTitle(getResources().getString(R.string.bvs_high));
                transaction.commit();
                break;

            case R.id.nav_routine:
                startActivity(new Intent(getApplicationContext(), fragment_routine.class));
                break;

            case R.id.homework:
                startActivity(new Intent(getApplicationContext(), fragment_assignment.class));
                break;

            case R.id.nav_attendance:
                fragment_attendance fragment_attendance = new fragment_attendance();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_activity, fragment_attendance);
                transaction.commit();
                break;

            case R.id.nav_results:
                startActivity(new Intent(getApplicationContext(), fragment_result_student.class));
                break;

            case R.id.nav_fees:
                fragment_fees fragment_fees = new fragment_fees();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_activity, fragment_fees);
                toolbar = (Toolbar) findViewById(R.id.toolbar);
                toolbar.setTitle(getResources().getString(R.string.events));
                transaction.commit();
                break;

            case R.id.nav_events:
                fragment_events fragment_events = new fragment_events();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_activity, fragment_events);
                transaction.addToBackStack("BACK");
                transaction.commit();
                break;

            case R.id.nav_alerts:
                startActivity(new Intent(getApplicationContext(), fragment_alerts.class));
                break;

            case R.id.nav_teacherContact:
                fragment_Teachers_Contact fragment = new fragment_Teachers_Contact();
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_activity, fragment);
                transaction.addToBackStack("BACK");
                transaction.commit();
                break;

            case R.id.nav_calendar:
                startActivity(new Intent(getApplicationContext(), fragment_calendar.class));
                break;

            case R.id.contact_us:
                startActivity(new Intent(getApplicationContext(), Contact_Us.class));
                break;

            case R.id.about_college:
                startActivity(new Intent(getApplicationContext(), about_college.class));
                break;

            case R.id.feedback:
                startActivity(new Intent(getApplicationContext(), Dashboard_Students.class));
                break;

            case R.id.message_to_school:
                message_us_alert_box message_us_alert_box = new message_us_alert_box();
                message_us_alert_box.showDialog(this);
                break;

            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FirebaseInstanceId.getInstance().deleteInstanceId();
                            FirebaseInstanceId.getInstance().deleteToken("bvshigh-eddfd","FCM");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                SharedPrefManager.getInstance(this).isLoggedOut();
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Logging Out...");

                FirebaseInstanceId.getInstance().getId();

                finish();
                progressDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), Select_Category.class));
                break;

            default:
                finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}