package com.example.rouzicpierre.jdr.nouveauPerso.dee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.rouzicpierre.jdr.LanceurDeDee;
import com.example.rouzicpierre.jdr.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeePerso extends AppCompatActivity {

    @BindView(R.id.rv_dee)
    RecyclerView rvDee;
    @BindView(R.id.tvrelance)
    TextView tvrelance;

    private DeeAdapteur adapter;
    int relance ;
    LanceurDeDee lanceurDeDee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dee_perso);
        ButterKnife.bind(this);
        lanceurDeDee = LanceurDeDee.getLanceurDeDee();
        rvDee.setLayoutManager(new LinearLayoutManager(this));
        relance = lanceurDeDee.random(3,1);
        adapter = new DeeAdapteur(relance);
        tvrelance.setText(Integer.toString(relance));
        rvDee.setAdapter(adapter);

    }
}
