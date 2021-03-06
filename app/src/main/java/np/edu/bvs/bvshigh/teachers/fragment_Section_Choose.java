package np.edu.bvs.bvshigh.teachers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.sliding_tab_layout_strip.SlidingTabLayout;

public class fragment_Section_Choose extends Fragment {

    SlidingTabLayout slidingTab;
    ViewPager pager;
    int tabs = 2;
    Section_Choose_Adapter adapter;
    CharSequence titles[] = {"SCIENCE", "MANAGEMENT"};


    public fragment_Section_Choose() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_section_choose, container, false);

        adapter = new Section_Choose_Adapter(getFragmentManager(), titles, tabs);

        pager = (ViewPager)view.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        slidingTab = (SlidingTabLayout)view.findViewById(R.id.slide_tab);
        slidingTab.setDistributeEvenly(true);

        slidingTab.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return ContextCompat.getColor(getContext(), R.color.tabsScrollColor);
            }
        });

        slidingTab.setViewPager(pager);

        return view;
    }
}