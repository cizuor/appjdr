package com.example.rouzicpierre.jdr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rouzicpierre.jdr.fichePerso.MonPersoActivity;
import com.example.rouzicpierre.jdr.nouveauPerso.NouveauPerso;
import com.orm.SugarRecord;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu extends AppCompatActivity {

    @BindView(R.id.buttonmonperso) Button monperso;
    @BindView(R.id.nompersofetch)
    EditText nom;
    Perso perso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        perso = Perso.getMonperso();
        if (perso.getNom()!= null && perso.getNom()!= ""){
            monperso.setEnabled(true);
            monperso.setText(""+perso.getNom());
        }else{
            monperso.setEnabled(false);
        }
    }

    public void gotomonperso (View view){
        Intent appel = new Intent(Menu.this, MonPersoActivity.class);
        startActivity(appel);


    }

    public void gotonouveauperso(View view){
        Intent appel = new Intent(Menu.this, NouveauPerso.class);
        startActivity(appel);

    }


    public void gotochargerperso (View view){


        String monnom = nom.getText().toString();
        List<Perso> p = SugarRecord.find(Perso.class,"nom = ?",monnom);
        if (p.get(0)!= null ){
            Perso.setMonperso(p.get(0));
        }

        /*SugarRecord.save(perso);

        List<Perso> p = SugarRecord.find(Perso.class,"nom = ?","test");
        //Perso p = SugarRecord.findById(Perso.class,1);
        Log.i("test","test = "+p.toString());*/

    }



}
