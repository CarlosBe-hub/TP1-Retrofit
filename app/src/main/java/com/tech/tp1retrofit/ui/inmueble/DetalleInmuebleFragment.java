package com.tech.tp1retrofit.ui.inmueble;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.tech.tp1retrofit.R;
import com.tech.tp1retrofit.data.model.Inmueble;
import com.tech.tp1retrofit.databinding.FragmentDetalleInmuebleBinding;


public class DetalleInmuebleFragment extends Fragment {

    private FragmentDetalleInmuebleBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding = FragmentDetalleInmuebleBinding.inflate(inflater,container,false);

      if (getArguments() != null){
          Inmueble inmueble = (Inmueble) getArguments().getSerializable("inmuebleSeleccionado");
          if (inmueble != null) {

              Glide.with(getContext())
                      .load(inmueble.getUrlImagen())
                      .placeholder(R.drawable.ic_launcher_background)
                      .error(R.drawable.ic_launcher_background)
                      .into(binding.ivDetalleFoto);

              binding.tvDetalleDireccion.setText(inmueble.getDireccion());
              binding.tvDetallePrecio.setText("$" + inmueble.getPrecio());
              binding.tvDetalleAmbientes.setText("Ambientes: " + inmueble.getAmbientes());
              binding.tvDetalleTipo.setText("Tipo: " + inmueble.getTipo());
              binding.tvDetalleUso.setText("Uso: " + inmueble.getUso());
              binding.cbDisponibilidad.setChecked(inmueble.isEstado());
          }
      }
      return binding.getRoot();
    }
}