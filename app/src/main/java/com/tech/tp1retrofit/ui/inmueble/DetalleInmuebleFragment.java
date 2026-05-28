package com.tech.tp1retrofit.ui.inmueble;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.tech.tp1retrofit.R;
import com.tech.tp1retrofit.data.model.Contrato;
import com.tech.tp1retrofit.data.model.Inmueble;
import com.tech.tp1retrofit.databinding.FragmentDetalleInmuebleBinding;
import com.tech.tp1retrofit.ui.contrato.ContratoViewModel;

public class DetalleInmuebleFragment extends Fragment {

    private FragmentDetalleInmuebleBinding binding;
    private InmuebleViewModel viewModel;
    private Inmueble inmuebleSeleccionado;
    private ContratoViewModel contratoViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetalleInmuebleBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(InmuebleViewModel.class);
        contratoViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);

        viewModel.getToastMessage().observe(getViewLifecycleOwner(), message -> {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });

        if (getArguments() != null) {
            inmuebleSeleccionado = (Inmueble) getArguments().getSerializable("inmuebleSeleccionado");
            if (inmuebleSeleccionado != null) {
                Glide.with(getContext())
                        .load(inmuebleSeleccionado.getUrlImagen())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(binding.ivDetalleFoto);

                binding.tvDetalleDireccion.setText(inmuebleSeleccionado.getDireccion());
                binding.tvDetallePrecio.setText("$" + inmuebleSeleccionado.getPrecio());
                binding.tvDetalleAmbientes.setText("Ambientes: " + inmuebleSeleccionado.getAmbientes());
                binding.tvDetalleTipo.setText("Tipo: " + inmuebleSeleccionado.getTipo());
                binding.tvDetalleUso.setText("Uso: " + inmuebleSeleccionado.getUso());
                binding.cbDisponibilidad.setChecked(inmuebleSeleccionado.isEstado());
            }
        }

        binding.cbDisponibilidad.setOnClickListener(v -> {
            if (inmuebleSeleccionado != null) {
                boolean nuevoEstado = binding.cbDisponibilidad.isChecked();
                viewModel.cambiarDisponibilidad(inmuebleSeleccionado, nuevoEstado);
            }
        });

        binding.btnContratos.setOnClickListener(v ->{
            if(inmuebleSeleccionado != null){
                contratoViewModel.obtenerContratos(inmuebleSeleccionado.getId());
            }
        });

        contratoViewModel.getContratos().observe(getViewLifecycleOwner(),contrato -> {
            if(contrato != null){

                contratoViewModel.limpiarContrato();

                Bundle bundle = new Bundle();
                bundle.putInt("idInmueble",inmuebleSeleccionado.getId());

                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_detalleInmuebleFragment_to_nav_contratos,bundle);

            }
        });
        contratoViewModel.getToastMessage().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null && !mensaje.isEmpty()) {
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });



        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}