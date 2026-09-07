package com.gestion.model;

public class Producto {
    private Integer id; 
    private String codigoBarras;
    private String nombre;
    private String marca;
    private Double cantidadProducto;
    private String unidadMedida;
    private Integer unidadAgrupada;
    private Double precio;
    private Double existencias;
    private Double minimoExistencias;
    private Boolean activo;
    // Constructor 

    /*
    public Producto(String codigoBarras, 
      String nombre, 
      String marca, 
      Double cantidadProducto,
      String unidadMedida,
      Integer unidadAgrupada,
      Double precio,
      Double existencias,
      Double minimoExistencias,
      Boolean activo) {
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

    public Double conseguirExistencias() {
      return existencias; 
    }

    public Double conseguirMinimoExistencias() {
      return minimoExistencias; 
    }

    public Boolean conseguirActivo() {
      return activo; 
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

    public void colocarExistencias(Double existencias) { 
      this.existencias = existencias; 
    }

    public void colocarMinimoExistencias(Double minimoExistencias) { 
      this.minimoExistencias = minimoExistencias; 
    }

    public void colocarActivo(Boolean activo) { 
      this.activo = activo; 
    }

    // Metodos

}
