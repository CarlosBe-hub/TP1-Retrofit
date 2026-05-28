package com.tech.tp1retrofit.ui.pago;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tech.tp1retrofit.R;
import com.tech.tp1retrofit.databinding.FragmentPagoBinding;


public class pagoFragment extends Fragment {
private FragmentPagoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentPagoBinding.inflate(inflater,container,false);
       binding.rvPagos.setLayoutManager(new LinearLayoutManager(getContext()));

       return binding.getRoot();

    }
}