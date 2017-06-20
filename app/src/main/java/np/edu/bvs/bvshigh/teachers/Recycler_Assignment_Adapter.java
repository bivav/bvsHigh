package np.edu.bvs.bvshigh.teachers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.interf.onMoveAndSwipedListener;

class Recycler_Assignment_Adapter extends RecyclerView.Adapter<Recycler_Assignment_Adapter.DetailsHolder>
        implements onMoveAndSwipedListener {

    private List<fragment_assignment.Assignment_Details> detailsList;
    protected int color;
    private Context context;
    private String[] getAssignmentServer, getAssignmentDateServer, getAssignmentSubjectServer, getAssignmentTeacherNameServer;

    private int icons[] = {
            R.drawable.acc,
            R.drawable.bot,
            R.drawable.bs,
            R.drawable.com,
            R.drawable.eco,
            R.drawable.eng,
            R.drawable.hm,
            R.drawable.mar,
            R.drawable.mat,
            R.drawable.nep,
            R.drawable.phy,
            R.drawable.zoo,
            R.drawable.che
    };


    Recycler_Assignment_Adapter(Context context, List<fragment_assignment.Assignment_Details> detailsList){
        this.context = context;
        this.detailsList = detailsList;

    }

    class DetailsHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView teachers;
        TextView title_assignment, teacher_name, due_date, assignment_details;
        TextView subjects;
        private View view;
        private RelativeLayout relativeLayout;
        TextView assignments;

        DetailsHolder(View itemView) {
            super(itemView);
            view = itemView;
            cv = (CardView)itemView.findViewById(R.id.card_view_item_recycler_view);

            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rela_round);
            teacher_name = (TextView)itemView.findViewById(R.id.teacher_name);
            due_date = (TextView)itemView.findViewById(R.id.due_date);
            assignment_details = (TextView)itemView.findViewById(R.id.assignment_details);

        }
    }


    @Override
    public DetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_assignment, parent, false);
        return new DetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(final DetailsHolder holder, final int position) {

        Log.i("CallingTest", String.valueOf(detailsList.get(position).getAssignmentSubjectServer[position]));

        holder.teacher_name.setText(detailsList.get(position).getAssignmentTeacherNameServer[position]);
        holder.due_date.setText(detailsList.get(position).getAssignmentDateServer[position]);
        holder.assignment_details.setText(detailsList.get(position).getAssignmentServer[position]);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, " "+holder.getAdapterPosition()+" ", Toast.LENGTH_SHORT).show();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Objects.equals(detailsList.get(position).getAssignmentSubjectServer[position], "Science")) {
                for (int i = 0; i < detailsList.size(); i++) {
                    holder.relativeLayout.setBackground(ContextCompat.getDrawable(context, icons[1]));
                }
            }else if (Objects.equals(detailsList.get(position).getAssignmentSubjectServer[position], "Technical")) {
                for (int i = 0; i < detailsList.size(); i++) {
                    holder.relativeLayout.setBackground(ContextCompat.getDrawable(context, icons[3]));
                }
            }
        }

    }


    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(detailsList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        detailsList.remove(position);
        notifyItemRemoved(position);
    }

    void setItems(int color) {
        this.color = color;
        notifyDataSetChanged();
    }

}
