package com.tech.tp1retrofit.ui.contrato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.tech.tp1retrofit.R;
import com.tech.tp1retrofit.databinding.FragmentContratosBinding;

public class ContratoFragment extends Fragment {

    private FragmentContratosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState ){
        binding = FragmentContratosBinding.inflate(inflater,container, false);


        binding.btnPagos.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_nav_contratos_to_pagoFragment);
        });
         return binding.getRoot();


    }
}
