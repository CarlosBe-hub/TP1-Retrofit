package com.tech.tp1retrofit.data.network.service;

import com.tech.tp1retrofit.data.model.Pago;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface PagoService {

    @GET("/api/pagos/contrato/{id}")
    Call<List<Pago>> obtenerPagosPorContrato(
            @Header("Authorization") String token,
            @Path("id") int contratoId
    );
}