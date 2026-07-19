package com.ejemplo;
public class SumarCommand implements Command {
    private Calculator calculator;
    private int numero;

    public SumarCommand(Calculator calculator, int numero) {
        this.calculator = calculator;
        this.numero = numero;
    }

    @Override
    public void ejecutar() {
        calculator.sumar(numero);
    }
}
