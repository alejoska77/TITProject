package com.example.irea.pruebacrud.model;

public class Emociones {    private String id;
    private String descripcion;
    private String puntuacion;
    private String user;

//    public int[] puntajeto= new int[6];

    private int total;

    public Emociones() {
    }

    public Emociones(String id, String descripcion, String puntuacion, String user, int total, String pregunta) {
        this.id = id;
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
        this.total=total;
        this.user = user;
        this.total=total;
        this.pregunta=pregunta;
    }




    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    private String pregunta;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }








    @Override
    public String toString() {
        return "Usuario: " +" "+id +"  Puntuación: "+ puntuacion + " "+"\n"+"  Pregunta ?"+ pregunta +" "+"\n"+"  Descripción: "+ descripcion;
    }




}

