package np.edu.bvs.bvshigh;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class home_sliding_adapter extends FragmentStatePagerAdapter {

    private int NumOfTabs;
    private CharSequence Titles[];


    public home_sliding_adapter(FragmentManager fm, CharSequence titles[], int numOfTabs) {
        super(fm);

        this.Titles = titles;
        this.NumOfTabs = numOfTabs;

    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
            return new fragment_home_bottom_1();
        else
            return new fragment_home_bottom_2();
    }

    public CharSequence getPageTitle(int position){
        return Titles[position];

    }

    @Override
    public int getCount() {
        return NumOfTabs;
    }
}
