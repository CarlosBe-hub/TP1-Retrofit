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

public class LoginViewModel extends AndroidViewModel {
    private final AuthService authService;
    private final SessionManager sessionManager;
    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> errorResult = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.authService = ApiClient.getClient().create(AuthService.class);
        this.sessionManager = new SessionManager(application);
    }

    public LiveData<Boolean> getLoginSuccess() { return loginSuccess; }
    public LiveData<String> getErrorResult() { return errorResult; }

    public void login(String usuario, String clave) {
        if (usuario == null || usuario.isEmpty() || clave == null || clave.isEmpty()) {
            errorResult.setValue("Por favor, completá ambos campos");
            return;
        }

        authService.login(usuario, clave).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    sessionManager.guardarToken(response.body());

                    loginSuccess.setValue(true);
                } else {
                    errorResult.setValue("Credenciales incorrectas");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                errorResult.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }
}