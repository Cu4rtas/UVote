package com.example.jham0.uvote.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jham0.uvote.CandidateItem.CandidateItem;

import java.util.ArrayList;

public class DbQuery {
    /**Atributos**/
    private DbHelper helper;
    public SQLiteDatabase db;
    private ContentValues cv;
    private Cursor cursor;

    public DbQuery(Context context) {
        this.helper = new DbHelper(context);
        cv = new ContentValues();
    }

    private void open() {
        this.db = helper.getWritableDatabase();
    }

    public void insertarCandidato(String nombre, String cedula, String partido, String url) {
        open();
        cv.put("Nombre", nombre);
        cv.put("Cedula", cedula);
        cv.put("Partido", partido);
        cv.put("Url", url);
        db.insert("Candidato", null, cv);
        close();
    }

    public void insertarVotante(String cedula) {
        open();
        cv.put("Cedula", cedula);
        cv.put("Voto", false);
        db.insert("Votante", null, cv);
        close();
    }

    /***
     * Obtiene las cédulas de los votantes.
     * @return Un ArrayList de tipo String
     * que contiene las cédulas de los votantes.
     */
    public ArrayList<String> obtenerCedulasVotante() {
        open();
        ArrayList<String> cedulas = new ArrayList<>();
        cursor = db.rawQuery("select Cedula from Votante", null);
        if (cursor.moveToFirst()) {
            do {
                cedulas.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        close();
        return cedulas;
    }

    public ArrayList<CandidateItem> obtenerCandidatos() {
        open();
        ArrayList<CandidateItem> candidatos = new ArrayList<>();
        cursor = db.rawQuery("select * from Candidato", null);
        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(cursor.getColumnIndex("Nombre"));
                String partido = cursor.getString(cursor.getColumnIndex("Partido"));
                String cedula = cursor.getString(cursor.getColumnIndex("Cedula"));
                String URL = cursor.getString(cursor.getColumnIndex("Url"));
                candidatos.add(new CandidateItem(cedula, nombre, partido, URL));
            } while (cursor.moveToNext());
        }
        close();
        return candidatos;
    }

    private void close() {
        db.close();
    }


    public ArrayList<String> obtenerCedulasCandidatos() {
        open();
        ArrayList<String> cedulas = new ArrayList<>();
        cursor = db.rawQuery("select Cedula from Candidato", null);
        if (cursor.moveToFirst()) {
            do {
                cedulas.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        close();
        return cedulas;
    }
}
