package com.gestion.view;

import java.util.Scanner;

import com.gestion.model.Usuario;


public class ClienteVista {
	//private Scanner sc = new Scanner(System.in);
	private Usuario usuario = new Usuario();


	// Getters
    public Usuario conseguirUsuario() { 
      return usuario; 
    }

    // Setters
    public void colocarNombre(Usuario usuario) { 
      this.usuario = usuario; 
    }
  
	// Metodos


}
