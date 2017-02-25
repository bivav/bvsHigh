package np.edu.bvs.bvshigh;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class fragment_routine_fri extends Fragment {

    String[] time = {"6:30 - 7:30", "7:30 - 8:30", "8:30 - 9:30","9:30 - 10:30", "10:30 - 11:30","11:30 - 12:30","12:30 - 1:30"};
    String[] subject = {"Chemisty","Physics","Chemistry","DG","Physics","English", "ETA"};
    String[] teacher = {"DG", "BN", "SR", "TRK", "DG", "BN", "SB"};
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine_customlist_view, container, false);

        String current_date_pull = DateFormat.getDateInstance().format(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String day = sdf.format(d);

        TextView current_date = (TextView)view.findViewById(R.id.current_date);
        TextView current_day = (TextView)view.findViewById(R.id.current_day);
        current_date.setText(current_date_pull);
        current_day.setText(day);

        listView = (ListView)view.findViewById(R.id.routine_display);
        routineAdapter adapter = new routineAdapter(getActivity(), time, subject, teacher);
        listView.setAdapter(adapter);

        return view;
    }

    class routineAdapter extends ArrayAdapter {

        String[] timeArray;
        String[] subjectArray;
        String[] teacherArray;

        routineAdapter(Context context, String[] mtimeArray, String[] msubjecArray, String[] mteacherArray) {
            super(context, R.layout.fragment_routine_fri, R.id.teacher_name, mteacherArray);
            this.timeArray = mtimeArray;
            this.subjectArray = msubjecArray;
            this.teacherArray = mteacherArray;
        }


        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.fragment_routine_fri, parent, false);

            TextView time = (TextView)row.findViewById(R.id.period_time);
            TextView subject_name = (TextView)row.findViewById(R.id.subject_name);
            TextView teacher_name = (TextView)row.findViewById(R.id.teacher_name);

            time.setText(timeArray[position]);
            subject_name.setText(subjectArray[position]);
            teacher_name.setText(teacherArray[position]);

            return row;
        }
    }

}


