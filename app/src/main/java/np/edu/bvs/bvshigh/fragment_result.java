package np.edu.bvs.bvshigh;

import android.content.DialogInterface;
import android.content.Intent;
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

    String[] monthly_test = {"Date: 02/04/2017", "Date: 04/05/2017", "Date: 06/06/2017", "Date: 08/07/2017","Date: 10/08/2017"};
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Initializing Alert Box
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want to download the result?");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(getApplicationContext(),"You clicked yes button",Toast.LENGTH_LONG).show();
            }
        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Cancelled!",Toast.LENGTH_LONG).show();
            }
        });


        /* This is for monthly test results *******/
        ListView monthly_test_view = (ListView)findViewById(R.id.monthly_test_view);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, monthly_test);
        monthly_test_view.setAdapter(arrayAdapter);

        monthly_test_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        alertDialogBuilder.setMessage("Download Result for "+ monthly_test[0]);
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        break;
                    case 1:
                        alertDialogBuilder.setMessage("Download Result for "+ monthly_test[1]);
                        alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        break;
                    case 2:
                        alertDialogBuilder.setMessage("Download Result for "+ monthly_test[2]);
                        alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        break;
                    case 3:
                        alertDialogBuilder.setMessage("Download Result for "+ monthly_test[3]);
                        alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        break;
                    case 4:
                        alertDialogBuilder.setMessage("Download Result for "+ monthly_test[4]);
                        alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        break;

                    default:
                        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }

            }
        });


        /* This is for MAIN exam results**/
        ListView main_exam_view = (ListView)findViewById(R.id.main_exam_view);
        ArrayAdapter<String> main_exam_array_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, main_exam);
        main_exam_view.setAdapter(main_exam_array_adapter);

        main_exam_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        alertDialogBuilder.setMessage("Download Result for "+ main_exam[0]);
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        break;
                    case 1:
                        alertDialogBuilder.setMessage("Download Result for "+ main_exam[1]);
                        alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        break;
                    case 2:
                        alertDialogBuilder.setMessage("Download Result for "+ main_exam[2]);
                        alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        break;
                    case 3:
                        alertDialogBuilder.setMessage("Download Result for "+ main_exam[3]);
                        alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        break;

                    default:
                        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Main_activity.class));
        finish();
    }
}
