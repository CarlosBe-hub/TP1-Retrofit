package com.tech.tp1retrofit.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tech.tp1retrofit.data.model.Propietario;
import com.tech.tp1retrofit.databinding.FragmentPerfilBinding;

public class PerfilFragment extends Fragment {
    private FragmentPerfilBinding binding;
    private PerfilViewModel perfilViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState ){
        perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        binding = FragmentPerfilBinding.inflate(inflater, container, false);

        // observer para obtener los mensajes de exito, error o validaciones con toast
        perfilViewModel.getToastMessage().observe(getViewLifecycleOwner(), mensaje -> {
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
        });

        // observer para cada vez que se obtenga un dato del paciente, actualiza los textfield
        perfilViewModel.getPropietario().observe(getViewLifecycleOwner(), propietario -> {
            binding.etNombre.setText(propietario.getNombre());
            binding.etApellido.setText(propietario.getApellido());
            binding.etDni.setText(propietario.getDni());
            binding.etEmail.setText(propietario.getEmail());
            binding.etTelefono.setText(propietario.getTelefono());
        });


        // cuando se usa el boton de editar, se mandan los cambios a la api
        binding.btnEditarPerfil.setOnClickListener(view ->{

            Propietario propietarioActual = perfilViewModel.getPropietario().getValue();

            propietarioActual.setNombre(binding.etNombre.getText().toString());
            propietarioActual.setApellido(binding.etApellido.getText().toString());
            propietarioActual.setDni(binding.etDni.getText().toString());
            propietarioActual.setTelefono(binding.etTelefono.getText().toString());
            propietarioActual.setEmail(binding.etEmail.getText().toString());

            perfilViewModel.actualizarPerfil(propietarioActual);
        });

        perfilViewModel.obtenerPerfil();

        return binding.getRoot();
    }
}
