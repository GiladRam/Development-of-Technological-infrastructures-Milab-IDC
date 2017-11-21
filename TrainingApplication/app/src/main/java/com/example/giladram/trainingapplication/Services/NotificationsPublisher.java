package com.example.giladram.trainingapplication.Services;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;


import com.example.giladram.trainingapplication.R;
import com.example.giladram.trainingapplication.Utilities.NotificationFactory;

/**
 * Created by giladram on 19/11/17.
 */

public class NotificationsPublisher extends BroadcastReceiver{

    private static NotificationFactory factory = NotificationFactory.getNotificationFactory();
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(factory.getPm_numberOfNutifications(), factory.getNewNotification(
                "MyAPP",
                "Notification Test",
                context));
    }
}
