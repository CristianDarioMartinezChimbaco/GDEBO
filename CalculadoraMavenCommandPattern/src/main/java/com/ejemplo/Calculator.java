package com.ejemplo;
public class Calculator {
    private int valor = 0;

    public void sumar(int n) {
        valor += n;
        System.out.println("Resultado: " + valor);
    }

    public void restar(int n) {
        valor -= n;
        System.out.println("Resultado: " + valor);
    }
}
