package np.edu.bvs.bvshigh.teachers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import np.edu.bvs.bvshigh.students.Dashboard_Buttons;
import np.edu.bvs.bvshigh.general.Main_Activity;
import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.general.fragment_alerts;
import np.edu.bvs.bvshigh.general.fragment_calendar;
import np.edu.bvs.bvshigh.login_sharedPref.Login_Student;
import np.edu.bvs.bvshigh.login_sharedPref.SharedPrefManager;
import np.edu.bvs.bvshigh.routine_bvs.fragment_routine;
import np.edu.bvs.bvshigh.students.fragment_result_student;


public class Dashboard_Teachers extends AppCompatActivity {

    de.hdodenhof.circleimageview.CircleImageView imageView;
    Main_Activity main_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // check if the user is not logged in -> get the user in login page
        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(), Login_Student.class));
        }

        TextView student_name = (TextView) findViewById(R.id.student_name);
        student_name.setText(SharedPrefManager.getInstance(this).getFullName());

        imageView = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.profile_image);

        //loading the image saved in above directory
        String path = String.valueOf(getApplicationContext().getDir("imageDir", Context.MODE_PRIVATE));
        loadImageFromStorage(path);

        TextView student_email = (TextView)findViewById(R.id.student_email);
        student_email.setText(getResources().getString(R.string.brihaspati));

        GridView gridView = (GridView)findViewById(R.id.dashboard_grid);
        gridView.setAdapter(new Dashboard_Buttons(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position) {
                    case 0:
                        startActivity(new Intent(getApplicationContext(), fragment_routine.class));
                        finish();
                        break;
                    case 1:
                        startActivity(new Intent(getApplicationContext(), fragment_alerts.class));
                        finish();
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(), fragment_result_student.class));
                        finish();
                        break;
                    case 3:
                        startActivity(new Intent(getApplicationContext(), fragment_result_student.class));
                        finish();
                        break;
                    case 4:
                        startActivity(new Intent(getApplicationContext(), fragment_result_student.class));
                        finish();
                        break;
                    case 5:
                        startActivity(new Intent(getApplicationContext(), fragment_calendar.class));
                        finish();
                        break;

                }
            }
        });

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
