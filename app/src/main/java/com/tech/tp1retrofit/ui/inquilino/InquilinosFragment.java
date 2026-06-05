package com.tech.tp1retrofit.ui.inquilino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.tech.tp1retrofit.databinding.FragmentInquilinoBinding;
import com.tech.tp1retrofit.ui.inmueble.InmuebleAdapter;

public class InquilinosFragment extends Fragment {

    private FragmentInquilinoBinding binding;
    private InmuebleAdapter inmuebleAdapter;
    private InquilinoViewModel inquilinoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState ){
        inquilinoViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        binding = FragmentInquilinoBinding.inflate(inflater, container, false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.rvInquilinos.setLayoutManager(gridLayoutManager);

        inmuebleAdapter = new InmuebleAdapter();
        inmuebleAdapter.setEsParaInquilinos(true);
        binding.rvInquilinos.setAdapter(inmuebleAdapter);

        inquilinoViewModel.getToastMessage().observe(getViewLifecycleOwner(), message -> {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });

        inquilinoViewModel.getInmueblesMutable().observe(getViewLifecycleOwner(), listaInmuebles -> {
            inmuebleAdapter.setInmuebles(listaInmuebles);
        });

        inquilinoViewModel.obtenerInmueblesAlquilados();

        return binding.getRoot();
    }
}