package com.example.jham0.uvote.Estadisticas;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.jham0.uvote.Db.DbQuery;
import com.example.jham0.uvote.R;
import com.example.jham0.uvote.Votaciones.Resultados;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Estadisticas extends AppCompatActivity {


    private List<Resultados> resultados;
    private LinearLayout linearLayout;
    private PieChart pieChart;
    private DbQuery query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        linearLayout = findViewById(R.id.results);
        pieChart = (PieChart) findViewById(R.id.pie);

        // Consigue resultados
        query = new DbQuery(getApplicationContext());
        resultados = query.obtenerResultados();

        drawResults();
    }

    /**
     * Dibuja los resultados en la activity
     */
    private void drawResults() {
        List<PieEntry> pieEntries = new ArrayList<>();

        for(Resultados resultados : resultados) {
            if (resultados.getVotos() != 0) {
                pieEntries.add(new PieEntry(
                        Float.parseFloat(resultados.getVotos() + ""),resultados.getCandidato()));
            }
        }
        PieDataSet set = new PieDataSet(pieEntries, "Candidatos");


        PieData data = new PieData(set);
        set.setValueTextSize(15f);
        int colors[] = new int[resultados.size()];
        Random random = new Random();
        for(int i = 0; i < colors.length; i++) {
            colors[i] = Color.rgb(random.nextInt(255),0,random.nextInt(255));
        }

        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.setDescription(null);
    }
}
