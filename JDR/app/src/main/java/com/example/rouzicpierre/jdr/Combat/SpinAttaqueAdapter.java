package com.example.rouzicpierre.jdr.Combat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rouzicpierre.jdr.R;
import com.example.rouzicpierre.jdr.api.MonstreCombatAPI;
import com.example.rouzicpierre.jdr.api.TypeAttaqueAPI;
import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by pierre on 31/01/2018.
 */

public class SpinAttaqueAdapter  extends BaseAdapter {
    Context context;
    ArrayList<ParseObject> objects;
    LayoutInflater inflater;

    public SpinAttaqueAdapter(Context context,ArrayList<ParseObject> objects){
        this.context=context;
        this.objects=objects;
        inflater = (LayoutInflater.from(context));
    }


    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.adapter_spin_custum_textview,null);
        TextView nom = (TextView) view.findViewById(R.id.nom);
        nom.setText(objects.get(i).getString(TypeAttaqueAPI.COLONNE_NOM)+" D"+objects.get(i).getInt(TypeAttaqueAPI.COLONNE_DEGATS)+"% CA "+objects.get(i).getInt(TypeAttaqueAPI.COLONNE_CA)+" CD "+objects.get(i).getInt(TypeAttaqueAPI.COLONNE_CD));

        return view;
    }
}