package np.edu.bvs.bvshigh;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Home_page extends android.support.v4.app.Fragment {

    ViewPager pager;
    home_sliding_adapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Today's Class", "Homeworks"};
    int NumbofTabs =2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        adapter = new home_sliding_adapter(getFragmentManager(), Titles, NumbofTabs);

        pager = (ViewPager)view.findViewById(R.id.home_pager);
        pager.setAdapter(adapter);

        tabs = (SlidingTabLayout)view.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);
        tabs.setViewPager(pager);

        return view;
    }
}