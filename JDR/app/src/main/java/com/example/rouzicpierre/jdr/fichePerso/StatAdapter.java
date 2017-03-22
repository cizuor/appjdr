package com.example.rouzicpierre.jdr.fichePerso;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.example.rouzicpierre.jdr.LanceurDeDee;
import com.example.rouzicpierre.jdr.R;

import java.util.ArrayList;

/**
 * Created by rouzic pierre on 18/03/2017.
 */

public class StatAdapter extends ArrayAdapter<String>{

        customButtonListener customListner;

        public interface customButtonListener {
            public void onButtonClickListner(int position,String value);
        }

        public void setCustomButtonListner(customButtonListener listener) {
            this.customListner = listener;
        }

        private Context context;

        //data contien la liste des stat a afficher
        private ArrayList<String> data = new ArrayList<String>();
        private ArrayList<String> valeurstat = new ArrayList<String>();

        public StatAdapter(Context context, ArrayList<String> dataItem,ArrayList<String> valeurstat) {
            super(context, R.layout.statlist, dataItem);
            this.data = dataItem;
            this.valeurstat = valeurstat;
            this.context = context;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.statlist, null);
                viewHolder = new ViewHolder();
                viewHolder.nomstat = (TextView) convertView
                        .findViewById(R.id.nomstat);
                viewHolder.buttonjet = (Button) convertView
                        .findViewById(R.id.buttonjet);
                viewHolder.valeurstat = (EditText) convertView.findViewById(R.id.valeurstat);
                viewHolder.resultat = (TextView) convertView.findViewById(R.id.resultatjet);
                viewHolder.nomstat.setText(data.get(position));
                viewHolder.valeurstat.setText(valeurstat.get(position));

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final String temp = getItem(position);
            final TextView nomstat = (TextView) viewHolder.nomstat;
            final TextView resultat = (TextView) viewHolder.resultat;
            final EditText valeurstat = (EditText) viewHolder.valeurstat;
            final Button buttonjet = (Button) viewHolder.buttonjet;

            viewHolder.buttonjet.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (customListner != null) {
                        LanceurDeDee lanceurDeDee =LanceurDeDee.getLanceurDeDee();
                        int nb = lanceurDeDee.random(100,1);
                        int mastat = Integer.parseInt(valeurstat.getText().toString());
                        // sans cela il refuse car il ne peu pas mettre un int dans un edittext
                        resultat.setText(""+nb);
                        if (nb<=mastat){
                            buttonjet.setBackgroundColor(Color.GREEN);
                        }else{
                            buttonjet.setBackgroundColor(Color.RED);
                        }
                        customListner.onButtonClickListner(position,temp);
                    }

                }
            });

            return convertView;
        }

        public class ViewHolder {
            TextView nomstat;
            EditText valeurstat;
            Button buttonjet;
            TextView resultat;
        }
    }

