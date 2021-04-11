package com.example.gymbro;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationManagerCompat;

public class MyNewIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 3;

    public MyNewIntentService() {
        super("MyNewIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("Chris", "Making notification");
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Gym Bro");
        builder.setContentText("Workout starting soon");
        builder.setSmallIcon(R.drawable.ic_stat_name);

        Intent notifyIntent = new Intent(this, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent);
        String channelId = "Your_channel_id";
        NotificationChannel channel = new NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_HIGH);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.createNotificationChannel(channel);
        builder.setChannelId(channelId);

        Notification notificationCompat = builder.build();
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);
        Log.e("Chris", "Created notification");
    }
}
