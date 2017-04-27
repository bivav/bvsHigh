package np.edu.bvs.bvshigh.sqLite_handler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDBHandler extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bvs_high.db";

    /** This is one table for sunday for bio class */
    public static final String TABLE_routine_sci_11_bio_sun = "routine_sci_11_bio_sun";
    public static final String TABLE_routine_sci_11_bio_mon = "routine_sci_11_bio_mon";
    public static final String TABLE_routine_sci_11_bio_tue = "routine_sci_11_bio_tue";
    public static final String TABLE_routine_sci_11_bio_wed = "routine_sci_11_bio_wed";
    public static final String TABLE_routine_sci_11_bio_thur = "routine_sci_11_bio_thur";
    public static final String TABLE_routine_sci_11_bio_fri = "routine_sci_11_bio_fri";

    private static final String COLUMN_ID = "_id";
    static final String COLUMN_start_time = "start_time";
    static final String COLUMN_end_time = "end_time";
    static final String COLUMN_subject = "subject";
    static final String COLUMN_teacher = "teacher";

    @SuppressWarnings("UnusedParameters")
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static String sun_routine_table() {
        return "CREATE TABLE "
                + TABLE_routine_sci_11_bio_sun
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_start_time + " VARCHAR, "
                + COLUMN_end_time + " VARCHAR, "
                + COLUMN_subject + " VARCHAR, "
                + COLUMN_teacher + " VARCHAR "
                + ");";
    }

    public static String mon_routine_table() {
        return "CREATE TABLE "
                + TABLE_routine_sci_11_bio_mon
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_start_time + " VARCHAR, "
                + COLUMN_end_time + " VARCHAR, "
                + COLUMN_subject + " VARCHAR, "
                + COLUMN_teacher + " VARCHAR "
                + ");";
    }

    public static String tue_routine_table() {
        return "CREATE TABLE "
                + TABLE_routine_sci_11_bio_tue
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_start_time + " VARCHAR, "
                + COLUMN_end_time + " VARCHAR, "
                + COLUMN_subject + " VARCHAR, "
                + COLUMN_teacher + " VARCHAR "
                + ");";
    }


    public static String wed_routine_table() {
        return "CREATE TABLE "
                + TABLE_routine_sci_11_bio_wed
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_start_time + " VARCHAR, "
                + COLUMN_end_time + " VARCHAR, "
                + COLUMN_subject + " VARCHAR, "
                + COLUMN_teacher + " VARCHAR "
                + ");";
    }

    public static String thur_routine_table() {
        return "CREATE TABLE "
                + TABLE_routine_sci_11_bio_thur
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_start_time + " VARCHAR, "
                + COLUMN_end_time + " VARCHAR, "
                + COLUMN_subject + " VARCHAR, "
                + COLUMN_teacher + " VARCHAR "
                + ");";
    }

    public static String fri_routine_table() {
        return "CREATE TABLE "
                + TABLE_routine_sci_11_bio_fri
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_start_time + " VARCHAR, "
                + COLUMN_end_time + " VARCHAR, "
                + COLUMN_subject + " VARCHAR, "
                + COLUMN_teacher + " VARCHAR "
                + ");";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        /* This is one table for sunday for bio class */
        String TAG = "Routine";
        Log.i(TAG, "Tables exists, dropping the table.");

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_routine_sci_11_bio_sun);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_routine_sci_11_bio_mon);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_routine_sci_11_bio_tue);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_routine_sci_11_bio_wed);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_routine_sci_11_bio_thur);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_routine_sci_11_bio_fri);
        onCreate(sqLiteDatabase);

        Log.i(TAG, "Tables deleted and new table has been created.");
    }

}
