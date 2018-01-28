package com.example.rouzicpierre.jdr.nouveauPerso.classe;

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
import com.example.rouzicpierre.jdr.nouveauPerso.TalentRaceChoix.TalentRaceChoix;
import com.example.rouzicpierre.jdr.nouveauPerso.dee.DeePerso;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by rouzic pierre on 18/08/2017.
 */

public class ClassAdapteur extends RecyclerView.Adapter<ClassAdapteur.ViewHolder> {


    private ArrayList<ParseObject> listClass;
    private ClassChoix classChoix;

    public ClassAdapteur(ArrayList<ParseObject> listClass,ClassChoix classChoix) {
        this.listClass = listClass;
        this.classChoix = classChoix;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_class_choix, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.display(listClass.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listClass.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nom;
        private TextView description;
        private LinearLayout caseclasse;
        private ParseObject parseObject;

        public ViewHolder(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nom);
            description = (TextView) itemView.findViewById(R.id.description);
            caseclasse = (LinearLayout) itemView.findViewById(R.id.caseclass);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PersoParse.Classe = parseObject;
                    Intent appel = new Intent(classChoix, DeePerso.class);
                    classChoix.startActivity(appel);
                }
            });
        }

        public void display(ParseObject classe, int position) {
            parseObject = classe;
            nom.setText(classe.getString(CompAPI.COLONNE_NOM));
            description.setText(classe.getString(CompAPI.COLONNE_DESCRIPTION));
            if (position % 2 == 0) {
                caseclasse.setBackgroundColor(0xFFFFFFFF);
            } else {
                caseclasse.setBackgroundColor(0xFFAAAAAA);
            }
        }
    }
}
