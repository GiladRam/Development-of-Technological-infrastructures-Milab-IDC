package com.example.giladram.trainingapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LannistersListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private  ShowCharacter[] myDataset;

    public LannistersListFragment(){
        myDataset = new ShowCharacter[3];
        myDataset[0] = new ShowCharacter("Peter Dinklage", R.mipmap.lannister_peter_dinklage);
        myDataset[1] = new ShowCharacter("Cersei Lannister", R.mipmap.cersei_lannister);
        myDataset[2] = new ShowCharacter("Jaime Lannister", R.mipmap.jaime_lannister);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mainView =  inflater.inflate(R.layout.lannister_list, container, false);
        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.lannister_list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CharacterListAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
        return mainView;
    }
}
