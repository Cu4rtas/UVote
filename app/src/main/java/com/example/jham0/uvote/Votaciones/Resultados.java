package com.example.jham0.uvote.Votaciones;

public class Resultados {

    private String candidato;
    private int votos;

    public Resultados(String candidato, int votos) {
        this.candidato = candidato;
        this.votos = votos;
    }

    public String getCandidato() {
        return candidato;
    }

    public void setCandidato(String candidato) {
        this.candidato = candidato;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }
}
