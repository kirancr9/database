package com.google.firebase.cs.database;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by kiran on 7/12/16.
 */

public class CertBase extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
