package com.example.rouzicpierre.jdr.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

/**
 * Created by pierre on 28/01/2018.
 */

public class PersoRapideAPI {
    public static String TABLE_PERSORAPIDE="PersoRapide";
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
    public static String COLONNE_Soc = "Soc";
    public static String COLONNE_PV = "PV";
    public static String COLONNE_PVMax = "PVMax";
    public static String COLONNE_MAG = "Mag";
    public static String COLONNE_MAINDROITE = "MainDroite";
    public static String COLONNE_MAINGAUCHE = "MainGauche";
    public static String COLONNE_PROTECTION = "Protection";
    public static String COLONNE_XP = "XP";
    public static String COLONNE_COMP = "Comp";
    public static String COLONNE_BONUSDEGAT = "BonusDegat";


    public ArrayList<ParseObject> getJoueurs(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_PERSORAPIDE);
        ArrayList<ParseObject> listJoueurs  = null;
        try {
            listJoueurs =new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listJoueurs ;
    }

    public ArrayList<ParseObject> getJoueursByName(String nom){
        ParseQuery<ParseObject > query = ParseQuery.getQuery(TABLE_PERSORAPIDE);
        query.whereMatches(COLONNE_NOM,nom);
        ArrayList<ParseObject> listJoueurs = new ArrayList<ParseObject>();
        try {
            listJoueurs = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listJoueurs;
    }



}
