package com.tech.tp1retrofit.ui.inquilino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tech.tp1retrofit.databinding.FragmentInquilinoDetalleBinding;

public class InquilinoDetalleFragment extends Fragment {
    private FragmentInquilinoDetalleBinding binding;
    private InquilinoDetalleViewModel inquilinoDetalleViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState ){
        inquilinoDetalleViewModel = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        binding = FragmentInquilinoDetalleBinding.inflate(inflater, container, false);

        inquilinoDetalleViewModel.getToastMessage().observe(getViewLifecycleOwner(), message -> {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });

        inquilinoDetalleViewModel.procesarArgumentos(getArguments());

        inquilinoDetalleViewModel.getInquilinoMutable().observe(getViewLifecycleOwner(), inquilino -> {
            binding.tvNombreInquilino.setText(inquilino.getNombre());
            binding.tvApellidoInquilino.setText(inquilino.getApellido());
            binding.tvDniInquilino.setText(inquilino.getDni());
            binding.tvTelefonoInquilino.setText(inquilino.getTelefono());
            binding.tvEmailInquilino.setText(inquilino.getEmail());
        });

        return binding.getRoot();
    }
}