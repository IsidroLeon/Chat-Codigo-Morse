package com.ucab.javachat.Cliente.main;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.ucab.javachat.Cliente.controller.ControladorCliente;
import com.ucab.javachat.Cliente.model.Cliente;
import com.ucab.javachat.Cliente.view.VentCliente;

public class Principal {

    public static void main(String args[]) throws IOException {
        Cliente.IP_SERVER = JOptionPane.showInputDialog("Introducir IP_SERVER :","localhost");
        VentCliente frame = new VentCliente();
        ControladorCliente p = new ControladorCliente(frame);
}

}
