package np.edu.bvs.bvshigh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDBHandler extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bvs_high.db";
    private static final String TABLE_routine_sci_11_bio_sun = "routine_sci_11_bio_sun";
    private static final String COLUMN_start_time = "_start_time";
    private static final String COLUMN_end_time = "end_time";
    private static final String COLUMN_subject = "subject";
    private static final String COLUMN_teacher = "teacher";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_routine_sci_11_bio_sun + "(" +
                COLUMN_start_time + " VARCHAR PRIMARY KEY " + ");";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_routine_sci_11_bio_sun);
        onCreate(sqLiteDatabase);
    }

}
