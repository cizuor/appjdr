package com.example.rouzicpierre.jdr.nouveauPerso;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rouzicpierre.jdr.Menu;
import com.example.rouzicpierre.jdr.Perso;
import com.example.rouzicpierre.jdr.R;
import com.orm.SugarRecord;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NouveauPerso extends Activity {

    @BindView(R.id.nom) EditText Nom;
    @BindView(R.id.race) EditText Race;
    @BindView(R.id.classe) EditText Classe;
    @BindView(R.id.CC) EditText CC;
    @BindView(R.id.CT) EditText CT;
    @BindView(R.id.Ag) EditText Ag;
    @BindView(R.id.F) EditText F;
    @BindView(R.id.E) EditText E;
    @BindView(R.id.FM) EditText FM;
    @BindView(R.id.At) EditText At;
    @BindView(R.id.Int) EditText Int;
    @BindView(R.id.Mag) EditText Mag;
    @BindView(R.id.P) EditText P;
    @BindView(R.id.Soc) EditText Soc;
    @BindView(R.id.PV) EditText PV;
    @BindView(R.id.poid) EditText Poid;
    @BindView(R.id.CA) EditText CA;
    @BindView(R.id.CD) EditText CD;
    @BindView(R.id.Degat) EditText Degat;
    @BindView(R.id.Armure) EditText Armure;
    @BindView(R.id.BE) EditText BE;
    @BindView(R.id.PS) EditText PS;
    @BindView(R.id.RS) EditText RS;
    @BindView(R.id.PO) EditText PO;
    @BindView(R.id.RM) EditText RM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_perso);
        ButterKnife.bind(this);

    }

    public void save (View view){
        Perso perso = Perso.getMonperso();
        perso.setNom(Nom.getText().toString());
        perso.setRace(Race.getText().toString());
        perso.setClasse(Classe.getText().toString());
        perso.setCC(Integer.parseInt(CC.getText().toString()));
        perso.setCT(Integer.parseInt(CT.getText().toString()));
        perso.setAg(Integer.parseInt(Ag.getText().toString()));
        perso.setF(Integer.parseInt(F.getText().toString()));
        perso.setE(Integer.parseInt(E.getText().toString()));
        perso.setFM(Integer.parseInt(FM.getText().toString()));
        perso.setAt(Integer.parseInt(At.getText().toString()));
        perso.setInt(Integer.parseInt(Int.getText().toString()));
        perso.setMag(Integer.parseInt(Mag.getText().toString()));
        perso.setP(Integer.parseInt(P.getText().toString()));
        perso.setSoc(Integer.parseInt(Soc.getText().toString()));
        perso.setPVmax(Integer.parseInt(PV.getText().toString()));
        perso.setPVactuel(Integer.parseInt(PV.getText().toString()));
        perso.setBonnuspoid(Integer.parseInt(Poid.getText().toString()));
        perso.setBonnusCA(Integer.parseInt(CA.getText().toString()));
        perso.setBonnusCD(Integer.parseInt(CD.getText().toString()));
        perso.setBonnusDegat(Integer.parseInt(Degat.getText().toString()));
        perso.setBonnusArmure(Integer.parseInt(Armure.getText().toString()));
        perso.setBonnusBE(Integer.parseInt(BE.getText().toString()));
        perso.setBonnusPuissSort(Integer.parseInt(PS.getText().toString()));
        perso.setBonnusReussitSort(Integer.parseInt(RS.getText().toString()));
        perso.setPointDeDestin(1);
        perso.setPO(Integer.parseInt(PO.getText().toString()));
        perso.setPA(0);
        perso.setXptotal(0);
        perso.setXpactuel(0);
        perso.setResmagique(Integer.parseInt(RM.getText().toString()));

        SugarRecord.save(perso);
        Intent appel = new Intent(NouveauPerso.this, Menu.class);
        startActivity(appel);

    }
}
