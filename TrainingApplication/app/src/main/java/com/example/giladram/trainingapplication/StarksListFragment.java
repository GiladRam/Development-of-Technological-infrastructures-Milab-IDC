package com.example.giladram.trainingapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class StarksListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private  ShowCharacter[] myDataset;

    public StarksListFragment(){
        myDataset = new ShowCharacter[3];
        myDataset[0] = new ShowCharacter("Eddard Stark", R.mipmap.ned_stark);
        myDataset[1] = new ShowCharacter("Sansa Stark", R.mipmap.sansa_stark);
        myDataset[2] = new ShowCharacter("Robb Stark", R.mipmap.robb_stark);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mainView =  inflater.inflate(R.layout.stark_list, container, false);
        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.stark_list);

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
