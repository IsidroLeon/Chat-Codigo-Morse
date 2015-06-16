package com.ucab.javachat.Cliente.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ucab.javachat.Cliente.view.VentCliente;
import com.ucab.javachat.Cliente.view.VentIniciarSesion;
import com.ucab.javachat.Cliente.model.ModeloValidacion;

/*
 * @authors Ismael T.
 * */
public class ControladorIniciarSesion implements ActionListener{
	private VentIniciarSesion vista;
	private String usuario;
	private String clave;
	private ModeloValidacion validar = new ModeloValidacion();
	
	public ControladorIniciarSesion(VentIniciarSesion vista) {
		this.vista = vista;
		this.vista.frmInicioDeSesion.setVisible(true);
		
		/*Definicion de botones*/
		this.vista.btnEnviar.addActionListener(this);
		this.vista.btnArchivo.addActionListener(this);
		this.vista.btnContrasena.addActionListener(this);
	}
	
	public boolean validarInicioSesion() {
		boolean flag = true;
		if (validar.validarUsuario(vista.txtUsuario.getText())) {
			usuario = vista.txtUsuario.getText();
			// devuelve error de usuario
		} else {
			flag = false;
		}
		if(validar.validarContraseña(String.valueOf(vista.txtClave.getPassword()))) {
			clave = String.valueOf(vista.txtClave.getPassword());
			//devuelve error de clave
		} else {
			flag = false;
		}
		return flag;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
	public String getClave() {
		return this.clave;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (vista.btnEnviar == e.getSource())	
			if (validarInicioSesion()) {
				VentCliente vistaCliente = new VentCliente(); // Si el inicio de sesion es valido crea la ventana
				ControladorCliente p = new ControladorCliente(vistaCliente, this);
				this.vista.frmInicioDeSesion.dispose();
			}
		
		if (vista.btnContrasena == e.getSource()){
			/*aqui se abre la ventana para recuperar la contrasena*/
		}
		
		if (vista.btnArchivo == e.getSource()){
			/*metodos de openCV para cargar la foto al sistema*/
		}
	}

}
