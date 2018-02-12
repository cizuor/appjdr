package com.example.rouzicpierre.jdr.EffetTemporaire;

import com.example.rouzicpierre.jdr.api.MonstreCombatAPI;
import com.parse.ParseObject;

/**
 * Created by pierre on 08/02/2018.
 */

public class CD extends Effect {

    public CD(ParseObject cible,ParseObject lanceur, int quantité, int temps) {
        super(cible,lanceur, quantité, temps);
        cible.increment(MonstreCombatAPI.COLONNE_CD,super.getQuantité());
        cible.saveInBackground();
    }

    @Override
    public void finEffect() {
        ParseObject cible =super.getCible();
        cible.increment(MonstreCombatAPI.COLONNE_CD, - (super.getQuantité()));
        cible.saveInBackground();
    }
}
