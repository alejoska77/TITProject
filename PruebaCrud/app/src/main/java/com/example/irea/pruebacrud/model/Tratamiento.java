package com.example.irea.pruebacrud.model;

public class Tratamiento {
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

    public Tratamiento() {
    }

    public Tratamiento(String id, int total) {
        this.id = id;
        this.total = total;
    }

    @Override
    public String toString()

    {
        if (total >22 && total<31){
            return "Usuario: " +" "+id  + " Mantenerse en el mismo estado";

        }
        if (total >16 && total<23){
            return "Usuario: " +" "+id + " Requiere 1 cita con el Psicologo";

        }

        if (total >10 && total<17){
            return "Usuario: " +" "+id + " Requiere 2 citas con el Psicologo";

        }
        if (total >6 && total<11){
            return "Usuario: " +" "+id + " Requiere de terapia con el Psicoterapeuta";

        }
        return null;
    }
}


