package com.example.irea.pruebacrud.model;

public class Resultados_Emociones {
    private String id;

    private int total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Resultados_Emociones() {
    }

    public Resultados_Emociones(String id, int total) {
        this.id = id;
        this.total = total;
    }

    @Override
    public String toString()

    {

            return "Usuario: " +" "+id +"  total : " + total + " de 7" ;



    }
}
