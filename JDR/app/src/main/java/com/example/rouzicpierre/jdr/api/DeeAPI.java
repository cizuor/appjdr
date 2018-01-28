package com.example.rouzicpierre.jdr.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rouzic pierre on 23/08/2017.
 */

public class DeeAPI {
    public static String TABLE_DEE="Dee";
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
    public static String COLONNE_TAILLE = "Taille";
    public static String COLONNE_POID = "Poid";
    public static String COLONNE_AGE = "Age";

    public static ArrayList<String> numNom;

    public DeeAPI() {
        numNom = new ArrayList<>();
        numNom.add("CC");
        numNom.add("CT");
        numNom.add("Ag");
        numNom.add("F");
        numNom.add("E");
        numNom.add("FM");
        numNom.add("Int");
        numNom.add("P");
        numNom.add("Soc");
        numNom.add("PV");
        numNom.add("Taille");
        numNom.add("Poid");
        numNom.add("Age");
    }




}
