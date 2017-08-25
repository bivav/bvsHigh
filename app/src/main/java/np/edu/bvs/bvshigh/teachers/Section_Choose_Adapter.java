package np.edu.bvs.bvshigh.teachers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import np.edu.bvs.bvshigh.general.fragment_section_managment;
import np.edu.bvs.bvshigh.general.fragment_section_science;

class Section_Choose_Adapter extends FragmentStatePagerAdapter {

    private int NumOfTabs;
    private CharSequence Titles[];

    Section_Choose_Adapter(FragmentManager fm, CharSequence mtitles[], int numOfTabs) {
        super(fm);
        this.Titles = mtitles;
        this.NumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0)
            return new fragment_section_science();
        else
            return new fragment_section_managment();
    }

    public CharSequence getPageTitle(int position){
        return Titles[position];

    }

    @Override
    public int getCount() {
        return NumOfTabs;
    }
}