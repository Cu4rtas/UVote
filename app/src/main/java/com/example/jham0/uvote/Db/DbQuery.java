package com.example.jham0.uvote.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jham0.uvote.CandidateItem.AdapterItem;
import com.example.jham0.uvote.CandidateItem.CandidateItem;
import com.example.jham0.uvote.Votaciones.Resultados;

import java.util.ArrayList;
import java.util.List;

import static com.example.jham0.uvote.Db.DbHelper.TABLA_CANDIDATO;
import static com.example.jham0.uvote.Db.DbHelper.TABLA_VOTANTES;

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
        cv.put("Votos", 0);
        cv.put("Url", url);
        db.insert("Candidato", null, cv);
        close();
    }

    public void insertarVotante(String cedula) {
        open();
        cv.put("Cedula", cedula);
        cv.put("Voto", 0);
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

    /**
     * Se encarga de actualizar los campos de las tablas Candidato
     * y Votante
     * @param cedulaCandidato Representa la cedula del candidato
     * @param cedulaVotante Representa la cedula del votante
     */
    public void actualizarVoto(String cedulaCandidato, String cedulaVotante) {
        open();
        db.execSQL("update Candidato set Votos = Votos + 1 where Cedula = " + cedulaCandidato);
        db.execSQL("update Votante set Voto = '1' where Cedula = " + cedulaVotante);
        close();
    }

    public boolean verificarVotacion(String cedulaVotante) {
        open();
        Boolean voto = null;
        cursor = db.rawQuery("select Voto from Votante where Cedula = " + cedulaVotante, null);
        if (cursor.moveToFirst()) {
            voto = cursor.getInt(cursor.getColumnIndex("Voto")) == 1;
        }
        close();
        return voto;
    }

    public ArrayList<Resultados> obtenerResultados() {
        ArrayList<Resultados> resultados = new ArrayList<>();
        open();
        String columns[] = { "Nombre","Votos" };
        cursor = db.query("Candidato",columns,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                resultados.add( new Resultados(cursor.getString(0),cursor.getInt(1)));
            } while (cursor.moveToNext());
        }
        close();
        return resultados;
    }

    private int totalVotos() {
        open();
        int total = 0;
        cursor = db.rawQuery("select * from Votante where Voto = 1",null);
        if (cursor.moveToFirst()) {
            do {
                total += 1;
            }while (cursor.moveToNext());
        }
        close();
        return total;
    }

    /**
     * Determina si el 'Voto en Blanco'
     * tiene mas del 50% de los votos.
     */
    public boolean mayorVotoEnBlanco() {
        return (obtenerResultados().get(0).getVotos() > totalVotos()/2);
    }

    public void eliminarTablas() {
        open();
        db.execSQL("drop table if exists Votante");
        db.execSQL("drop table if exists Candidato");
        db.execSQL(TABLA_VOTANTES);
        db.execSQL(TABLA_CANDIDATO);
        db.execSQL("insert into Candidato values('Voto en Blanco','0',' ','0','#fff')");
        close();
    }
}
