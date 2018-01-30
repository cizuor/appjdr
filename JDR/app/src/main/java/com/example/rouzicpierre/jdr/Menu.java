package com.example.rouzicpierre.jdr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rouzicpierre.jdr.Combat.Combat;
import com.example.rouzicpierre.jdr.fichePerso.MonPersoActivity;
import com.example.rouzicpierre.jdr.nouveauPerso.race.NouveauPersoParse;
import com.orm.SugarRecord;
import com.parse.Parse;
import com.parse.ParseUser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu extends AppCompatActivity {

    @BindView(R.id.buttonmonperso) Button monperso;
    @BindView(R.id.nompersofetch)    EditText nom;
    @BindView(R.id.menuButtonCombat) Button combat;
    @BindView(R.id.menuEditEquipe) EditText equipe;
    public static int Equipe;
    Perso perso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Mm6O7tRLRJqh9xamUmmscI8bZRMZWS9L7EnBxSOB")
                .server("https://parseapi.back4app.com/")
                .clientKey("1WrFFbjqDq72WBGQP2spcyUreociFTn4KLJWU8Qn")
                .build()
        );
        //App app = new App();

        ParseUser.enableRevocableSessionInBackground();
        /*ParseObject gameScore = new ParseObject("GameScore");
        gameScore.put("score", 1337);
        gameScore.put("playerName", "Sean Plott");
        gameScore.put("cheatMode", false);
        gameScore.saveInBackground();*/
        /*ParseUser.logInInBackground("cizuor", "cizuor", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                Log.i("test","test user = "+user.getObjectId())   ;
            }
        });*/



        /*ParseObject race = new ParseObject("Race");
        race.put("Nom", "nain");
        race.saveInBackground();*/


        combat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCombat();
            }
        });


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

    public void goToMonPerso(View view){
        perso.actualiseMap();
        Intent appel = new Intent(Menu.this, MonPersoActivity.class);
        startActivity(appel);


    }

    public void goToNouveauPerso(View view){
        Intent appel = new Intent(Menu.this, NouveauPersoParse.class);
        startActivity(appel);
    }


    public void goToChargerPerso(View view){

        // recuperation du personnage en BDD local
        if (nom.getText().toString() == null || nom.getText().toString().equals("") ){
            Toast toast = Toast.makeText(this, " nom de perso invalide ",Toast.LENGTH_LONG);
            toast.show();
        }else {
            String monnom = nom.getText().toString();
            List<Perso> p = SugarRecord.find(Perso.class, "nom = ?", monnom);
            if (p.get(0) != null) {
                Perso.setMonperso(p.get(0));
                perso = Perso.getMonperso();
                monperso.setEnabled(true);
                monperso.setText(perso.getNom());
            }else{
                Toast toast = Toast.makeText(this, " nom de perso inexistant ",Toast.LENGTH_LONG);
                toast.show();
            }
        }
        /*SugarRecord.save(perso);

        List<PersoParse> p = SugarRecord.find(PersoParse.class,"nom = ?","test");
        //PersoParse p = SugarRecord.findById(PersoParse.class,1);
        Log.i("test","test = "+p.toString());*/
    }


    public void onClickCombat(){
        if (!equipe.getText().toString().equals("")) {
            Equipe=Integer.parseInt(equipe.getText().toString());
            Intent myIntent = new Intent(Menu.this, Combat.class);
            //myIntent.putExtra("key", value); //Optional parameters
            this.startActivity(myIntent);
        }else{
            Toast toast = Toast.makeText(this, "choisir equipe ", Toast.LENGTH_LONG);
            toast.show();
        }
    }


}
