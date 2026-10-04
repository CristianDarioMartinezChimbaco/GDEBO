package com.gestion.service;

import com.gestion.model.Lote;
import com.gestion.repository.LoteRepositorio;

public class LoteServicio {

    private LoteRepositorio loteRepositorio;

    public LoteServicio() {
        loteRepositorio = new LoteRepositorio();
    }

    public void agregarLote(Lote lote) {
        loteRepositorio.agregarLote(lote);
    }
}
