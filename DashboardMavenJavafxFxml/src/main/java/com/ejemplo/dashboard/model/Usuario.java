package com.ejemplo.dashboard.model;

import javafx.beans.property.*;

public class Usuario {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty rol = new SimpleStringProperty();
    private final BooleanProperty activo = new SimpleBooleanProperty();

    public Usuario(int id, String nombre, String email, String rol, boolean activo) {
        this.id.set(id);
        this.nombre.set(nombre);
        this.email.set(email);
        this.rol.set(rol);
        this.activo.set(activo);
    }

    // Getters y Setters con Properties
    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }
    
    public String getNombre() { return nombre.get(); }
    public StringProperty nombreProperty() { return nombre; }
    
    public String getEmail() { return email.get(); }
    public StringProperty emailProperty() { return email; }
    
    public String getRol() { return rol.get(); }
    public StringProperty rolProperty() { return rol; }
    
    public boolean isActivo() { return activo.get(); }
    public BooleanProperty activoProperty() { return activo; }
}