package np.edu.bvs.bvshigh.general;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

class Teacher_Contact_Adapter extends FragmentStatePagerAdapter {

    private int NumOfTabs;
    private CharSequence Titles[];

    Teacher_Contact_Adapter(FragmentManager fm, CharSequence mtitles[], int numOfTabs) {
        super(fm);
        this.Titles = mtitles;
        this.NumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0)
            return new fragment_teacher_contact_science();
        else
            return new fragment_teacher_contact_managment();
    }

    public CharSequence getPageTitle(int position){
        return Titles[position];

    }

    @Override
    public int getCount() {
        return NumOfTabs;
    }
}