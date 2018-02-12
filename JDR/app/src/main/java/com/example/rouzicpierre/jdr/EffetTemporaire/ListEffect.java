package com.example.rouzicpierre.jdr.EffetTemporaire;

import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by pierre on 08/02/2018.
 */

public class ListEffect {
    private ArrayList<Effect> effects;
    private static ListEffect listEffect = null;

    private ListEffect() {
        this.effects = new ArrayList<Effect>();
    }

    public static ListEffect getListEffect() {
        if (listEffect==null){
            listEffect=new ListEffect();
        }
        return listEffect;
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<Effect> effects) {
        this.effects = effects;
    }

    public void addEffects(Effect effect){
        this.effects.add(effect);
    }

    public void finDeTour(ParseObject perso){
        for (int i = 0; i < effects.size() ; i++) {
            effects.get(i).finTour(perso);
        }
    }

}
