package com.gestion.model;

public class Producto {
    private Integer id = -1; //int
    private String codigoBarras;
    private String nombre;
    private String marca;
    private Double cantidadProducto;
    private String unidadMedida;
    private Integer unidadAgrupada;
    private Double precio;
    private Integer existencias;
    private Integer minimoExistencias;

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
    public Integer conseguirId() {
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

    public Double conseguirCantidadProducto() {
      return cantidadProducto; 
    }

    public String conseguirUnidadMedida() {
      return unidadMedida; 
    }

    public Integer conseguirUnidadAgrupada() {
      return unidadAgrupada; 
    }

    public Double conseguirPrecio() {
      return precio; 
    }

    public Integer conseguirExistencias() {
      return existencias; 
    }

    public Integer conseguirMinimoExistencias() {
      return minimoExistencias; 
    }

    // Setters
    public void colocarId(Integer id) { 
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

    public void colocarCantidadProducto(Double cantidadProducto) { 
      this.cantidadProducto = cantidadProducto; 
    }

    public void colocarUnidadMedida(String unidadMedida) { 
      this.unidadMedida = unidadMedida; 
    }

    public void colocarUnidadAgrupada(Integer unidadAgrupada) { 
      this.unidadAgrupada = unidadAgrupada; 
    }

    public void colocarPrecio(Double precio) { 
      this.precio = precio; 
    }

    public void colocarExistencias(Integer existencias) { 
      this.existencias = existencias; 
    }

    public void colocarMinimoExistencias(Integer minimoExistencias) { 
      this.minimoExistencias = minimoExistencias; 
    }

    // Metodos

}
