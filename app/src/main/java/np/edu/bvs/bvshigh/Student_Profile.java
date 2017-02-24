package np.edu.bvs.bvshigh;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Student_Profile extends AppCompatActivity {

    TextView student_name, branch_name, student_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        student_name = (TextView)findViewById(R.id.student_name);
        branch_name = (TextView)findViewById(R.id.branch_name);
        student_email = (TextView)findViewById(R.id.student_email);

        student_name.setText(SharedPrefManager.getInstance(getApplicationContext()).getFullName());
        branch_name.setText(SharedPrefManager.getInstance(getApplicationContext()).getBranch());
        student_email.setText(SharedPrefManager.getInstance(getApplicationContext()).getEmail());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.edit_profile:
                startActivity(new Intent(getApplicationContext(), Student_Profile_Edit.class));
                break;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return true;
    }
}
