package np.edu.bvs.bvshigh.teachers;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.general.Request_Queue_Handler;
import np.edu.bvs.bvshigh.general.SharedPrefManager;
import np.edu.bvs.bvshigh.interf.ItemTouchHelperCallback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class fragment_assignment extends AppCompatActivity {

    RecyclerView recyclerView;
    private int color = 0;
    SwipeRefreshLayout swipeRefreshLayout;
    private List<Assignment_Details> detailsList;
    Button select_due_date, post_assignment;
    private Calendar calendar;
    private EditText assignment_text;
    String getAssignment, getAssignmentDate, getAssignmentSubject, getAssignmentTeacherName;
    ProgressDialog progressDialog;
    String[] getAssignmentServer, getAssignmentDateServer, getAssignmentSubjectServer, getAssignmentTeacherNameServer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_assignment_recycler);

        calendar = Calendar.getInstance();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        initializeData();

        progressDialog = new ProgressDialog(fragment_assignment.this);
        progressDialog.setMessage("Posting..");
        progressDialog.setCancelable(false);

        assignment_text = (EditText)findViewById(R.id.assignment_text);
        select_due_date = (Button)findViewById(R.id.select_due_date);
        post_assignment = (Button)findViewById(R.id.post_assignment);

        select_due_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(fragment_assignment.this,
                        android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        select_due_date.setText(date);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        post_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getAssignment = assignment_text.getText().toString();
                        getAssignmentDate = select_due_date.getText().toString();
                        getAssignmentSubject = SharedPrefManager.getInstance(fragment_assignment.this).getBranch_Teachers();
                        getAssignmentTeacherName = SharedPrefManager.getInstance(fragment_assignment.this).getFullName_Teachers();

                        Log.i("getAssignment", getAssignmentTeacherName + " " + getAssignmentSubject + " " + getAssignmentDate
                                + " " + getAssignment);

                        storeAssignment(getAssignment, getAssignmentDate, getAssignmentSubject, getAssignmentTeacherName);
                    }
                }, 3000);
            }
        });



        recyclerView = (RecyclerView)findViewById(R.id.assignment_recycler);
        recyclerView.hasFixedSize();

        Recycler_Assignment_Adapter adapter = new Recycler_Assignment_Adapter(getApplicationContext(), detailsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout_recycler_view);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();

            }
        });

    }

    private void storeAssignment(String assignmentText, String assignmentDate, String assignmentSubject,
                                 String assignmentTeacherName) {

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("assignment", assignmentText)
                .add("datePosted", assignmentDate)
                .add("teacher", assignmentTeacherName)
                .add("subject", assignmentSubject)
                .build();

        Request request = new Request.Builder()
                .url("http://192.168.0.109/bvs_high/assignment/save_assignment.php")
                .post(requestBody)
                .build();

        try {
            okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assignment_text.setText("");
        select_due_date.setText(getResources().getString(R.string.due_date));
        progressDialog.dismiss();

    }

    class Assignment_Details {

        String[] getAssignmentServer;
        String[] getAssignmentDateServer;
        String[] getAssignmentSubjectServer;
        String[] getAssignmentTeacherNameServer;

        Assignment_Details(String[] mGetAssignmentServer, String[] mGetAssignmentDateServer, String[] mGetAssignmentSubjectServer,
                           String[] mGetAssignmentTeacherNameServer) {
            this.getAssignmentServer = mGetAssignmentServer;
            this.getAssignmentDateServer = mGetAssignmentDateServer;
            this.getAssignmentSubjectServer = mGetAssignmentSubjectServer;
            this.getAssignmentTeacherNameServer = mGetAssignmentTeacherNameServer;
        }
    }

    void onItemsLoadComplete(){

        Recycler_Assignment_Adapter adapter = new Recycler_Assignment_Adapter(getApplicationContext(), detailsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        Toast.makeText(fragment_assignment.this, "UPDATED!", Toast.LENGTH_SHORT).show();

        swipeRefreshLayout.setRefreshing(false);
    }

    public void initializeData() {
        detailsList = new ArrayList<>();

        StringRequest request = new StringRequest(
                com.android.volley.Request.Method.POST,
                "http://192.168.0.109/bvs_high/assignment/pull_assignment.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject;

                            getAssignmentServer = new String[jsonArray.length()];
                            getAssignmentDateServer = new String[jsonArray.length()];
                            getAssignmentSubjectServer = new String[jsonArray.length()];
                            getAssignmentTeacherNameServer = new String[jsonArray.length()];

                            for (int i=0; i<jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);
                                getAssignmentServer[i] = jsonObject.getString("assignment");
                                getAssignmentDateServer[i] = jsonObject.getString("datePosted");
                                getAssignmentSubjectServer[i] = jsonObject.getString("subject");;
                                getAssignmentTeacherNameServer[i] = jsonObject.getString("teacher");

                                detailsList.add(new Assignment_Details(getAssignmentServer, getAssignmentDateServer, getAssignmentSubjectServer, getAssignmentTeacherNameServer));

                            }
                            Log.i("fetched_from_server", Arrays.toString(getAssignmentDateServer) + Arrays.toString(getAssignmentServer) + Arrays.toString(getAssignmentSubjectServer)+Arrays.toString(getAssignmentTeacherNameServer));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("fetched_from_server", String.valueOf(error));

            }
        });

        Request_Queue_Handler.getInstance(fragment_assignment.this).addToRequestQueue(request);


        Log.i("Testing", String.valueOf(detailsList));
    }

    public void refreshData() {
        detailsList = new ArrayList<>();

        StringRequest request = new StringRequest(
                com.android.volley.Request.Method.POST,
                "http://192.168.0.109/bvs_high/assignment/pull_assignment.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject;

                            getAssignmentServer = new String[jsonArray.length()];
                            getAssignmentDateServer = new String[jsonArray.length()];
                            getAssignmentSubjectServer = new String[jsonArray.length()];
                            getAssignmentTeacherNameServer = new String[jsonArray.length()];

                            for (int i=0; i<jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);
                                getAssignmentServer[i] = jsonObject.getString("assignment");
                                getAssignmentDateServer[i] = jsonObject.getString("datePosted");
                                getAssignmentSubjectServer[i] = jsonObject.getString("subject");;
                                getAssignmentTeacherNameServer[i] = jsonObject.getString("teacher");

                                detailsList.add(new Assignment_Details(getAssignmentServer, getAssignmentDateServer, getAssignmentSubjectServer, getAssignmentTeacherNameServer));

                            }
                            Log.i("fetched_from_server", Arrays.toString(getAssignmentDateServer) + Arrays.toString(getAssignmentServer) + Arrays.toString(getAssignmentSubjectServer)+Arrays.toString(getAssignmentTeacherNameServer));

                            onItemsLoadComplete();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("fetched_from_server", String.valueOf(error));

            }
        });

        Request_Queue_Handler.getInstance(fragment_assignment.this).addToRequestQueue(request);


        Log.i("Testing", String.valueOf(detailsList));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}