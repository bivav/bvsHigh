package np.edu.bvs.bvshigh.students;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import np.edu.bvs.bvshigh.R;


public class fragment_attendance extends Fragment {


    public fragment_attendance() {
        // Required empty public constructor
    }

    String[] sections = {"A", "B", "C", "D"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_attendance_choose, container, false);

        return view;
    }

}
