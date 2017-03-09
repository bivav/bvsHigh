package np.edu.bvs.bvshigh;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Student_Profile extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    TextView student_name, branch_name, student_email, edit_pic;
    de.hdodenhof.circleimageview.CircleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        imageView = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.profile_pic);

        student_name = (TextView)findViewById(R.id.student_name);
        branch_name = (TextView)findViewById(R.id.branch_name);
        student_email = (TextView)findViewById(R.id.student_email);
        edit_pic =(TextView)findViewById(R.id.edit_pic);

        student_name.setText(SharedPrefManager.getInstance(getApplicationContext()).getFullName());
        branch_name.setText(SharedPrefManager.getInstance(getApplicationContext()).getBranch());
        student_email.setText(SharedPrefManager.getInstance(getApplicationContext()).getEmail());

        edit_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        });

        //loading the image saved in above directory
        String path = String.valueOf(getApplicationContext().getDir("imageDir", Context.MODE_PRIVATE));
        loadImageFromStorage(path);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.edit_profile:
                startActivity(new Intent(getApplicationContext(), Student_Profile_Edit.class));
                break;

            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            // Source is Drop box or other similar local file path
            return contentURI.getPath();

        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            String filePathColumn = cursor.getString(idx);
            cursor.close();
            return filePathColumn;
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());

        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(mypath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }

    public void loadImageFromStorage(String path) {
        try {
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