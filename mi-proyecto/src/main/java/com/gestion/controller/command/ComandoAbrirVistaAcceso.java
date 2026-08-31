package com.gestion.controller.command;

import com.gestion.view.AccesoVista;

public class ComandoAbrirVistaAcceso implements Comando {
    private final AccesoVista accesoVista;
    
    // Constructor
    public ComandoAbrirVistaAcceso(AccesoVista accesoVista) {
        this.accesoVista = accesoVista;
    }

    // Getters
    public AccesoVista conseguirAccesoVista() {
        return accesoVista;
    }   

    @Override
    public void ejecutar() {
        accesoVista.abrirVistaAcceso();
        throw new UnsupportedOperationException("Metodo no implementado 'ejecutar'");
    }
    
}
