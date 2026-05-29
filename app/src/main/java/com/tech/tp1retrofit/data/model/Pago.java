package com.tech.tp1retrofit.data.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Pago implements Serializable {
    @SerializedName("idPago")
    private int id;
    private String fechaPago;
    @SerializedName("monto")
    private double importe;
    private String detalle;
    private boolean estado;
    private int idContrato;
    private Contrato contrato;

    public Pago() {}

    public Pago(int id, String fechaPago, double importe, String detalle, boolean estado, int idContrato, Contrato contrato) {
        this.id = id;
        this.fechaPago = fechaPago;
        this.importe = importe;
        this.detalle = detalle;
        this.estado = estado;
        this.idContrato = idContrato;
        this.contrato = contrato;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFechaPago() { return fechaPago; }
    public void setFechaPago(String fechaPago) { this.fechaPago = fechaPago; }

    public double getImporte() { return importe; }
    public void setImporte(double importe) { this.importe = importe; }

    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public int getIdContrato() { return idContrato; }
    public void setIdContrato(int idContrato) { this.idContrato = idContrato; }

    public Contrato getContrato() { return contrato; }
    public void setContrato(Contrato contrato) { this.contrato = contrato; }
}