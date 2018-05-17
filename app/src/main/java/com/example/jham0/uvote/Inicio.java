package com.example.jham0.uvote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jham0.uvote.Db.DbQuery;
import com.example.jham0.uvote.Estadisticas.Estadisticas;
import com.example.jham0.uvote.Votaciones.Votacion;

import java.util.ArrayList;

public class Inicio extends AppCompatActivity {
    /**Componentes**/
    private TextView btnRegistrarCandidato, btnRegistrarVotante;
    private EditText etVerificarCedula;
    private ImageButton btnIrVotar,btnIrEstadisticas;
    private Button btnVerificarVotBlanco;
    /**Atributos**/
    DbQuery query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        connect();
        ir();

        btnVerificarVotBlanco.setOnClickListener(view -> {
            verVotoB();
        });
    }

    public void connect() {
        btnRegistrarCandidato = findViewById(R.id.tvIrRegistroCandidato);
        btnRegistrarVotante = findViewById(R.id.tvIrRegistroVotante);
        etVerificarCedula = findViewById(R.id.etVerificacionCedula);
        btnIrVotar = findViewById(R.id.btnIrVotar);
        btnIrEstadisticas = findViewById(R.id.btnIrEstadisticas);
        btnVerificarVotBlanco = findViewById(R.id.btnVerificarVotoBlanco);
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
                    if (query.verificarVotacion(etVerificarCedula.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Ya se ha registrado su voto " +
                                "anteriormente", Toast.LENGTH_LONG).show();
                        etVerificarCedula.setText("");
                    } else {
                        irVotacion();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Cédula no registrada", Toast.LENGTH_SHORT).show();
                    etVerificarCedula.setText("");
                }
            }
        });
        //TODO: Crear el barchart para mostrar los resultados
        btnIrEstadisticas.setOnClickListener(view -> {
            startActivity( new Intent(getApplicationContext(), Estadisticas.class));
        });
    }

    private void irVotacion() {
        Intent intent = new Intent(getApplicationContext(), Votacion.class);
        intent.putExtra("Cedula", etVerificarCedula.getText().toString());
        startActivity(intent);
        etVerificarCedula.setText("");
    }

    private boolean verificarCedula() {
        ArrayList<String> cedulas = query.obtenerCedulasVotante();
        return cedulas.contains(etVerificarCedula.getText().toString());
    }

    private void verVotoB() {
        if (query.mayorVotoEnBlanco()) {
            query.eliminarTablas();
            Toast.makeText(getApplicationContext(), "Voto en blanco con mas del 50%\nSe reeligen candidatos", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "El voto en blanco no es mayor al 50% de los votos", Toast.LENGTH_LONG).show();
        }
    }
}














