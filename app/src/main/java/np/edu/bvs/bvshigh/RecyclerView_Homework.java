package np.edu.bvs.bvshigh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class RecyclerView_Homework extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view__homework);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);;
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_homeworks);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("BVK");
        arrayList.add("TRK");
        arrayList.add("SK");
        arrayList.add("SB");
        arrayList.add("SP");
        arrayList.add("DG");
        arrayList.add("DPP");

        recyclerView.setAdapter(new RecyclerAdapter(arrayList));

    }
}
