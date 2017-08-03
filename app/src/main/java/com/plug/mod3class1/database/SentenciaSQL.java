package com.plug.mod3class1.database;

import com.plug.mod3class1.entidades.Ubicaciones;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by OSKAR on 31/07/2017.
 * Oskar Steven Conislla Contreras
 * oskarconislla20@gmail.com
 * 947446763
 */

public class SentenciaSQL {

    //Agregar registro a la base de datos
    public static void registrar(Ubicaciones ubicaciones){
        Realm realm=Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(ubicaciones);
        realm.commitTransaction();
    }

    //Obtener mi registro guardado
    public static RealmResults<Ubicaciones> obtener(){
        Realm realm=Realm.getDefaultInstance();
        return realm.where(Ubicaciones.class).findAll();
    }
    //Obtener el Ultimo Id
    public static long obtenerId(){
        Realm realm=Realm.getDefaultInstance();
        long cantidad=realm.where(Ubicaciones.class).count();
        /*Ubicaciones ubicaciones=realm.where(Ubicaciones.class)
        .findAllSorted("id",Sort.DESCENDING).first();
        */
        return cantidad+1;

    }

    public static void eliminar(long id){
        Realm realm=Realm.getDefaultInstance();
        realm.beginTransaction();
        //Para convetir el query que te manda a la clase ubicaciones, como la quieres tener
        Ubicaciones ubicaciones=realm.where(Ubicaciones.class).equalTo("id",id).findFirst();
        ubicaciones.deleteFromRealm();
        realm.commitTransaction();
    }

}
