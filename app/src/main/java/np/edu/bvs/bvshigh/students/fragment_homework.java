package np.edu.bvs.bvshigh.students;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.general.Constants;
import np.edu.bvs.bvshigh.general.Request_Queue_Handler;
import np.edu.bvs.bvshigh.interf.ItemTouchHelperCallback;

public class fragment_homework extends AppCompatActivity {

    RecyclerView recyclerView;
    private int color = 0;
    SwipeRefreshLayout swipeRefreshLayout;
    private List<Assignment_Details_Students> detailsList;

    ProgressDialog progressDialog;
    String[] getAssignmentServer, getAssignmentDateServer, getAssignmentSubjectServer, getAssignmentTeacherNameServer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_homework_customlist_view);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        initializeData();

        progressDialog = new ProgressDialog(fragment_homework.this);
        progressDialog.setMessage("Posting..");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        recyclerView = (RecyclerView)findViewById(R.id.assignment_recycler);
        recyclerView.hasFixedSize();

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


    public class Assignment_Details_Students {

        String[] getAssignmentServer;
        String[] getAssignmentDateServer;
        String[] getAssignmentSubjectServer;
        String[] getAssignmentTeacherNameServer;

        Assignment_Details_Students(String[] mGetAssignmentServer,
                                    String[] mGetAssignmentDateServer,
                                    String[] mGetAssignmentSubjectServer,
                                    String[] mGetAssignmentTeacherNameServer) {

            this.getAssignmentServer = mGetAssignmentServer;
            this.getAssignmentDateServer = mGetAssignmentDateServer;
            this.getAssignmentSubjectServer = mGetAssignmentSubjectServer;
            this.getAssignmentTeacherNameServer = mGetAssignmentTeacherNameServer;
        }
    }

    void onItemsLoadComplete(){

        Recycler_Homework_Adapter adapter = new Recycler_Homework_Adapter(getApplicationContext(), detailsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        Toast.makeText(fragment_homework.this, "UPDATED!", Toast.LENGTH_SHORT).show();

        swipeRefreshLayout.setRefreshing(false);
    }

    public void initializeData() {
        detailsList = new ArrayList<>();

        StringRequest request = new StringRequest(
                com.android.volley.Request.Method.POST,
                Constants.URL_ASSIGNMENT_PULL,
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

                                detailsList.add(new Assignment_Details_Students(getAssignmentServer, getAssignmentDateServer, getAssignmentSubjectServer, getAssignmentTeacherNameServer));

                            }
                            Log.i("fetched_from_server", Arrays.toString(getAssignmentDateServer) + Arrays.toString(getAssignmentServer) + Arrays.toString(getAssignmentSubjectServer)+Arrays.toString(getAssignmentTeacherNameServer));

                            Recycler_Homework_Adapter adapter = new Recycler_Homework_Adapter(getApplicationContext(), detailsList);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(adapter);

                            ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
                            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
                            itemTouchHelper.attachToRecyclerView(recyclerView);

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

        Request_Queue_Handler.getInstance(fragment_homework.this).addToRequestQueue(request);


        Log.i("Testing", String.valueOf(detailsList));
    }

    public void refreshData() {
        detailsList = new ArrayList<>();

        StringRequest request = new StringRequest(
                com.android.volley.Request.Method.POST,
                Constants.URL_ASSIGNMENT_PULL,
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

                                detailsList.add(new Assignment_Details_Students(getAssignmentServer, getAssignmentDateServer, getAssignmentSubjectServer, getAssignmentTeacherNameServer));

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

        Request_Queue_Handler.getInstance(fragment_homework.this).addToRequestQueue(request);


        Log.i("Testing", String.valueOf(detailsList));
    }


}
