package np.edu.bvs.bvshigh.routine_bvs_students;

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

import org.json.JSONArray;
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
import java.util.Date;
import java.util.List;

import np.edu.bvs.bvshigh.general.Constants;
import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.sqLite_handler.DatabaseManager;
import np.edu.bvs.bvshigh.sqLite_handler.MyDBHandler;
import np.edu.bvs.bvshigh.sqLite_handler.Routine_Database;


public class fragment_routine_tue extends Fragment {

    ListView listView;
    String address = Constants.URL_Routine_Sci_Bio_11_TUE;
    InputStream inputStream;
    String line, result;
    String[] start_time, end_time, subject, teacher;
    DatabaseManager dbManager;
    MyDBHandler handler;
    SQLiteDatabase sqLiteDatabase;
    String TAG = "routineTUE";
    View cv;
    List<Routine_Database> routine_list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = DatabaseManager.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine_customlist_view, container, false);

        String current_date_pull = DateFormat.getDateInstance().format(new Date());

        TextView current_date = (TextView)view.findViewById(R.id.current_date);
        TextView current_day = (TextView)view.findViewById(R.id.current_day);

        handler = new MyDBHandler(getContext(), null, null, 1);

        current_date.setText(current_date_pull);
        current_day.setText(getResources().getString(R.string.tuesday));

        listView = (ListView)view.findViewById(R.id.routine_display);

        if (dbManager.isTableExists(MyDBHandler.TABLE_routine_sci_11_bio_tue, true)) {
            routine_list = dbManager.gettingAllDataTUE();
            routineAdapter adapter = new routineAdapter(getContext(), routine_list);
            listView.setAdapter(adapter);
        }
        else{
            // Getting Routine from background
            GetResultFromServer getResult = new GetResultFromServer();
            getResult.execute();
        }

        return view;
    }

    private class GetResultFromServer extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            GetDataFromServer();
            return null;
        }

        @Override
        protected void onPostExecute(String resultData) {

            routine_list = dbManager.gettingAllDataTUE();
            routineAdapter adapter = new routineAdapter(getActivity(), routine_list);
            listView.setAdapter(adapter);
        }
    }

    public String GetDataFromServer() {
        try {

            // Get url and open the connection
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();

            // set the method to GET
            con.setRequestMethod("GET");

            // use InputStream to get the InputStream content
            inputStream = new BufferedInputStream(con.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read the InputStream content as String
        try {
            // reading the inputStream and converting into string using stringBuilder
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            inputStream.close();

            // the data are converted as a string JSON
            result = stringBuilder.toString();

            Log.i("TUESDAY_RESULT", result);

            Log.i("TUESDAY_routine", result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Try to pass JSON DATA
        try {

            // passing the result string JSON into JSONArray
            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject;

            start_time = new String[jsonArray.length()];
            end_time = new String[jsonArray.length()];
            subject = new String[jsonArray.length()];
            teacher = new String[jsonArray.length()];

            sqLiteDatabase = getContext().openOrCreateDatabase("bvs_high.db", Context.MODE_PRIVATE, null);

            if (sqLiteDatabase != null) {
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MyDBHandler.TABLE_routine_sci_11_bio_tue);
                sqLiteDatabase.execSQL(MyDBHandler.tue_routine_table());

                for (int i = 0; i < jsonArray.length(); i++) {

                    jsonObject = jsonArray.getJSONObject(i);
                    start_time[i] = jsonObject.getString("start_time");
                    end_time[i] = jsonObject.getString("end_time");
                    subject[i] = jsonObject.getString("subject");
                    teacher[i] = jsonObject.getString("teacher");

                    Routine_Database routine_database = new Routine_Database(start_time[i], end_time[i], subject[i], teacher[i]);
                    dbManager.saveDataTUE(routine_database);
                    Log.i(TAG, "DATA in json Format : " + start_time[i]);
                }
            } else {

                Log.i(TAG, "DATA in json Format : " + start_time.length);
                Log.i(TAG, "DATA in json Format : " + end_time.length);
                Log.i(TAG, "DATA in json Format : " + subject.length);
                Log.i(TAG, "DATA in json Format : " + teacher.length);
            }
            sqLiteDatabase.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    private class routineAdapter extends ArrayAdapter<Routine_Database> {

        private final List<Routine_Database> list;

        routineAdapter(Context context, List<Routine_Database> list) {
            super(context, R.layout.fragment_routine_tue, list);
            this.list = list;

        }

        class ViewHolder {
            protected TextView start_time;
            protected TextView end_time;
            protected TextView subject;
            protected TextView teacher_name;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            ViewHolder viewholder;
            cv = convertView;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.fragment_routine_tue, parent, false);

                viewholder = new ViewHolder();

                viewholder.start_time = (TextView) convertView.findViewById(R.id.start_time);
                viewholder.end_time = (TextView) convertView.findViewById(R.id.end_time);
                viewholder.subject = (TextView) convertView.findViewById(R.id.subject_name);
                viewholder.teacher_name = (TextView) convertView.findViewById(R.id.teacher_name);

                convertView.setTag(viewholder);
                convertView.setTag(R.id.start_time, viewholder.start_time);
                convertView.setTag(R.id.end_time, viewholder.end_time);
                convertView.setTag(R.id.subject_name, viewholder.subject);
                convertView.setTag(R.id.teacher_name, viewholder.teacher_name);

            } else {
                viewholder = (ViewHolder)convertView.getTag();
            }

            //populating items with data
            viewholder.start_time.setTag(position);
            viewholder.start_time.setText(list.get(position).get_start_time());

            viewholder.end_time.setTag(position);
            viewholder.end_time.setText(list.get(position).get_end_time());

            viewholder.subject.setTag(position);
            viewholder.subject.setText(list.get(position).get_subject());

            viewholder.teacher_name.setTag(position);
            viewholder.teacher_name.setText(list.get(position).get_teacher());

            Log.i("printing", list.get(position).get_teacher());

            return convertView;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}


