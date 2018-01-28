package com.example.rouzicpierre.jdr.fichePerso;

import android.util.Log;

import com.example.rouzicpierre.jdr.Perso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rouzic pierre on 04/03/2017.
 */

public class MonPersoPresenteur {


    private static  MonPersoPresenteur monPersoPresenteur = null;
    MonPersoModel monPersoModel = null;
    MonPersoActivity monPersoActivity = null;
    private ArrayList<String> ListStat = new ArrayList<String>();
    private ArrayList<String> ValeurStat = new ArrayList<String>();
    private Perso perso;

    private MonPersoPresenteur(MonPersoActivity monPersoActivity) {
        // on relie le presenteur au model
        monPersoModel = MonPersoModel.getMonPersoModel();
        perso =Perso.getMonperso();
        if (true) {
            this.monPersoActivity = monPersoActivity;
            ListStat.add("CC");
            ValeurStat.add(String.valueOf(perso.getCC()));
            ListStat.add("CT");
            ValeurStat.add(String.valueOf(perso.getCT()));
            ListStat.add("Ag");
            ValeurStat.add(String.valueOf(perso.getAg()));
            ListStat.add("F");
            ValeurStat.add(String.valueOf(perso.getF()));
            ListStat.add("E");
            ValeurStat.add(String.valueOf(perso.getE()));
            ListStat.add("FM");
            ValeurStat.add(String.valueOf(perso.getFM()));
            ListStat.add("Int");
            ValeurStat.add(String.valueOf(perso.getInt()));
            ListStat.add("P");
            ValeurStat.add(String.valueOf(perso.getP()));
            ListStat.add("Soc");
            ValeurStat.add(String.valueOf(perso.getSoc()));

        }

    }


    // pour éviter quil y est plein d'instance de cete classe on la transforme en singletton
    public static MonPersoPresenteur getMonPersoPresenteur (MonPersoActivity monPersoActivity){
        //Log.i("test","get presenteur ");
        if (monPersoPresenteur == null){
            //Log.i("test","get presenteur if ");
            monPersoPresenteur = new MonPersoPresenteur(monPersoActivity);
        }
        return monPersoPresenteur;
    }





    public ArrayList<String> getListStat() {
        return ListStat;
    }


    public ArrayList<String> getValeurStat() {
        return ValeurStat;
    }
}
