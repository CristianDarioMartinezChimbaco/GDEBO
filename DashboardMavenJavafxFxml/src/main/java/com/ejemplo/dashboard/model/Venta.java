package com.ejemplo.dashboard.model;

import javafx.beans.property.*;

public class Venta {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty producto = new SimpleStringProperty();
    private final DoubleProperty monto = new SimpleDoubleProperty();
    private final StringProperty fecha = new SimpleStringProperty();
    private final StringProperty estado = new SimpleStringProperty();

    public Venta(int id, String producto, double monto, String fecha, String estado) {
        this.id.set(id);
        this.producto.set(producto);
        this.monto.set(monto);
        this.fecha.set(fecha);
        this.estado.set(estado);
    }

    // Getters y Setters con Properties
    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }
    
    public String getProducto() { return producto.get(); }
    public StringProperty productoProperty() { return producto; }
    
    public double getMonto() { return monto.get(); }
    public DoubleProperty montoProperty() { return monto; }
    
    public String getFecha() { return fecha.get(); }
    public StringProperty fechaProperty() { return fecha; }
    
    public String getEstado() { return estado.get(); }
    public StringProperty estadoProperty() { return estado; }
}