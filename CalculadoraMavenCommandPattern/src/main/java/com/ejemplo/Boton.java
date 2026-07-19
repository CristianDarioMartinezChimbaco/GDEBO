package com.ejemplo;
public class Boton {
    private Command command;

    public Boton(Command command) {
        this.command = command;
    }

    public void presionar() {
        command.ejecutar();
    }
}
