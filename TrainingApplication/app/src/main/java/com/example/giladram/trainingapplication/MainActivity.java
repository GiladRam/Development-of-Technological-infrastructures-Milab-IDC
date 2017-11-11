package com.example.giladram.trainingapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityFragment mainFragment = new MainActivityFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mainFragment).commit();

    }
}
