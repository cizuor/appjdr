package com.example.rouzicpierre.jdr;

import java.util.Random;

/**
 * Created by rouzic pierre on 11/03/2017.
 */

public class LanceurDeDee {
    private static LanceurDeDee lanceurDeDee = null;
    Random rand;
    private LanceurDeDee(){
        rand = new Random();
    }

    public static LanceurDeDee getLanceurDeDee() {
        if (lanceurDeDee==null){
            lanceurDeDee = new LanceurDeDee();
        }
        return lanceurDeDee;
    }

    public int random (int dees, int nb){
        //Log.i("test","model avant ");
        int r = 0;
        for (int i = 0; i< nb ; i++){
            r=r+(rand.nextInt(dees))+1;
            //Log.i("test","r= "+r);
        }
        //Log.i("test","model après "+r);
        return r;
    }

}
