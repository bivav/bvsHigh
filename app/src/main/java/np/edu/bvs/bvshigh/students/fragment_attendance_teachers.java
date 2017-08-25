package np.edu.bvs.bvshigh.students;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.general.Constants;
import np.edu.bvs.bvshigh.general.Request_Queue_Handler;

public class fragment_attendance_teachers extends AppCompatActivity {

    String[] student_name, student_section;
    String[] section_A, section_B;

    RecyclerView recyclerView;

    List<Students_Attendance_List> section_B_list, section_A_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_attendance);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = (RecyclerView)findViewById(R.id.student_list_recycler);
        recyclerView.hasFixedSize();

        pullFromServer();

    }

    public void pullFromServer() {

        section_B_list = new ArrayList<>();
        section_A_list = new ArrayList<>();
        final List<String> tempListing = new ArrayList<>();
        final List<String> tempListing2 = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_STUDENT_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            // creating JSONObject to get data
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject object;

                            student_name = new String[jsonArray.length()];
                            student_section = new String[jsonArray.length()];

                            section_A = new String[jsonArray.length()];
                            section_B = new String[jsonArray.length()];

                            for (int i = 0; i<jsonArray.length(); i++) {
                                object = jsonArray.getJSONObject(i);
                                student_name[i] = object.getString("fullname");
                                student_section[i] = object.getString("sec");

                                if (student_section[i].equals("B")) {
                                    Log.i("all_Data_BB", student_name[i]);

                                    section_B[i] = student_name[i];

                                    if (section_B[i] != null) {
                                        tempListing.add(section_B[i]);
                                    }
                                } else {
                                    section_A[i] = student_name[i];
                                    if (section_A[i] != null) {
                                        tempListing2.add(section_A[i]);
                                    }
                                    Log.i("all_Data_AA", student_name[i]);
                                }
                            }

                            section_A = tempListing2.toArray(new String[tempListing2.size()]);

                            section_B = tempListing.toArray(new String [tempListing.size()]);

                            for (String aSection_B : section_B) {
                                section_B_list.add(new Students_Attendance_List(aSection_B));
                            }

                            for (String aSection_A : section_A) {
                                section_A_list.add(new Students_Attendance_List(aSection_A));
                            }

                            // getting details from server
                            Log.i("all_Data", Arrays.toString(section_B) + " \n " + Arrays.toString(section_A));

                            Bundle gettingExtraData = getIntent().getExtras();
                            if (gettingExtraData == null) {
                                return;
                            }

                            String getString = gettingExtraData.getString("sendingSectionValue");
                            Log.i("dude", getString);

                            if (getString.equals("A")) {
                                Recycler_Attendance_Adapter recycler_attendance_adapter =
                                        new Recycler_Attendance_Adapter(getApplicationContext(), section_A_list);

                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(linearLayoutManager);
                                recyclerView.setAdapter(recycler_attendance_adapter);
                            } else if (getString.equals("B")) {
                                Recycler_Attendance_Adapter recycler_attendance_adapter =
                                        new Recycler_Attendance_Adapter(getApplicationContext(), section_B_list);

                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(linearLayoutManager);
                                recyclerView.setAdapter(recycler_attendance_adapter);
                            }





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Can't Connect. Error : "+ error, Toast.LENGTH_LONG).show();
            }
        }
        );

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                8000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Request_Queue_Handler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }


}
