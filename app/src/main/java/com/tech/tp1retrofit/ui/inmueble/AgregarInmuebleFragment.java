package com.tech.tp1retrofit.ui.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.tech.tp1retrofit.databinding.FragmentAgregarInmuebleBinding;

public class AgregarInmuebleFragment extends Fragment {

    private FragmentAgregarInmuebleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAgregarInmuebleBinding.inflate(inflater, container, false);

        // prueba para ver como se muestra
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}