package com.tech.tp1retrofit.data.repository;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import com.tech.tp1retrofit.data.local.SessionManager;
import com.tech.tp1retrofit.data.model.Propietario;
import com.tech.tp1retrofit.data.network.ApiCallBack;
import com.tech.tp1retrofit.data.network.ApiClient;
import com.tech.tp1retrofit.data.network.service.PropietarioService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropietarioRepository {

    private PropietarioService propietarioService;
    private SessionManager sessionManager;

    public PropietarioRepository(Context context){
        propietarioService = ApiClient.getClient().create(PropietarioService.class);
        sessionManager = new SessionManager(context);
    }

    public void obtenerPerfil(ApiCallBack<Propietario> callBack){
        String token = sessionManager.obtenerToken();

        if(token == null){
            callBack.onError("No existe un token de autenticación");
            return;
        }

        propietarioService.obtenerPerfil(token).enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onError("Ocurrio un error al obtener el perfil");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                callBack.onError("Error de conexión: " + t.getMessage());
            }
        });
    }

    public void actualizarPerfil(Propietario propietarioActualizado, ApiCallBack<Propietario> callBack) {
        String token = sessionManager.obtenerToken();

        if(token == null){
            callBack.onError("No existe un token de autenticación");
            return;
        }

        propietarioService.actualizarPropietario(token, propietarioActualizado).enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onError("Ocurrió un error al actualizar el perfil");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                callBack.onError("Error de conexión: " + t.getMessage());
            }
        });
    }
}
