package com.example.rouzicpierre.jdr.fichePerso;

import java.util.Random;

/**
 * Created by rouzic pierre on 04/03/2017.
 */

public class MonPersoModel {

    Random rand;
    private static MonPersoModel monPersoModel = null;


    // pour éviter quil y est plein d'instance de cete classe on la transforme en singletton
    public static MonPersoModel getMonPersoModel (){
        if(monPersoModel == null){
            monPersoModel=new MonPersoModel();
        }
        return monPersoModel;
    }



    private MonPersoModel() {}

}
