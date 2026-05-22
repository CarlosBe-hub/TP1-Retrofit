package com.tech.tp1retrofit.ui.inmueble;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tech.tp1retrofit.databinding.FragmentAgregarInmuebleBinding;

public class AgregarInmuebleFragment extends Fragment {

    private FragmentAgregarInmuebleBinding binding;
    private AgregarInmuebleViewModel viewModel;
    private ActivityResultLauncher<Intent> selectorGaleria;
    private Uri uriFotoSeleccionada = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAgregarInmuebleBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(AgregarInmuebleViewModel.class);

        configurarLanzadorGaleria();

        viewModel.getToastMessage().observe(getViewLifecycleOwner(), mensaje -> {
            Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
        });

        viewModel.getIsLoading().observe(getViewLifecycleOwner(), cargando -> {
            if (cargando != null && cargando) {
                binding.btnGuardarInmueble.setEnabled(false);
                binding.btnGuardarInmueble.setText("Guardando...");
            } else {
                binding.btnGuardarInmueble.setEnabled(true);
                binding.btnGuardarInmueble.setText("GUARDAR");
            }
        });

        viewModel.getOperacionExitosa().observe(getViewLifecycleOwner(), exito -> {
            if (exito != null && exito) {
                binding.etDireccion.setText("");
                binding.etUso.setText("");
                binding.etTipo.setText("");
                binding.etAmbientes.setText("");
                binding.etPrecio.setText("");

                binding.ivPreviewInmueble.setVisibility(View.GONE);
                uriFotoSeleccionada = null;
            }
        });

        binding.btnCargarImagen.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            selectorGaleria.launch(intent);
        });

        binding.btnGuardarInmueble.setOnClickListener(v -> {
            String direccion = binding.etDireccion.getText().toString().trim();
            String uso = binding.etUso.getText().toString().trim();
            String tipo = binding.etTipo.getText().toString().trim();
            String ambientes = binding.etAmbientes.getText().toString().trim();
            String precio = binding.etPrecio.getText().toString().trim();

            viewModel.guardarInmueble(direccion, uso, tipo, ambientes, precio, uriFotoSeleccionada);
        });

        return binding.getRoot();
    }

    private void configurarLanzadorGaleria() {
        selectorGaleria = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                resultado -> {
                    if (resultado.getResultCode() == Activity.RESULT_OK && resultado.getData() != null) {
                        uriFotoSeleccionada = resultado.getData().getData();
                        binding.ivPreviewInmueble.setImageURI(uriFotoSeleccionada);
                        binding.ivPreviewInmueble.setVisibility(View.VISIBLE);
                    }
                }
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}