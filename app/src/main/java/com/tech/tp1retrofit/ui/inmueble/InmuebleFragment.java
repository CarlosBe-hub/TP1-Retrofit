package com.tech.tp1retrofit.ui.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.tech.tp1retrofit.R;
import com.tech.tp1retrofit.data.model.Inmueble;
import com.tech.tp1retrofit.databinding.FragmentInmueblesBinding;

import java.util.ArrayList;
import java.util.List;

public class InmuebleFragment extends Fragment {
    private FragmentInmueblesBinding binding;
    private InmuebleAdapter inmuebleAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState ){
        binding = FragmentInmueblesBinding.inflate(inflater,container,false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.rvInmuebles.setLayoutManager(gridLayoutManager);

        inmuebleAdapter = new InmuebleAdapter(inmueble -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("inmubeleSeleccionado",inmueble);

            if (getView() != null){
                Navigation.findNavController(getView()).navigate(
                        R.id.action_nav_inmuebles_to_detalleInmuebleFragment,
                        bundle
                );
            }
        });
        binding.rvInmuebles.setAdapter(inmuebleAdapter);

        //Hay que borrar la harcodeada..
        List<Inmueble> listaDePrueba = new ArrayList<>();

        listaDePrueba.add(new Inmueble("Salta 325", 3, true, 1, "", 17000.0, null, 1, 1200, "Casa", "Residencial"));
        listaDePrueba.add(new Inmueble("Lavalle 450", 2, true, 2, "", 25000.0, null, 1, 800, "Departamento", "Residencial"));
        listaDePrueba.add(new Inmueble("Belgrano 218", 5, true, 3, "", 90000.0, null, 2, 3500, "Casa", "Residencial"));
        listaDePrueba.add(new Inmueble("San Martín 102", 1, true, 4, "", 35000.0, null, 2, 40, "Local", "Comercial"));

        inmuebleAdapter.setInmuebles(listaDePrueba);

        binding.fabAgregarInmueble.setOnClickListener(v ->{

        });
        return binding.getRoot();
    }
}
