package com.example.rouzicpierre.jdr.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseRelation;

import java.util.ArrayList;

/**
 * Created by rouzic pierre on 18/08/2017.
 */

public class ClasseAPI {
    public static String TABLE_CLASSE="Class";
    public static String COLONNE_NOM = "Nom";
    public static String COLONNE_DESCRIPTION = "Description";


    public ArrayList<ParseObject> getClasse (ParseObject race){
        ArrayList<ParseObject> classes = null;
        ParseRelation relation = race.getRelation(RaceAPI.COLONNE_CLASSE_POSSIBLE);
        try {
            classes=new ArrayList<>(relation.getQuery().find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return classes;
    }


}
