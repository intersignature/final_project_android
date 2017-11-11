package kmitl.final_project.sirichai.eventontheday.notification;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.DatabaseAdapter;

/**
 * Created by atomiz on 12/11/2560.
 */

public class TimerService extends IntentService{
    public TimerService() {
        super("TimerService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("timer", "timer is start!!!");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent == null){
            int time = 5;
            for (int i = 0; i<time; i++){
                Log.i("timer", "i (intent is null) = "+ i);
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    Log.e("error", e.toString());
                }
            }

            // Sets an ID for the notification, so it can be updated.
            int notifyID = 1;
            String CHANNEL_ID = "my_channel_01";// The id of the channel.
            CharSequence name = "name";// The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                try {
                    NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                    Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                            .setContentTitle("New Message")
                            .setContentText("You've received new messages.")
                            .setSmallIcon(R.mipmap.ic_launcher_round)
                            .setChannelId(CHANNEL_ID)
                            .build();

                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.createNotificationChannel(mChannel);

                    mNotificationManager.notify(notifyID , notification);
                }catch (Exception e) {
                    Log.e("errmes", e.toString());
                }
            }
            else {
                NotificationCompat.Builder nb = new NotificationCompat.Builder(getApplicationContext());
                nb.setDefaults(NotificationCompat.DEFAULT_ALL);
                nb.setContentText("Timer done");
                nb.setContentTitle("Hi");
                nb.setSmallIcon(R.mipmap.ic_launcher);

                NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                nm.notify(100254, nb.build());
            }






            return;
        }

        ResultReceiver receiver = intent.getParcelableExtra("receiver");
        int time = intent.getIntExtra("time", 0);
        for (int i=0; i<time; i++){
            Log.i("timer","i (intent is not null) = " + i);
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                Log.e("error", e.toString());
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString("message", "Counting done...");
        receiver.send(1234, bundle);
    }
}
