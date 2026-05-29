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
import com.tech.tp1retrofit.ui.pago.PagoViewModel;

public class ContratoFragment extends Fragment {

    private FragmentContratosBinding binding;
    private ContratoViewModel contratoViewModel;
    private PagoViewModel pagoViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentContratosBinding.inflate(inflater, container, false);

        contratoViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);
        pagoViewModel = new ViewModelProvider(this).get(PagoViewModel.class);

        contratoViewModel.getToastMessage().observe(getViewLifecycleOwner(), message -> {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });

        pagoViewModel.getToastMessage().observe(getViewLifecycleOwner(), message -> {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });

        contratoViewModel.getContratos().observe(getViewLifecycleOwner(), contrato -> {
            if (contrato != null) {
                binding.valFechaInicio.setText(contrato.getFechaInicio());
                binding.valFechaFin.setText(contrato.getFechaFinalizacion());
                binding.valMonto.setText("$" + contrato.getMontoAlquiler());

                if (contrato.getInquilino() != null) {
                    binding.valInquilino.setText(contrato.getInquilino().getNombre() + " " + contrato.getInquilino().getApellido());
                }
                if (contrato.getInmueble() != null) {
                    binding.valInmueble.setText(contrato.getInmueble().getDireccion());
                }
            }
        });

        pagoViewModel.getNavegarAPagosEvent().observe(getViewLifecycleOwner(), idContrato -> {
            if (idContrato != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("idContrato", idContrato);
                Navigation.findNavController(requireView()).navigate(R.id.action_nav_contratos_to_pagoFragment, bundle);
                pagoViewModel.limpiarNavegacion();
            }
        });

        binding.btnPagos.setOnClickListener(v -> {
            Integer idContrato = contratoViewModel.getIdContratoActual();
            if (idContrato != null) {
                pagoViewModel.verificarPagos(idContrato);
            }
        });

        if (getArguments() != null) {
            int idInmueble = getArguments().getInt("idInmueble");
            binding.valCodigo.setText(String.valueOf(idInmueble));
            contratoViewModel.obtenerContratos(idInmueble);
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}