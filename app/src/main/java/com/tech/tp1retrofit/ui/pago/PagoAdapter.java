package com.tech.tp1retrofit.ui.pago;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.tp1retrofit.data.model.Pago;
import com.tech.tp1retrofit.databinding.ItemPagoBinding;

import java.util.ArrayList;
import java.util.List;

public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.ViewHolder> {

    private List<Pago> listaPagos = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPagoBinding binding = ItemPagoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pago pago = listaPagos.get(position);

        holder.binding.valCodigoPago.setText(String.valueOf(pago.getId()));
        holder.binding.valNumPago.setText(String.valueOf(pago.getId()));
        holder.binding.valCodContrato.setText(String.valueOf(pago.getIdContrato()));
        holder.binding.valImporte.setText("$" + pago.getImporte());
        holder.binding.valFechaPago.setText(pago.getFechaPago());
    }

    @Override
    public int getItemCount() {
        return listaPagos.size();
    }

    public void setListaPagos(List<Pago> nuevaLista) {
        this.listaPagos = nuevaLista;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemPagoBinding binding;

        public ViewHolder(@NonNull ItemPagoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}