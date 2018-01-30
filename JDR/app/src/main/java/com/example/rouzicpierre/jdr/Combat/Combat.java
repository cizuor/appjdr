package com.example.rouzicpierre.jdr.Combat;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.rouzicpierre.jdr.Bestiaire.Bestiaire;
import com.example.rouzicpierre.jdr.Bestiaire.BestiaireAdapter;
import com.example.rouzicpierre.jdr.Menu;
import com.example.rouzicpierre.jdr.R;
import com.example.rouzicpierre.jdr.api.MonstreCombatAPI;
import com.parse.ParseObject;

import java.util.ArrayList;

public class Combat extends AppCompatActivity {

    private Button mNewMonstre;
    private Button mNewJoueur;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MonstreCombatAPI monstreCombatAPI = new MonstreCombatAPI();
    private SwipeRefreshLayout mrefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);
        mNewMonstre = (Button) findViewById(R.id.combatButtonAddMonstre);
        mNewJoueur = (Button) findViewById(R.id.combatButtonAddJoueur);
        mrefreshLayout = (SwipeRefreshLayout) findViewById(R.id.combatSwiperefresh);

        mNewMonstre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNewMonstre();
            }
        });
        mNewJoueur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNewJoueur();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.combatRecyclerviewCombatant);
        mRecyclerView.setHasFixedSize(true);



        mrefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                affiche(monstreCombatAPI.getMonstres());
                mrefreshLayout.setRefreshing(false);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        affiche(monstreCombatAPI.getMonstres());
    }


    private void onClickNewMonstre(){
        Intent myIntent = new Intent(Combat.this, Bestiaire.class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }

    private void onClickNewJoueur(){

    }



    private void affiche (ArrayList<ParseObject> listCombatants){

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new MonstreCombatAdapter(listCombatants,this);
        mRecyclerView.setAdapter(mAdapter);
    }




}
