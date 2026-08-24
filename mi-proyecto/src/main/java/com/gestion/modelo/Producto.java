package com.gestion.modelo;

public class Producto {
    private int id;
    private long codigoBarras;
    private String nombre;
    private String marca;
    private Double cantidadPresentacion;
    private String unidadMedida;
    private int unidadAgrupada;
    private double precio;
    private int existencias;


    public Producto(String codigoBarras, String nombre, String marca, double precio) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
    }
}
