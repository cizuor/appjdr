package com.example.rouzicpierre.jdr.nouveauPerso.TalentRaceChoix;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rouzicpierre.jdr.R;
import com.example.rouzicpierre.jdr.api.CompAPI;
import com.example.rouzicpierre.jdr.modele.PersoParse;
import com.example.rouzicpierre.jdr.nouveauPerso.classe.ClassChoix;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by rouzic pierre on 16/08/2017.
 */

public class TalentRaciauxAdapteur extends RecyclerView.Adapter<TalentRaciauxAdapteur.ViewHolder> {


    private ArrayList<ParseObject> listTalent;
    private TalentRaceChoix talentRaceChoix;

    public TalentRaciauxAdapteur(ArrayList<ParseObject> listTalent,TalentRaceChoix talentRaceChoix) {
        this.listTalent = listTalent;
        this.talentRaceChoix = talentRaceChoix;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_talent_race_layout, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.display(listTalent.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listTalent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nom;
        private TextView description;
        private LinearLayout casetalent;
        private ParseObject parseObject;

        public ViewHolder(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nom);
            description = (TextView) itemView.findViewById(R.id.description);
            casetalent = (LinearLayout) itemView.findViewById(R.id.casetalent);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PersoParse.TalentRace = parseObject;
                    Intent appel = new Intent(talentRaceChoix, ClassChoix.class);
                    talentRaceChoix.startActivity(appel);
                }
            });
        }

        public void display(ParseObject talent, int position) {
            parseObject = talent;
            nom.setText(talent.getString(CompAPI.COLONNE_NOM));
            description.setText(talent.getString(CompAPI.COLONNE_DESCRIPTION));
            if (position % 2 == 0) {
                Log.i("test", "test if ");
                casetalent.setBackgroundColor(0xFFFFFFFF);
            } else {
                Log.i("test", "test else ");
                casetalent.setBackgroundColor(0xFFAAAAAA);
            }
        }
    }
}
