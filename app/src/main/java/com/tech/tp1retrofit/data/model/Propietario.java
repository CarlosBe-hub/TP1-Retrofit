package com.tech.tp1retrofit.data.model;

public class Propietario {

    private int idPropietario;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;

    public Propietario(int idPropietario, String nombre, String dni, String apellido, String telefono, String email) {
        this.idPropietario = idPropietario;
        this.nombre = nombre;
        this.dni = dni;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    public Propietario(String nombre, String dni, String apellido, String telefono, String email) {
        this.nombre = nombre;
        this.dni = dni;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    public Propietario() {
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
