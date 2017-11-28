package com.example.giladram.trainingapplication.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.giladram.trainingapplication.R;
import com.example.giladram.trainingapplication.Utilities.GoogleResultListAdapter;

import org.jsoup.select.Elements;

/**
 * Created by giladram on 28/11/17.
 */

public class GoogleResultFragment extends Fragment {

    private Elements searchResults = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.google_result_list, container, false);
        RecyclerView googleResultRecyclerView = (RecyclerView) mainView.findViewById(R.id.google_result_recycler_view);
        googleResultRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this.getActivity());
        googleResultRecyclerView.setLayoutManager(linearLayoutManager);
        if (searchResults != null){
            GoogleResultListAdapter googleResultListAdapter= new GoogleResultListAdapter(searchResults);
            googleResultRecyclerView.setAdapter(googleResultListAdapter);
        }
        return mainView;
    }

    public void setSearchResults(Elements searchResults) {
        this.searchResults = searchResults;
    }
}
