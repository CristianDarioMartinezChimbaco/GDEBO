package com.gestion.controller.command;

import com.gestion.view.AccesoVista;

public class ComandoCerrarVistaAcceso implements Comando {
    private final AccesoVista accesoVista;

    // Constructor
    public ComandoCerrarVistaAcceso(AccesoVista accesoVista) {
        this.accesoVista = accesoVista;
    }

    // Getters
    public AccesoVista conseguirAccesoVista() {
        return accesoVista;
    }

    @Override
    public void ejecutar() {
        accesoVista.cerrarVistaAcceso();
        throw new UnsupportedOperationException("Metodo no implementado 'ejecutar'");
    }
    
}
