package np.edu.bvs.bvshigh.teachers;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.general.ItemTouchHelperCallback;

public class fragment_assignment extends AppCompatActivity {

    RecyclerView recyclerView;
    private int color = 0;
    SwipeRefreshLayout swipeRefreshLayout;
    private List<Assignment_Details> detailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_assignment_recycler);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initializeData();

        recyclerView = (RecyclerView)findViewById(R.id.assignment_recycler);
        recyclerView.hasFixedSize();

        final Recycler_Assignment_Adapter adapter = new Recycler_Assignment_Adapter(getApplicationContext(), detailsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout_recycler_view);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (color > 4) {
                            color = 0;
                        }
                        adapter.setItems(++color);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2300);

            }
        });
    }

    class Assignment_Details {

        String teachers;
        String subjects;
        String assignments;

        Assignment_Details(String mTeachers, String mSubjects, String mAssignments) {
            this.assignments = mAssignments;
            this.subjects = mSubjects;
            this.teachers = mTeachers;
        }
    }

    public void initializeData() {

        detailsList = new ArrayList<>();
        detailsList.add(new Assignment_Details("BVk", "Phy", "This is something you have to do very fast."));
        detailsList.add(new Assignment_Details("BVk", "Phy", "This is a test."));
        detailsList.add(new Assignment_Details("BVk", "Phy", "Do very fast."));
        detailsList.add(new Assignment_Details("BVk", "Phy", "This is something"));

        Log.i("Testing", String.valueOf(detailsList));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}