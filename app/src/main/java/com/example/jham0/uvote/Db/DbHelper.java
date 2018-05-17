package com.example.jham0.uvote.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

/**
 * Created by jham0 on 15/04/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    /**Constantes**/

    public static final String TABLA_VOTANTES = "create table Votante(Cedula text primary key, Voto interger)";

    public static final String TABLA_CANDIDATO = "create table Candidato(Nombre text," +
            " Cedula text primary key, Partido text, Votos interger, Url text)";


    /**Metodos**/
    public DbHelper(Context context) {
        super(context, "Db_UV", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_VOTANTES);
        db.execSQL(TABLA_CANDIDATO);
        db.execSQL("insert into Candidato values('Voto en Blanco','0',' ','0','#fff')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists Votante");
        db.execSQL("drop table if exists Candidato");
        db.execSQL(TABLA_VOTANTES);
        db.execSQL(TABLA_CANDIDATO);
    }

}
