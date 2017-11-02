package com.example.giladram.trainingapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mainView =  inflater.inflate(R.layout.fragment_main, container, false);
        Button clickToToast = mainView.findViewById(R.id.ChangeTextButton);
        final TextView mainTextView = mainView.findViewById(R.id.editText);
        clickToToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), mainTextView.getText(), Toast.LENGTH_LONG);
                toast.show();
            }        });
        return mainView;
    }
}
