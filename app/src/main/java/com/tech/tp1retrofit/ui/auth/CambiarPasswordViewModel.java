package com.tech.tp1retrofit.ui.auth;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tech.tp1retrofit.data.local.SessionManager;
import com.tech.tp1retrofit.data.network.ApiClient;
import com.tech.tp1retrofit.data.network.service.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambiarPasswordViewModel extends AndroidViewModel {
    private final AuthService authService;
    private final SessionManager sessionManager;

    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> exitoNavegacion = new MutableLiveData<>();

    public CambiarPasswordViewModel(@NonNull Application application) {
        super(application);
        this.authService = ApiClient.getClient().create(AuthService.class);
        this.sessionManager = new SessionManager(application);
    }

    public LiveData<String> getToastMessage(){
        return toastMessage;
    }

    public LiveData<Boolean> getExitoNavegacion() {
        return exitoNavegacion;
    }

    public void cambiarPassword(String passActual, String passNuevo) {

        if (passActual.trim().isEmpty()) {
            toastMessage.setValue("La contraseña actual es obligatoria");
            return;
        }

        if (passNuevo.trim().isEmpty()) {
            toastMessage.setValue("La nueva contraseña es obligatoria");
            return;
        }

        if (passActual.equals(passNuevo)) {
            toastMessage.setValue("La nueva contraseña debe ser diferente a la actual");
            return;
        }

        String tokenParaUsar = sessionManager.obtenerToken();
        if (tokenParaUsar == null) {
            toastMessage.setValue("Error de sesión. Por favor, vuelva a iniciar sesión.");
            return;
        }

        authService.CambioContraseña(tokenParaUsar, passActual, passNuevo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    toastMessage.setValue("La contraseña se actualizó con éxito");
                    exitoNavegacion.setValue(true);
                } else {
                    toastMessage.setValue("Error al cambiar contraseña");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                toastMessage.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }
}