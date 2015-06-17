package com.ucab.javachat.Cliente.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ucab.javachat.Cliente.model.Cliente;
import com.ucab.javachat.Cliente.model.Criptologia;
import com.ucab.javachat.Cliente.model.Usuario;
import com.ucab.javachat.Cliente.view.VentRegistro;
import com.ucab.javachat.Cliente.view.VentSeleccionFoto;

/**
 * Esta clase es el controlador de la vista del registro de usuario.
 * Al usuario se le indica con que caracteristica especifica debe contar cada campo 
 * de registro. Luego se obtiene la informacion indicada en los campos mostrados en 
 * las vista y se aplican los metodos necesarios para la comprobacion de la informacion. 
 * Si todos los campos estan correctamente rellenados, se procede a enviar la información
 * al servidor para guardarla.
 * 
 * @authors Grupo 3 - A. Rodriguez, I. Teixeira, L. Valladares, D. Suarez
 * @version 2.0
 * 
 */

public class ControladorRegistrarUsuario implements ActionListener {
	private VentRegistro vista;
	private Usuario nuevoUsuario;
	private Cliente cliente;	
	
	/**
	 * Constructor del controlador. Aqui se añaden los Listener a los botones de la vista.
	 * @param vista - Instancia de la ventana para registrar al usuario.
	 * @param cliente - Instancia del modelo en el que se envian los datos del usuario al servidor.
	 */
	public ControladorRegistrarUsuario(VentRegistro vista, Cliente cliente){
		this.vista = vista;
		this.vista.btnRegistrar.addActionListener(this);
		this.vista.btnSeleccionarFoto.addActionListener(this);
		this.vista.btnSalir.addActionListener(this);
		nuevoUsuario = new Usuario();
		this.cliente = cliente;
	}
	
	/**
	 * Controlador de eventos para los botones de la vista.
	 */
	public void actionPerformed(ActionEvent e) {
		boolean flag = false;
		if (vista.btnSeleccionarFoto == e.getSource()){
			VentSeleccionFoto seleccionarFoto = new VentSeleccionFoto();
			seleccionarFoto.frmSeleccioneUnaFoto.setVisible(true);
		}
		if (vista.btnRegistrar == e.getSource()){			
			
			if (vista.rdbtnMasculino.isSelected()) 
				nuevoUsuario.setSexo(true);
			else if (vista.rdbtnFemenino.isSelected()) 
				nuevoUsuario.setSexo(false);
			
			if (nuevoUsuario.setNombreDeUsuario(vista.campoUsuario.getText()) == false){
				vista.usuarioValido.setText("escriba otro username.");
				flag = false;
			}
			else {
				;
				flag = true;
			}
			
			if (nuevoUsuario.setNombreCompleto(vista.campoNombre.getText()) == false){
				vista.nombreValido.setText("escriba nombre valido.");
				flag = false;
			}
			else{	
				flag = true;
			}
			
			if (nuevoUsuario.setFecha(vista.fechaUsuario.getDate()) == false){
				vista.fechaValida.setText("usted es muy joven.");
				flag = false;
			}
			else{
				flag = true;
			}
			
			if (nuevoUsuario.setEmail(vista.campoEmail.getText()) == false){
				vista.emailValido.setText("correo en formato incorrecto");
				flag = false;
			}
			else{
				flag = true;
			}
			
 			if (nuevoUsuario.setClave(String.valueOf(vista.campoContraseña.getPassword())) == false){
 				vista.contraseñaValida.setText("contraseña invalida.");
 				flag = false;
 			}
 			else{
 				flag = true;
 			}
 		}
		
		if (vista.btnSalir == e.getSource()){
		    vista.frmRegistroDeUsuario.dispose();
		}	
		
		if (flag){
			nuevoUsuario.setEmail(Criptologia.Encriptar(nuevoUsuario.getEmail()));
			nuevoUsuario.setClave(Criptologia.Encriptar(nuevoUsuario.getClave()));
			cliente.flujo(nuevoUsuario);
		}
	}
	
}
