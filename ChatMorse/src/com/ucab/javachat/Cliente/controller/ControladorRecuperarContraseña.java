package com.ucab.javachat.Cliente.controller;

import com.ucab.javachat.Cliente.model.Cliente;
import com.ucab.javachat.Cliente.view.VentRecuperarContraseña;
import com.ucab.javachat.Cliente.model.Criptologia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

public class ControladorRecuperarContraseña implements ActionListener{

	private VentRecuperarContraseña vista;

	public ControladorRecuperarContraseña (VentRecuperarContraseña vista){
		this.vista = vista;
		this.vista.frameRecuperarContraseña.setVisible(true);
		this.vista.btnEnviar.addActionListener(this);
		this.vista.btnSeleccionar.addActionListener(this);
	}

		
/*Primero se codifica, despues se manda al servidor y se ve si esta registrado. 
 * Si el usuario esta registrado se envia el correo. */
	public void actionPerformed(ActionEvent e) {	//metodo para enviar la informacion
		String correo;
		correo = Criptologia.encriptar(vista.textField.getText());
		try {
			Cliente cliente = new Cliente();
			if(cliente.conexion(correo)) {
				vista.frameRecuperarContraseña.setVisible(false);
				 JOptionPane.showMessageDialog(null, "Se ha enviado un correo con su contraseña", "Recuperación", JOptionPane.INFORMATION_MESSAGE);
			} else {
				 JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Problema de conexión", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}