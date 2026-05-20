package com.tech.tp1retrofit.ui.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tech.tp1retrofit.databinding.FragmentCambiarPasswordBinding;


public class CambiarPasswordFragment extends Fragment {

    private FragmentCambiarPasswordBinding binding;
    private CambiarPasswordViewModel cambiarPasswordViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        cambiarPasswordViewModel = new ViewModelProvider(this).get(CambiarPasswordViewModel.class);
        binding = FragmentCambiarPasswordBinding.inflate(inflater,container,false);

        //Observer para mensajes de exito, error y validaciones..
        cambiarPasswordViewModel.getToastMessage().observe(getViewLifecycleOwner(), mensaje -> {
            Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
        });


        //Observer para cerrar el fragment si el cambio de pass es exitoso..
        cambiarPasswordViewModel.getExitoNavegacion().observe(getViewLifecycleOwner(), exito -> {
            if (exito && getView() != null) {
                Navigation.findNavController(getView()).popBackStack();
            }
        });

        binding.btCambiarPass.setOnClickListener(view ->{
            String passActual = binding.etPassActual.getText().toString();
            String passNuevo = binding.etPassNuevo.getText().toString();

            cambiarPasswordViewModel.cambiarPassword(passActual,passNuevo);
        });

        return binding.getRoot();
    }
}