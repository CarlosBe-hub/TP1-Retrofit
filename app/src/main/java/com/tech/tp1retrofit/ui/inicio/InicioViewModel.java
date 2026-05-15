package com.tech.tp1retrofit.ui.inicio;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class InicioViewModel extends AndroidViewModel {
    private MutableLiveData<GeoPoint> mUbicacion = new MutableLiveData<>();
    private MutableLiveData<Marker> mMarker = new MutableLiveData<>();

    public InicioViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<GeoPoint> getUbicacion() {
        return mUbicacion;
    }

    public LiveData<Marker> getMarker() {
        return mMarker;
    }

    public void cargarMapa(MapView mapa, Context context) {

        GeoPoint SanLuis = new GeoPoint(-33.301, -66.337);
        mUbicacion.setValue(SanLuis);

        Marker marcador = new Marker(mapa);
        marcador.setPosition(SanLuis);
        marcador.setTitle("Inmobiliaria Central");
        marcador.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        mMarker.setValue(marcador);
    }
}