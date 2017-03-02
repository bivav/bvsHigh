package np.edu.bvs.bvshigh;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class fragment_result extends AppCompatActivity {

    String[] monthly_test = {"Date: 02/04/2017", "Date: 05/06/2017", "Date: 05/06/2017", "Date: 05/06/2017","Date: 05/06/2017"};
    String[] main_exam = {"First Term","Second Term", "Pre Board", "Final Exam"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_result);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        /** This is for monthly test results **/

        ListView monthly_test_view = (ListView)findViewById(R.id.monthly_test_view);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, monthly_test);

        monthly_test_view.setAdapter(arrayAdapter);

        monthly_test_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Test no: "+monthly_test[i], Toast.LENGTH_SHORT).show();
            }
        });


        /** This is for MAIN exam results**/

        ListView main_exam_view = (ListView)findViewById(R.id.main_exam_view);
        ArrayAdapter<String> main_exam_array_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, main_exam);
        main_exam_view.setAdapter(main_exam_array_adapter);

        main_exam_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Exam "+main_exam[i], Toast.LENGTH_SHORT).show();
            }
        });

    }
}
