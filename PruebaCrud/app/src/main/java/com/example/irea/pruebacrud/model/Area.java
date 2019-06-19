package com.example.irea.pruebacrud.model;

public class Area {
    private String UidNivel;
    private String NombreNivel;
    private String DescripcionNivel;


    public Area(String uidNivel, String nombreNivel, String descripcionNivel) {
        this.UidNivel = uidNivel;
        NombreNivel = nombreNivel;
        DescripcionNivel = descripcionNivel;
    }

    public Area() {
    }

    public String getUidNivel() {
        return UidNivel;
    }

    public void setUidNivel(String uidNivel) {
        this.UidNivel = uidNivel;
    }

    public String getNombreNivel() {
        return NombreNivel;
    }

    public void setNombreNivel(String nombreNivel) {
        NombreNivel = nombreNivel;
    }

    public String getDescripcionNivel() {
        return DescripcionNivel;
    }

    public void setDescripcionNivel(String descripcionNivel) {
        DescripcionNivel = descripcionNivel;
    }




    @Override
    public String toString() {
        return NombreNivel;
    }
}
