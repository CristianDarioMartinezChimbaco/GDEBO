package com.gestion;

import com.gestion.controlador.comando.*;
import com.gestion.controlador.invocador.*;
import com.gestion.modelo.*;
import com.gestion.vista.*;

public class Aplicacion {
    public static void main(String[] args) {
        System.out.println("Aplicación de gestión iniciada.");

        Usuario usuario = new Usuario();

        AccesoVista accesoVista = new AccesoVista(usuario);
        Invocador invocador = new Invocador();

        // Comandos individuales
        Comando AbrirVistaAcceso = new ComandoAbrirVistaAcceso(accesoVista);

        Comando CerrarVistaAcceso = new ComandoCerrarVistaAcceso(accesoVista);

        // Escenario 1: Encender y dimmer
        invocador.colocarComando(AbrirVistaAcceso);
        invocador.presionarBoton();  // ON

        invocador.colocarComando(CerrarVistaAcceso);
        invocador.presionarBoton();  // OFF

        // Usando Comando Macro para ejecutar múltiples comandos
        ComandoMacro macro = new ComandoMacro();

        macro.agregarComando(new ComandoAbrirVistaAcceso(accesoVista));
        macro.agregarComando(new ComandoCerrarVistaAcceso(accesoVista));

        invocador.colocarComando(macro);
        invocador.presionarBoton(); 
    }
}
