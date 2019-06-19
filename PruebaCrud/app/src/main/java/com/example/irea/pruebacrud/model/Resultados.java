package com.example.irea.pruebacrud.model;

public class Resultados {
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

    public Resultados() {
    }

    public Resultados(String id, int total) {
        this.id = id;
        this.total = total;
    }
    
    @Override
    public String toString()

    {
        if (total >22 && total<31){
        return "Usuario: " +" "+id +"  total : " + total + " Depresión minima";

        }
        if (total >16 && total<23){
            return "Usuario: " +" "+id +"  total : " + total + " Depresión Leve";

        }

        if (total >10 && total<17){
            return "Usuario: " +" "+id +"  total : " + total + " Depresión Moderada";

        }
        if (total >6 && total<11){
            return "Usuario: " +" "+id +"  total : " + total + " Depresión Grave";

        }
            return null;
    }
}
