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
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.general.fragment_alerts;
import np.edu.bvs.bvshigh.general.fragment_calendar;
import np.edu.bvs.bvshigh.general.SharedPrefManager;
import np.edu.bvs.bvshigh.students.routine_bvs_students.fragment_routine;
import np.edu.bvs.bvshigh.students.fragment_result_student;
import np.edu.bvs.bvshigh.teachers.login_teachers.Login_Teacher;


public class Dashboard_Teachers extends AppCompatActivity {

    de.hdodenhof.circleimageview.CircleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String displayToken = FirebaseInstanceId.getInstance().getToken();
        if (FirebaseInstanceId.getInstance().getToken() == null) {
            Toast.makeText(this, "Please turn on Internet Connection to receive Notifications.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Welcome!: -> " + displayToken, Toast.LENGTH_LONG).show();
        }

        // check if the user is not logged in -> get the user in login page
        if (!SharedPrefManager.getInstance(this).isLoggedInTeachers()){
            finish();
            startActivity(new Intent(getApplicationContext(), Login_Teacher.class));
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
        gridView.setAdapter(new np.edu.bvs.bvshigh.teachers.Dashboard_Buttons(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position) {
                    case 0:
                        startActivity(new Intent(getApplicationContext(), fragment_routine.class));
                        break;
                    case 1:
                        startActivity(new Intent(getApplicationContext(), fragment_alerts.class));
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(), fragment_result_student.class));
                        break;
                    case 3:
                        startActivity(new Intent(getApplicationContext(), fragment_assignment.class));
                        break;
                    case 4:
                        startActivity(new Intent(getApplicationContext(), fragment_result_student.class));
                        break;
                    case 5:
                        startActivity(new Intent(getApplicationContext(), fragment_calendar.class));
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
