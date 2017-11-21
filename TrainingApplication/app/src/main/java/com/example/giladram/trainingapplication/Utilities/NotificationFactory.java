package com.example.giladram.trainingapplication.Utilities;

import android.app.Notification;
import android.content.Context;

import com.example.giladram.trainingapplication.R;

/*
 * Created by giladram on 21/11/17.
 */

public class NotificationFactory {

    private static final NotificationFactory notificationFactory = new NotificationFactory();
    private int pm_numberOfNutifications = 0;
    public NotificationFactory(){}

    public static NotificationFactory getNotificationFactory(){
        return notificationFactory;
    }

    public Notification getNewNotification(
            String i_notificationTitle,
            String i_notificatioContent,
            Context i_context){
        Notification.Builder builder = new Notification.Builder(i_context);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setContentTitle(i_notificationTitle);
        builder.setContentText(i_notificatioContent + " " + pm_numberOfNutifications);
        pm_numberOfNutifications++;
        return builder.build();
    }

    public int getPm_numberOfNutifications() {
        return pm_numberOfNutifications;
    }
}
