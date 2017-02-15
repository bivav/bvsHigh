package np.edu.bvs.bvshigh;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class fragment_home_bottom_1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine_sun, container, false);

        String current_date_pull = DateFormat.getDateInstance().format(new Date());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String day = sdf.format(d);

        TextView current_date = (TextView)view.findViewById(R.id.current_date);
        TextView current_day = (TextView)view.findViewById(R.id.current_day);
        current_date.setText(current_date_pull);
        current_day.setText(day);

        return view;
    }
}
