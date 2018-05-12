package com.example.jham0.uvote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jham0.uvote.Db.DbQuery;
import com.example.jham0.uvote.Votaciones.Votacion;

import java.util.ArrayList;

public class Inicio extends AppCompatActivity {
    /**Componentes**/
    private TextView btnRegistrarCandidato, btnRegistrarVotante;
    private EditText etVerificarCedula;
    private ImageButton btnIrVotar,btnIrEstadisticas;
    /**Atributos**/
    DbQuery query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        connect();
        ir();
    }

    public void connect() {
        btnRegistrarCandidato = findViewById(R.id.tvIrRegistroCandidato);
        btnRegistrarVotante = findViewById(R.id.tvIrRegistroVotante);
        etVerificarCedula = findViewById(R.id.etVerificacionCedula);
        btnIrVotar = findViewById(R.id.btnIrVotar);
        btnIrEstadisticas = findViewById(R.id.btnIrEstadisticas);
        /**Atributos**/
        query = new DbQuery(getApplicationContext());
    }

    public void ir() {
        btnRegistrarCandidato.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), Registro_Candidato.class);
            startActivity(i);
        });

        btnRegistrarVotante.setOnClickListener(view ->{
            Intent i = new Intent(getApplicationContext(), Registro_Votante.class);
            startActivity(i);
        });

        btnIrVotar.setOnClickListener(view-> {
            if (etVerificarCedula.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Ingrese cédula", Toast.LENGTH_SHORT).show();
            } else {
                if (verificarCedula()) {
                    Intent intent = new Intent(getApplicationContext(), Votacion.class);
                    intent.putExtra("Cedula", etVerificarCedula.getText().toString());
                    startActivity(intent);
                    etVerificarCedula.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Cédula no registrada", Toast.LENGTH_SHORT).show();
                    etVerificarCedula.setText("");
                }
            }
        });
        //TODO: Crear el barchart para mostrar los resultados
        btnIrEstadisticas.setOnClickListener(view -> {

        });
    }

    private boolean verificarCedula() {
        ArrayList<String> cedulas = query.obtenerCedulasVotante();
        return cedulas.contains(etVerificarCedula.getText().toString());
    }
}














