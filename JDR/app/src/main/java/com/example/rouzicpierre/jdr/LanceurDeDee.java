package com.example.rouzicpierre.jdr;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.rouzicpierre.jdr.EffetTemporaire.CD;
import com.example.rouzicpierre.jdr.EffetTemporaire.Effect;
import com.example.rouzicpierre.jdr.EffetTemporaire.ListEffect;
import com.example.rouzicpierre.jdr.api.ArmeAPI;
import com.example.rouzicpierre.jdr.api.ArmeBaseAPI;
import com.example.rouzicpierre.jdr.api.MarqueArmeAPI;
import com.example.rouzicpierre.jdr.api.MetalArmeAPI;
import com.example.rouzicpierre.jdr.api.MonstreCombatAPI;
import com.example.rouzicpierre.jdr.api.TypeAttaqueAPI;
import com.parse.ParseObject;

import java.util.Random;

/**
 * Created by rouzic pierre on 11/03/2017.
 */

public class LanceurDeDee {
    private static LanceurDeDee lanceurDeDee = null;
    Random rand;
    private ArmeAPI armeAPI = new ArmeAPI();
    private LanceurDeDee(){
        rand = new Random();
    }
    private ListEffect listEffect = ListEffect.getListEffect();

    public static LanceurDeDee getLanceurDeDee() {
        if (lanceurDeDee==null){
            lanceurDeDee = new LanceurDeDee();
        }
        return lanceurDeDee;
    }

    public int random (int dees, int nb){
        int r = 0;
        for (int i = 0; i< nb ; i++){
            r=r+(rand.nextInt(dees))+1;
        }
        return r;
    }



    public int JetDegat (int dees, int nb, int crit){

        int degat = random(dees,nb);
        if (degat == dees ){
            return degat+((dees*crit)/100);
        }else{
            return degat;
        }
    }



    public int JetCA (){

        int D1 = random(20,1);
        if (D1 == 20 ){
            return 200;
        }else if (D1 == 1){
            return 100;
        }else {
            return D1;
        }
    }

    public void Attaque (ParseObject attaquant, ParseObject cible, ParseObject typeAttaque, Boolean dos, Activity myActivity){

        String result = " votre attaque ";
        int jetCA = JetCA();
        int bonusCA;
        int totalCA;
        int totalCD;
        Boolean crit = false;
        Boolean defMainGauche = false;
        Boolean atMainGauche = false;
        int totalDegats = 0;
        int nbParades = cible.getInt(MonstreCombatAPI.COLONNE_NBPARADE);
        ParseObject atArme = armeAPI.getArmeByID(attaquant.getParseObject(MonstreCombatAPI.COLONNE_MAINDROITE).getObjectId());
        ParseObject atBaseArme = atArme.getParseObject(ArmeAPI.COLONNE_BASE);
        ParseObject atMetalArme = atArme.getParseObject(ArmeAPI.COLONNE_METAL);
        ParseObject atMarqueArme = atArme.getParseObject(ArmeAPI.COLONNE_MARQUE);

        ParseObject defArme = armeAPI.getArmeByID(cible.getParseObject(MonstreCombatAPI.COLONNE_MAINDROITE).getObjectId());
        ParseObject defBaseArme = defArme.getParseObject(ArmeAPI.COLONNE_BASE);
        ParseObject defMetalArme = defArme.getParseObject(ArmeAPI.COLONNE_METAL);
        ParseObject defMarqueArme = defArme.getParseObject(ArmeAPI.COLONNE_MARQUE);


        ParseObject atArme2=null;
        ParseObject atBaseArme2=null;
        ParseObject atMetalArme2=null;
        ParseObject atMarqueArme2=null;

        ParseObject defArme2=null;
        ParseObject defBaseArme2=null;
        ParseObject defMetalArme2=null;
        ParseObject defMarqueArme2=null;

        if (cible.getParseObject(MonstreCombatAPI.COLONNE_MAINGAUCHE)!=null){
            defMainGauche=true;
            defArme2 = armeAPI.getArmeByID(cible.getParseObject(MonstreCombatAPI.COLONNE_MAINGAUCHE).getObjectId());
            defBaseArme2 = defArme2.getParseObject(ArmeAPI.COLONNE_BASE);
            defMetalArme2 = defArme2.getParseObject(ArmeAPI.COLONNE_METAL);
            defMarqueArme2 = defArme2.getParseObject(ArmeAPI.COLONNE_MARQUE);
        }
        if (attaquant.getParseObject(MonstreCombatAPI.COLONNE_MAINGAUCHE)!=null){

            atArme2 = armeAPI.getArmeByID(attaquant.getParseObject(MonstreCombatAPI.COLONNE_MAINGAUCHE).getObjectId());
            atBaseArme2 = atArme2.getParseObject(ArmeAPI.COLONNE_BASE);
            atMetalArme2 = atArme2.getParseObject(ArmeAPI.COLONNE_METAL);
            atMarqueArme2 = atArme2.getParseObject(ArmeAPI.COLONNE_MARQUE);
            if (!atBaseArme2.getBoolean(ArmeBaseAPI.COLONNE_ISBOUCLIER)){
                atMainGauche=true;
            }
        }

        bonusCA = attaquant.getInt(MonstreCombatAPI.COLONNE_CA)+(attaquant.getInt(MonstreCombatAPI.COLONNE_CC)/10)+typeAttaque.getInt(TypeAttaqueAPI.COLONNE_CA);
        totalCD = cible.getInt(MonstreCombatAPI.COLONNE_CD)+(cible.getInt(MonstreCombatAPI.COLONNE_CC)/10)+(cible.getInt(MonstreCombatAPI.COLONNE_AG)/10)+5;

        if (typeAttaque.getInt(TypeAttaqueAPI.COLONNE_CD)!=0){
            Effect effect = new CD(attaquant,attaquant,typeAttaque.getInt(TypeAttaqueAPI.COLONNE_CD),1);
            listEffect.addEffects(effect);
        }

        if (jetCA==200){
            result = result + "est un critique et touche ";
            totalCA = bonusCA+ 200 + attaquant.getInt(MonstreCombatAPI.COLONNE_CA)+(attaquant.getInt(MonstreCombatAPI.COLONNE_CC)/10)+typeAttaque.getInt(TypeAttaqueAPI.COLONNE_CA);
            totalDegats=CalculeDegats(attaquant,atBaseArme,atMarqueArme,atMetalArme,true,false,typeAttaque,dos);
            result = result+" et inflige "+totalDegats;
            DégatSubit(cible,"physique",totalDegats);
        }else if ( jetCA == 100 ){
            result = result + "est un echec critique et touche un allié ou vous même ";
            totalCA = bonusCA+-200 + attaquant.getInt(MonstreCombatAPI.COLONNE_CA)+(attaquant.getInt(MonstreCombatAPI.COLONNE_CC)/10)+typeAttaque.getInt(TypeAttaqueAPI.COLONNE_CA);
            totalDegats=CalculeDegats(attaquant,atBaseArme,atMarqueArme,atMetalArme,false,false,typeAttaque,false);
            result = result+" et inflige "+totalDegats;

        }else {
            totalCA =bonusCA+ jetCA + attaquant.getInt(MonstreCombatAPI.COLONNE_CA)+(attaquant.getInt(MonstreCombatAPI.COLONNE_CC)/10)+typeAttaque.getInt(TypeAttaqueAPI.COLONNE_CA);
            if (totalCA>totalCD){
                result = result+"est une réussite et touche ";
                totalDegats=CalculeDegats(attaquant,atBaseArme,atMarqueArme,atMetalArme,false,false,typeAttaque,dos);

                if (nbParades>0){
                    int degatApresParade;
                    degatApresParade = Parrade(cible,totalDegats);
                    if (degatApresParade!=totalDegats){
                        result = result+"mais est parrer ";
                    }
                    totalDegats=degatApresParade;
                    nbParades = nbParades-1;
                }
                result = result+" et inflige "+totalDegats;
                DégatSubit(cible,"physique",totalDegats);
            }else {
                result = result+"est un echec et manque";
                Toast toast = Toast.makeText(myActivity, result, Toast.LENGTH_LONG);
                toast.show();
            }
        }
        Toast toast = Toast.makeText(myActivity, result, Toast.LENGTH_LONG);
        toast.show();


        if (atMainGauche){
            result = " votre attaque avec la main gauche ";
            bonusCA = bonusCA-2;
            jetCA = JetCA();

            if (jetCA==200){
                result = result + "est un critique et touche ";
                totalCA = bonusCA+ 200 + attaquant.getInt(MonstreCombatAPI.COLONNE_CA)+(attaquant.getInt(MonstreCombatAPI.COLONNE_CC)/10)+typeAttaque.getInt(TypeAttaqueAPI.COLONNE_CA);
                totalDegats=CalculeDegats(attaquant,atBaseArme2,atMarqueArme2,atMetalArme2,true,true,typeAttaque,dos);
                result = result+" et inflige "+totalDegats;
                DégatSubit(cible,"physique",totalDegats);
            }else if ( jetCA == 100 ){
                result = result + "est un echec critique et touche un allié ou vous même ";
                totalCA = bonusCA+-200 + attaquant.getInt(MonstreCombatAPI.COLONNE_CA)+(attaquant.getInt(MonstreCombatAPI.COLONNE_CC)/10)+typeAttaque.getInt(TypeAttaqueAPI.COLONNE_CA);
                totalDegats=CalculeDegats(attaquant,atBaseArme2,atMarqueArme2,atMetalArme2,false,true,typeAttaque,false);
                result = result+" et inflige "+totalDegats;
            }else {
                totalCA =bonusCA+ jetCA + attaquant.getInt(MonstreCombatAPI.COLONNE_CA)+(attaquant.getInt(MonstreCombatAPI.COLONNE_CC)/10)+typeAttaque.getInt(TypeAttaqueAPI.COLONNE_CA);
                if (totalCA>totalCD){
                    result = result+"est une réussite et touche ";
                    totalDegats=CalculeDegats(attaquant,atBaseArme2,atMarqueArme2,atMetalArme2,false,true,typeAttaque,dos);

                    if (nbParades>0){
                        int degatApresParade;
                        degatApresParade = Parrade(cible,totalDegats);
                        if (degatApresParade!=totalDegats){
                            result = result+"mais est parrer ";
                        }
                        totalDegats=degatApresParade;

                    }
                    result = result+" et inflige "+totalDegats;
                    DégatSubit(cible,"physique",totalDegats);
                }else {
                    result = result+"est un echec et manque";

                }
            }
            toast = Toast.makeText(myActivity, result, Toast.LENGTH_LONG);
            toast.show();
        }
    }


    public int Parrade(ParseObject cible,int degats){
        cible.put(MonstreCombatAPI.COLONNE_NBPARADE,cible.getInt(MonstreCombatAPI.COLONNE_NBPARADE)-1);
        cible.saveInBackground();
        int CCParade = cible.getInt(MonstreCombatAPI.COLONNE_CC)+cible.getInt(MonstreCombatAPI.COLONNE_BONUSPARADE);
        int roll = random(100,1);
        if (CCParade>=roll){
            degats = degats-cible.getInt(MonstreCombatAPI.COLONNE_DEGATPARER);
        }
        if (degats<0){
            return 0;
        }else{
            return degats;
        }
    }

    //todo choisir qoi faire comme boost avec les toucher critique
    public int CalculeDegats(ParseObject attaquant,ParseObject atBaseArme,ParseObject atMarqueArme,ParseObject atMetalArme,Boolean crit,Boolean mainGauche,ParseObject typeAttaque,Boolean dos){
        int degatcrit = attaquant.getInt(MonstreCombatAPI.COLONNE_DEGATCRIT)+atBaseArme.getInt(ArmeBaseAPI.COLONNE_DEGATCRITIQUE)+atMarqueArme.getInt(MarqueArmeAPI.COLONNE_DEGATCRIT)+atMetalArme.getInt(MetalArmeAPI.COLONNE_DEGATCRIT);
        int totalBonusDegats = (atBaseArme.getInt(ArmeBaseAPI.COLONNE_DEGATBASE)+(attaquant.getInt(MonstreCombatAPI.COLONNE_F)/10));
        int déesDegats = atBaseArme.getInt(ArmeBaseAPI.COLONNE_TYPEDEE);
        int nbDee = atBaseArme.getInt(ArmeBaseAPI.COLONNE_NBDEE);
        int degats = JetDegat(déesDegats,nbDee,degatcrit);
        if (atBaseArme.getBoolean(ArmeBaseAPI.COLONNE_DEUXMAINS)){
            int percutant = JetDegat(déesDegats,nbDee,degatcrit);
            if (percutant>degats){
                degats = percutant;
            }
        }
        if (atMarqueArme.getBoolean(MarqueArmeAPI.COLONNE_PERCUTANT)){
            int percutant = JetDegat(déesDegats,nbDee,degatcrit);
            if (percutant>degats){
                degats = percutant;
            }
        }

        degats=degats+totalBonusDegats;
        int degatfinaux;
        if (mainGauche){
            degatfinaux =50+ atMarqueArme.getInt(MarqueArmeAPI.COLONNE_DEGATS)+atMetalArme.getInt(MetalArmeAPI.COLONNE_DEGATS)+typeAttaque.getInt(TypeAttaqueAPI.COLONNE_DEGATS)+attaquant.getInt(MonstreCombatAPI.COLONNE_BONUSDEGAT);
        }else {
            degatfinaux =100+ atMarqueArme.getInt(MarqueArmeAPI.COLONNE_DEGATS)+atMetalArme.getInt(MetalArmeAPI.COLONNE_DEGATS)+typeAttaque.getInt(TypeAttaqueAPI.COLONNE_DEGATS)+attaquant.getInt(MonstreCombatAPI.COLONNE_BONUSDEGAT);
        }
        degats= (degats*degatfinaux)/100;
        if (dos){
            int degatdos =attaquant.getInt(MonstreCombatAPI.COLONNE_BONUSDOS)+atBaseArme.getInt(ArmeBaseAPI.COLONNE_BONUSDOS)+atMarqueArme.getInt(MarqueArmeAPI.COLONNE_DEGATDOS)+atMetalArme.getInt(MetalArmeAPI.COLONNE_DEGATDOS)+100;
            degats = (degats*degatdos)/100;
        }

        return degats;
    }

    public void DégatSubit(ParseObject cible , String typeDegats, int NBDegats){
        switch (typeDegats){
            case "physique":
                if (cible.getInt(MonstreCombatAPI.COLONNE_ARMURE)>NBDegats){
                    NBDegats =NBDegats- (NBDegats*cible.getInt(MonstreCombatAPI.COLONNE_REDUCDEGATS))/100;
                    cible.increment(MonstreCombatAPI.COLONNE_ARMURE,-NBDegats);
                    cible.saveInBackground();
                }else if (cible.getInt(MonstreCombatAPI.COLONNE_ARMURE)!=0){
                    cible.increment(MonstreCombatAPI.COLONNE_PV,-(NBDegats-cible.getInt(MonstreCombatAPI.COLONNE_ARMURE)));
                    cible.put(MonstreCombatAPI.COLONNE_ARMURE,0);
                    cible.saveInBackground();
                }else{
                    cible.increment(MonstreCombatAPI.COLONNE_PV,-NBDegats);
                    cible.saveInBackground();
                }
                break;
            case "magique":
                if (cible.getInt(MonstreCombatAPI.COLONNE_RM)>NBDegats){
                    cible.increment(MonstreCombatAPI.COLONNE_RM,-NBDegats);
                    cible.saveInBackground();
                }else if (cible.getInt(MonstreCombatAPI.COLONNE_RM)!=0){
                    cible.increment(MonstreCombatAPI.COLONNE_PV,-(NBDegats-cible.getInt(MonstreCombatAPI.COLONNE_RM)));
                    cible.put(MonstreCombatAPI.COLONNE_RM,0);
                    cible.saveInBackground();
                }else{
                    cible.increment(MonstreCombatAPI.COLONNE_PV,-NBDegats);
                    cible.saveInBackground();
                }
                break;
            case "brut":
                cible.increment(MonstreCombatAPI.COLONNE_PV,-NBDegats);
                cible.saveInBackground();
                break;
        }
    }



}
