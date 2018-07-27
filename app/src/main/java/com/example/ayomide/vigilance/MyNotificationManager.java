package com.example.ayomide.vigilance;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class MyNotificationManager {

    private Context mCtx;
    private static MyNotificationManager mInstance;

    private MyNotificationManager(Context context) {
        mCtx = context;
    }

    //A method that will return to us the instance
    public static synchronized MyNotificationManager getInstance(Context context) {
        if(mInstance == null){
            mInstance = new MyNotificationManager(context);
        }
        return mInstance;
    }

    //display the notification
    public void displayNotification(String title, String body){

        //here you will build your notification. Create your builder object
        // Four parameter; Context context, String channelId
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mCtx, Constants.CHANNEL_ID)
        //Create a channel first
        // If you don't pass a small icon, you'll either get an error or your crashes.
        .setSmallIcon(R.drawable.app_icon)
        .setContentTitle(title)
        .setContentText(body);

        Intent intent = new Intent(mCtx, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Set pendingIntent to your notification builder
        mBuilder.setContentIntent(pendingIntent);

        //Create Notification Manager object
        NotificationManager mNotificationManager = (NotificationManager) (mCtx.getSystemService(Context.NOTIFICATION_SERVICE));

        if(mNotificationManager != null){
            mNotificationManager.notify(1, mBuilder.build());
        }
    }
}
