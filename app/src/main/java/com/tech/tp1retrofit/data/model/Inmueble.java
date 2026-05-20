package com.tech.tp1retrofit.data.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Inmueble implements Serializable {

    @SerializedName("idInmueble")
    private int id;
    private String direccion;
    private String uso;
    private String tipo;
    private int ambientes;
    private int superficie;

    @SerializedName("valor")
    private double precio;
    private String imagen;

    @SerializedName("disponible")
    private boolean estado;

    @SerializedName("idPropietario")
    private int propietarioId;

    @SerializedName("poseedor")
    private Propietario propietario;

    public Inmueble() {
    }

    public Inmueble(String direccion, int ambientes, boolean estado, int id, String imagen, double precio, Propietario propietario, int propietarioId, int superficie, String tipo, String uso) {
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.estado = estado;
        this.id = id;
        this.imagen = imagen;
        this.precio = precio;
        this.propietario = propietario;
        this.propietarioId = propietarioId;
        this.superficie = superficie;
        this.tipo = tipo;
        this.uso = uso;
    }

    public Inmueble(int ambientes, String direccion, boolean estado, String imagen, double precio, Propietario propietario, int propietarioId, int superficie, String tipo, String uso) {
        this.ambientes = ambientes;
        this.direccion = direccion;
        this.estado = estado;
        this.imagen = imagen;
        this.precio = precio;
        this.propietario = propietario;
        this.propietarioId = propietarioId;
        this.superficie = superficie;
        this.tipo = tipo;
        this.uso = uso;
    }


    public String getUso() { return uso; }
    public void setUso(String uso) { this.uso = uso; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getSuperficie() { return superficie; }
    public void setSuperficie(int superficie) { this.superficie = superficie; }

    public int getPropietarioId() { return propietarioId; }
    public void setPropietarioId(int propietarioId) { this.propietarioId = propietarioId; }

    public Propietario getPropietario() { return propietario; }
    public void setPropietario(Propietario propietario) { this.propietario = propietario; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getAmbientes() { return ambientes; }
    public void setAmbientes(int ambientes) { this.ambientes = ambientes; }
}