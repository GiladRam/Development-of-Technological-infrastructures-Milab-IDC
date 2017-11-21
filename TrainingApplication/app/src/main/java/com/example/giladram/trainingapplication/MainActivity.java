package com.example.giladram.trainingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.giladram.trainingapplication.Fragments.MainActivityFragment;
import com.example.giladram.trainingapplication.Services.NotificationsPublisher;


public class MainActivity extends AppCompatActivity {

    public static long defaultAlarmInterval = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivityFragment mainFragment = new MainActivityFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mainFragment).commit();
        startAlarm();
    }


    public void startAlarm() {
        final Handler h = new Handler();

        h.postDelayed(new Runnable(){
            public void run(){
                Intent alarmIntent = new Intent(getApplicationContext(), NotificationsPublisher.class);
                sendBroadcast(alarmIntent);
                h.postDelayed(this, defaultAlarmInterval);
            }
        }, defaultAlarmInterval);
    }
}
