package com.example.rouzicpierre.jdr.Combat;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.rouzicpierre.jdr.Bestiaire.Bestiaire;
import com.example.rouzicpierre.jdr.Bestiaire.BestiaireAdapter;
import com.example.rouzicpierre.jdr.Menu;
import com.example.rouzicpierre.jdr.R;
import com.example.rouzicpierre.jdr.api.MonstreCombatAPI;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.ArrayList;

public class Combat extends AppCompatActivity {

    private Button mNewMonstre;
    private Button mNewJoueur;
    private Button mDebutCombat;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MonstreCombatAPI monstreCombatAPI = new MonstreCombatAPI();
    private SwipeRefreshLayout mrefreshLayout;
    public static int attaqueFaite;
    private ArrayList<ParseObject> listCombatants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);
        mNewMonstre = (Button) findViewById(R.id.combatButtonAddMonstre);
        mNewJoueur = (Button) findViewById(R.id.combatButtonAddJoueur);
        mDebutCombat = (Button) findViewById(R.id.combatButtonDebutCombat);

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
        mDebutCombat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDebutCombat();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.combatRecyclerviewCombatant);
        mRecyclerView.setHasFixedSize(true);
        attaqueFaite=0;


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
    private void onClickDebutCombat(){
        Boolean isStarted = false;
        if (listCombatants.size()!=0) {
            for (int i = 0; i < listCombatants.size(); i++) {
                if (listCombatants.get(i).getBoolean(MonstreCombatAPI.COLONNE_PLAYED)) {
                    isStarted = true;

                }
            }
            if (!isStarted) {
                ParseObject monstre = listCombatants.get(0);
                int init = monstre.getInt(MonstreCombatAPI.COLONNE_INITIATIVE);
                for (int i = 1; i < listCombatants.size(); i++) {
                    if (listCombatants.get(i).getInt(MonstreCombatAPI.COLONNE_INITIATIVE)>init) {
                        monstre=listCombatants.get(i);
                        init=monstre.getInt(MonstreCombatAPI.COLONNE_INITIATIVE);
                    }
                }
                monstre.put(MonstreCombatAPI.COLONNE_PLAYED,true);
                monstre.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        onResume();
                    }
                });

            }
        }
    }


    private void affiche (ArrayList<ParseObject> listCombatants){
        this.listCombatants=listCombatants;
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new MonstreCombatAdapter(listCombatants,this);
        mRecyclerView.setAdapter(mAdapter);
    }




}
