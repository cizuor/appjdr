package com.example.rouzicpierre.jdr.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by pierre on 30/01/2018.
 */

public class ArmureAPI {
    public static String TABLE_ARMURE="Armure";
    public static String COLONNE_NOM = "Nom";
    public static String COLONNE_BASE = "Base";
    public static String COLONNE_MARQUE = "Marque";




    public ParseObject getArmureByID(String id){
        ParseObject armure = new ParseObject(TABLE_ARMURE);
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_ARMURE);
        query.include(COLONNE_BASE);
        query.include(COLONNE_MARQUE);
        try {
            armure = query.get(id);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return armure;
    }

}
