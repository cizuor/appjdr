package com.example.rouzicpierre.jdr.nouveauPerso.race;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rouzicpierre.jdr.R;
import com.example.rouzicpierre.jdr.api.RaceAPI;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NouveauPersoParse extends AppCompatActivity {

    @BindView(R.id.rv_race)
    RecyclerView rvRace;
    private RaceAdapteur adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_perso_parse);
        ButterKnife.bind(this);

        RaceAPI raceAPI = new RaceAPI();
        rvRace.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RaceAdapteur(raceAPI.getRaces(),this);
        rvRace.setAdapter(adapter);

    }
}
