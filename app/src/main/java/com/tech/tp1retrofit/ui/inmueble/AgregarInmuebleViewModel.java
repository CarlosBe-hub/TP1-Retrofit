package com.tech.tp1retrofit.ui.inmueble;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.tech.tp1retrofit.data.local.SessionManager;
import com.tech.tp1retrofit.data.model.Inmueble;
import com.tech.tp1retrofit.data.network.ApiClient;
import com.tech.tp1retrofit.data.network.service.InmuebleService;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarInmuebleViewModel extends AndroidViewModel {

    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> operacionExitosa = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private final SessionManager sessionManager;
    private final InmuebleService inmuebleService;

    public AgregarInmuebleViewModel(@NonNull Application application) {
        super(application);
        this.sessionManager = new SessionManager(application);
        this.inmuebleService = ApiClient.getClient().create(InmuebleService.class);
    }

    public LiveData<String> getToastMessage() { return toastMessage; }
    public LiveData<Boolean> getOperacionExitosa() { return operacionExitosa; }
    public LiveData<Boolean> getIsLoading() { return isLoading; }


    public void guardarInmueble(String direccion, String uso, String tipo, String ambientes, String precio, Uri uriFoto) {
        if (direccion.isEmpty() || uso.isEmpty() || tipo.isEmpty() || ambientes.isEmpty() || precio.isEmpty()) {
            toastMessage.setValue("Por favor, complete todos los campos de texto");
            return;
        }

        if (uriFoto == null) {
            toastMessage.setValue("Debe seleccionar una foto de la galería!");
            return;
        }

        isLoading.setValue(true);

        try {
            Inmueble nuevoInmueble = new Inmueble();
            nuevoInmueble.setDireccion(direccion);
            nuevoInmueble.setUso(uso);
            nuevoInmueble.setTipo(tipo);
            nuevoInmueble.setAmbientes(Integer.parseInt(ambientes));
            nuevoInmueble.setPrecio(Double.parseDouble(precio));
            nuevoInmueble.setEstado(true);

            byte[] imagenBytes = convertirUriABytes(uriFoto);

            if (imagenBytes == null) {
                toastMessage.setValue("Error al procesar la imagen");
                isLoading.setValue(false);
                return;
            }

            prepararYEnviarPeticion(nuevoInmueble, imagenBytes);

        } catch (NumberFormatException e) {
            toastMessage.setValue("Los ambientes y el precio deben ser valores numéricos");
            isLoading.setValue(false);
        }
    }

    private byte[] convertirUriABytes(Uri uri) {
        try {
            InputStream inputStream = getApplication().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    private void prepararYEnviarPeticion(Inmueble inmueble, byte[] imagenBytes) {
        String token = sessionManager.obtenerToken();
        if (token == null) {
            toastMessage.setValue("Error de autenticación");
            isLoading.setValue(false);
            return;
        }
        if (!token.startsWith("Bearer ")) {
            token = "Bearer " + token;
        }

        String inmuebleJson = new Gson().toJson(inmueble);
        RequestBody inmuebleBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), inmuebleJson);

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imagenBytes);
        MultipartBody.Part imagenPart = MultipartBody.Part.createFormData("imagen", "foto_inmueble.jpg", requestFile);

        inmuebleService.cargarInmueble(token, imagenPart, inmuebleBody).enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                isLoading.setValue(false);
                if (response.isSuccessful()) {
                    toastMessage.setValue("Inmueble publicado con éxito");
                    operacionExitosa.setValue(true);
                    operacionExitosa.postValue(null);
                } else {
                    toastMessage.setValue("Error al guardar en el servidor: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                isLoading.setValue(false);
                toastMessage.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }
}