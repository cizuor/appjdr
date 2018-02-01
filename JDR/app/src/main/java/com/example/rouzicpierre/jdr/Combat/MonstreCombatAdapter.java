package com.example.rouzicpierre.jdr.Combat;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rouzicpierre.jdr.LanceurDeDee;
import com.example.rouzicpierre.jdr.Menu;
import com.example.rouzicpierre.jdr.R;
import com.example.rouzicpierre.jdr.api.MonstreCombatAPI;
import com.example.rouzicpierre.jdr.api.TypeAttaqueAPI;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.ArrayList;

/**
 * Created by pierre on 28/01/2018.
 */

public class MonstreCombatAdapter extends RecyclerView.Adapter<MonstreCombatAdapter.ViewHolder> {

    private ArrayList<ParseObject> listEquipe = new ArrayList<>();
    private ArrayList<ParseObject> listMonstres = new ArrayList<ParseObject>();
    private ArrayList<ParseObject> listTypeAttaques;
    private ArrayList<ParseObject> listCombatants;
    private ArrayList listTypeDegats = new ArrayList<>();
    private Combat myActivity;
    private TypeAttaqueAPI typeAttaqueAPI = new TypeAttaqueAPI();
    private ArrayAdapter<String> typeDegatsAdapter;
    private LanceurDeDee lanceurDeDee = LanceurDeDee.getLanceurDeDee();

    public MonstreCombatAdapter(ArrayList<ParseObject> listCombatants,Combat myActivity ) {
        this.listCombatants=listCombatants;
        for (int i = 0; i < listCombatants.size() ; i++) {
            if (listCombatants.get(i).getInt(MonstreCombatAPI.COLONNE_EQUIPE)!= Menu.Equipe){
                listMonstres.add(listCombatants.get(i));
            }else {
                this.listEquipe.add(listCombatants.get(i));
            }
        }
        this.myActivity=myActivity;
        listTypeAttaques=typeAttaqueAPI.getTypeAttaques();
        listTypeDegats.add("physique");
        listTypeDegats.add("magique");
        listTypeDegats.add("brut");
        typeDegatsAdapter = new ArrayAdapter<String>(myActivity,android.R.layout.simple_spinner_item,listTypeDegats);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_monstre_combat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.display(listEquipe.get(position));
    }

    @Override
    public int getItemCount() {
        return listEquipe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nom;
        private TextView PV;
        private Spinner spinnertypeAttaque;
        private Spinner spinnerCible;
        private Spinner spinnertypeDegat;
        private CheckBox checkBoxDos;
        private EditText editDegatSubit;
        private Button buttonSubire;
        private Button buttonAttaque;
        private Button buttonFinTour;
        private ParseObject monstre;
        public ViewHolder(final View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nom);
            PV = (TextView) itemView.findViewById(R.id.PV);
            spinnerCible = (Spinner) itemView.findViewById(R.id.cible);
            buttonAttaque = (Button) itemView.findViewById(R.id.attaque);
            spinnertypeAttaque = (Spinner) itemView.findViewById(R.id.typeAttaque);
            spinnertypeDegat = (Spinner) itemView.findViewById(R.id.typeDegat);
            checkBoxDos = (CheckBox) itemView.findViewById(R.id.dos);
            editDegatSubit = (EditText) itemView.findViewById(R.id.degatSubit);
            buttonSubire = (Button) itemView.findViewById(R.id.subir);
            buttonFinTour = (Button) itemView.findViewById(R.id.finTour);
            spinnertypeDegat.setAdapter(typeDegatsAdapter);

            SpinMonstreAdapter spinMonstreAdapter = new SpinMonstreAdapter(myActivity.getApplicationContext(),listMonstres);
            spinnerCible.setAdapter(spinMonstreAdapter);

            SpinAttaqueAdapter spinAttaqueAdapter = new SpinAttaqueAdapter(myActivity.getApplicationContext(),listTypeAttaques);
            spinnertypeAttaque.setAdapter(spinAttaqueAdapter);
        }


        public void display(ParseObject monstre){
            if (getAdapterPosition()%2==0){
                itemView.setBackgroundColor(0xFFFFFFFF);
            }
            this.monstre = monstre;
            nom.setText(monstre.getString(MonstreCombatAPI.COLONNE_NOM));
            PV.setText(String.valueOf(monstre.getInt(MonstreCombatAPI.COLONNE_PV)));
            if (!monstre.getBoolean(MonstreCombatAPI.COLONNE_PLAYED)){
                buttonFinTour.setEnabled(false);
                buttonAttaque.setEnabled(false);
                buttonSubire.setEnabled(false);
            }else{
                buttonFinTour.setEnabled(true);
                buttonAttaque.setEnabled(true);
                buttonSubire.setEnabled(true);
            }
            buttonAttaque.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickAttaque((ParseObject)spinnerCible.getSelectedItem(),(ParseObject)spinnertypeAttaque.getSelectedItem(),checkBoxDos.isChecked());
                }
            });
            buttonSubire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickSubire();
                }
            });

            buttonFinTour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickFinTour();
                }
            });

        }


        public void onClickAttaque(ParseObject cible, ParseObject typeAttaque, Boolean dos){
            lanceurDeDee.Attaque(monstre,cible,typeAttaque,dos,myActivity);

        }

        public void onClickSubire(){

           lanceurDeDee.DégatSubit(monstre,spinnertypeDegat.getSelectedItem().toString(),Integer.parseInt(editDegatSubit.getText().toString()));
        }

        public void onClickFinTour(){
            monstre.put(MonstreCombatAPI.COLONNE_NBPARADE,monstre.getInt(MonstreCombatAPI.COLONNE_NBPARADEMAX));
            monstre.put(MonstreCombatAPI.COLONNE_PLAYED,false);
            for (int i = 0; i < listCombatants.size(); i++) {
                if (monstre.getObjectId().equals(listCombatants.get(i).getObjectId())){
                    if (i<listCombatants.size()-1){
                        listCombatants.get(i+1).put(MonstreCombatAPI.COLONNE_PLAYED,true);
                        listCombatants.get(i+1).saveInBackground();
                    }else{
                        listCombatants.get(0).put(MonstreCombatAPI.COLONNE_PLAYED,true);
                        listCombatants.get(0).saveInBackground();
                    }
                    i=listCombatants.size();
                }
            }
            monstre.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    myActivity.onResume();
                }
            });

        }



    }
}