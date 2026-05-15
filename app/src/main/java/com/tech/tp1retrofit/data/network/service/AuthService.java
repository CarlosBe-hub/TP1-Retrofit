package com.tech.tp1retrofit.data.network.service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface AuthService {

    @FormUrlEncoded
    @POST("/api/Propietarios/login")
    Call<String> login(@Field("Usuario") String usuario, @Field("Clave") String clave);

    @FormUrlEncoded
    @PUT("/api/Propietarios/changePassword")
    Call<Void> CambioContraseña(@Header ("Authorization")String token,@Field("currentPassword") String ClaveActual, @Field("newPassword") String ClaveNueva);


}
