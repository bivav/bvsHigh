package np.edu.bvs.bvshigh.general;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import np.edu.bvs.bvshigh.R;

public class message_us_alert_box {

    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.message_to_school);

        EditText text = (EditText) dialog.findViewById(R.id.subject);

        Button dialogButton = (Button) dialog.findViewById(R.id.send);
        dialogButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }
}
