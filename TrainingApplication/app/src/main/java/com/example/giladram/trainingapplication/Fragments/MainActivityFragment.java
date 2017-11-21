package com.example.giladram.trainingapplication.Fragments;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giladram.trainingapplication.R;
import com.example.giladram.trainingapplication.Utilities.NotificationsSettingsDialog;

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
        Button clickToUserList = mainView.findViewById(R.id.users_page_button);
        Button clickToPicturesList = mainView.findViewById(R.id.pic_page_button);
        ImageButton notificationPrefernces = mainView.findViewById(R.id.notification_preference);

        final FragmentManager manager = getActivity().getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        final TextView mainTextView = mainView.findViewById(R.id.editText);
        final LannistersListFragment usersListPage = new LannistersListFragment();
        final StarksListFragment starksListPage = new StarksListFragment();

        clickToToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), mainTextView.getText(), Toast.LENGTH_LONG);
                toast.show();
            }        });

        clickToUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.fragment_container, usersListPage);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        clickToPicturesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.fragment_container, starksListPage);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        notificationPrefernces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragmentDialog = new NotificationsSettingsDialog();
                newFragmentDialog.show(getFragmentManager(), "Interval Settings");
}
        });
        return mainView;
    }
}
