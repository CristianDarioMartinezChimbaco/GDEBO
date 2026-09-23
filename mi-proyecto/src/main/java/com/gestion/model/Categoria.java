package com.gestion.model;

public class Categoria {

    private Integer id;
    private String nombre;
    private Integer activo;

    public Categoria() {
    }

    public Categoria(Integer id, String nombre, Integer activo) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
    }

    public Integer conseguirId() {
        return id;
    }

    public void colocarId(Integer id) {
        this.id = id;
    }

    public String conseguirNombre() {
        return nombre;
    }

    public void colocarNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer conseguirActivo() {
        return activo;
    }

    public void colocarActivo(Integer activo) {
        this.activo = activo;
    }

}