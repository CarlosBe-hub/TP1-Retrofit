package com.tech.tp1retrofit.ui.pago;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tech.tp1retrofit.data.local.SessionManager;
import com.tech.tp1retrofit.data.model.Pago;
import com.tech.tp1retrofit.data.network.ApiClient;
import com.tech.tp1retrofit.data.network.service.PagoService;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Pago>> pagosMutable = new MutableLiveData<>();
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();
    private final MutableLiveData<Integer> navegarAPagosEvent = new MutableLiveData<>();

    private final SessionManager sessionManager;
    private final PagoService pagoService;

    public PagoViewModel(@NonNull Application application) {
        super(application);
        this.sessionManager = new SessionManager(application);
        this.pagoService = ApiClient.getClient().create(PagoService.class);
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public LiveData<List<Pago>> getPagos() {
        return pagosMutable;
    }

    public LiveData<Integer> getNavegarAPagosEvent() {
        return navegarAPagosEvent;
    }

    public void obtenerPagos(int idContrato) {
        if (pagosMutable.getValue() != null) {
            return;
        }

        String token = getAuthToken();
        if (token == null) return;

        pagoService.obtenerPagosPorContrato(token, idContrato).enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pagosMutable.setValue(response.body());
                } else {
                    toastMessage.setValue("Error al obtener pagos");
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                toastMessage.setValue("Error de conexión");
            }
        });
    }

    public void verificarPagos(int idContrato) {
        String token = getAuthToken();
        if (token == null) return;

        pagoService.obtenerPagosPorContrato(token, idContrato).enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    navegarAPagosEvent.setValue(idContrato);
                } else {
                    toastMessage.setValue("No existen pagos registrados para este contrato");
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                toastMessage.setValue("Error de conexión");
            }
        });
    }

    public void limpiarNavegacion() {
        navegarAPagosEvent.setValue(null);
    }

    private String getAuthToken() {
        String token = sessionManager.obtenerToken();
        if (token == null) {
            toastMessage.setValue("No existe un token de autenticación");
            return null;
        }
        return token.startsWith("Bearer ") ? token : "Bearer " + token;
    }
}