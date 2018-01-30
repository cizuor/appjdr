package com.example.rouzicpierre.jdr;

import android.util.Log;

import com.example.rouzicpierre.jdr.api.MonstreCombatAPI;
import com.parse.ParseObject;

import java.util.Random;

/**
 * Created by rouzic pierre on 11/03/2017.
 */

public class LanceurDeDee {
    private static LanceurDeDee lanceurDeDee = null;
    Random rand;
    private LanceurDeDee(){
        rand = new Random();
    }

    public static LanceurDeDee getLanceurDeDee() {
        if (lanceurDeDee==null){
            lanceurDeDee = new LanceurDeDee();
        }
        return lanceurDeDee;
    }

    public int random (int dees, int nb){
        int r = 0;
        for (int i = 0; i< nb ; i++){
            r=r+(rand.nextInt(dees))+1;
        }
        return r;
    }



    public int JetDegat (int dees, int nb){

        int degat = random(dees,nb);
        if (degat == dees ){
            return degat+3;
        }else{
            return degat;
        }
    }



    public int JetCA (){

        int D1 = random(10,1);
        int D2 = random(10,1);

        if (D1 == 10 && D2 == 10 ){
            return 200+D1+D2;
        }else if (D1 == 10 || D2 == 10 ){
            return 100+D1+D2;
        }else {
            return D1+D2;
        }
    }


    public void DégatSubit(ParseObject cible , String typeDegats, int NBDegats){
        switch (typeDegats){
            case "physique":
                if (cible.getInt(MonstreCombatAPI.COLONNE_ARMURE)>NBDegats){
                    cible.increment(MonstreCombatAPI.COLONNE_ARMURE,-NBDegats);
                    cible.saveInBackground();
                }else if (cible.getInt(MonstreCombatAPI.COLONNE_ARMURE)!=0){
                    cible.increment(MonstreCombatAPI.COLONNE_PV,-(NBDegats-cible.getInt(MonstreCombatAPI.COLONNE_ARMURE)));
                    cible.put(MonstreCombatAPI.COLONNE_ARMURE,0);
                    cible.saveInBackground();
                }else{
                    cible.increment(MonstreCombatAPI.COLONNE_PV,-NBDegats);
                    cible.saveInBackground();
                }
                break;
            case "magique":
                if (cible.getInt(MonstreCombatAPI.COLONNE_RM)>NBDegats){
                    cible.increment(MonstreCombatAPI.COLONNE_RM,-NBDegats);
                    cible.saveInBackground();
                }else if (cible.getInt(MonstreCombatAPI.COLONNE_RM)!=0){
                    cible.increment(MonstreCombatAPI.COLONNE_PV,-(NBDegats-cible.getInt(MonstreCombatAPI.COLONNE_RM)));
                    cible.put(MonstreCombatAPI.COLONNE_RM,0);
                    cible.saveInBackground();
                }else{
                    cible.increment(MonstreCombatAPI.COLONNE_PV,-NBDegats);
                    cible.saveInBackground();
                }
                break;
            case "brut":
                cible.increment(MonstreCombatAPI.COLONNE_PV,-NBDegats);
                cible.saveInBackground();
                break;
        }
    }



}
