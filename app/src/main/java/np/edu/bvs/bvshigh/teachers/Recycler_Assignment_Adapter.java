package np.edu.bvs.bvshigh.teachers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.interf.onMoveAndSwipedListener;

class Recycler_Assignment_Adapter extends RecyclerView.Adapter<Recycler_Assignment_Adapter.DetailsHolder>
        implements onMoveAndSwipedListener {

    private List<fragment_assignment.Assignment_Details> detailsList;
    protected int color;
    private Context context;


    Recycler_Assignment_Adapter( Context context, List<fragment_assignment.Assignment_Details> detailsList){
        this.context = context;
        this.detailsList = detailsList;
    }

    class DetailsHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView teachers;
        TextView subjects;
        private View view;
        TextView assignments;

        DetailsHolder(View itemView) {
            super(itemView);
            view = itemView;
            cv = (CardView)itemView.findViewById(R.id.card_view_item_recycler_view);
            teachers = (TextView)itemView.findViewById(R.id.texting);
            subjects = (TextView)itemView.findViewById(R.id.texting2);
            assignments = (TextView) itemView.findViewById(R.id.title_assignment);
        }
    }


    @Override
    public DetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_assignment, parent, false);
        return new DetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(final DetailsHolder holder, final int position) {

        Log.i("CallingTest", String.valueOf(detailsList.get(position).assignments));

        holder.teachers.setText(detailsList.get(position).teachers);
        holder.subjects.setText(detailsList.get(position).subjects);
        holder.assignments.setText(detailsList.get(position).assignments);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, " "+holder.getAdapterPosition()+" ", Toast.LENGTH_SHORT).show();
            }
        });

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
