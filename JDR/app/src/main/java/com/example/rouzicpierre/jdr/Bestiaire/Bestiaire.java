package com.example.rouzicpierre.jdr.Bestiaire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import com.example.rouzicpierre.jdr.R;
import com.example.rouzicpierre.jdr.api.BestiaireAPI;
import com.parse.ParseObject;

import java.util.ArrayList;

public class Bestiaire extends AppCompatActivity {

    private android.widget.SearchView mSearchViewMonstre;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private BestiaireAPI bestiaireAPI = new BestiaireAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestiaire);

        mSearchViewMonstre = (SearchView) findViewById(R.id.bestiaireSearchMonstre);
        mRecyclerView = (RecyclerView) findViewById(R.id.bestiaireRecyclerMonstre);
        mRecyclerView.setHasFixedSize(true);


        mSearchViewMonstre.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                affiche(bestiaireAPI.getMonstresByName(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        affiche(bestiaireAPI.getMonstres());
    }

    private void affiche (ArrayList<ParseObject> listMonstre){
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new BestiaireAdapter(listMonstre,this);
        mRecyclerView.setAdapter(mAdapter);
    }




}
