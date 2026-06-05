package com.tech.tp1retrofit.ui.inquilino;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tech.tp1retrofit.data.local.SessionManager;
import com.tech.tp1retrofit.data.model.Inmueble;
import com.tech.tp1retrofit.data.network.ApiClient;
import com.tech.tp1retrofit.data.network.service.InmuebleService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Inmueble>> inmueblesMutable = new MutableLiveData<>();
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();
    private final SessionManager sessionManager;
    private final InmuebleService inmuebleService;

    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        this.sessionManager = new SessionManager(application);
        this.inmuebleService = ApiClient.getClient().create(InmuebleService.class);
    }

    public LiveData<List<Inmueble>> getInmueblesMutable() {
        return inmueblesMutable;
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public void obtenerInmueblesAlquilados() {
        String token = sessionManager.obtenerToken();

        if (token == null) {
            toastMessage.setValue("No existe un token de autenticación");
            return;
        }

        inmuebleService.obtenerInmueblesAlquilados(token).enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()) {
                    inmueblesMutable.setValue(response.body());
                } else {
                    toastMessage.setValue("Ocurrió un error al obtener los inmuebles alquilados");
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                toastMessage.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }
}