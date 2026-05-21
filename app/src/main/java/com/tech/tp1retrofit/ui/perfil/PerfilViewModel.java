package com.tech.tp1retrofit.ui.perfil;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tech.tp1retrofit.data.local.SessionManager;
import com.tech.tp1retrofit.data.model.Propietario;
import com.tech.tp1retrofit.data.network.ApiClient;
import com.tech.tp1retrofit.data.network.service.PropietarioService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private final PropietarioService propietarioService;
    private final SessionManager sessionManager;
    private final MutableLiveData<Propietario> propietarioMutable = new MutableLiveData<>();
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        this.propietarioService = ApiClient.getClient().create(PropietarioService.class);
        this.sessionManager = new SessionManager(application);
    }

    public LiveData<Propietario> getPropietario(){
        return propietarioMutable;
    }

    public LiveData<String> getToastMessage(){
        return toastMessage;
    }

    public void obtenerPerfil() {
        String token = sessionManager.obtenerToken();

        if (token == null) {
            toastMessage.setValue("No existe un token de autenticación");
            return;
        }

        propietarioService.obtenerPerfil(token).enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    propietarioMutable.setValue(response.body());
                } else {
                    toastMessage.setValue("Ocurrió un error al obtener el perfil");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                toastMessage.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }

    public void actualizarPerfil(Propietario propietarioActualizado){
        if(propietarioActualizado.getNombre().trim().isEmpty()){
            toastMessage.setValue("El nombre es obligatorio");
            return;
        }

        if(propietarioActualizado.getApellido().trim().isEmpty()){
            toastMessage.setValue("El apellido es obligatorio");
            return;
        }

        if(!propietarioActualizado.getEmail().contains("@")){
            toastMessage.setValue("El formato de email es inválido");
            return;
        }

        if(propietarioActualizado.getDni().trim().isEmpty()){
            toastMessage.setValue("El DNI es obligatorio");
            return;
        }

        if(propietarioActualizado.getTelefono().trim().isEmpty()){
            toastMessage.setValue("El teléfono es obligatorio");
            return;
        }

        String token = sessionManager.obtenerToken();

        if (token == null) {
            toastMessage.setValue("No existe un token de autenticación");
            return;
        }

        propietarioService.actualizarPropietario(token, propietarioActualizado).enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()) {
                    propietarioMutable.setValue(response.body());
                    toastMessage.setValue("El perfil se actualizó con éxito!");
                } else {
                    toastMessage.setValue("Ocurrió un error al actualizar el perfil");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                toastMessage.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }
}
