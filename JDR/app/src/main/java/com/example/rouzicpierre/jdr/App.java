package com.example.rouzicpierre.jdr;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by rouzic pierre on 14/08/2017.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this);
    }

}
