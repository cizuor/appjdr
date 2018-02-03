package com.example.rouzicpierre.jdr.Comp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rouzicpierre.jdr.R;
import com.example.rouzicpierre.jdr.api.CompAPI;

public class NewComp extends AppCompatActivity {

    private EditText editNom;
    private Button validez;
    private CompAPI compAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comp);
        compAPI = new CompAPI();
        editNom = (EditText)findViewById(R.id.newCompEditNom);
        validez = (Button) findViewById(R.id.newCompButtonValidez);

        validez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickValidez();
            }
        });
    }
    private void onClickValidez(){
        compAPI.setComp0(editNom.getText().toString());
    }



}
