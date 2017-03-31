package np.edu.bvs.bvshigh;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class fragment_routine_wed extends Fragment {

    ListView listView;
    String address;
    InputStream inputStream;
    String line, result;
    String[] start_time, end_time, subject, teacher;
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine_customlist_view, container, false);

        String current_date_pull = DateFormat.getDateInstance().format(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.US);
        Date d = new Date();
        String day = sdf.format(d);

        TextView current_date = (TextView)view.findViewById(R.id.current_date);
        TextView current_day = (TextView)view.findViewById(R.id.current_day);

        current_date.setText(current_date_pull);
        current_day.setText(day);

        listView = (ListView)view.findViewById(R.id.routine_display);

//        Allowing NETWORK on the MAIN THREAD.... <<<---------NOT A GOOD PRACTICE BUT DOES THE WORK FOR NOW----------->>>
//        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        // Getting Routine from background
        new GetResultFromServer().execute();
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new GetResultFromServer().execute();
    }

    private class GetResultFromServer extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            address = "http://172.16.11.247/bvs_high/v1/routine_sci_11_bio_wed.php";
        }

        @Override
        protected String doInBackground(String... strings) {

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

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Try to pass JSON DATA
            try {

                // passing the result string JSON into JSONArray
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject;

                start_time = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {

                    jsonObject = jsonArray.getJSONObject(i);
                    start_time[i] = jsonObject.getString("start_time");

                }

                end_time = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {

                    jsonObject = jsonArray.getJSONObject(i);
                    end_time[i] = jsonObject.getString("end_time");

                }

                subject = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {

                    jsonObject = jsonArray.getJSONObject(i);
                    subject[i] = jsonObject.getString("subject");

                }

                teacher = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {

                    jsonObject = jsonArray.getJSONObject(i);
                    teacher[i] = jsonObject.getString("teacher");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            routineAdapter adapter = new routineAdapter(getActivity(), start_time, end_time, subject, teacher);
            listView.setAdapter(adapter);
        }
    }

    private class routineAdapter extends ArrayAdapter{

        String[] timeArray;
        String[] timeEndArray;
        String[] subjectArray;
        String[] teacherArray;

        routineAdapter(Context context, String[] mtimeArray, String[] mtimeEndArray, String[] msubjecArray, String[] mteacherArray) {
            //noinspection unchecked
            super(context, R.layout.fragment_routine_sun, R.id.teacher_name, mteacherArray);
            this.timeArray = mtimeArray;
            this.timeEndArray = mtimeEndArray;
            this.subjectArray = msubjecArray;
            this.teacherArray = mteacherArray;
        }


        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.fragment_routine_sun, parent, false);

            TextView time = (TextView)row.findViewById(R.id.start_time);
            TextView end_time = (TextView)row.findViewById(R.id.end_time);
            TextView subject_name = (TextView)row.findViewById(R.id.subject_name);
            TextView teacher_name = (TextView)row.findViewById(R.id.teacher_name);

            time.setText(timeArray[position]);
            end_time.setText(timeEndArray[position]);
            subject_name.setText(subjectArray[position]);
            teacher_name.setText(teacherArray[position]);

            return row;
        }
    }

}


