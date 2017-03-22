package com.example.rouzicpierre.jdr.fichePerso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rouzicpierre.jdr.Perso;
import com.example.rouzicpierre.jdr.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MonPersoActivity extends AppCompatActivity implements StatAdapter.customButtonListener {

    @BindView(R.id.vuenom) TextView nom;
    @BindView(R.id.editPO) EditText PO;
    @BindView(R.id.editPA) EditText PA;
    @BindView(R.id.viewpv) TextView PVMax;
    @BindView(R.id.editpv) EditText PVAct;
    @BindView(R.id.vuerace) TextView Race;
    @BindView(R.id.vueclasse) TextView Classe;
    MonPersoPresenteur monPersoPresenteur=null;

    private ListView listView;
    StatAdapter adapter;
    private Perso perso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.i("test","oncreateactivity");
        setContentView(R.layout.activity_mon_perso);
        //Log.i("test","oncreateactivity2");
        ButterKnife.bind(this);
        perso = Perso.getMonperso();
        monPersoPresenteur= MonPersoPresenteur.getMonPersoPresenteur(this);

        nom.setText(perso.getNom());
        PO.setText(String.valueOf(perso.getPO()));
        PA.setText(String.valueOf(perso.getPA()));
        PVMax.setText("PV Max = "+String.valueOf(perso.getPVmax()));
        PVAct.setText(String.valueOf(perso.getPVactuel()));
        Race.setText(perso.getRace());
        Classe.setText(perso.getRace());


        PO.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (PO.getText().toString()== null || PO.getText().toString().equals("")) {
                    perso.setPO(0);
                }else{
                    perso.setPO(Integer.parseInt(PO.getText().toString()));
                }
            }
        });
        PA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (PA.getText().toString()== null || PA.getText().toString().equals("")) {

                    perso.setPA(0);
                }else{
                    perso.setPA(Integer.parseInt(PA.getText().toString()));
                }
            }


        });

        PVAct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("test","test" + PVAct.getText().toString());
                if (PVAct.getText().toString()== null || PVAct.getText().toString().equals("")) {
                    Log.i("test","if");
                    perso.setPVactuel(0);
                }else{
                    Log.i("test","else");
                    perso.setPVactuel(Integer.parseInt(PVAct.getText().toString()));
                }
            }
        });

        affiche();

    }


    public void affiche(){
        listView = (ListView) findViewById(R.id.listView);
        adapter = new StatAdapter(MonPersoActivity.this,monPersoPresenteur.getListStat(),monPersoPresenteur.getValeurStat());
        adapter.setCustomButtonListner(MonPersoActivity.this);
        listView.setAdapter(adapter);


    }
    @Override
    public void onButtonClickListner(int position, String value) {

    }
}
