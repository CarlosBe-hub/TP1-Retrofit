package com.tech.tp1retrofit.ui.pago;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tech.tp1retrofit.databinding.FragmentPagoBinding;

public class pagoFragment extends Fragment {

    private FragmentPagoBinding binding;
    private PagoViewModel viewModel;
    private PagoAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPagoBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(PagoViewModel.class);
        adapter = new PagoAdapter();

        binding.rvPagos.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPagos.setAdapter(adapter);

        viewModel.getPagos().observe(getViewLifecycleOwner(), lista -> {
            if (lista != null) {
                adapter.setListaPagos(lista);
            }
        });

        viewModel.getToastMessage().observe(getViewLifecycleOwner(), mensaje -> {
            if (getContext() != null) {
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });

        if (getArguments() != null) {
            int idContrato = getArguments().getInt("idContrato");
            viewModel.obtenerPagos(idContrato);
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}