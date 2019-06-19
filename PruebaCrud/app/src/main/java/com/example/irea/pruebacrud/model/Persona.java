package com.example.irea.pruebacrud.model;

public class Persona {

    private String uid;
    private String Nombre;
    private String Apellido;
    private String Email;
    private String Password;
    private String Rol;



    public Persona(String uid, String nombre, String apellido, String email, String password, String rol) {
        this.uid = uid;
        Nombre = nombre;
        Apellido = apellido;
        Email = email;
        Password = password;
        Rol = rol;
    }

    public Persona() {
    }


    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        Rol = rol;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
    @Override
    public String toString() {
        return "Usuario: "+ Nombre +" "+"\n"+"Rol: "+ Rol;
    }
}
