package com.tech.tp1retrofit.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tech.tp1retrofit.data.network.ApiCallBack;
import com.tech.tp1retrofit.data.repository.AuthRepository;

public class LoginViewModel extends ViewModel {

    private AuthRepository repository;

    private MutableLiveData<String> tokenResult = new MutableLiveData<>();
    private MutableLiveData<String> errorResult = new MutableLiveData<>();

    public LoginViewModel() {
        this.repository = new AuthRepository();
    }

    public LiveData<String> getTokenResult() { return tokenResult; }
    public LiveData<String> getErrorResult() { return errorResult; }

    public void login(String usuario, String clave) {

        // pase la logica del login activity al viewmodel asi queda prolijo 
        if (usuario == null || usuario.isEmpty() || clave == null || clave.isEmpty()) {
            errorResult.setValue("Por favor, completá ambos campos");
            return;
        }

        repository.login(usuario, clave, new ApiCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                // si Retrofit nos devuelve el token exitosamente, le avisamos a la Activity
                tokenResult.setValue(result);
            }

            @Override
            public void onError(String message) {
                // si la API tira error (ej: usuario no existe), mandamos el error a la Activity
                errorResult.setValue(message);
            }
        });
    }
}