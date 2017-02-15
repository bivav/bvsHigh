package np.edu.bvs.bvshigh;

import android.content.Context;
import android.os.Bundle;

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

public class fragment_home_bottom_2 extends Fragment {

    String[] titles = {"PHY", "CHE", "COM", "MAT", "ENG", "BIO", "ETC"};
    String[] teachers = {"BVK", "BN", "SK", "TRK", "DG", "AG", "RY"};
    String[] homework = {"Do the works from the blackboard", "Do questions from old is gold page 102 103 question no 5 to 10",
            "Do the questions given in class", "Read for tomorrow's test", "Revise today's classwork", "REVISE THE WORK",
            "REVISE THE WORK"};

    ListView homework_customview;

    String current_day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homework_customlist_view, container, false);

        homework_customview = (ListView)view.findViewById(R.id.homework_display);
        homeworkAdapter adapter = new homeworkAdapter(getContext(),titles,teachers,homework);
        homework_customview.setAdapter(adapter);

        String current_date_pull = DateFormat.getDateInstance().format(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE" );
        Date d = new Date();
        String day = sdf.format(d);

        TextView current_date = (TextView)view.findViewById(R.id.current_date);
        TextView current_day = (TextView)view.findViewById(R.id.current_day);
        current_date.setText(current_date_pull);
        current_day.setText(day);

        return view;
    }

    class homeworkAdapter extends ArrayAdapter {

        String[] titles;
        String[] teachers;
        String[] homework;

        public homeworkAdapter(Context context, String[] mtitles, String[] mteachers, String[] mhomework) {
            super(context, R.layout.fragment_homework,R.id.teacher_name, mteachers);
            this.titles = mtitles;
            this.teachers = mteachers;
            this.homework = mhomework;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.fragment_homework, parent, false);

            TextView homework_subject = (TextView)row.findViewById(R.id.homework_subject);
            TextView homework_details = (TextView)row.findViewById(R.id.homework_details);
            TextView teacher_name = (TextView)row.findViewById(R.id.teacher_name);

            homework_subject.setText(titles[position]);
            homework_details.setText(homework[position]);
            teacher_name.setText(teachers[position]);

            return row;
        }
    }

}
