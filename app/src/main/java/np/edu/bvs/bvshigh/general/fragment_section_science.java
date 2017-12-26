package np.edu.bvs.bvshigh.general;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.students.fragment_attendance_teachers;

public class fragment_section_science extends Fragment {

    String[] section_list = {"Sec A", "Sec B", "Sec C", "Sec D"};

    public fragment_section_science() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_section_science, container, false);

        GridView gridView = (GridView)view.findViewById(R.id.science_section);
        Science_section_adapter adapter = new Science_section_adapter(getContext(), section_list);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                switch (position) {
                    case 0:
                        Intent intent = new Intent(getContext(), fragment_attendance_teachers.class);
                        String sendSection = "A";
                        Log.i("error_dude", sendSection);

                        intent.putExtra("sendingSectionValue", sendSection);

                        startActivity(intent);
                        break;

                    case 1:
                        Intent intent1 = new Intent(getContext(), fragment_attendance_teachers.class);
                        String sendSection_B = "B";

                        intent1.putExtra("sendingSectionValue", sendSection_B);

                        startActivity(intent1);
                        break;
                }

            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;
    }

    private class Science_section_adapter extends ArrayAdapter {

        private String[] sections;
        private Context context;

        @SuppressWarnings("unchecked")
        Science_section_adapter(@NonNull Context context, String[] sections) {
            super(context, R.layout.fragment_section_science, R.id.science_section, sections);
            this.context = context;
            this.sections = sections;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_section_custom_view, parent, false);

            TextView sections = (TextView)convertView.findViewById(R.id.sections);
            sections.setFitsSystemWindows(true);

            sections.setText(section_list[position]);

            return convertView;
        }
    }

}
