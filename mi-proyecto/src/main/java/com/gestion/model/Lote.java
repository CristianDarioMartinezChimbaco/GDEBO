package com.gestion.model;

public class Lote {

    // Atributos
    private Integer id;
    private Integer idProducto;
    private String lote;
    private String fechaRegistro;
    private String fechaVencimiento;
    private Double cantidadEntrada;
    private Double precioCompra;
    private Boolean activo;

    // Constructor

    public Lote() {
        // Vacio
    }

    // Getters / Conseguir
    public Integer conseguirId() {
        return id;
    }

    public Integer conseguirIdProducto() {
        return idProducto;
    }

    public String conseguirLote() {
        return lote;
    }

    public String conseguirFechaRegistro() {
        return fechaRegistro;
    }

    public String conseguirFechaVencimiento() {
        return fechaVencimiento;
    }

    public Double conseguirCantidadEntrada() {
        return cantidadEntrada;
    }

    public Double conseguirPrecioCompra() {
        return precioCompra;
    }

    public Boolean conseguirActivo() {
        return activo;
    }

    // Setters / Colocar
    public void colocarId(Integer id) {
        this.id = id;
    }

    public void colocarIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public void colocarLote(String lote) {
        this.lote = lote;
    }

    public void colocarFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void colocarFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void colocarCantidadEntrada(Double cantidadEntrada) {
        this.cantidadEntrada = cantidadEntrada;
    }

    public void colocarPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void colocarActivo(Boolean activo) {
        this.activo = activo;
    }
}