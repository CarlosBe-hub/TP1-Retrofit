package com.tech.tp1retrofit.ui.inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.tech.tp1retrofit.databinding.FragmentInicioBinding;
import org.osmdroid.config.Configuration;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;
    private InicioViewModel inicioViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Configuration.getInstance().setUserAgentValue(requireContext().getPackageName());
        binding = FragmentInicioBinding.inflate(inflater, container, false);
        inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);

        binding.mapaInicio.setMultiTouchControls(true);
        binding.mapaInicio.getController().setZoom(16.0);

        inicioViewModel.getUbicacion().observe(getViewLifecycleOwner(), geoPoint -> {
            binding.mapaInicio.getController().setCenter(geoPoint);
        });

        inicioViewModel.getMarker().observe(getViewLifecycleOwner(), marker -> {
            binding.mapaInicio.getOverlays().clear();
            binding.mapaInicio.getOverlays().add(marker);
            binding.mapaInicio.invalidate(); // Refresca el mapa
        });

        // Le pedimos al ViewModel que procese la logica
        inicioViewModel.cargarMapa(binding.mapaInicio, requireContext());

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (binding != null) binding.mapaInicio.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (binding != null) binding.mapaInicio.onPause();
    }
}