package np.edu.bvs.bvshigh.students.routine_bvs_students;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

class fragment_routine_week extends FragmentStatePagerAdapter {

    private int NumOfTabs;
    private CharSequence Titles[];

    fragment_routine_week(FragmentManager fm, CharSequence mTitles[], int numOfTabs) {
        super(fm);
        this.Titles = mTitles;
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