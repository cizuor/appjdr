package com.example.rouzicpierre.jdr.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

/**
 * Created by pierre on 28/01/2018.
 */

public class MonstreCombatAPI {
    public static String TABLE_MONSTRECOMBAT="MonstreDuCombat";
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
    public static String COLONNE_PVMAX = "PVMax";
    public static String COLONNE_AT = "At";
    public static String COLONNE_MAG = "Mag";
    public static String COLONNE_MAINDROITE = "MainDroite";
    public static String COLONNE_MAINGAUCHE = "MainGauche";
    public static String COLONNE_PROTECTION = "Protection";
    public static String COLONNE_XP = "XP";
    public static String COLONNE_COMP = "Comp";
    public static String COLONNE_BONUSDEGAT = "BonusDegat";
    public static String COLONNE_EQUIPE = "Equipe";
    public static String COLONNE_INITIATIVE = "Init";
    public static String COLONNE_PLAYED = "Played";
    public static String COLONNE_BONUSPARADE = "BonusParade";
    public static String COLONNE_NBPARADE = "NBParade";
    public static String COLONNE_NBPARADEMAX = "NBParadeMax";
    public static String COLONNE_DEGATPARER = "DegatParer";
    public static String COLONNE_DEGATCRIT = "DegatCrit";
    public static String COLONNE_BONUSDOS = "BonusDos";
    public static String COLONNE_REDUCDEGATS = "ReducDegats";

    public ArrayList<ParseObject> getMonstres(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_MONSTRECOMBAT);
        ArrayList<ParseObject> listMonstres  = null;
        query.orderByDescending(COLONNE_INITIATIVE);
        try {
            listMonstres =new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listMonstres ;
    }

    public ArrayList<ParseObject> getMonstresByTeam(int Team){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_MONSTRECOMBAT);
        query.whereEqualTo(COLONNE_EQUIPE,Team);
        ArrayList<ParseObject> listMonstres  = null;
        try {
            listMonstres =new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listMonstres ;
    }

    public ArrayList<ParseObject> getMonstresExeptTeam(int Team){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_MONSTRECOMBAT);
        query.whereNotEqualTo(COLONNE_EQUIPE,Team);
        ArrayList<ParseObject> listMonstres  = null;
        try {
            listMonstres =new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listMonstres ;
    }

    public ArrayList<ParseObject> getMonstresByName(String nom){
        ParseQuery<ParseObject > query = ParseQuery.getQuery(TABLE_MONSTRECOMBAT);
        query.whereMatches(COLONNE_NOM,nom);
        ArrayList<ParseObject> listMonstres = new ArrayList<ParseObject>();
        try {
            listMonstres = new ArrayList<>(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listMonstres;
    }


}
