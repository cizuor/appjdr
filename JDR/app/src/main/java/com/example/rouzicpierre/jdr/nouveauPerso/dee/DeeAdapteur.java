package com.example.rouzicpierre.jdr.nouveauPerso.dee;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rouzicpierre.jdr.LanceurDeDee;
import com.example.rouzicpierre.jdr.R;
import com.example.rouzicpierre.jdr.api.DeeAPI;
import com.example.rouzicpierre.jdr.api.RaceAPI;
import com.example.rouzicpierre.jdr.modele.PersoParse;
import com.parse.ParseObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rouzic pierre on 23/08/2017.
 */

public class DeeAdapteur extends RecyclerView.Adapter<DeeAdapteur.ViewHolder> {

    private int relance = 0;
    private Map<String,Integer> mapDee;
    private DeeAPI deeAPI;
    private LanceurDeDee lanceurDeDee;
    private DeePerso myActivity;

    public DeeAdapteur(int relance,DeePerso myActivity) {
        this.relance = relance;
        mapDee = new HashMap();
        deeAPI=new DeeAPI();
        lanceurDeDee=LanceurDeDee.getLanceurDeDee();
        this.myActivity=myActivity;

    }

    @Override
    public DeeAdapteur.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dee_layout, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DeeAdapteur.ViewHolder holder, int position) {
        holder.display(position);
    }

    @Override
    public int getItemCount() {
        return deeAPI.numNom.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        int position;
        private TextView nom;
        private TextView valeur;
        private LinearLayout caseDee;

        public ViewHolder(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nomstat);
            valeur = (TextView) itemView.findViewById(R.id.valeurstat);
            caseDee = (LinearLayout) itemView.findViewById(R.id.casedee);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myActivity.relance>0){
                        int val=rand(position);
                        if (Integer.parseInt(valeur.getText().toString())<val){
                            valeur.setText(Integer.toString(val));
                        }
                        myActivity.ActualiseRelance();
                    }
                }
            });
        }


        public void display(int position) {
            this.position = position;
            nom.setText(deeAPI.numNom.get(position));
            int val;
            val = rand(position);

            valeur.setText(Integer.toString(val));
            if (position % 2 == 0) {
                caseDee.setBackgroundColor(0xFFFFFFFF);
            } else {
                caseDee.setBackgroundColor(0xFFAAAAAA);
            }
        }

        private int rand(int position) {
            int val;
            if (deeAPI.numNom.get(position).equals("PV")) {
                val = lanceurDeDee.random(PersoParse.Race.getInt(RaceAPI.COLONNE_PVTYPEDEE), PersoParse.Race.getInt(RaceAPI.COLONNE_PVNBDEE));
            } else if (deeAPI.numNom.get(position).equals("Taille")) {
                val = lanceurDeDee.random(PersoParse.Race.getInt(RaceAPI.COLONNE_TAILLETYPEDEE), PersoParse.Race.getInt(RaceAPI.COLONNE_TAILLENBDEE));
            } else if (deeAPI.numNom.get(position).equals("Poid")) {
                val = lanceurDeDee.random(PersoParse.Race.getInt(RaceAPI.COLONNE_POIDTYPEDEE), PersoParse.Race.getInt(RaceAPI.COLONNE_POIDNBDEE));
            } else if (deeAPI.numNom.get(position).equals("Age")) {
                val = lanceurDeDee.random(PersoParse.Race.getInt(RaceAPI.COLONNE_AGETYPEDEE), PersoParse.Race.getInt(RaceAPI.COLONNE_AGENBDEE));
            } else {
                val = lanceurDeDee.random(6, 2);
            }
            mapDee.put(deeAPI.numNom.get(position), val);
            return val;
        }
    }
}
