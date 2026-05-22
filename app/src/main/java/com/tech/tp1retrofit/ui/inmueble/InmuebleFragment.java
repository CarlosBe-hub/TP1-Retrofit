package com.tech.tp1retrofit.ui.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.tech.tp1retrofit.data.model.Inmueble;
import com.tech.tp1retrofit.databinding.FragmentInmueblesBinding;

import java.util.ArrayList;
import java.util.List;

public class InmuebleFragment extends Fragment {
    private FragmentInmueblesBinding binding;
    private InmuebleAdapter inmuebleAdapter;
    private InmuebleViewModel inmuebleViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState ){
        inmuebleViewModel = new ViewModelProvider(this).get(InmuebleViewModel.class);
        binding = FragmentInmueblesBinding.inflate(inflater, container, false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.rvInmuebles.setLayoutManager(gridLayoutManager);

        inmuebleAdapter = new InmuebleAdapter();
        binding.rvInmuebles.setAdapter(inmuebleAdapter);

        inmuebleViewModel.getToastMessage().observe(getViewLifecycleOwner(), message -> {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });

        inmuebleViewModel.getInmuebles().observe(getViewLifecycleOwner(), listaInmuebles -> {
            inmuebleAdapter.setInmuebles(listaInmuebles);
        });

        binding.fabAgregarInmueble.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(com.tech.tp1retrofit.R.id.action_nav_inmuebles_to_agregarInmuebleFragment);
        });

        inmuebleViewModel.obtenerListaInmuebles();

        return binding.getRoot();
    }
}
