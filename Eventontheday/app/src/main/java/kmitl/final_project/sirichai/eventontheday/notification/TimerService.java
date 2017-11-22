package kmitl.final_project.sirichai.eventontheday.notification;

import android.app.IntentService;
//import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.controller.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.model.EventInfo;

/**
 * Created by atomiz on 12/11/2560.
 */

public class TimerService extends IntentService {
    private DatabaseAdapter databaseAdapter;
    private List<String> listAllDate;
    private List<List> listAllDates = new ArrayList<>();

    public TimerService() {
        super("TimerService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("timer", "timer is start!!!");
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    public void addDbForNotification() {
        listAllDates.clear();
        final List<EventInfo> datas = databaseAdapter.getDataEvent();
        for (int i = 0; i < datas.size(); i++) {
            listAllDate = new ArrayList<>();
            listAllDate.add(datas.get(i).getStart_date());//start date
            listAllDate.add(datas.get(i).getId());//id
            listAllDate.add(datas.get(i).getTitle());//title
            listAllDate.add(datas.get(i).getAlert_date());//alertdate
            listAllDate.add(datas.get(i).getAlert_time());//alerttime
            listAllDates.add(listAllDate);
        }
    }

    public void createNotification(String id, String title) {
        //Sets an ID for the notification, so it can be updated.
        int notifyID = 1;
        String CHANNEL_ID = "my_channel_01";// The id of the channel.
        CharSequence name = "name";// The user-visible name of the channel.
        int importance = NotificationManager.IMPORTANCE_HIGH;
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                    try {
//                        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
//                        Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID)
//                                .setContentTitle("New Message")
//                                .setContentText("You've received new messages.")
//                                .setSmallIcon(R.mipmap.ic_launcher_round)
//                                .setChannelId(CHANNEL_ID)
//                                .build();
//
//                        NotificationManager mNotificationManager =
//                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                        mNotificationManager.createNotificationChannel(mChannel);
//
//                        mNotificationManager.notify(notifyID, notification);
//                    } catch (Exception e) {
//                        Log.e("errmes", e.toString());
//                    }
//                } else {
//                    NotificationCompat.Builder nb = new NotificationCompat.Builder(getApplicationContext());
//                    nb.setDefaults(NotificationCompat.DEFAULT_ALL);
//                    nb.setContentText("You have event : " + title);
//                    nb.setContentTitle("Hi");
//                    nb.setSmallIcon(R.mipmap.ic_launcher);
//
//                    NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                    nm.notify(Integer.parseInt(id), nb.build());
//                }
        NotificationCompat.Builder nb = new NotificationCompat.Builder(getApplicationContext());
        nb.setDefaults(NotificationCompat.DEFAULT_ALL);
        nb.setContentText("You have event : " + title);
        nb.setContentTitle("Hi");
        nb.setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(Integer.parseInt(id), nb.build());
        return;
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        addDbForNotification();
        if (intent == null) {
            addDbForNotification();
            while (true) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                Log.i("timer", "(intent is null) = " + dateFormat.format(date));
                String currentYear = dateFormat.format(date).split(" ")[0].split("/")[0];
                String currentMonth = dateFormat.format(date).split(" ")[0].split("/")[1];
                String currentDay = dateFormat.format(date).split(" ")[0].split("/")[2];
                String currentHour = dateFormat.format(date).split(" ")[1].split(":")[0];
                String currentMin = dateFormat.format(date).split(" ")[1].split(":")[1];
                String currentSec = dateFormat.format(date).split(" ")[1].split(":")[2];
                for (int i = 0; i < listAllDates.size(); i++) {
                    String selectYear = listAllDates.get(i).get(3).toString().split("/")[2];
                    String selectMonth = listAllDates.get(i).get(3).toString().split("/")[1];
                    String selectDay = listAllDates.get(i).get(3).toString().split("/")[0];
                    String selectHour = listAllDates.get(i).get(4).toString().split(":")[0];
                    String selectMin = listAllDates.get(i).get(4).toString().split(":")[1];
                    Log.i("timer", "select = " + selectYear + " " + selectMonth + " " + selectDay + " " + selectHour + " " + selectMin);
                    if (currentYear.equals(selectYear) && currentMonth.equals(selectMonth) && currentDay.equals(selectDay) &&
                            currentHour.equals(selectHour) && currentMin.equals(selectMin) && currentSec.equals("00")) {
                        createNotification(listAllDates.get(i).get(1).toString(), listAllDates.get(i).get(2).toString());
                    }
                }
                try {
                    if (currentSec.equals("00")) {
                        Thread.sleep(60000);
                    } else {
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    Log.e("error", e.toString());
                }
            }
        }
        while (true) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            Log.i("timer", "(intent is not null) = " + dateFormat.format(date));
            String currentYear = dateFormat.format(date).split(" ")[0].split("/")[0];
            String currentMonth = dateFormat.format(date).split(" ")[0].split("/")[1];
            String currentDay = dateFormat.format(date).split(" ")[0].split("/")[2];
            String currentHour = dateFormat.format(date).split(" ")[1].split(":")[0];
            String currentMin = dateFormat.format(date).split(" ")[1].split(":")[1];
            String currentSec = dateFormat.format(date).split(" ")[1].split(":")[2];
            for (int i = 0; i < listAllDates.size(); i++) {
                String selectYear = listAllDates.get(i).get(3).toString().split("/")[2];
                String selectMonth = listAllDates.get(i).get(3).toString().split("/")[1];
                String selectDay = listAllDates.get(i).get(3).toString().split("/")[0];
                String selectHour = listAllDates.get(i).get(4).toString().split(":")[0];
                String selectMin = listAllDates.get(i).get(4).toString().split(":")[1];
                if (currentYear.equals(selectYear) && currentMonth.equals(selectMonth) && currentDay.equals(selectDay) &&
                        currentHour.equals(selectHour) && currentMin.equals(selectMin) && currentSec.equals("00")) {
                    createNotification(listAllDates.get(i).get(1).toString(), listAllDates.get(i).get(2).toString());
                }
            }
            try {
                if (currentSec.equals("00")) {
                    Thread.sleep(60000);
                } else {
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                Log.e("error", e.toString());
            }
        }
    }
}

