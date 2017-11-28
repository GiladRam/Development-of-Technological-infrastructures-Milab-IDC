package com.example.giladram.trainingapplication.Fragments;


import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.giladram.trainingapplication.R;
import com.example.giladram.trainingapplication.Utilities.NotificationsSettingsDialog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    final String googleSearchUrl = "http://www.google.com/search?q=";
    final String googleParameters = "";

    public MainActivityFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View mainView =  inflater.inflate(R.layout.fragment_main, container, false);
        Button clickToToast = mainView.findViewById(R.id.ChangeTextButton);
        Button clickToUserList = mainView.findViewById(R.id.users_page_button);
        Button clickToPicturesList = mainView.findViewById(R.id.pic_page_button);
        ImageButton notificationPrefernces = mainView.findViewById(R.id.notification_preference);
        final ImageButton googleSearch = mainView.findViewById(R.id.google_search);

        final FragmentManager manager = getActivity().getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        final TextView mainTextView = mainView.findViewById(R.id.editText);
        final LannistersListFragment usersListPage = new LannistersListFragment();
        final StarksListFragment starksListPage = new StarksListFragment();
        final GoogleResultFragment googleResultFragment = new GoogleResultFragment();
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

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
        googleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String encodedSearchReq = getEncodedGoogleSearchReq(mainTextView.getText().toString());
                Log.d("GoogleSearch", encodedSearchReq);
                StringRequest googleSearchReq = new StringRequest(
                        Request.Method.GET,
                        encodedSearchReq,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("Google search",response);
                                Document doc = Jsoup.parse(response);
                                Elements results = doc.select("div._Z1m");
                                Elements link;
                                for(Element result : results){
                                    link = result.select("a");
                                    Log.d("GoogleLink", link.text());
                                }
                                googleResultFragment.setSearchResults(results);
                                transaction.replace(R.id.fragment_container, googleResultFragment);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                });
                requestQueue.add(googleSearchReq);
            }
        });
        return mainView;
    }


    private String getEncodedGoogleSearchReq(String i_searchPlainText){
        String googleSearchReq = " ";
        try{
            googleSearchReq = URLEncoder.encode(i_searchPlainText, "UTF-8");
        }catch (UnsupportedEncodingException e){
            Log.e("MainActivityFragment", e.toString());
        }

        return googleSearchUrl + googleSearchReq + googleParameters;

    }
}
