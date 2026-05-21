package com.tech.tp1retrofit.ui.inmueble;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.tp1retrofit.R;
import com.tech.tp1retrofit.data.model.Inmueble;
import com.tech.tp1retrofit.databinding.ItemInmuebleBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import androidx.recyclerview.widget.RecyclerView;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder>{

    private List<Inmueble> inmuebles = new ArrayList<>();
    private final Consumer<Inmueble> onInmuebleClick;

    public InmuebleAdapter(Consumer<Inmueble>onInmuebleClick){
        this.onInmuebleClick = onInmuebleClick;
    }



    @NonNull
    @Override
    public InmuebleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemInmuebleBinding binding = ItemInmuebleBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding);
    }
    public void setInmuebles(List<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.ViewHolder holder, int position) {
        Inmueble inmueble = inmuebles.get(position);
        holder.binding.tvDireccion.setText(inmueble.getDireccion());
        holder.binding.tvPrecio.setText("$" + inmueble.getPrecio());

        holder.binding.getRoot().setOnClickListener(v -> onInmuebleClick.accept(inmueble));
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemInmuebleBinding binding;

        public ViewHolder(@NonNull ItemInmuebleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
