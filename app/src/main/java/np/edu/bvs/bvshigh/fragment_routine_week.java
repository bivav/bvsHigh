package np.edu.bvs.bvshigh;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class fragment_routine_week extends FragmentStatePagerAdapter {

    private int NumOfTabs;
    private CharSequence Titles[];

    public fragment_routine_week(FragmentManager fm, CharSequence mtitles[], int numOfTabs) {
        super(fm);

        this.Titles = mtitles;
        this.NumOfTabs = numOfTabs;

    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0)
            return new fragment_routine_sun();
        else
            return new fragment_routine_mon();
    }

    public CharSequence getPageTitle(int position){
        return Titles[position];

    }

    @Override
    public int getCount() {
        return NumOfTabs;
    }
}
