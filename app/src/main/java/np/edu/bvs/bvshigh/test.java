package np.edu.bvs.bvshigh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import np.edu.bvs.bvshigh.students.Main_Activity_Students;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        final Button buttontest = (Button)findViewById(R.id.button_test);

        buttontest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Main_Activity_Students.class);
                startActivity(i);
            }
        });


    }
}
