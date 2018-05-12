package com.example.jham0.uvote.CandidateItem;

import android.graphics.drawable.Drawable;

public class CandidateItem {

    private String nombre;
    private String cedula;
    private String partido;
    private String urlImagen;

    public CandidateItem() {
        super();
    }

    public CandidateItem(String cedula, String nombre, String partido, String urlImagen) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.partido = partido;
        this.urlImagen = urlImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
