package com.tech.tp1retrofit.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tech.tp1retrofit.ui.auth.LoginActivity;
import com.tech.tp1retrofit.databinding.FragmentLogoutBinding;

public class LogoutFragment extends Fragment {

    private FragmentLogoutBinding binding;
    private LogoutViewModel logoutViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLogoutBinding.inflate(inflater, container, false);

        logoutViewModel = new ViewModelProvider(this).get(LogoutViewModel.class);

        logoutViewModel.getLogueado().observe(getViewLifecycleOwner(), estaLogueado -> {
            if (!estaLogueado) {
                // solo cuando el ViewModel confirma que borro el token, vamos al login
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        mostrarDialogo();

        return binding.getRoot();
    }

    private void mostrarDialogo() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Cerrar Sesión")
                .setMessage("¿Estas seguro de que queres salir de la aplicacion?")
                .setCancelable(false)
                .setPositiveButton("Salir", (dialog, which) -> {
                    logoutViewModel.cerrarSesion();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    requireActivity().onBackPressed();
                })
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}