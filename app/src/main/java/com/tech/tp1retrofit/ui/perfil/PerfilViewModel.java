package com.tech.tp1retrofit.ui.perfil;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tech.tp1retrofit.data.model.Propietario;
import com.tech.tp1retrofit.data.network.ApiCallBack;
import com.tech.tp1retrofit.data.repository.PropietarioRepository;

public class PerfilViewModel extends AndroidViewModel {
    private final PropietarioRepository propietarioRepository;
    private final MutableLiveData<Propietario> propietario = new MutableLiveData<>();
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        propietarioRepository = new PropietarioRepository(application);
    }

    public LiveData<Propietario> getPropietario(){
        return propietario;
    }

    public LiveData<String> getToastMessage(){
        return toastMessage;
    }

    public void obtenerPerfil() {
        propietarioRepository.obtenerPerfil(new ApiCallBack<Propietario>() {
            @Override
            public void onSuccess(Propietario result) {
                propietario.setValue(result);
            }

            @Override
            public void onError(String message) {
                toastMessage.setValue(message);
            }
        });
    }

    public void actualizarPerfil(Propietario propietarioActualizado){

        if(propietarioActualizado.getNombre().trim().isEmpty()){
            toastMessage.setValue("EL nombre es obligatorio");
            return;
        }

        if(propietarioActualizado.getApellido().trim().isEmpty()){
            toastMessage.setValue("El apellido es obligatorio");
            return;
        }

        if(!propietarioActualizado.getEmail().contains("@")){
            toastMessage.setValue("El formato de email es invalido");
            return;
        }

        if(propietarioActualizado.getDni().trim().isEmpty()){
            toastMessage.setValue("El DNI es obligatorio");
            return;
        }

        if(propietarioActualizado.getTelefono().trim().isEmpty()){
            toastMessage.setValue("El telefono es obligatorio");
            return;
        }

        propietarioRepository.actualizarPerfil(propietarioActualizado, new ApiCallBack<Propietario>() {
            @Override
            public void onSuccess(Propietario result) {
                propietario.setValue(result);
                toastMessage.setValue("El perfil se actualizo con exito!");
            }

            @Override
            public void onError(String message) {
                toastMessage.setValue(message);
            }
        });
    }
}
