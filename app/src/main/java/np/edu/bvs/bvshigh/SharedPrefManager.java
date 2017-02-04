package np.edu.bvs.bvshigh;


import android.content.Context;
import android.content.SharedPreferences;

class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static String SHARED_PREF_NAME = "savedPref";
    private static String KEY_USERNAME = "username";
    private static String KEY_USER_EMAIL = "useremail";
    private static String KEY_USER_ID = "userid";

    public SharedPrefManager(Context context) {
        mCtx = context;
    }


    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(int id, String username, String email){

        // Creating the SharedPref to store data of student
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // SharedEditor to save user details
        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USERNAME, username);
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

    public boolean isLoggedOut(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // clearing the SharedPref when logged out
        editor.clear();
        editor.apply();
        return true;
    }

}
