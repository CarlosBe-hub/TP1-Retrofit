package com.tech.tp1retrofit.data.network.service;

import com.tech.tp1retrofit.data.model.Propietario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface PropietarioService {

    @GET("/api/Propietarios")
    Call<Propietario> obtenerPerfil(@Header("Authorization") String token);

    @PUT("/api/Propietarios/actualizar")
    Call<Propietario> actualizarPropietario(@Header("Authorization") String token, @Body Propietario propietario);

}
