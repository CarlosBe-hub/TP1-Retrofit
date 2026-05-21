package com.tech.tp1retrofit.ui.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tech.tp1retrofit.R;
import com.tech.tp1retrofit.data.model.Inmueble;
import com.tech.tp1retrofit.data.network.ApiClient;
import com.tech.tp1retrofit.databinding.ItemInmuebleBinding;

import java.util.ArrayList;
import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder>{

    private List<Inmueble> inmuebles = new ArrayList<>();

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


        Glide.with(holder.itemView.getContext())
                .load(inmueble.getUrlImagen())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.binding.ivInmueble);

        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("inmuebleSeleccionado", inmueble);

            Navigation.findNavController(v).navigate(R.id.action_nav_inmuebles_to_detalleInmuebleFragment, bundle);
        });
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
