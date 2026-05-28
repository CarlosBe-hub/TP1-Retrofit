package com.tech.tp1retrofit.data.model;

public class Contrato {
    private int idContrato;
    private String fechaInicio;
    private String fechaFinalizacion;
    private float montoAlquiler;
    private Inquilino inquilino;
    private Inmueble inmueble;

    public Contrato(int idContrato, String fechaInicio, float montoAlquiler, String fechaFinalizacion, Inquilino inquilino, Inmueble inmueble) {
        this.idContrato = idContrato;
        this.fechaInicio = fechaInicio;
        this.montoAlquiler = montoAlquiler;
        this.fechaFinalizacion = fechaFinalizacion;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public float getMontoAlquiler() {
        return montoAlquiler;
    }

    public void setMontoAlquiler(float montoAlquiler) {
        this.montoAlquiler = montoAlquiler;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }
}
