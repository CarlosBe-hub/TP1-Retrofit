package com.tech.tp1retrofit.ui.contrato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.tech.tp1retrofit.R;
import com.tech.tp1retrofit.databinding.FragmentContratosBinding;

public class ContratoFragment extends Fragment {

    private FragmentContratosBinding binding;
    private ContratoViewModel contratoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState ){
        binding = FragmentContratosBinding.inflate(inflater,container, false);

        contratoViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);

        contratoViewModel.getToastMessage().observe(getViewLifecycleOwner(), message -> {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });

        contratoViewModel.getContratos().observe(getViewLifecycleOwner(), contrato -> {
            if (contrato != null) {
                binding.valFechaInicio.setText(contrato.getFechaInicio());
                binding.valFechaFin.setText(contrato.getFechaFinalizacion());
                binding.valMonto.setText("$" + contrato.getMontoAlquiler());

                binding.valInquilino.setText(contrato.getInquilino().getNombre() + " " + contrato.getInquilino().getApellido());
                binding.valInmueble.setText(contrato.getInmueble().getDireccion());
            }
        });


        binding.btnPagos.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_nav_contratos_to_pagoFragment);
        });

        if (getArguments() != null) {
            int idInmueble = getArguments().getInt("idInmueble");

            binding.valCodigo.setText(String.valueOf(idInmueble));

            contratoViewModel.obtenerContratos(idInmueble);
        }

         return binding.getRoot();
    }
}
