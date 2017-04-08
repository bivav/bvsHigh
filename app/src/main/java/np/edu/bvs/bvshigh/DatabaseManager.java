package np.edu.bvs.bvshigh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Bivav on 4/8/2017.
 */

public class DatabaseManager {
    SQLiteDatabase db;

    private static DatabaseManager ourInstance;

    public static DatabaseManager getInstance(Context context) {
        if(ourInstance==null){
            ourInstance = new DatabaseManager(context);
        }
        return ourInstance;
    }

    private DatabaseManager(Context context) {
       MyDBHandler myDBHandler = new MyDBHandler(context,null,null,1);
        db = myDBHandler.getWritableDatabase();
    }

    public void saveData(Routine_Database routine){

        ContentValues cv = new ContentValues();
        cv.put(MyDBHandler.COLUMN_start_time , routine.get_start_time());
        cv.put(MyDBHandler.COLUMN_end_time , routine.get_end_time());
        cv.put(MyDBHandler.COLUMN_subject , routine.get_subject());
        cv.put(MyDBHandler.COLUMN_teacher,routine.get_teacher());

        db.insert(MyDBHandler.TABLE_routine_sci_11_bio_sun,null,cv);


    }

    public ArrayList<Routine_Database> getData(){
        String sql = "SELECT * FROM " + MyDBHandler.TABLE_routine_sci_11_bio_sun + " ; " ;
        ArrayList<Routine_Database> dataList = new ArrayList<>();
        Cursor c = db.rawQuery(sql,null);
        while(c.moveToNext()){
            String start = c.getColumnName(c.getColumnIndex(MyDBHandler.COLUMN_start_time));
            String end = c.getColumnName(c.getColumnIndex(MyDBHandler.COLUMN_end_time));
            String subject = c.getColumnName(c.getColumnIndex(MyDBHandler.COLUMN_subject));
            String teacher = c.getColumnName(c.getColumnIndex(MyDBHandler.COLUMN_teacher));
            Routine_Database routine_database = new Routine_Database(start,end,subject,teacher);
            dataList.add(routine_database);
        }

        return dataList;


    }
}