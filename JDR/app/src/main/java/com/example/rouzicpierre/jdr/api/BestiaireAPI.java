package com.example.rouzicpierre.jdr.api;

import android.util.Log;

import com.example.rouzicpierre.jdr.Menu;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

/**
 * Created by pierre on 28/01/2018.
 */

public class BestiaireAPI {
    public static String TABLE_BESTIAIRE="Bestiaire";
    public static String COLONNE_NOM = "Nom";
    public static String COLONNE_CA = "CA";
    public static String COLONNE_CD = "CD";
    public static String COLONNE_ARMURE = "Armure";
    public static String COLONNE_RM = "RM";
    public static String COLONNE_MOUVEMENT = "Mouvement";
    public static String COLONNE_CC = "CC";
    public static String COLONNE_CT = "CT";
    public static String COLONNE_AG = "Ag";
    public static String COLONNE_F = "F";
    public static String COLONNE_E = "E";
    public static String COLONNE_FM = "FM";
    public static String COLONNE_INT = "Int";
    public static String COLONNE_P = "P";
    public static String COLONNE_SOC = "Soc";
    public static String COLONNE_PV = "PV";
    public static String COLONNE_AT = "At";
    public static String COLONNE_MAG = "Mag";
    public static String COLONNE_MAINDROITE = "MainDroite";
    public static String COLONNE_MAINGAUCHE = "MainGauche";
    public static String COLONNE_PROTECTION = "Protection";
    public static String COLONNE_XP = "XP";
    public static String COLONNE_COMP = "Comp";
    public static String COLONNE_BONUSDEGAT = "BonusDegat";

    private ArmureAPI armureAPI = new ArmureAPI();



    public ArrayList<ParseObject> getMonstres(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_BESTIAIRE);
        ArrayList<ParseObject> listMonstres  = null;
        try {
            listMonstres =new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listMonstres ;
    }

    public ArrayList<ParseObject> getMonstresByName(String nom){
        ParseQuery<ParseObject > query = ParseQuery.getQuery(TABLE_BESTIAIRE);
        query.whereMatches(COLONNE_NOM,nom,"i");// i pour ne pas étre sensible au majuscule
        ArrayList<ParseObject> listMonstres = new ArrayList<ParseObject>();
        try {
            listMonstres = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listMonstres;
    }


    public void transfertToCombat(ParseObject bestiaire,int nombre){


        for (int i = 0; i <nombre ; i++) {
            ParseObject combat = new ParseObject(MonstreCombatAPI.TABLE_MONSTRECOMBAT);
            combat.put(MonstreCombatAPI.COLONNE_NOM, bestiaire.getString(COLONNE_NOM)+i);
            combat.put(MonstreCombatAPI.COLONNE_CA, bestiaire.getInt(COLONNE_CA));
            combat.put(MonstreCombatAPI.COLONNE_CD, bestiaire.getInt(COLONNE_CD));

            ParseObject protection = armureAPI.getArmureByID(bestiaire.getParseObject(COLONNE_PROTECTION).getObjectId());
            ParseObject armureBase = protection.getParseObject(ArmureAPI.COLONNE_BASE);
            ParseObject marqueArmure = protection.getParseObject(ArmureAPI.COLONNE_MARQUE);

            combat.put(MonstreCombatAPI.COLONNE_ARMURE, bestiaire.getInt(COLONNE_ARMURE)+ ((armureBase.getInt(ArmureBaseAPI.COLONNE_ARMURE)*(marqueArmure.getInt(MarqueArmureAPI.COLONNE_ARMURE)+100))/100));
            combat.put(MonstreCombatAPI.COLONNE_RM, bestiaire.getInt(COLONNE_RM)+((armureBase.getInt(ArmureBaseAPI.COLONNE_RM)*(marqueArmure.getInt(MarqueArmureAPI.COLONNE_RM)+100))/100) );
            combat.put(MonstreCombatAPI.COLONNE_MOUVEMENT, bestiaire.getInt(COLONNE_MOUVEMENT)+armureBase.getInt(ArmureBaseAPI.COLONNE_MOUVEMENT)+marqueArmure.getInt(MarqueArmureAPI.COLONNE_MOUVEMENT));
            combat.put(MonstreCombatAPI.COLONNE_CC, bestiaire.getInt(COLONNE_CC)+armureBase.getInt(ArmureBaseAPI.COLONNE_CCCT)+marqueArmure.getInt(MarqueArmureAPI.COLONNE_CCCT));
            combat.put(MonstreCombatAPI.COLONNE_CT, bestiaire.getInt(COLONNE_CT)+armureBase.getInt(ArmureBaseAPI.COLONNE_CCCT)+marqueArmure.getInt(MarqueArmureAPI.COLONNE_CCCT));
            combat.put(MonstreCombatAPI.COLONNE_AG, bestiaire.getInt(COLONNE_AG)+armureBase.getInt(ArmureBaseAPI.COLONNE_AG)+marqueArmure.getInt(MarqueArmureAPI.COLONNE_AG));
            combat.put(MonstreCombatAPI.COLONNE_F, bestiaire.getInt(COLONNE_F));
            combat.put(MonstreCombatAPI.COLONNE_E, bestiaire.getInt(COLONNE_E));
            combat.put(MonstreCombatAPI.COLONNE_FM, bestiaire.getInt(COLONNE_FM));
            combat.put(MonstreCombatAPI.COLONNE_INT, bestiaire.getInt(COLONNE_INT));
            combat.put(MonstreCombatAPI.COLONNE_P, bestiaire.getInt(COLONNE_P));
            combat.put(MonstreCombatAPI.COLONNE_PV, bestiaire.getInt(COLONNE_PV));
            combat.put(MonstreCombatAPI.COLONNE_PVMAX, bestiaire.getInt(COLONNE_PV));
            combat.put(MonstreCombatAPI.COLONNE_AT, bestiaire.getInt(COLONNE_AT));
            combat.put(MonstreCombatAPI.COLONNE_SOC, bestiaire.getInt(COLONNE_SOC));
            combat.put(MonstreCombatAPI.COLONNE_MAG, bestiaire.getInt(COLONNE_MAG));
            combat.put(MonstreCombatAPI.COLONNE_XP, bestiaire.getInt(COLONNE_XP));
            combat.put(MonstreCombatAPI.COLONNE_BONUSDEGAT, bestiaire.getInt(COLONNE_BONUSDEGAT));
            combat.put(MonstreCombatAPI.COLONNE_EQUIPE, Menu.Equipe);
            combat.put(MonstreCombatAPI.COLONNE_INITIATIVE,combat.getInt(MonstreCombatAPI.COLONNE_AG)+armureBase.getInt(ArmureBaseAPI.COLONNE_INITIATIVE)+marqueArmure.getInt(MarqueArmureAPI.COLONNE_INITIATIVE));
            try {
                combat.put(MonstreCombatAPI.COLONNE_MAINDROITE, bestiaire.getParseObject(COLONNE_MAINDROITE));
            }catch (IllegalArgumentException e){

            }
            try {
                combat.put(MonstreCombatAPI.COLONNE_MAINGAUCHE, bestiaire.getParseObject(COLONNE_MAINGAUCHE));
            }catch (IllegalArgumentException e){

            }
            try {
                combat.put(MonstreCombatAPI.COLONNE_PROTECTION, bestiaire.getParseObject(COLONNE_PROTECTION));
            }catch (IllegalArgumentException e){

            }
            combat.saveInBackground();
        }
    }

}
