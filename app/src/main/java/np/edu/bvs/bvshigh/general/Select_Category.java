package np.edu.bvs.bvshigh.general;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.students.Dashboard_Students;
import np.edu.bvs.bvshigh.students.Main_Activity_Students;
import np.edu.bvs.bvshigh.students.login_students.Login_Student;
import np.edu.bvs.bvshigh.teachers.Main_Activity_Teachers;
import np.edu.bvs.bvshigh.teachers.login_teachers.Login_Teacher;

public class Select_Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        // Checking if user is logged in already -> profile is displayed
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(getApplicationContext(), Main_Activity_Students.class));
            return;
        } else if (SharedPrefManager.getInstance(this).isLoggedInTeachers()) {
            finish();
            startActivity(new Intent(getApplicationContext(), Main_Activity_Teachers.class));
            return;
        }

        Button student_button = (Button)findViewById(R.id.student_button);
        Button teacher_button = (Button)findViewById(R.id.teacher_button);

        // button selects first page
        student_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login_Student.class));
                finish();
            }
        });

        teacher_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login_Teacher.class));
                finish();
            }
        });
    }
}