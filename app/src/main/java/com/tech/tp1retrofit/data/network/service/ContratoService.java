package com.tech.tp1retrofit.data.network.service;

import com.tech.tp1retrofit.data.model.Contrato;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ContratoService {
    @GET("/api/contratos/inmueble/{id}")
    Call<Contrato> obtenerContrato(@Path("id") int id, @Header("Authorization") String token);
}
