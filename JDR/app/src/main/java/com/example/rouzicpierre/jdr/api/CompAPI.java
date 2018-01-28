package com.example.rouzicpierre.jdr.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseRelation;

import java.util.ArrayList;

/**
 * Created by rouzic pierre on 16/08/2017.
 */

public class CompAPI {
    public static String TABLE_COMP="Comp";
    public static String COLONNE_NOM = "Nom";
    public static String COLONNE_DESCRIPTION = "Description";
    public static String COLONE_ISTALENT = "isTalent";
    public static String COLONE_TYPE = "Type";
    public static String COLONE_BONNUS = "bonnus";



    public ArrayList<ParseObject> getTalentRacial (ParseObject race){
        ArrayList<ParseObject> talent = null;
        ParseRelation relation = race.getRelation(RaceAPI.COLONNE_TALENT_RACIAL_CHOIX);
        try {
            talent=new ArrayList<>(relation.getQuery().find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return talent;
    }


}
