package com.plug.mod3class1;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by OSKAR on 31/07/2017.
 * Oskar Steven Conislla Contreras
 * oskarconislla20@gmail.com
 * 947446763
 */

public class Configuracion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
        //Creamos la nueva configuracion de la base de datos
        RealmConfiguration realmConfiguration=
                new RealmConfiguration.Builder()
                        .name("GPS.realm")//Aqui cualquier nombre
                        .schemaVersion(1)
                        .build();
        //Cambiamos la configuracion por la que hemos creado
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
