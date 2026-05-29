package com.tech.tp1retrofit.ui.contrato;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tech.tp1retrofit.data.local.SessionManager;
import com.tech.tp1retrofit.data.model.Contrato;
import com.tech.tp1retrofit.data.network.ApiClient;
import com.tech.tp1retrofit.data.network.service.ContratoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {

    private final MutableLiveData<Contrato> contratoMutable = new MutableLiveData<>();
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();
    private final SessionManager sessionManager;
    private final ContratoService contratoService;

    public ContratoViewModel(@NonNull Application application) {
        super(application);
        this.sessionManager = new SessionManager(application);
        this.contratoService = ApiClient.getClient().create(ContratoService.class);
    }

    public LiveData<String> getToastMessage(){
        return toastMessage;
    }

    public LiveData<Contrato> getContratos(){
        return contratoMutable;
    }

    public void obtenerContratos(int idInmueble){
        String token = sessionManager.obtenerToken();

        if (token == null) {
            toastMessage.setValue("No existe un token de autenticación");
            return;
        }

        contratoService.obtenerContrato(idInmueble, token).enqueue(new Callback<Contrato>() {

            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if(response.isSuccessful() && response.body() != null) {
                    contratoMutable.setValue(response.body());
                } else if (response.code() == 404) {
                    limpiarContrato();
                    toastMessage.setValue("No existe un contrato para este inmueble");
                } else {
                    toastMessage.setValue("Ocurrio un error al obtener un contrato");
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                toastMessage.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }

    public void limpiarContrato() {
        contratoMutable.setValue(null);
    }

    public Integer getIdContratoActual() {
        Contrato c = contratoMutable.getValue();
        return (c != null) ? c.getIdContrato() : null;
    }
}