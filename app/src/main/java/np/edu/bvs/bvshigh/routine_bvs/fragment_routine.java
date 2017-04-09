package np.edu.bvs.bvshigh.routine_bvs;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.sliding_tab_layout_strip.SlidingTabLayout;

public class fragment_routine extends AppCompatActivity {

    ViewPager pager;
    SlidingTabLayout tabs;
    fragment_routine_week adapter;
    CharSequence Titles[] = {"SUN", "MON", "TUE", "WED", "THU", "FRI"};
    int NumOfTabs = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_routine);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Only to change the color of back button
        //toolbar.setNavigationIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_arrow_back_black));

        adapter = new fragment_routine_week(getSupportFragmentManager(), Titles, NumOfTabs);

        pager = (ViewPager)findViewById(R.id.routine_slide);
        pager.setAdapter(adapter);

        tabs = (SlidingTabLayout)findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return ContextCompat.getColor(getApplicationContext(), R.color.tabsScrollColor);
            }
        });

        tabs.setViewPager(pager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
