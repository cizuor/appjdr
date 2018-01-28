package com.example.rouzicpierre.jdr.nouveauPerso.race;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rouzicpierre.jdr.R;
import com.example.rouzicpierre.jdr.api.RaceAPI;
import com.example.rouzicpierre.jdr.modele.PersoParse;
import com.example.rouzicpierre.jdr.nouveauPerso.TalentRaceChoix.TalentRaceChoix;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by rouzic pierre on 16/08/2017.
 */

public class RaceAdapteur extends RecyclerView.Adapter<RaceAdapteur.ViewHolder>{

    private ArrayList<ParseObject> listrace;
    NouveauPersoParse nouveauPersoParse;

    public RaceAdapteur(ArrayList<ParseObject> listrace,NouveauPersoParse nouveauPersoParse) {
        this.listrace = listrace;
        this.nouveauPersoParse = nouveauPersoParse;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_race_layout, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.display(listrace.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listrace.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nom;
        private TextView description;
        private LinearLayout caserace;
        private ParseObject parseObject;

        public ViewHolder(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nom);
            description = (TextView) itemView.findViewById(R.id.description);
            caserace = (LinearLayout) itemView.findViewById(R.id.caserace);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PersoParse.Race=parseObject;
                    Intent appel = new Intent(nouveauPersoParse, TalentRaceChoix.class);
                    nouveauPersoParse.startActivity(appel);
                }
            });
        }

        public void display(ParseObject race,int position){
            parseObject=race;
            nom.setText(race.getString(RaceAPI.COLONNE_NOM));
            description.setText(race.getString(RaceAPI.COLONNE_DESCRIPTION));
            if (position%2 == 0 ){
                caserace.setBackgroundColor(0xFFFFFFFF);
            }else{
                caserace.setBackgroundColor(0xFFAAAAAA);
            }
        }


    }
}
