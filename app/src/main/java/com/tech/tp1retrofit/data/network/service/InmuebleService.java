package com.tech.tp1retrofit.data.network.service;

import com.tech.tp1retrofit.data.model.Inmueble;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface InmuebleService {

    @GET("/api/Inmuebles")
    Call<List<Inmueble>> obtenerInmuebles(@Header("Authorization") String token);

}