package com.tech.tp1retrofit.data.repository;

import com.tech.tp1retrofit.data.network.ApiCallBack;
import com.tech.tp1retrofit.data.network.ApiClient;
import com.tech.tp1retrofit.data.network.service.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthRepository {
    private AuthService authService;

    public AuthRepository(){
        authService = ApiClient.getClient().create(AuthService.class);
    }

    public void login(String user, String password, ApiCallBack<String> callback){
        authService.login(user, password).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }else{
                    callback.onError("Credenciales incorrectas");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onError("Error de conexión: " + t.getMessage());
            }
        });
    }

    public void CambiarPass(String token,String passwordActual, String passwordNuevo, ApiCallBack<String> callBack){
       authService.CambioContraseña(token, passwordActual, passwordNuevo).enqueue(new Callback<Void>() {
           @Override
           public void onResponse(Call<Void> call, Response<Void> response) {
               if (response.isSuccessful()) {
                   callBack.onSuccess("Constraseña cambiada correctamente..");
               } else {
                   callBack.onError("Error al cambiar contraseña");
               }
           }

           @Override
           public void onFailure(Call<Void> call, Throwable t) {
               callBack.onError("Error de conexion" + t.getMessage());

           }
       });

    }
}
