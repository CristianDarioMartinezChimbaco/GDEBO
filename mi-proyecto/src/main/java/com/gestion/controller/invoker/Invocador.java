package com.gestion.controller.invoker;

import com.gestion.controller.command.Comando;
public class Invocador {
    private Comando comando;

    // Getters
    public Comando conseguirComando() {
        return comando;
    }

    // Setters
    public void colocarComando(Comando comando) {
        this.comando = comando;
    }

    public void presionarBoton() {
        if (comando != null) {
            comando.ejecutar();
        }
    }
}
