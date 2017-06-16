package np.edu.bvs.bvshigh.routine_bvs_students;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import np.edu.bvs.bvshigh.general.Main_Activity;
import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.sliding_tab_layout_strip.SlidingTabLayout;

public class fragment_routine extends AppCompatActivity {

    ViewPager pager;
    SlidingTabLayout tabs;
    fragment_routine_week adapter;
    CharSequence Titles[] = {"SUN", "MON", "TUE", "WED", "THU", "FRI"};

    int NumOfTabs = 6;

    SwipeRefreshLayout swipeRefreshLayout;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_routine);

        // Close the activity if internet is not available
        if (!isNetworkAvailable(this)) {
            Toast.makeText(this, "Please Connect to Internet to get updated data.", Toast.LENGTH_LONG).show();
        }

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeToRefresh);

        progressDialog = new ProgressDialog(fragment_routine.this);
        progressDialog.setMessage("Loading  Data...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        tabs = (SlidingTabLayout)findViewById(R.id.tabs);
        pager = (ViewPager)findViewById(R.id.routine_slide);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        executeCode();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                progressDialog.show();
                progressDialog.setCancelable(false);
                executeCode();
            }
        });

        // Only to change the color of back button
        //toolbar.setNavigationIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_arrow_back_black));

    }

    private void executeCode() {

        adapter = new fragment_routine_week(getSupportFragmentManager(), Titles, NumOfTabs);

        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(6);

        tabs.setDistributeEvenly(true);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return ContextCompat.getColor(getApplicationContext(), R.color.tabsScrollColor);
            }
        });
        tabs.setViewPager(pager);
        swipeRefreshLayout.setRefreshing(false);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        }, 2000);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return conMan.getActiveNetworkInfo() != null && conMan.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Main_Activity.class));
        finish();
    }

}