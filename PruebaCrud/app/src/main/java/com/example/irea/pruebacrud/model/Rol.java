package com.example.irea.pruebacrud.model;

public class Rol {
    private String UidRol;
    private String NombreRol;
    private String DescripcionRol;

    public Rol(String uidRol, String nombreRol, String descripcionRol) {
        this.UidRol = uidRol;
        NombreRol = nombreRol;
        DescripcionRol = descripcionRol;
    }

    public Rol() {
    }

    public String getUidRol() {
        return UidRol;
    }

    public void setUidRol(String uidRol) {
        this.UidRol = uidRol;
    }

    public String getNombreRol() {
        return NombreRol;
    }

    public void setNombreRol(String nombreRol) {
        NombreRol = nombreRol;
    }

    public String getDescripcionRol() {
        return DescripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        DescripcionRol = descripcionRol;
    }



    @Override
    public String toString() {
        return NombreRol;
    }
}

