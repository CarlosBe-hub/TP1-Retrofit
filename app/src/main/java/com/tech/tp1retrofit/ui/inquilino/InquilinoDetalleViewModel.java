package com.tech.tp1retrofit.ui.inquilino;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tech.tp1retrofit.data.local.SessionManager;
import com.tech.tp1retrofit.data.model.Contrato;
import com.tech.tp1retrofit.data.model.Inmueble;
import com.tech.tp1retrofit.data.model.Inquilino;
import com.tech.tp1retrofit.data.network.ApiClient;
import com.tech.tp1retrofit.data.network.service.ContratoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoDetalleViewModel extends AndroidViewModel {

    private final MutableLiveData<Inquilino> inquilinoMutable = new MutableLiveData<>();
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();
    private final SessionManager sessionManager;
    private final ContratoService contratoService;

    public InquilinoDetalleViewModel(@NonNull Application application) {
        super(application);
        this.sessionManager = new SessionManager(application);
        this.contratoService = ApiClient.getClient().create(ContratoService.class);
    }

    public LiveData<Inquilino> getInquilinoMutable() {
        return inquilinoMutable;
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public void procesarArgumentos(Bundle arguments) {
        if (arguments != null) {
            Inmueble inmueble = (Inmueble) arguments.getSerializable("inmueble");
            if (inmueble != null) {
                obtenerContratoPorInmueble(inmueble.getId());
            } else {
                toastMessage.setValue("Error: No se recibió la información del inmueble");
            }
        }
    }

    private void obtenerContratoPorInmueble(int idInmueble) {
        String token = sessionManager.obtenerToken();

        if (token == null) {
            toastMessage.setValue("No existe un token de autenticación");
            return;
        }

        contratoService.obtenerContratoPorInmueble(token, idInmueble).enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Contrato contrato = response.body();
                    if (contrato.getInquilino() != null) {
                        inquilinoMutable.setValue(contrato.getInquilino());
                    } else {
                        toastMessage.setValue("Este contrato no tiene un inquilino asignado");
                    }
                } else {
                    toastMessage.setValue("No se pudo obtener el contrato del servidor");
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                toastMessage.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }
}