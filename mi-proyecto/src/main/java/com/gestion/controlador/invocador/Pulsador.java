package com.gestion.controlador.invocador;

import java.util.Stack;

import com.gestion.controlador.comando.Comando;

public class Pulsador {
    private final Stack<Comando> history = new Stack<>();
    private final Stack<Comando> redoStack = new Stack<>();
    private Comando comandoActual;

    // Setters
    public void setComando(Comando comando) {
        this.comandoActual = comando;
    }

    public void pressButton() {
        if (comandoActual != null) {
            comandoActual.ejecutar();
            history.push(comandoActual);
            redoStack.clear();  // Nuevo comando invalida el redo
            System.out.println(
              "✅ Comando ejecutado. Historial: " + 
              history.size()
            );
        }
    }

    public void undo() {
        if (!history.isEmpty()) {
            Comando lastCommand = history.pop();
            lastCommand.deshacer();
            redoStack.push(lastCommand);
            System.out.println(
              "↩️ Undo ejecutado. Quedan: " + 
              history.size()
            );
        } else {
            System.out.println("❌ No hay comandos para deshacer");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Comando commandToRedo = redoStack.pop();
            commandToRedo.ejecutar();
            history.push(commandToRedo);
            System.out.println("↪️ Redo ejecutado");
        } else {
            System.out.println("❌ No hay comandos para rehacer");
        }
    }

    public void showHistory() {
        System.out.println(
          "📜 Historial: " + history.size() + 
          " comandos"
        );
    }
}
