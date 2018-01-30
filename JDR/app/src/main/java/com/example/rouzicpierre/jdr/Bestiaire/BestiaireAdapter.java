package com.example.rouzicpierre.jdr.Bestiaire;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rouzicpierre.jdr.R;
import com.example.rouzicpierre.jdr.api.BestiaireAPI;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by pierre on 29/01/2018.
 */

public class BestiaireAdapter extends RecyclerView.Adapter<BestiaireAdapter.ViewHolder> {

    private ArrayList<ParseObject> listMonstres;
    private Activity myActivity;
    private BestiaireAPI bestiaireAPI = new BestiaireAPI();


    public BestiaireAdapter(ArrayList<ParseObject> listMonstres, Activity myActivity) {
        this.listMonstres = listMonstres;
        this.myActivity = myActivity;
    }

    @Override
    public BestiaireAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adapter_bestiaire,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BestiaireAdapter.ViewHolder holder, int position) {
        holder.display(listMonstres.get(position));
    }

    @Override
    public int getItemCount() {
        return listMonstres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nom;
        private TextView XP;
        private Spinner spinnerUP;
        private EditText editNombre;
        private Button buttonValidez;
        private ParseObject monstre;

        public ViewHolder(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.nom);
            XP = (TextView) itemView.findViewById(R.id.xp);
            spinnerUP = (Spinner) itemView.findViewById(R.id.spinnerUP);
            editNombre = (EditText) itemView.findViewById(R.id.nombre);
            buttonValidez = (Button) itemView.findViewById(R.id.validez);

            buttonValidez.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickValidez();
                }
            });
        }


        public void display(ParseObject monstre){
            if (getAdapterPosition()%2==0){
                itemView.setBackgroundColor(0xFFFFFFFF);
            }
            this.monstre=monstre;
            nom.setText(monstre.getString(BestiaireAPI.COLONNE_NOM));
            XP.setText(String.valueOf(monstre.getInt(BestiaireAPI.COLONNE_XP)));


        }


        public void onClickValidez(){
            try {
                bestiaireAPI.transfertToCombat(monstre, Integer.parseInt(editNombre.getText().toString()));
            }catch (NumberFormatException e){
                bestiaireAPI.transfertToCombat(monstre, 1);
            }
            myActivity.finish();
        }
    }
}
