package com.example.rouzicpierre.jdr.fichePerso;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rouzicpierre.jdr.LanceurDeDee;
import com.example.rouzicpierre.jdr.Perso;
import com.example.rouzicpierre.jdr.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MonPersoActivity extends Activity implements StatAdapter.customButtonListener {

    @BindView(R.id.vuenom) TextView nom;
    @BindView(R.id.editPO) EditText PO;
    @BindView(R.id.editPA) EditText PA;
    @BindView(R.id.viewpv) TextView PVMax;
    @BindView(R.id.editpv) EditText PVAct;
    @BindView(R.id.vuerace) TextView Race;
    @BindView(R.id.vueclasse) TextView Classe;
    @BindView(R.id.editPD) EditText PD;
    @BindView(R.id.XPview) TextView XP;
    @BindView(R.id.XPadd) EditText XPadd;
    @BindView(R.id.BEedit) EditText BE;
    @BindView(R.id.CDedit) EditText CD;
    @BindView(R.id.Armureedit) EditText Armure;
    @BindView(R.id.Degatedit) EditText Degat;
    @BindView(R.id.NBDeeedit) EditText nbDee;
    @BindView(R.id.TDeeedit) EditText tDee;
    @BindView(R.id.CAedit) EditText CA;
    @BindView(R.id.resultatjetCA) TextView ResultCA;
    @BindView(R.id.Degatarmeedit) EditText Degatarme;
    @BindView(R.id.resultatjetdegat) TextView ResultDegats;


    MonPersoPresenteur monPersoPresenteur=null;

    private ListView listView;
    StatAdapter adapter;
    private Perso perso;

    private LanceurDeDee roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_perso);
        ButterKnife.bind(this);
        perso = Perso.getMonperso();
        roll = LanceurDeDee.getLanceurDeDee();

        monPersoPresenteur= MonPersoPresenteur.getMonPersoPresenteur(this);

        nom.setText(perso.getNom());
        PO.setText(String.valueOf(perso.getPO()));
        PA.setText(String.valueOf(perso.getPA()));
        PVMax.setText("PV Max = "+String.valueOf(perso.getPVmax()));
        PVAct.setText(String.valueOf(perso.getPVactuel()));
        Race.setText(perso.getRace());
        Classe.setText(perso.getRace());
        XP.setText(""+perso.getXpactuel());
        nbDee.setText("1");
        tDee.setText("10");
        Degat.setText(""+perso.getBonnusDegat());
        Degatarme.setText(""+perso.getArme());


        actualise();


        Degatarme.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Degatarme.getText().toString()== null || Degatarme.getText().toString().equals("")) {
                    perso.setArme(0);
                }else{
                    perso.setArme(Integer.parseInt(Degatarme.getText().toString()));
                }
            }
        });


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





    public void addxp (View view){

        if (XPadd.getText().toString() == null || XPadd.getText().toString().isEmpty()){
            XPadd.setText("0");
        }else{
            perso.setXpactuel(Integer.parseInt(XPadd.getText().toString())+perso.getXpactuel());
            perso.setXptotal(Integer.parseInt(XPadd.getText().toString())+perso.getXptotal());
            XP.setText(String.valueOf(perso.getXpactuel()));
            XPadd.setText("0");
        }

    }



    public void refresh (View view){
        actualise();
    }

    public void actualise (){
        int tmp;
        Log.i("test","map = "+perso.mapperso.toString());
        tmp = perso.getBonnusCD();
        tmp = tmp + (((Integer)perso.mapperso.get("Ag"))/20);
        tmp = tmp + (((Integer)perso.mapperso.get("CC"))/20);
        tmp = tmp+5;
        CD.setText(""+tmp);

        tmp = perso.getBonnusBE();
        tmp = tmp + (((Integer)perso.mapperso.get("E"))/10);
        BE.setText(""+tmp);

        tmp = perso.getBonnusCA();
        tmp = tmp + (((Integer)perso.mapperso.get("CC"))/20);
        CA.setText(""+tmp);



    }


    public void jetdegat (View view){
        int taille = Integer.parseInt(tDee.getText().toString());
        int nb = Integer.parseInt(nbDee.getText().toString());
        int d = roll.JetDegat(taille , nb);
        d = d + Integer.parseInt(Degatarme.getText().toString());
        d=d+Integer.parseInt(Degat.getText().toString());
        ResultDegats.setText(""+d);
    }


    public void jetCA (View view){
        int jet = roll.JetCA();
        Log.i("test","jet = "+jet);
        if (jet > 200){
            ResultCA.setText("crit +5D");
        }else if (jet > 100){
            jet = jet -100;
            jet = jet + Integer.parseInt(CA.getText().toString());
            ResultCA.setText(""+jet+" +1D");
        }else{
            jet = jet + Integer.parseInt(CA.getText().toString());
            ResultCA.setText(""+jet);
        }

    }

}
