package com.example.rouzicpierre.jdr.api;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.SaveCallback;

import java.util.ArrayList;

/**
 * Created by rouzic pierre on 16/08/2017.
 */

public class CompAPI {
    public static String TABLE_COMP="Comp";
    public static String COLONNE_NOM = "Nom";
    public static String COLONNE_DESCRIPTION = "Description";
    public static String COLONNE_ISTALENT = "isTalent";
    public static String COLONNE_TYPE = "Type";
    public static String COLONNE_BONNUS = "bonnus";
    public static String COLONNE_PO = "PO";
    public static String COLONNE_PVNUITEXTERIEUR = "PVNuitExterieur";
    public static String COLONNE_DISSIMULATION = "Dissimulation";
    public static String COLONNE_DISCRETION = "Discretion";
    public static String COLONNE_MOUVEMENTTERRE = "MouvementTerre";
    public static String COLONNE_MOUVEMENTEAU = "MouvementEau";
    public static String COLONNE_ESCALADE = "Escalade";
    public static String COLONNE_ACROBATIE = "Acrobatie";
    public static String COLONNE_DEGATDECHUTE = "DegatDeChute";
    public static String COLONNE_DEGATATTAQUETOMBER = "DegatAttaqueTomber";
    public static String COLONNE_RESISTANCECHAUD = "ResistanceChaud";
    public static String COLONNE_RESISTANCEFROID = "ResistanceFroid";
    public static String COLONNE_RM = "RM";
    public static String COLONNE_ARMURE = "Armure";
    public static String COLONNE_CHARISME = "Charisme";
    public static String COLONNE_SOCNAIN = "SocNain";
    public static String COLONNE_ORIENTATION = "Orientation";
    public static String COLONNE_FUITE = "Fuite";
    public static String COLONNE_INTIMIDATION = "Intimidation";
    public static String COLONNE_MINAGE = "Minage";
    public static String COLONNE_RESISTANCEMALADIE = "ResistanceMaladie";
    public static String COLONNE_RESISTANCEMUTATION = "ResistanceMutation";
    public static String COLONNE_CA = "CA";
    public static String COLONNE_CD = "CD";
    public static String COLONNE_MARCHANDAGE = "Marchandage";
    public static String COLONNE_DEGATS = "Degats";
    public static String COLONNE_ARNAQUE = "Arnaque";
    public static String COLONNE_XPBONUSMONSTRE = "XPBonusMonstre";
    public static String COLONNE_XPBONUSQUETES = "XPBonusQuetes";
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
    public static String COLONNE_COMMANDEMENT = "Commandement";
    public static String COLONNE_DEGATCRIT = "DegatCrit";
    public static String COLONNE_BONUSPARADE = "BonusParade";
    public static String COLONNE_NBPARADE = "NBParade";
    public static String COLONNE_DEGATPARER = "DegatParer";
    public static String COLONNE_BONUSDOS = "BonusDos";
    public static String COLONNE_TALENTINTEL = "TalentIntel";
    public static String COLONNE_TALENTCORPO = "TalentCorpo";
    public static String COLONNE_TALENTGUERRE = "TalentGuerre";
    public static String COLONNE_VISIONNOCTURNE = "VisionNocturne";
    public static String COLONNE_PVISUEL = "PVisuel";
    public static String COLONNE_PAUDITIVE = "PAuditive";
    public static String COLONNE_POLFACTIVE = "POlfactive";
    public static String COLONNE_SOCELFE = "SocElfe";
    public static String COLONNE_TALENT = "Talent";
    public static String COLONNE_ENCAISSEMENT = "Encaissement";
    public static String COLONNE_RESISTANCEALCOOL = "ResistanceAlcool";




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





    public void setComp0 (String nom){
        ParseObject comp = new ParseObject(TABLE_COMP);
        comp.put(COLONNE_NOM,nom);
        comp.put(COLONNE_PO,0);
        comp.put(COLONNE_PVNUITEXTERIEUR,0);
        comp.put(COLONNE_DISSIMULATION,0);
        comp.put(COLONNE_DISCRETION,0);
        comp.put(COLONNE_MOUVEMENTTERRE,0);
        comp.put(COLONNE_MOUVEMENTEAU,0);
        comp.put(COLONNE_ESCALADE,0);
        comp.put(COLONNE_ACROBATIE,0);
        comp.put(COLONNE_DEGATDECHUTE,0);
        comp.put(COLONNE_DEGATATTAQUETOMBER,0);
        comp.put(COLONNE_RESISTANCECHAUD,0);
        comp.put(COLONNE_RESISTANCEFROID,0);
        comp.put(COLONNE_RM,0);
        comp.put(COLONNE_ARMURE,0);
        comp.put(COLONNE_CHARISME,0);
        comp.put(COLONNE_SOCNAIN,0);
        comp.put(COLONNE_ORIENTATION,0);
        comp.put(COLONNE_FUITE,0);
        comp.put(COLONNE_INTIMIDATION,0);
        comp.put(COLONNE_MINAGE,0);
        comp.put(COLONNE_RESISTANCEMALADIE,0);
        comp.put(COLONNE_RESISTANCEMUTATION,0);
        comp.put(COLONNE_CA,0);
        comp.put(COLONNE_CD,0);
        comp.put(COLONNE_MARCHANDAGE,0);
        comp.put(COLONNE_DEGATS,0);
        comp.put(COLONNE_ARNAQUE,0);
        comp.put(COLONNE_XPBONUSMONSTRE,0);
        comp.put(COLONNE_XPBONUSQUETES,0);
        comp.put(COLONNE_SOC,0);
        comp.put(COLONNE_CC,0);
        comp.put(COLONNE_CT,0);
        comp.put(COLONNE_AG,0);
        comp.put(COLONNE_F,0);
        comp.put(COLONNE_E,0);
        comp.put(COLONNE_FM,0);
        comp.put(COLONNE_AT,0);
        comp.put(COLONNE_INT,0);
        comp.put(COLONNE_P,0);
        comp.put(COLONNE_PV,0);
        comp.put(COLONNE_COMMANDEMENT,0);
        comp.put(COLONNE_DEGATCRIT,0);
        comp.put(COLONNE_BONUSPARADE,0);
        comp.put(COLONNE_NBPARADE,0);
        comp.put(COLONNE_DEGATPARER,0);
        comp.put(COLONNE_BONUSDOS,0);
        comp.put(COLONNE_TALENTINTEL,0);
        comp.put(COLONNE_TALENTCORPO,0);
        comp.put(COLONNE_TALENTGUERRE,0);
        comp.put(COLONNE_VISIONNOCTURNE,0);
        comp.put(COLONNE_PVISUEL,0);
        comp.put(COLONNE_PAUDITIVE,0);
        comp.put(COLONNE_POLFACTIVE,0);
        comp.put(COLONNE_SOCELFE,0);
        comp.put(COLONNE_TALENT,0);
        comp.put(COLONNE_ENCAISSEMENT,0);
        comp.put(COLONNE_RESISTANCEALCOOL,0);
        comp.saveInBackground();

    }


}
