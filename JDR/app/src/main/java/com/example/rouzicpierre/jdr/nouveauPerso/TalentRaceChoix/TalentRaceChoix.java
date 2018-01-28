package com.example.rouzicpierre.jdr.nouveauPerso.TalentRaceChoix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rouzicpierre.jdr.R;
import com.example.rouzicpierre.jdr.api.CompAPI;
import com.example.rouzicpierre.jdr.modele.PersoParse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TalentRaceChoix extends AppCompatActivity {


    @BindView(R.id.rv_talent_choix_race)
    RecyclerView rvTalentChoix;
    private TalentRaciauxAdapteur adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_race_choix);

        ButterKnife.bind(this);

        CompAPI compAPI = new CompAPI();
        rvTalentChoix.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TalentRaciauxAdapteur(compAPI.getTalentRacial(PersoParse.Race),this);
        rvTalentChoix.setAdapter(adapter);
    }





}
