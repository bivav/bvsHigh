package np.edu.bvs.bvshigh.students;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import np.edu.bvs.bvshigh.R;
import np.edu.bvs.bvshigh.general.SharedPrefManager;

public class fragment_home_top extends Fragment {

    TextView name, grade, sec, branch_name, id_no;
    Button view_profile;
    de.hdodenhof.circleimageview.CircleImageView imageView;
    private static int RESULT_LOAD_IMAGE = 1;
    Main_Activity_Students mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (Main_Activity_Students)this.getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        name = (TextView)view.findViewById(R.id.name);
        grade = (TextView)view.findViewById(R.id.grade);
        sec = (TextView)view.findViewById(R.id.sec);
        branch_name = (TextView)view.findViewById(R.id.branch_name);
        id_no = (TextView)view.findViewById(R.id.id_no);
        view_profile = (Button)view.findViewById(R.id.view_profile);

        imageView = (de.hdodenhof.circleimageview.CircleImageView)view.findViewById(R.id.user_pic);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Pop Up alert for changing the picture
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mActivity);
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, RESULT_LOAD_IMAGE);
                    }
                });

                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "Canceled!", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setMessage("Upload a new profile picture.");
                alertDialog.create();
                alertDialog.show();
            }
        });

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

        //loading the image saved in above directory
        String path = String.valueOf(getContext().getDir("imageDir", Context.MODE_PRIVATE));
        loadImageFromStorage(path);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // image is extracted and displayed in the image view
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            String filePathColumn = getRealPathFromURI(selectedImage);

            imageView.setImageBitmap(BitmapFactory.decodeFile(filePathColumn));

            // Storing the image in internal memory /data/data/"appName"/app_image
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            saveToInternalStorage(bitmap);

        }
    }

    private String getRealPathFromURI(Uri contentURI) {

        Cursor cursor = mActivity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            // Source is Dropbox or other similar local file path
            return contentURI.getPath();

        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            String filePathColumn = cursor.getString(idx);
            cursor.close();
            return filePathColumn;
        }
    }

    // func to save the picture as profile.jpg inside the app directory not external
    private String saveToInternalStorage(Bitmap bitmapImage){

        ContextWrapper cw = new ContextWrapper(getContext());

        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

        // Create imageDir along with keep profile.jpg
        File mypath = new File(directory,"profile.jpg");

        FileOutputStream fos;
        try {

            fos = new FileOutputStream(mypath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            // the image saves in jpg but compressing in png
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return directory.getAbsolutePath();
    }

    // loading the image from app directory
    public void loadImageFromStorage(String path) {
        try {
            // calling the profile.jpg
            File f = new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            imageView.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}