package com.example.rouzicpierre.jdr.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

/**
 * Created by pierre on 28/01/2018.
 */

public class TypeAttaqueAPI {
    public static String TABLE_TYPEATTAQUE="TypeAttaque";
    public static String COLONNE_NOM = "Nom";
    public static String COLONNE_DEGATS = "Degats";
    public static String COLONNE_CA = "CA";
    public static String COLONNE_CD = "CD";
    public static String COLONNE_ORDRE = "Ordre";

    public ArrayList<ParseObject> getTypeAttaques(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_TYPEATTAQUE);
        ArrayList<ParseObject> listTypeAttaques  = null;
        query.orderByAscending(COLONNE_ORDRE);
        try {
            listTypeAttaques =new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listTypeAttaques ;
    }

}
