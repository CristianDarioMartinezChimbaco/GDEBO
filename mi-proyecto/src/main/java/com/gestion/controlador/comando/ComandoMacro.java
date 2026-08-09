package com.gestion.controlador.comando;

import java.util.ArrayList;
import java.util.List;

public class ComandoMacro implements Comando {
    private final List<Comando> comandos = new ArrayList<>();

    public void agregarComando(Comando comando) {
        comandos.add(comando);
    }

    @Override
    public void ejecutar() {
        for (Comando cmd : comandos) {
            cmd.ejecutar();
        }
    }
}
