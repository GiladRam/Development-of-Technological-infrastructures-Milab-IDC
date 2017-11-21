package com.example.giladram.trainingapplication.Utilities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.giladram.trainingapplication.MainActivity;
import com.example.giladram.trainingapplication.R;

/**
 * Created by giladram on 21/11/17.
 */

public class NotificationsSettingsDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogInflator = inflater.inflate(R.layout.notifications_dialog, null);
        final TextView timeIntervalTextView = (EditText) dialogInflator.findViewById(R.id.intervalTime);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialogInflator)
                // Add action buttons
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        long timeInterval = 60000;
                        try{
                            timeInterval = Long.valueOf(timeIntervalTextView.getText().toString());
                        }catch (Exception e){

                        }
                        MainActivity.defaultAlarmInterval = timeInterval;
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NotificationsSettingsDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
