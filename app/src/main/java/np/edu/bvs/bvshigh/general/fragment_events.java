package np.edu.bvs.bvshigh.general;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import np.edu.bvs.bvshigh.R;

public class fragment_events extends Fragment {


    public fragment_events() {
        // Required empty public constructor
    }

    String[] title = {"SPORTS WEEK!", "ANAANYADANTA", "FEAST", "WHATEVER"};
    String[] description = {"This is test and I am testing a lot and lot.", "This is test and I am testing a lot and lot.",
            "This is test and I am testing a lot and lot.This is test and I am testing a lot and lot.This is test and I am testing a lot and lot.","This is test and I am testing a lot and lot.This is test and I am testing a lot and lot.This is test and I am testing a lot and lot."};

    ListView event_list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        event_list = (ListView)view.findViewById(R.id.event_list);

        event_adapter adapter = new event_adapter(getContext(), title, description);
        event_list.setAdapter(adapter);

        return view;

    }


    public class event_adapter extends ArrayAdapter {

        String[] mtitles;
        String[] mdescription;

        public event_adapter(Context context, String[] titles, String[] description) {

            super(context, R.layout.fragment_events_customview, R.id.event_title, titles);

            mtitles = titles;
            mdescription = description;

        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_events_customview, parent, false);

            TextView title = (TextView)convertView.findViewById(R.id.event_title);
            TextView description = (TextView)convertView.findViewById(R.id.event_description);

            String current_date_pull = DateFormat.getDateInstance().format(new Date());

            TextView current_date = (TextView)convertView.findViewById(R.id.event_date);
            current_date.setText(current_date_pull);


            title.setText(mtitles[position]);
            description.setText(mdescription[position]);

            return convertView;
        }
    }
}