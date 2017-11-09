package kmitl.final_project.sirichai.eventontheday.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;

/**
 * Created by atomiz on 8/11/2560.
 */

public class DatabaseAdapter {
    MyDbHelper myDbHelper;
    public DatabaseAdapter(Context context) {
        myDbHelper = new MyDbHelper(context);
    }
    public long insertData(String title, String location, String start_date, String end_date, String start_time, String end_time
            , String alertTime, String detail){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDbHelper.TITLE, title);
        contentValues.put(MyDbHelper.LOCATION, location);
        contentValues.put(MyDbHelper.START_DATE, start_date);
        contentValues.put(MyDbHelper.END_DATE, end_date);
        contentValues.put(MyDbHelper.START_TIME, start_time);
        contentValues.put(MyDbHelper.END_TIME, end_time);
        contentValues.put(MyDbHelper.ALERT_TIME, alertTime);
        contentValues.put(MyDbHelper.DETAIL, detail);
        long id = db.insert(MyDbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public List<List> getData(){
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        List<List> datas = new ArrayList<>();
        String[] columns = {myDbHelper.ID, myDbHelper.TITLE, myDbHelper.LOCATION, myDbHelper.START_DATE, myDbHelper.END_DATE,
                myDbHelper.START_TIME, myDbHelper.END_TIME, myDbHelper.ALERT_TIME, myDbHelper.DETAIL};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            List<String> data = new ArrayList<>();
            int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.ID));
            String title = cursor.getString(cursor.getColumnIndex(myDbHelper.TITLE));
            String location = cursor.getString(cursor.getColumnIndex(myDbHelper.LOCATION));
            String start_date = cursor.getString(cursor.getColumnIndex(myDbHelper.START_DATE));
            String end_date = cursor.getString(cursor.getColumnIndex(myDbHelper.END_DATE));
            String start_time = cursor.getString(cursor.getColumnIndex(myDbHelper.START_TIME));
            String end_time = cursor.getString(cursor.getColumnIndex(myDbHelper.END_TIME));
            String alertTime = cursor.getString(cursor.getColumnIndex(myDbHelper.ALERT_TIME));
            String detail = cursor.getString(cursor.getColumnIndex(myDbHelper.DETAIL));
            data.add(title);
            data.add(location);
            data.add(start_date);
            data.add(end_date);
            data.add(start_time);
            data.add(end_time);
            data.add(alertTime);
            data.add(detail);
            datas.add(data);
//            buffer.append(cid+" "+title+" "+location+" "+start_date+" "+end_date+" "+start_time+" "+end_time+" "+alertTime+" "+detail+"\n");
        }
        return datas;
    }

    public int delete(String title){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        String[] whereArgs = {title};
        int count = db.delete(myDbHelper.TABLE_NAME, myDbHelper.TITLE+" = ?", whereArgs);
        return count;
    }

    public int clearDB(){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        int count = db.delete(myDbHelper.TABLE_NAME, null,null);
        return count;
    }

    public int update(String newTitle, String newLocation, String newStart_date, String newEnd_date, String newStart_time, String newEnd_time
            , String newAlertTime, String newDetail
            , String OldTitle){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.TITLE, newTitle);
        contentValues.put(myDbHelper.LOCATION, newLocation);
        contentValues.put(myDbHelper.START_DATE, newStart_date);
        contentValues.put(myDbHelper.END_DATE, newEnd_date);
        contentValues.put(myDbHelper.START_TIME, newStart_time);
        contentValues.put(myDbHelper.END_TIME, newEnd_time);
        contentValues.put(myDbHelper.ALERT_TIME, newAlertTime);
        contentValues.put(myDbHelper.DETAIL, newDetail);
        String[] whereArgs = {OldTitle};
        int count = db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.TITLE+" = ?",whereArgs);
        return count;

    }

    static class MyDbHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "EventDB"; //Database name
        private static final String TABLE_NAME = "EventTable"; //Table name
        private static final int DATABASE_VERSION = 1; //Database version
        private static final String ID = "_id"; //1st column (primary key)
        private static final String TITLE = "Title"; //2nd column
        private static final String LOCATION = "Location"; //3rd column
        private static final String START_DATE = "Start_date"; //4th column
        private static final String END_DATE = "End_date"; //5th column
        private static final String START_TIME = "Start_time"; //6th column
        private static final String END_TIME = "End_time"; //7th column
        private static final String ALERT_TIME = "Alert"; //8th column
        private static final String DETAIL = "Detail"; //9th column
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+ " ("+
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TITLE+" VARCHAR(255), "+
                LOCATION+" VARCHAR(225), "+
                START_DATE+" VARCHAR(225), "+
                END_DATE+" VARCHAR(225), "+
                START_TIME+" VARCHAR(225), "+
                END_TIME+" VARCHAR(225), "+
                ALERT_TIME+" VARCHAR(225), "+
                DETAIL+" VARCHAR(255));";
        private Context context;
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;

        public MyDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try {
                sqLiteDatabase.execSQL(CREATE_TABLE);
            }catch (Exception e){
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            try {
                Toast.makeText(context, "OnUpgrade", Toast.LENGTH_LONG).show();
                sqLiteDatabase.execSQL(DROP_TABLE);
                onCreate(sqLiteDatabase);
            }catch (Exception e){
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
