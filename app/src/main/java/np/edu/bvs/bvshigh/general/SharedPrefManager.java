package np.edu.bvs.bvshigh.general;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import np.edu.bvs.bvshigh.sqLite_handler.MyDBHandler;
import np.edu.bvs.bvshigh.teachers.login_teachers.Login_Teacher;

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private Context mCtx;
    de.hdodenhof.circleimageview.CircleImageView imageView;


    private static String SHARED_PREF_NAME = "savedPref";

    private static String KEY_USERNAME = "username";
    private static String KEY_USER_EMAIL = "useremail";
    private static String KEY_USER_ID = "userid";
    private static String KEY_FULLNAME = "fullname";
    private static String KEY_GRADE = "grade";
    private static String KEY_SEC = "sec";
    private static String KEY_BRANCH = "branch";

    //teachers
    private static String KEY_USER_TEACHERS_ID = "userid";
    private static String KEY_FULLNAME_TEACHERS = "fullname";
    private static String KEY_USER_EMAIL_TEACHERS = "useremail_teacher";
    private static String KEY_USERNAME_TEACHERS = "username_teachers";
    private static String KEY_BRANCH_TEACHERS = "branch";
    private static String KEY_CONTACT_TEACHERS = "sec";

    public SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(int id, String username, String email, String fullname,
                             String grade, String sec, String branch){

        // Creating the SharedPref to store data of student
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // SharedEditor to save user details
        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_FULLNAME, fullname);
        editor.putString(KEY_GRADE, grade);
        editor.putString(KEY_SEC, sec);
        editor.putString(KEY_BRANCH, branch);
        editor.apply();

        return true;
    }


    public boolean userLoginTeachers(int id, String username, String email, String fullname,
                             String contact, String branch){

        // Creating the SharedPref to store data of student
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // SharedEditor to save user details
        editor.putInt(KEY_USER_TEACHERS_ID, id);
        editor.putString(KEY_USER_EMAIL_TEACHERS, email);
        editor.putString(KEY_USERNAME_TEACHERS, username);
        editor.putString(KEY_FULLNAME_TEACHERS, fullname);
        editor.putString(KEY_CONTACT_TEACHERS, contact);
        editor.putString(KEY_BRANCH_TEACHERS, branch);
        editor.apply();

        return true;
    }


    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        // if this condition is not equal to null then there is some user logged in SharedPref
        if (sharedPreferences.getString(KEY_USERNAME, null) != null){
            return true;
        }else {

            // returns if the user is not logged in
            return false;
        }
    }

    public boolean isLoggedInTeachers(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        // if this condition is not equal to null then there is some user logged in SharedPref
        if (sharedPreferences.getString(KEY_USERNAME_TEACHERS, null) != null){
            return true;
        }else {

            // returns if the user is not logged in
            return false;
        }
    }

    public boolean isLoggedOut(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // clearing the SharedPref when logged out
        editor.clear();
        editor.apply();
        return true;
    }

    public boolean isLoggedOutTeachers(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // clearing the SharedPref when logged out
        editor.clear();
        editor.apply();



        return true;
    }

    public String getUsername(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }

    public String getFullName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FULLNAME, null);
    }

    public String getGrade(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_GRADE, null);
    }
    public String getSec(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SEC, null);
    }
    public String getBranch(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_BRANCH, null);
    }

    //Teachers
    public String getUsername_Teachers(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getEmail_Teachers(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }

    public String getFullName_Teachers(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FULLNAME, null);
    }

    public String getGrade_Teachers(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_GRADE, null);
    }

    public String getBranch_Teachers(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_BRANCH, null);
    }

    public String getContact_Teachers(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CONTACT_TEACHERS, null);
    }

}
