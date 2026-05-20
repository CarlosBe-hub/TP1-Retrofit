package com.tech.tp1retrofit.ui.auth;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tech.tp1retrofit.data.local.SessionManager;
import com.tech.tp1retrofit.data.network.ApiCallBack;
import com.tech.tp1retrofit.data.repository.AuthRepository;

public class CambiarPasswordViewModel extends AndroidViewModel {

    private final AuthRepository authRepository;
    private final SessionManager sessionManager;
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();

    private final MutableLiveData<Boolean> exitoNavegacion = new MutableLiveData<>(); // Usamos este LiveData extra para avisarle al Fragment cuando volver a la pantalla anterior

    public CambiarPasswordViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository();
        sessionManager = new SessionManager(application);
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

        authRepository.CambiarPass(tokenParaUsar, passActual, passNuevo, new ApiCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                toastMessage.setValue("La contraseña se actualizó con éxito");
                exitoNavegacion.setValue(true);
            }

            @Override
            public void onError(String message) {
                toastMessage.setValue(message);
            }
        });
    }
}
