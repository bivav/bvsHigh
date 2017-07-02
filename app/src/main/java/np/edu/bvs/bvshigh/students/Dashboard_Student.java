package np.edu.bvs.bvshigh.students;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.general.SharedPrefManager;
import np.edu.bvs.bvshigh.general.fragment_alerts;
import np.edu.bvs.bvshigh.general.fragment_calendar;
import np.edu.bvs.bvshigh.students.routine_bvs_students.fragment_routine;

public class Dashboard_Student extends Fragment {

    de.hdodenhof.circleimageview.CircleImageView imageView;

    public Dashboard_Student() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard, container, false);

        TextView student_name = (TextView)view.findViewById(R.id.student_name);
        student_name.setText(SharedPrefManager.getInstance(getContext()).getFullName());

        imageView = (de.hdodenhof.circleimageview.CircleImageView)view.findViewById(R.id.profile_image);

        //loading the image saved in above directory
        String path = String.valueOf(getContext().getDir("imageDir", Context.MODE_PRIVATE));
        loadImageFromStorage(path);

        TextView student_email = (TextView)view.findViewById(R.id.student_email);
        student_email.setText(getResources().getString(R.string.brihaspati));

        GridView gridView = (GridView)view.findViewById(R.id.dashboard_grid);
        gridView.setAdapter(new Dashboard_Buttons(getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position) {
                    case 0:
                        startActivity(new Intent(getContext(), fragment_routine.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext(), fragment_alerts.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(), fragment_result_student.class));
                        break;
                    case 3:
                        startActivity(new Intent(getContext(), fragment_homework.class));
                        break;
                    case 4:
                        startActivity(new Intent(getContext(), fragment_result_student.class));
                        break;
                    case 5:
                        startActivity(new Intent(getContext(), fragment_calendar.class));
                        break;
                }
            }
        });

        return view;
    }

    public void loadImageFromStorage(String pathImg) {

        try {
            File f = new File(pathImg, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            imageView.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

}
