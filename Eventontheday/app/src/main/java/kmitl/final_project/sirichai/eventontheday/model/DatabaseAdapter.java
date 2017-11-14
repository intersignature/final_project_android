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
            , String alertDate, String alertTime, String detail){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDbHelper.TITLE, title);
        contentValues.put(MyDbHelper.LOCATION, location);
        contentValues.put(MyDbHelper.START_DATE, start_date);
        contentValues.put(MyDbHelper.END_DATE, end_date);
        contentValues.put(MyDbHelper.START_TIME, start_time);
        contentValues.put(MyDbHelper.END_TIME, end_time);
        contentValues.put(MyDbHelper.ALERT_DATE, alertDate);
        contentValues.put(MyDbHelper.ALERT_TIME, alertTime);
        contentValues.put(MyDbHelper.DETAIL, detail);
        long id = db.insert(MyDbHelper.TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }

    public List<String> getEachData(String selectId){
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        List<String> eachData= new ArrayList<String>();
        String[] columns = {MyDbHelper.ID, MyDbHelper.TITLE, MyDbHelper.LOCATION, MyDbHelper.START_DATE, MyDbHelper.END_DATE,
                MyDbHelper.START_TIME, MyDbHelper.END_TIME, MyDbHelper.ALERT_DATE , MyDbHelper.ALERT_TIME, MyDbHelper.DETAIL};
        String[] whereArgs = {selectId};
        Cursor cursor = db.query(MyDbHelper.TABLE_NAME, columns, MyDbHelper.ID+"=?", whereArgs, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(MyDbHelper.ID)));
            String title = cursor.getString(cursor.getColumnIndex(MyDbHelper.TITLE));
            String location = cursor.getString(cursor.getColumnIndex(MyDbHelper.LOCATION));
            String start_date = cursor.getString(cursor.getColumnIndex(MyDbHelper.START_DATE));
            String end_date = cursor.getString(cursor.getColumnIndex(MyDbHelper.END_DATE));
            String start_time = cursor.getString(cursor.getColumnIndex(MyDbHelper.START_TIME));
            String end_time = cursor.getString(cursor.getColumnIndex(MyDbHelper.END_TIME));
            String alert_date = cursor.getString(cursor.getColumnIndex(MyDbHelper.ALERT_DATE));
            String alert_time = cursor.getString(cursor.getColumnIndex(MyDbHelper.ALERT_TIME));
            String detail = cursor.getString(cursor.getColumnIndex(MyDbHelper.DETAIL));
            eachData.add(title);
            eachData.add(location);
            eachData.add(start_date);
            eachData.add(end_date);
            eachData.add(start_time);
            eachData.add(end_time);
            eachData.add(alert_date);
            eachData.add(alert_time);
            eachData.add(detail);
            eachData.add(String.valueOf(id));
        }
        db.close();
        return eachData;
    }

    public List<List> getData(){
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        List<List> datas = new ArrayList<>();
        String[] columns = {MyDbHelper.ID, MyDbHelper.TITLE, MyDbHelper.LOCATION, MyDbHelper.START_DATE, MyDbHelper.END_DATE,
                MyDbHelper.START_TIME, MyDbHelper.END_TIME, MyDbHelper.ALERT_DATE, MyDbHelper.ALERT_TIME, MyDbHelper.DETAIL};
        Cursor cursor = db.query(MyDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            List<String> data = new ArrayList<>();
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(MyDbHelper.ID)));
            String title = cursor.getString(cursor.getColumnIndex(MyDbHelper.TITLE));
            String location = cursor.getString(cursor.getColumnIndex(MyDbHelper.LOCATION));
            String start_date = cursor.getString(cursor.getColumnIndex(MyDbHelper.START_DATE));
            String end_date = cursor.getString(cursor.getColumnIndex(MyDbHelper.END_DATE));
            String start_time = cursor.getString(cursor.getColumnIndex(MyDbHelper.START_TIME));
            String end_time = cursor.getString(cursor.getColumnIndex(MyDbHelper.END_TIME));
            String alert_date = cursor.getString(cursor.getColumnIndex(MyDbHelper.ALERT_DATE));
            String alert_time = cursor.getString(cursor.getColumnIndex(MyDbHelper.ALERT_TIME));
            String detail = cursor.getString(cursor.getColumnIndex(MyDbHelper.DETAIL));
            data.add(title);
            data.add(location);
            data.add(start_date);
            data.add(end_date);
            data.add(start_time);
            data.add(end_time);
            data.add(alert_date);
            data.add(alert_time);
            data.add(detail);
            datas.add(data);
            data.add(String.valueOf(id));
//            buffer.append(cid+" "+title+" "+location+" "+start_date+" "+end_date+" "+start_time+" "+end_time+" "+alertTime+" "+detail+"\n");
        }
        db.close();
        return datas;
    }

    public int delete(String id){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        String[] whereArgs = {id};
        int count = db.delete(MyDbHelper.TABLE_NAME, MyDbHelper.ID +" = ?", whereArgs);
        db.close();
        return count;
    }

    public int clearDB(){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        int count = db.delete(MyDbHelper.TABLE_NAME, null,null);
        db.close();
        return count;
    }

    public int update(String newTitle, String newLocation, String newStart_date, String newEnd_date, String newStart_time, String newEnd_time
            , String newAlertDate, String newAlertTime, String newDetail
            , String OldId){
        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDbHelper.TITLE, newTitle);
        contentValues.put(MyDbHelper.LOCATION, newLocation);
        contentValues.put(MyDbHelper.START_DATE, newStart_date);
        contentValues.put(MyDbHelper.END_DATE, newEnd_date);
        contentValues.put(MyDbHelper.START_TIME, newStart_time);
        contentValues.put(MyDbHelper.END_TIME, newEnd_time);
        contentValues.put(MyDbHelper.ALERT_DATE, newAlertDate);
        contentValues.put(MyDbHelper.ALERT_TIME, newAlertTime);
        contentValues.put(MyDbHelper.DETAIL, newDetail);
        String[] whereArgs = {OldId};
        int count = db.update(MyDbHelper.TABLE_NAME,contentValues, MyDbHelper.ID +" = ?",whereArgs);
        db.close();
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
        private static final String ALERT_DATE = "Alert_date"; //8th column
        private static final String DETAIL = "Detail"; //9th column
        public static final String ALERT_TIME = "Alert_time";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+ " ("+
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TITLE+" VARCHAR(255), "+
                LOCATION+" VARCHAR(225), "+
                START_DATE+" VARCHAR(225), "+
                END_DATE+" VARCHAR(225), "+
                START_TIME+" VARCHAR(225), "+
                END_TIME+" VARCHAR(225), "+
                ALERT_DATE+" VARCHAR(225), "+
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
