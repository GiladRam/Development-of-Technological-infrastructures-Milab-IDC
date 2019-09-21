package com.example.giladram.timeapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView timeView = (TextView) findViewById(R.id.TimeTextView);
        Button getTimeButton = (Button) findViewById(R.id.GetTimeButton);
        getTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeFetcher fetcher = new TimeFetcher(view.getContext());

                fetcher.dispatchRequest(new TimeFetcher.TimeResponseListener() {
                    @Override
                    public void onResponse(TimeFetcher.TimeResponse response) {
                        timeView.setText(response.CurrentTime);
                    }
                });
            }
        });
    }
}
