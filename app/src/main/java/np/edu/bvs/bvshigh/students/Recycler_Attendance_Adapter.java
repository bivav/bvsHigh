package np.edu.bvs.bvshigh.students;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import np.edu.bvs.bvshigh.R;


public class Recycler_Attendance_Adapter extends RecyclerView.Adapter<Recycler_Attendance_Adapter.DetailsHolder> {

    private List<Students_Attendance_List> student_list;
    private Context context;

    Recycler_Attendance_Adapter(Context context, List<Students_Attendance_List> list) {
        this.context = context;
        this.student_list = list;
    }

    class DetailsHolder extends RecyclerView.ViewHolder {
        TextView student_names;
        CheckBox checked;
        DetailsHolder(View itemView) {
            super(itemView);

            student_names = (TextView)itemView.findViewById(R.id.student_name_list);
            checked = (CheckBox)itemView.findViewById(R.id.checked);

        }
    }

    @Override
    public DetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_attendance_customlist_view, parent, false);

        return new DetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(final DetailsHolder holder, final int position) {

        holder.student_names.setText(student_list.get(position).getName());

        holder.checked.setChecked(student_list.get(position).isSelected());
        holder.checked.setTag(student_list.get(position));

        holder.checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Students_Attendance_List contact = (Students_Attendance_List) cb.getTag();

                contact.setSelected(cb.isChecked());
                student_list.get(holder.getAdapterPosition()).setSelected(cb.isChecked());

                if (holder.checked.isChecked()) {
                    holder.checked.setChecked(true);
                    Toast.makeText(context, "Checked! " + holder.getAdapterPosition() + " " + holder.student_names.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    holder.checked.setChecked(false);
                    Toast.makeText(context, "Not Checked! " + holder.getAdapterPosition() + " " + holder.student_names.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return student_list.size();
    }
}
