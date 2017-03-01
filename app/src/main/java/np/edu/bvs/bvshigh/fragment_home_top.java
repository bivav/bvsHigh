package np.edu.bvs.bvshigh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class fragment_home_top extends Fragment {

    TextView name, grade, sec, branch_name, id_no;
    Button view_profile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        name = (TextView)view.findViewById(R.id.name);
        grade = (TextView)view.findViewById(R.id.grade);
        sec = (TextView)view.findViewById(R.id.sec);
        branch_name = (TextView)view.findViewById(R.id.branch_name);
        id_no = (TextView)view.findViewById(R.id.id_no);
        view_profile = (Button)view.findViewById(R.id.view_profile);

        name.setText(SharedPrefManager.getInstance(getContext()).getFullName());
        grade.setText(SharedPrefManager.getInstance(getContext()).getGrade());
        sec.setText(SharedPrefManager.getInstance(getContext()).getSec());
        branch_name.setText(SharedPrefManager.getInstance(getContext()).getBranch());

        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Student_Profile.class));
            }
        });



        return view;
    }
}
