package np.edu.bvs.bvshigh.general;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import np.edu.bvs.bvshigh.routine_bvs.fragment_routine_fri;
import np.edu.bvs.bvshigh.routine_bvs.fragment_routine_mon;
import np.edu.bvs.bvshigh.routine_bvs.fragment_routine_sat;
import np.edu.bvs.bvshigh.routine_bvs.fragment_routine_sun;
import np.edu.bvs.bvshigh.routine_bvs.fragment_routine_thur;
import np.edu.bvs.bvshigh.routine_bvs.fragment_routine_tue;
import np.edu.bvs.bvshigh.routine_bvs.fragment_routine_wed;
import np.edu.bvs.bvshigh.students.fragment_homework;

class Home_Adapter extends FragmentStatePagerAdapter {

    private int NumOfTabs;
    private CharSequence Titles[];

    Home_Adapter(FragmentManager fm, CharSequence titles[], int numOfTabs) {
        super(fm);
        this.Titles = titles;
        this.NumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.US);
            Date d = new Date();
            String day = sdf.format(d);

            switch (day) {
                case "Sunday":
                    return new fragment_routine_sun();

                case "Monday":
                    return new fragment_routine_mon();

                case "Tuesday":
                    return new fragment_routine_tue();

                case "Wednesday":
                    return new fragment_routine_wed();

                case "Thursday":
                    return new fragment_routine_thur();

                case "Friday":
                    return new fragment_routine_fri();

                case "Saturday":
                    return new fragment_routine_sat();

            }
            return null;
        }

        else
            return new fragment_homework();
    }

    public CharSequence getPageTitle(int position){
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumOfTabs;
    }
}