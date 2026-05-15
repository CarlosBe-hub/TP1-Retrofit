package com.tech.tp1retrofit.ui.logout;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.tech.tp1retrofit.data.local.SessionManager;

public class LogoutViewModel extends AndroidViewModel {
    private SessionManager sessionManager;
    private MutableLiveData<Boolean> logueado = new MutableLiveData<>(true);

    public LogoutViewModel(@NonNull Application application) {
        super(application);
        sessionManager = new SessionManager(application.getApplicationContext());
    }

    public LiveData<Boolean> getLogueado() {
        return logueado;
    }

    public void cerrarSesion() {
        sessionManager.cerrarSesion();

        logueado.setValue(false);
    }
}