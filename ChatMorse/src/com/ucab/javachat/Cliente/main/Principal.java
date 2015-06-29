package com.ucab.javachat.Cliente.main;

import java.io.IOException;

import com.ucab.javachat.Cliente.controller.ControladorIniciarSesion;
import com.ucab.javachat.Cliente.model.Cliente;
import com.ucab.javachat.Cliente.view.VentIniciarSesion;

/**
 * Esta clase se encarga de ejecutar la vista y el controlador del paquete Cliente para iniciar 
 * la aplicacion por parte del cliente
 * @author Grupo 3
 *
 */

public class Principal {
	
    public static void main(String args[]) throws IOException {
        Cliente.IP_SERVER = "localhost";
        VentIniciarSesion ventIniciar = new VentIniciarSesion();
		new ControladorIniciarSesion(ventIniciar);
    }
}