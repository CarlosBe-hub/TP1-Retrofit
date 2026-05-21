package com.tech.tp1retrofit.ui.inmueble;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tech.tp1retrofit.data.local.SessionManager;
import com.tech.tp1retrofit.data.model.Inmueble;
import com.tech.tp1retrofit.data.network.ApiClient;
import com.tech.tp1retrofit.data.network.service.InmuebleService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Inmueble>> inmuebleMutable = new MutableLiveData<>();
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();
    private final SessionManager sessionManager;
    private final InmuebleService inmuebleService;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);
        this.sessionManager = new SessionManager(application);
        this.inmuebleService = ApiClient.getClient().create(InmuebleService.class);

    }

    public LiveData<List<Inmueble>> getInmuebles(){
        return inmuebleMutable;
    }

    public LiveData<String> getToastMessage(){
        return toastMessage;
    }

    public void obtenerListaInmuebles(){
        String token = sessionManager.obtenerToken();

        if (token == null) {
            toastMessage.setValue("No existe un token de autenticación");
            return;
        }

        inmuebleService.obtenerInmuebles(token).enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    inmuebleMutable.setValue(response.body());
                }else{
                    toastMessage.setValue("Ocurrió un error al obtener la lista de inmuebles");
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                toastMessage.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }



}
