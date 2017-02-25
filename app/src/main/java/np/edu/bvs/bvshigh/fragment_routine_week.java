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
        else if(position == 1)
            return new fragment_routine_mon();
        else if (position == 2)
            return new fragment_routine_tue();
        else if (position == 3)
            return new fragment_routine_wed();
        else if (position == 4)
            return new fragment_routine_thur();
        else
            return new fragment_routine_fri();
    }

    public CharSequence getPageTitle(int position){
        return Titles[position];

    }

    @Override
    public int getCount() {
        return NumOfTabs;
    }
}
