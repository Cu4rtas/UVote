package com.example.jham0.uvote.Votaciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.jham0.uvote.CandidateItem.AdapterItem;
import com.example.jham0.uvote.CandidateItem.CandidateItem;
import com.example.jham0.uvote.Db.DbQuery;
import com.example.jham0.uvote.R;

import java.util.ArrayList;


public class Votacion extends AppCompatActivity {
    /**Componentes**/
    private ListView lvCanditatos;
    /**Atributos**/
    ArrayList<CandidateItem> candidatos;
    AdapterItem adapterCandidate;
    DbQuery query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacion);
        connect();
    }

    private void connect() {
        /**Componentes**/
        lvCanditatos = findViewById(R.id.lvCandidatos);
        /**Atributos**/
        query = new DbQuery(getApplicationContext());
        candidatos = query.obtenerCandidatos();
        llenarAdaptador();
    }

    private void llenarAdaptador() {
        adapterCandidate = new AdapterItem(getApplicationContext(), this, candidatos);
        lvCanditatos.setAdapter(adapterCandidate);
    }
}
