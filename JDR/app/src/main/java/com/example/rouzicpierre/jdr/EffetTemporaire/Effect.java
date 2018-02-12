package com.example.rouzicpierre.jdr.EffetTemporaire;

import com.parse.ParseObject;

/**
 * Created by pierre on 08/02/2018.
 */

public abstract class Effect {
    private ParseObject lanceur;
    private ParseObject cible;
    private int quantité;
    private int temps;

    public Effect(ParseObject cible,ParseObject lanceur, int quantité, int temps) {
        this.lanceur = lanceur;
        this.cible = cible;
        this.quantité = quantité;
        this.temps = temps+1;
    }

    public ParseObject getCible() {
        return cible;
    }

    public ParseObject getLanceur(){return lanceur; }

    public int getQuantité() {
        return quantité;
    }

    public int getTemps() {
        return temps;
    }


    public void finTour (ParseObject perso){
        if (perso.getObjectId().equals(lanceur.getObjectId())) {
            this.temps = this.temps - 1;
            if (temps == 0) {
                finEffect();
            }
        }
    }

    public abstract void finEffect();

}
