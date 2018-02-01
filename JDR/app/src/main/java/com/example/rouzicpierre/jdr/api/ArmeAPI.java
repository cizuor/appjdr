package com.example.rouzicpierre.jdr.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by pierre on 31/01/2018.
 */

public class ArmeAPI {
    public static String TABLE_ARME="Arme";
    public static String COLONNE_NOM = "Nom";
    public static String COLONNE_BASE = "Base";
    public static String COLONNE_MARQUE = "Marque";
    public static String COLONNE_METAL = "Metal";




    public ParseObject getArmeByID(String id){
        ParseObject armure = new ParseObject(TABLE_ARME);
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_ARME);
        query.include(COLONNE_BASE);
        query.include(COLONNE_MARQUE);
        query.include(COLONNE_METAL);
        try {
            armure = query.get(id);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return armure;
    }

}
