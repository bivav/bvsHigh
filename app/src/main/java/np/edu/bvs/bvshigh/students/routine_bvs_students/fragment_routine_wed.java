package np.edu.bvs.bvshigh.students.routine_bvs_students;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import np.edu.bvs.bvshigh.general.Constants;
import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.sqLite_handler.DatabaseManager;
import np.edu.bvs.bvshigh.sqLite_handler.MyDBHandler;
import np.edu.bvs.bvshigh.sqLite_handler.Routine_Database;


public class fragment_routine_wed extends Fragment {

    FirebaseDatabase firebaseDatabase;
    String[] start_time, end_time, subject, teacher;
    ArrayList arrayList;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ListView listView;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine_customlist_view, container, false);

        // DATE AND DAY
        TextView current_date = (TextView)view.findViewById(R.id.current_date);
        TextView current_day = (TextView)view.findViewById(R.id.current_day);
        String current_date_pull = DateFormat.getDateInstance().format(new Date());
        current_date.setText(current_date_pull);
        current_day.setText(getResources().getString(R.string.wednesday));

        listView = (ListView)view.findViewById(R.id.routine_display);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("11A/3/wed");
        callingDatabaseEvent();

        return view;
    }

    private void callingDatabaseEvent() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrayList = (ArrayList)dataSnapshot.getValue();
                jsonArray = new JSONArray(arrayList);

                start_time = new String[jsonArray.length()];
                end_time = new String[jsonArray.length()];
                subject = new String[jsonArray.length()];
                teacher = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    try {

                        jsonObject = jsonArray.getJSONObject(i);
                        start_time[i] = jsonObject.getString("start_time");
                        end_time[i] = jsonObject.getString("end_time");
                        subject[i] = jsonObject.getString("subject");
                        teacher[i] = jsonObject.getString("teacher");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("testValue", Arrays.toString(start_time)+ "\n" + Arrays.toString(end_time) + "\n" + Arrays.toString(subject));

                routineAdapter adapter = new routineAdapter(getContext(), start_time, end_time, subject, teacher);
                Log.i("testedValue", Arrays.toString(start_time));
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("testError", String.valueOf(databaseError));
            }
        });

    }

    private class routineAdapter extends ArrayAdapter {

        private String[] startTIme, endTime, subjects, teacher;

        @SuppressWarnings("unchecked")
        routineAdapter(Context context, String[] start, String[] end, String[] subject, String[] teachers) {
            super(context, R.layout.fragment_routine_sun, R.id.start_time, start);
            this.startTIme = start;
            this.endTime = end;
            this.subjects = subject;
            this.teacher = teachers;
        }

        TextView start_time;
        TextView end_time;
        TextView subject;
        TextView teacher_name;

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            Log.i("printing", startTIme[position]);

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.fragment_routine_sun, parent, false);

                start_time = (TextView) convertView.findViewById(R.id.start_time);
                end_time = (TextView) convertView.findViewById(R.id.end_time);
                subject = (TextView) convertView.findViewById(R.id.subject_name);
                teacher_name = (TextView) convertView.findViewById(R.id.teacher_name);

            }

            //populating items with data
            start_time.setText(startTIme[position]);
            end_time.setText(endTime[position]);
            subject.setText(subjects[position]);
            teacher_name.setText(teacher[position]);

            Log.i("printing", startTIme[position]);

            return convertView;
        }
    }

}


