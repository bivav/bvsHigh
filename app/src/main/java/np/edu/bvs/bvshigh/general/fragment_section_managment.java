package np.edu.bvs.bvshigh.general;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.students.fragment_attendance_teachers;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_section_managment extends Fragment {


    public fragment_section_managment() {
        // Required empty public constructor
    }

    String[] sections_list = {"Sec A", "Sec B", "Sec C", "Sec D"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_section_managment, container, false);

        GridView gridView = (GridView)view.findViewById(R.id.management_section);

        Section_adapter_management section_adapter_management = new Section_adapter_management(getContext(), sections_list);
        gridView.setAdapter(section_adapter_management);


//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
//
//                switch (position) {
//                    case 0:
//                        Intent intent = new Intent(getContext(), fragment_attendance_teachers.class);
//                        String sendSection = "A";
//                        Log.i("error_dude", sendSection);
//
//                        intent.putExtra("sendingSectionValue", sendSection);
//
//                        startActivity(intent);
//                        break;
//
//                    case 1:
//                        Intent intent1 = new Intent(getContext(), fragment_attendance_teachers.class);
//                        String sendSection_B = "B";
//
//                        intent1.putExtra("sendingSectionValue", sendSection_B);
//
//                        startActivity(intent1);
//                        break;
//                }
//
//            }
//        });

        return view;
    }

    private class Section_adapter_management extends ArrayAdapter {

        String[] sections;
        private Context context;

        Section_adapter_management(Context context, String[] msections) {
            super(context, R.layout.fragment_section_managment, R.id.management_section, msections);
            this.context = context;
            this.sections = msections;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.fragment_section_custom_view, parent, false);

            TextView sections = (TextView)convertView.findViewById(R.id.sections);

            sections.setFitsSystemWindows(true);

            sections.setText(sections_list[position]);

            return convertView;
        }
    }

}
