package com.example.rouzicpierre.jdr.nouveauPerso.classe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rouzicpierre.jdr.R;
import com.example.rouzicpierre.jdr.api.ClasseAPI;
import com.example.rouzicpierre.jdr.api.CompAPI;
import com.example.rouzicpierre.jdr.modele.PersoParse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassChoix extends AppCompatActivity {

    @BindView(R.id.rv_class) RecyclerView rvClasse;
    private ClassAdapteur adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_choix);

        ButterKnife.bind(this);

        ClasseAPI classeAPI = new ClasseAPI();
        rvClasse.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ClassAdapteur(classeAPI.getClasse(PersoParse.Race),this);
        rvClasse.setAdapter(adapter);
    }
}
