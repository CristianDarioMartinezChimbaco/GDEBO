package com.gestion.modelo;

public class Cliente {
    private Persona persona = new Persona();
    private String correoElectronico;
    private int telefono;
    private Double saldo;

    // Getters
    public Persona conseguirPersona() { 
      return persona; 
    }

    public String conseguirCorreoElectronico() { 
      return correoElectronico; 
    }

    public int conseguirTelefono() {
      return telefono; 
    }

    public Double conseguirSaldo() {
      return saldo; 
    }

    // Setters
    public void colocarNombre(Persona persona) { 
      this.persona = persona; 
    }

    public void colocarCorreoElectronico(String correoElectronico) { 
      this.correoElectronico = correoElectronico; 
    }

    public void colocarTelefono(int telefono) { 
      this.telefono = telefono; 
    }

    public void colocarSaldo(Double saldo) { 
      this.saldo = saldo; 
    }
    
    // Metodos

}
