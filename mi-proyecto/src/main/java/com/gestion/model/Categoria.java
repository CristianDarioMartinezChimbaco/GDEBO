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

    public String conseguirNombre() {
        return nombre;
    }

    public Integer conseguirActivo() {
        return activo;
    }

    public void colocarId(Integer id) {
        this.id = id;
    }

    public void colocarNombre(String nombre) {
        this.nombre = nombre;
    }

    public void colocarActivo(Integer activo) {
        this.activo = activo;
    }

      // Metodos
    @Override
    public String toString() {
      return id + " - " + nombre;
    }
    
}