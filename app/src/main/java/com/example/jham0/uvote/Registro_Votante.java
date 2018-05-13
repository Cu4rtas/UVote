package com.example.jham0.uvote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.jham0.uvote.Db.DbQuery;

import java.util.ArrayList;

public class Registro_Votante extends AppCompatActivity {

    /////////////////
    //Atributos
    ////////////////

    /**Componentes**/
    EditText etCedulaVotante;
    Button btnRegisVotante;
    /**Variables**/
    DbQuery query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__votante);
        connect();
        saveRecord();
    }

    /**
     * Conecta todos los compenentes de la actividad
     * e inicializa todas las variables
     */
    private void connect() {
        /**Componentes**/
        btnRegisVotante = findViewById(R.id.btnRegisVotante);
        etCedulaVotante = findViewById(R.id.etCedulaVotante);
        /**Variables**/
        query = new DbQuery(getApplicationContext());
    }

    private void saveRecord() {
        btnRegisVotante.setOnClickListener(view -> {
                if (etCedulaVotante.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ingrese cédula.", Toast.LENGTH_LONG).show();
                } else {
                    ArrayList<String> votantes = query.obtenerCedulasVotante();
                    if (votantes.contains(etCedulaVotante.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "La cédula ya esta registrada", Toast.LENGTH_LONG).show();
                    } else {
                        query.insertarVotante(etCedulaVotante.getText().toString());
                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                    }
                }
                etCedulaVotante.setText("");
        });
    }
}
