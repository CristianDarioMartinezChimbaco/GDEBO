package com.ejemplo;
public class RestarCommand implements Command {
    private Calculator calculator;
    private int numero;

    public RestarCommand(Calculator calculator, int numero) {
        this.calculator = calculator;
        this.numero = numero;
    }

    @Override
    public void ejecutar() {
        calculator.restar(numero);
    }
}
