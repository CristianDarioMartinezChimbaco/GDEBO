package com.gestion.modelo;

public class Producto {
    private int id = -1;
    private String codigoBarras = "";
    private String nombre = "";
    private String marca = "";
    private double cantidadProducto  = -1;
    private String unidadMedida = "";
    private int unidadAgrupada = -1;
    private double precio = -1;
    private int existencias = -1;
    private int minimoExistencias = -1;

    // Constructor 
    /*
    public Producto(String codigoBarras, String nombre, String marca, double precio) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
    }
    */

    // Getters
    public int conseguirId() {
      return id; 
    }

    public String conseguirCodigoBarras() {
      return codigoBarras; 
    }

    public String conseguirNombre() {
      return nombre; 
    }

    public String conseguirMarca() {
      return marca; 
    }

    public double conseguirCantidadProducto() {
      return cantidadProducto; 
    }

    public String conseguirUnidadMedida() {
      return unidadMedida; 
    }

    public int conseguirUnidadAgrupada() {
      return unidadAgrupada; 
    }

    public double conseguirPrecio() {
      return precio; 
    }

    public int conseguirExistencias() {
      return existencias; 
    }

    public int conseguirMinimoExistencias() {
      return minimoExistencias; 
    }

    // Setters
    public void colocarId(int id) { 
      this.id = id; 
    }

    public void colocarCodigoBarras(String codigoBarras) { 
      this.codigoBarras = codigoBarras; 
    }

    public void colocarNombre(String nombre) { 
      this.nombre = nombre; 
    }

    public void colocarMarca(String marca) { 
      this.marca = marca; 
    }

    public void colocarCantidadProducto(double cantidadProducto) { 
      this.cantidadProducto = cantidadProducto; 
    }

    public void colocarUnidadMedida(String unidadMedida) { 
      this.unidadMedida = unidadMedida; 
    }

    public void colocarUnidadAgrupada(int unidadAgrupada) { 
      this.unidadAgrupada = unidadAgrupada; 
    }

    public void colocarPrecio(double precio) { 
      this.precio = precio; 
    }

    public void colocarExistencias(int existencias) { 
      this.existencias = existencias; 
    }

    public void colocarMinimoExistencias(int minimoExistencias) { 
      this.minimoExistencias = minimoExistencias; 
    }

    // Metodos

}
