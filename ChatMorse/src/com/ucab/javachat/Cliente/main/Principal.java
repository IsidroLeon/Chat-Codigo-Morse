package com.ucab.javachat.Cliente.main;

import java.io.IOException;

import com.ucab.javachat.Cliente.controller.ControladorCliente;
import com.ucab.javachat.Cliente.controller.ControladorIniciarSesion;
import com.ucab.javachat.Cliente.model.Cliente;
import com.ucab.javachat.Cliente.view.VentCliente;
import com.ucab.javachat.Cliente.view.VentIniciarSesion;

public class Principal {
	
    public static void main(String args[]) throws IOException {
        Cliente.IP_SERVER = "localhost";
        //VentCliente frame = new VentCliente();
        @SuppressWarnings("unused")
		//ControladorCliente p = new ControladorCliente(frame);
        VentIniciarSesion ventIniciar = new VentIniciarSesion();
        ControladorIniciarSesion contIniciar = new ControladorIniciarSesion(ventIniciar);
}

}
