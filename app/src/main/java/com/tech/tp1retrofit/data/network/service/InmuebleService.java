package com.tech.tp1retrofit.data.network.service;

import com.tech.tp1retrofit.data.model.Inmueble;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface InmuebleService {

    @GET("/api/Inmuebles")
    Call<List<Inmueble>> obtenerInmuebles(@Header("Authorization") String token);

    @PUT("/api/Inmuebles/actualizar")
    Call<Inmueble> cambiarDisponibilidad(
            @Header("Authorization") String token,
            @Body Inmueble inmueble
    );

    @Multipart
    @POST("/api/Inmuebles/cargar")
    Call<Inmueble> cargarInmueble(
            @Header("Authorization") String token,
            @Part MultipartBody.Part imagen,
            @Part("inmueble") RequestBody inmuebleBody
    );
}