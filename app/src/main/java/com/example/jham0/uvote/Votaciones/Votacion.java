package com.example.jham0.uvote.Votaciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jham0.uvote.CandidateItem.AdapterItem;
import com.example.jham0.uvote.CandidateItem.CandidateItem;
import com.example.jham0.uvote.Db.DbQuery;
import com.example.jham0.uvote.R;
import java.util.ArrayList;


public class Votacion extends AppCompatActivity {
    /**Componentes**/
    private ListView lvCanditatos;
    private ImageView ivChecked;
    private TextView tvMensajeVotacion;
    /**Atributos**/
    ArrayList<CandidateItem> candidatos;
    AdapterItem adapterCandidate;
    DbQuery query;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacion);
        connect();
        preparar();
        votar();
    }

    private void connect() {
        /**Componentes**/
        lvCanditatos = findViewById(R.id.lvCandidatos);
        ivChecked = findViewById(R.id.ivChecked);
        tvMensajeVotacion = findViewById(R.id.tvMensajeVotacion);

        /**Atributos**/
        query = new DbQuery(getApplicationContext());
        candidatos = query.obtenerCandidatos();
        bundle = getIntent().getExtras();

        llenarAdaptador();
    }

    private void llenarAdaptador() {
        adapterCandidate = new AdapterItem(getApplicationContext(), this, candidatos);
        lvCanditatos.setAdapter(adapterCandidate);
    }

    private void votar() {
        lvCanditatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                query.actualizarVoto(candidatos.get(position).getCedula(), bundle.getString("Cedula"));
                lvCanditatos.setVisibility(View.INVISIBLE);
                ivChecked.setVisibility(View.VISIBLE);
                tvMensajeVotacion.setText("Se ha registrado su voto");
                Toast.makeText(getApplicationContext(),"HA VOTADO!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void preparar() {
        lvCanditatos.setVisibility(View.VISIBLE);
        ivChecked.setVisibility(View.INVISIBLE);
        tvMensajeVotacion.setText("Seleccione Candidato");
    }
}
