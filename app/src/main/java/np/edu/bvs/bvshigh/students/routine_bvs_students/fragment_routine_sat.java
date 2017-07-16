package np.edu.bvs.bvshigh.students.routine_bvs_students;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import np.edu.bvs.bvshigh.R;

public class fragment_routine_sat extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_routine_sat, container, false);

        TextView quoteForTheDay1 = (TextView)view.findViewById(R.id.quoteForTheDay1);
        TextView quoteForTheDay2 = (TextView)view.findViewById(R.id.quoteForTheDay2);

        quoteForTheDay1.setText(" \" " + getResources().getString(R.string.quoteForTheDay1)+ " \" ");
        quoteForTheDay2.setText(getResources().getString(R.string.quoteForTheDay2));

        return view;
    }
}
