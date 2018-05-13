package com.example.jham0.uvote;

import android.media.ImageReader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;



import com.example.jham0.uvote.Db.DbQuery;

public class Registro_Candidato extends AppCompatActivity {

    ////////////////////
    //Atributos
    ////////////////////
    /**Componentes**/
    private EditText etNombreCandidato;
    private EditText etCedulaCandidato;
    private EditText etPartidoCandidato;
    private Button btnRegisCandidato;
    private EditText etUrlImagen;
    private Button btnMostrarImagem;
    private ImageView imgURL;

    /**Variables**/
    DbQuery query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__candidato);
        connect();
        saveRecord();
        loadImage();
    }

    private void connect() {
        /**Componentes**/
        etNombreCandidato = findViewById(R.id.etNombreCandidato);
        etCedulaCandidato = findViewById(R.id.etCedulaCandidato);
        etPartidoCandidato = findViewById(R.id.etPartidoCandidato);
        btnRegisCandidato = findViewById(R.id.btnRegisCandidato);
        etUrlImagen = findViewById(R.id.etURL);
        btnMostrarImagem = findViewById(R.id.btnMostrarImagen);
        imgURL = findViewById(R.id.imgURL);
        /**Variables**/
        query = new DbQuery(getApplicationContext());
    }

    private void loadImage() {
        btnMostrarImagem.setOnClickListener(view -> {
            String URL = etUrlImagen.getText().toString();
            Glide.with(getApplicationContext()).load(URL).into(imgURL);
        });
    }

    private void saveRecord() {
        btnRegisCandidato.setOnClickListener(view -> {
            String nombre = etNombreCandidato.getText().toString();
            String cedula = etCedulaCandidato.getText().toString();
            String partido = etPartidoCandidato.getText().toString();
            String url = etUrlImagen.getText().toString();
            if(nombre.isEmpty() || cedula.isEmpty() || partido.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Registro erroneo", Toast.LENGTH_LONG).show();
            } else {
                try {
                    if (query.obtenerCedulasCandidatos().contains(etCedulaCandidato.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Candidato ya registrado", Toast.LENGTH_LONG).show();
                    } else {
                        query.insertarCandidato(nombre, cedula, partido, url);
                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error: " +
                            e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            clearFields();
        });
    }

    private void clearFields() {
        etNombreCandidato.setText("");
        etCedulaCandidato.setText("");
        etPartidoCandidato.setText("");
        etUrlImagen.setText("");
        imgURL.setImageResource(R.drawable.iconaapp);
    }
}