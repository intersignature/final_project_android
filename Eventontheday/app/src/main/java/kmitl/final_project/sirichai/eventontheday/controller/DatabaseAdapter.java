package kmitl.final_project.sirichai.eventontheday.controller;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.EventInfo;
import kmitl.final_project.sirichai.eventontheday.model.ListPreset;
import kmitl.final_project.sirichai.eventontheday.notification.TimerService;

/**
 * Created by atomiz on 8/11/2560.
 */

public class DatabaseAdapter {
    public DatabaseAdapter(Context context) {
        EventOnTheDayDB.getInstance(context, DB_NAME);
    }

    /*
    Event database zone
     */

    public String deleteDataEvent(String id) {
        String result = "success";

        String DeleteQuery = "DELETE FROM EVENT " + "WHERE ID = '" + id + "';";
        try {
            EventOnTheDayDB.execute(DeleteQuery);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public void clearDataEvent(){
        String ClearQuery = "DELETE FROM EVENT;";
        try {
            EventOnTheDayDB.execute(ClearQuery);
        }catch (Exception e){
            return;
        }
    }

    public String updateDataEvent(String newTitle, String newLocation, String newStart_date, String newEnd_date, String newStart_time, String newEnd_time
            , String newAlertDate, String newAlertTime, String newDetail
            , String OldId) {
        String result = "success";
        String UpdateQuery = " UPDATE EVENT set "
                + "TITLE='" + newTitle + "', "
                + "LOCATION='" + newLocation + "', "
                + "START_DATE='" + newStart_date + "', "
                + "END_DATE='" + newEnd_date + "', "
                + "START_TIME='" + newStart_time + "', "
                + "END_TIME='" + newEnd_time + "', "
                + "ALERT_DATE='" + newAlertDate + "', "
                + "ALERT_TIME='" + newAlertTime + "', "
                + "DETAIL='" + newDetail + "' "
                + "where ID ='" + OldId + "' ";
        try {
            EventOnTheDayDB.execute(UpdateQuery);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public String insertDataEvent(String title, String location, String start_date, String end_date, String start_time, String end_time
            , String alertDate, String alertTime, String detail) {
        String result = "success";
        String insertQuery = "INSERT INTO EVENT VALUES('"
                + (getLastIdEvent() + 2) + "'," + "'"
                + title + "'," + "'"
                + location + "','"
                + start_date + "','"
                + end_date + "','"
                + start_time + "','"
                + end_time + "','"
                + alertDate + "','"
                + alertTime + "','"
                + detail + "');";

        try {
            EventOnTheDayDB.execute(insertQuery);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public int getLastIdEvent() {
        String query = "SELECT * FROM EVENT";
        Cursor c1 = EventOnTheDayDB.rawQuery(query);
        c1.moveToLast();
        if (c1.getCount() == 0) {
            return -1;
        } else {
            c1.moveToLast();
            return Integer.parseInt(c1.getString(c1.getColumnIndex("ID")));
        }
    }

    public EventInfo getEachDataEvent(String selectId) {
        String query = "SELECT * FROM EVENT WHERE ID = " + selectId;
        Cursor c1 = EventOnTheDayDB.rawQuery(query);
        EventInfo eventInfo = null;
        if (c1 != null && c1.getCount() != 0) {
            while (c1.moveToNext()) {
                int id = Integer.parseInt(c1.getString(c1.getColumnIndex("ID")));
                String title = c1.getString(c1.getColumnIndex("TITLE"));
                String location = c1.getString(c1.getColumnIndex("LOCATION"));
                String start_date = c1.getString(c1.getColumnIndex("START_DATE"));
                String end_date = c1.getString(c1.getColumnIndex("END_DATE"));
                String start_time = c1.getString(c1.getColumnIndex("START_TIME"));
                String end_time = c1.getString(c1.getColumnIndex("END_TIME"));
                String alert_date = c1.getString(c1.getColumnIndex("ALERT_DATE"));
                String alert_time = c1.getString(c1.getColumnIndex("ALERT_TIME"));
                String detail = c1.getString(c1.getColumnIndex("DETAIL"));
                eventInfo = new EventInfo(title, location, start_date, end_date, start_time, end_time, alert_date, alert_time,
                        detail, String.valueOf(id));
            }
        }
        c1.close();
        return eventInfo;
    }

    public List<EventInfo> getDataEvent() {
        String query = "SELECT * FROM EVENT";
        Cursor c1 = EventOnTheDayDB.rawQuery(query);
        List<EventInfo> datas = new ArrayList<>();
        if (c1 != null && c1.getCount() != 0) {
            while (c1.moveToNext()) {
                int id = Integer.parseInt(c1.getString(c1.getColumnIndex("ID")));
                String title = c1.getString(c1.getColumnIndex("TITLE"));
                String location = c1.getString(c1.getColumnIndex("LOCATION"));
                String start_date = c1.getString(c1.getColumnIndex("START_DATE"));
                String end_date = c1.getString(c1.getColumnIndex("END_DATE"));
                String start_time = c1.getString(c1.getColumnIndex("START_TIME"));
                String end_time = c1.getString(c1.getColumnIndex("END_TIME"));
                String alert_date = c1.getString(c1.getColumnIndex("ALERT_DATE"));
                String alert_time = c1.getString(c1.getColumnIndex("ALERT_TIME"));
                String detail = c1.getString(c1.getColumnIndex("DETAIL"));
                EventInfo eventInfo = new EventInfo(title, location, start_date, end_date, start_time, end_time, alert_date,
                        alert_time, detail, String.valueOf(id));
                datas.add(eventInfo);
            }
        }
        c1.close();
        return datas;
    }

    /*
    Preset database zone
     */

    public String deleteDataPreset(String id) {
        String result = "success";
        String DeleteQuery = "DELETE FROM PRESET " + "WHERE ID = '" + id + "';";
        try {
            EventOnTheDayDB.execute(DeleteQuery);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public void clearDataPreset(){
        String ClearQuery = "DELETE FROM PRESET;";
        try {
            EventOnTheDayDB.execute(ClearQuery);
        }catch (Exception e){
            return;
        }
    }

    public String updateDataPreset(String newTitle, String newLocation, String newDetail, String OldId) {
        String result = "success";
        String UpdateQuery = " UPDATE PRESET set "
                + "TITLE='" + newTitle + "', "
                + "LOCATION='" + newLocation + "', "
                + "DETAIL='" + newDetail + "' "
                + "where ID ='" + OldId + "' ";
        try {
            EventOnTheDayDB.execute(UpdateQuery);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public String insertDataPreset(String title, String location, String detail) {
        String result = "success";
        String insertQuery = "INSERT INTO PRESET VALUES('"
                + (getLastIdPreset() + 2) + "'," + "'"
                + title + "'," + "'"
                + location + "','"
                + detail + "');";
        try {
            EventOnTheDayDB.execute(insertQuery);
            return result;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public int getLastIdPreset() {
        String query = "SELECT * FROM PRESET";
        Cursor c1 = EventOnTheDayDB.rawQuery(query);
        c1.moveToLast();
        if (c1.getCount() == 0) {
            return -1;
        } else {
            c1.moveToLast();
            return Integer.parseInt(c1.getString(c1.getColumnIndex("ID")));
        }
    }

    public ListPreset getEachDataPreset(String selectId) {
        String query = "SELECT * FROM PRESET WHERE ID = " + selectId;
        Cursor c1 = EventOnTheDayDB.rawQuery(query);
        ListPreset listPreset = null;
        if (c1 != null && c1.getCount() != 0) {
            while (c1.moveToNext()) {
                int id = Integer.parseInt(c1.getString(c1.getColumnIndex("ID")));
                String title = c1.getString(c1.getColumnIndex("TITLE"));
                String location = c1.getString(c1.getColumnIndex("LOCATION"));
                String detail = c1.getString(c1.getColumnIndex("DETAIL"));
                listPreset = new ListPreset(title, location, detail, String.valueOf(id));
            }
        }
        c1.close();
        return listPreset;
    }

    public List<ListPreset> getDataPreset() {
        String query = "SELECT * FROM PRESET";
        Cursor c1 = EventOnTheDayDB.rawQuery(query);
        List<ListPreset> datas = new ArrayList<>();
        if (c1 != null && c1.getCount() != 0) {
            while (c1.moveToNext()) {
                int id = Integer.parseInt(c1.getString(c1.getColumnIndex("ID")));
                String title = c1.getString(c1.getColumnIndex("TITLE"));
                String location = c1.getString(c1.getColumnIndex("LOCATION"));
                String detail = c1.getString(c1.getColumnIndex("DETAIL"));
                ListPreset listPreset = new ListPreset(title, location, detail, String.valueOf(id));
                datas.add(listPreset);
            }
        }
        c1.close();
        return datas;
    }


    private static final String DB_NAME = "DB_EVENTONTHEDAY.db";

    static class EventOnTheDayDB extends SQLiteOpenHelper {

        private static SQLiteDatabase sqliteDb;
        private static EventOnTheDayDB instance;
        private static final int DATABASE_VERSION = 1;

        static Cursor cursor = null;

        EventOnTheDayDB(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        private static void initialize(Context context, String databaseName) {
            if (instance == null) {

                if (!checkDatabase(context, databaseName)) {

                    try {
                        copyDataBase(context, databaseName);
                    } catch (IOException e) {

                        System.out.println(databaseName
                                + " does not exists ");
                    }
                }

                instance = new EventOnTheDayDB(context, databaseName, null,
                        DATABASE_VERSION);
                sqliteDb = instance.getWritableDatabase();

                System.out.println("instance of  " + databaseName + " created ");
            }
        }

        public static final EventOnTheDayDB getInstance(Context context,
                                                        String databaseName) {
            initialize(context, databaseName);
            return instance;
        }

        public SQLiteDatabase getDatabase() {
            return sqliteDb;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        private static void copyDataBase(Context aContext, String databaseName)
                throws IOException {

            InputStream myInput = aContext.getAssets().open(databaseName);

            String outFileName = getDatabasePath(aContext, databaseName);

            File f = new File("/data/data/" + aContext.getPackageName()
                    + "/databases/");
            if (!f.exists())
                f.mkdir();

            OutputStream myOutput = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();

            System.out.println(databaseName + " copied");
        }

        public static boolean checkDatabase(Context aContext, String databaseName) {
            SQLiteDatabase checkDB = null;

            try {
                String myPath = getDatabasePath(aContext, databaseName);

                checkDB = SQLiteDatabase.openDatabase(myPath, null,
                        SQLiteDatabase.OPEN_READONLY);

                checkDB.close();
            } catch (SQLiteException e) {

                System.out.println(databaseName + " does not exists");
            }

            return checkDB != null;
        }

        private static String getDatabasePath(Context aContext, String databaseName) {
            return "/data/data/" + aContext.getPackageName() + "/databases/"
                    + databaseName;
        }

        public static Cursor rawQuery(String query) {
            try {
                if (sqliteDb.isOpen()) {
                    sqliteDb.close();
                }
                sqliteDb = instance.getWritableDatabase();

                cursor = null;
                cursor = sqliteDb.rawQuery(query, null);
            } catch (Exception e) {
                System.out.println("DB ERROR  " + e.getMessage());
                e.printStackTrace();
            }
            return cursor;
        }

        public static void execute(String query) {
            try {
                if (sqliteDb.isOpen()) {
                    sqliteDb.close();
                }
                sqliteDb = instance.getWritableDatabase();
                sqliteDb.execSQL(query);
            } catch (Exception e) {
                System.out.println("DB ERROR  " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
