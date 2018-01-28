package com.example.rouzicpierre.jdr.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

/**
 * Created by rouzic pierre on 16/08/2017.
 */

public class RaceAPI {
    public static String TABLE_RACE="Race";
    public static String COLONNE_NOM = "Nom";
    public static String COLONNE_DESCRIPTION = "Description";
    public static String COLONNE_TALENT_RACIAL_CHOIX = "TalentRacialChoix";
    public static String COLONNE_CLASSE_POSSIBLE = "ClassePossible";
    public static String COLONNE_TAILLETYPEDEE = "TailleTypeDee";
    public static String COLONNE_TAILLENBDEE = "TailleNBDee";
    public static String COLONNE_PVTYPEDEE = "PVTypeDee";
    public static String COLONNE_PVNBDEE = "PVNBDee";
    public static String COLONNE_POIDNBDEE = "PoisNBDee";
    public static String COLONNE_POIDTYPEDEE = "PoidTypeDee";
    public static String COLONNE_AGETYPEDEE = "AgeTypeDee";
    public static String COLONNE_AGENBDEE = "AgeNBDee";


    public ArrayList<ParseObject> getRaces(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_RACE);
        ArrayList<ParseObject> listRaces = null;
        try {
            listRaces=new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listRaces;
    }

}
