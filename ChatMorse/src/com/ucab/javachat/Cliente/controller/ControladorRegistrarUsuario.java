package com.ucab.javachat.Cliente.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ucab.javachat.Cliente.model.Criptologia;
import com.ucab.javachat.Cliente.model.Usuario;
import com.ucab.javachat.Cliente.view.VentCliente;
import com.ucab.javachat.Cliente.view.VentRegistro;
import com.ucab.javachat.Cliente.model.Validacion;

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
	private boolean flagImagen = false;
	
	
	public Usuario getNuevoUsuario() {
		return nuevoUsuario;
	}
	
	/**
	 * Constructor del controlador. Aqui se añaden los Listener a los botones de la vista.
	 * @param vista - Instancia de la ventana para registrar al usuario.
	 * @param cliente - Instancia del modelo en el que se envian los datos del usuario al servidor.
	 */
	public ControladorRegistrarUsuario(VentRegistro vista){
		this.vista = vista;
		this.vista.btnRegistrar.addActionListener(this);
		this.vista.btnSeleccionarFoto.addActionListener(this);
		this.vista.btnSalir.addActionListener(this);
		this.vista.frmRegistroDeUsuario.setVisible(true);
		this.vista.nombreImagen.setEditable(false);
		nuevoUsuario = new Usuario();
	}
	
	public void cerrarVentana() {
		this.vista.frmRegistroDeUsuario.dispose();
	}
	
	public void correoRepetido() {
		this.vista.emailValido.setText("Este correo ya existe.");
		vista.emailValido.setForeground(Color.RED);
	}
	
	public void usuarioRepetido() {
		this.vista.usuarioValido.setText("Este usuario ya existe");
		vista.usuarioValido.setForeground(Color.RED);
	}
	
	/**
	 * Controlador de eventos para los botones de la vista.
	 */
	public void actionPerformed(ActionEvent e) {
		boolean flag = true;
		Validacion validacion = new Validacion();
		if (vista.btnSeleccionarFoto == e.getSource()){
			if (vista.campoUsuario.getText() != ""){
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter(".jpg", "jpg");
		        chooser.setFileFilter(filtro);
		        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				File archivos = new File ("/home/user");
				chooser.setCurrentDirectory(archivos);
				chooser.setDialogTitle("Seleccione una foto.");
				
				//Elegiremos archivos del directorio;
				chooser.setAcceptAllFileFilterUsed(false);
				
				//Si seleccionamos algún archivo retornaremos su directorio
				if (chooser.showOpenDialog(vista) == JFileChooser.APPROVE_OPTION) {
					File fichero = chooser.getSelectedFile();
					nuevoUsuario.setImagen(fichero);
					vista.nombreImagen.setText(fichero.getName());
					vista.lblImagenSeleccionada.setForeground(new Color(0, 128, 0));
					vista.lblImagenSeleccionada.setText(" imagen seleccionada!");
					flagImagen = true;
				} else {
					vista.lblImagenSeleccionada.setForeground(Color.RED);
					vista.lblImagenSeleccionada.setText("por favor, seleccione una imagen.");
				}
			}
		}
		
		
		if (vista.btnRegistrar == e.getSource()){
			//Validaciones en ventana de botones para seleccionar sexo.
			if (vista.rdbtnMasculino.isSelected()){ 
				nuevoUsuario.setSexo(true);
				vista.lblSexoValido.setText(" ");
			}
			else if (vista.rdbtnFemenino.isSelected()){
				nuevoUsuario.setSexo(false);
				vista.lblSexoValido.setText(" ");
			}
			else{
				vista.lblSexoValido.setForeground(Color.RED);
				vista.lblSexoValido.setText("seleccione su sexo.");
				flag = false;
			}
			
			//validaciones en ventana para el campo de nombre de usuario.
			if (vista.campoUsuario.getText().equals("")){
				vista.usuarioValido.setForeground(Color.RED);
				vista.usuarioValido.setText("escriba un nombre de usuario.");
				flag = false;
			}
			else if (validacion.validarUsuario(vista.campoUsuario.getText()) == false){
				vista.usuarioValido.setForeground(Color.RED);
				vista.usuarioValido.setText("ingrese otro nombre de usuario.");
				flag = false;
			}
			else{
				vista.usuarioValido.setForeground(new Color(0, 128, 0));
				vista.usuarioValido.setText(" campo valido!");
				nuevoUsuario.setNombreDeUsuario(vista.campoUsuario.getText());
			}
			//Validaciones en ventana para el campo de nombre completo del usuario
			
			if (vista.campoNombre.getText().equals("")){
				vista.nombreValido.setForeground(Color.RED);
				vista.nombreValido.setText("escriba su nombre completo.");
				flag = false;
			}
			else if (validacion.validarNombreCompleto(vista.campoNombre.getText()) == false){
				vista.nombreValido.setForeground(Color.RED);
				vista.nombreValido.setText("escriba nombre valido.");
				flag = false;
			}
			else{
				vista.nombreValido.setForeground(new Color(0, 128, 0));
				vista.nombreValido.setText(" campo valido!");
				nuevoUsuario.setNombreCompleto(vista.campoNombre.getText());
			}
			
			//Validaciones en ventana para el campo de la fecha.
			if (vista.fechaUsuario.getDate() == null){
				vista.fechaValida.setForeground(Color.RED);
				vista.fechaValida.setText("seleccione una fecha.");
				flag = false;
			}	
			else if (validacion.validarFecha(vista.fechaUsuario.getDate()) == false){
				vista.fechaValida.setForeground(Color.RED);
				vista.fechaValida.setText("usted es muy joven.");
				flag = false;
			}
			else{
				vista.fechaValida.setForeground(new Color(0, 128, 0));
				vista.fechaValida.setText(" campo valido!");
				nuevoUsuario.setFecha(vista.fechaUsuario.getDate());
			}
			
			//validaciones en ventana para el campo de email.
			if (vista.campoEmail.getText().equals("")){
				vista.emailValido.setForeground(Color.RED);
				vista.emailValido.setText("escriba un correo electronico.");
				flag = false;
			}
			else if (validacion.validarEmail(vista.campoEmail.getText()) == false){
				vista.emailValido.setForeground(Color.RED);
				vista.emailValido.setText("correo en formato incorrecto.");
				flag = false;
			}
			else{
				vista.emailValido.setForeground(new Color(0, 128, 0));
				vista.emailValido.setText(" campo valido!");
				nuevoUsuario.setEmail(vista.campoEmail.getText());
			}
			
			//validaciones en ventana para el campo de contraseña
			if(String.valueOf(vista.campoContraseña.getPassword()).equals("")){
				vista.contraseñaValida.setForeground(Color.RED);
 				vista.contraseñaValida.setText("escriba una contraseña.");
 				flag = false;
			}
				
			else if (validacion.validarContraseña(String.valueOf(vista.campoContraseña.getPassword())) == false){
 				vista.contraseñaValida.setForeground(Color.RED);
 				vista.contraseñaValida.setText("contraseña invalida.");
 				flag = false;
 			}
 			else{
 				vista.contraseñaValida.setForeground(new Color(0, 128, 0));
 				vista.contraseñaValida.setText(" campo valido!");
 				nuevoUsuario.setClave(String.valueOf(vista.campoContraseña.getPassword()));
 			}
			// validaciones en ventana para el campo de repetir la contraseña
 			if (String.valueOf(vista.campoContraseña.getPassword()).equals(String.valueOf(vista.campoRepContraseña.getPassword()))){
 				vista.lblContraseñaIgual.setForeground(new Color(0, 128 , 0));
 				vista.lblContraseñaIgual.setText("la contraseña coincide.");
 			}
 			else{
 				vista.lblContraseñaIgual.setForeground(Color.RED);
 				vista.lblContraseñaIgual.setText("la contraseña no coincide.");
 				flag = false;
 			}
 			System.out.println(flag + "     "+flagImagen);
 			if (flag && flagImagen) {
 				nuevoUsuario.setEmail(Criptologia.encriptar(nuevoUsuario.getEmail()));
 				nuevoUsuario.setClave(Criptologia.encriptar(nuevoUsuario.getClave()));
 				VentCliente ventana = new VentCliente();
 				new ControladorCliente(ventana, this);
 			}
 		}
		
		if (vista.btnSalir == e.getSource()){
			flag = false;
			
		    vista.frmRegistroDeUsuario.dispose();
		}	
	}
}
