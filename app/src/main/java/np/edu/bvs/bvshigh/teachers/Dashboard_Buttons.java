package np.edu.bvs.bvshigh.teachers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import np.edu.bvs.bvshigh.R;

public class Dashboard_Buttons extends BaseAdapter {

    private Context mContext;
    private Integer[] button_names = {
            R.drawable.icons_rout,
            R.drawable.icons_alerts,
            R.drawable.icons_results,
            R.drawable.icons_homework,
            R.drawable.icons_bus,
            R.drawable.icons_calendar
    };

    public Dashboard_Buttons(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return button_names.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.dashboard_buttons, viewGroup, false);

        ImageView imageView = (ImageView)view.findViewById(R.id.buttons);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        imageView.setImageResource(button_names[i]);

        return view;
    }
}