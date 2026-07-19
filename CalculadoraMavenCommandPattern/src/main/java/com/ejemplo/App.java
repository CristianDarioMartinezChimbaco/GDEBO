package com.ejemplo;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        Calculator calculator = new Calculator();

        Command sumar10 = new SumarCommand(calculator, 10);
        Command restar3 = new RestarCommand(calculator, 3);

        Boton botonSumar = new Boton(sumar10);
        Boton botonRestar = new Boton(restar3);

        botonSumar.presionar();   // Resultado: 10
        botonRestar.presionar();  // Resultado: 7
        botonSumar.presionar();   // Resultado: 17
    }
}
