package com.ucab.javachat.Cliente.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ucab.javachat.Cliente.view.VentCliente;
import com.ucab.javachat.Cliente.view.VentIniciarSesion;
import com.ucab.javachat.Cliente.view.VentRecuperarContraseña;
import com.ucab.javachat.Cliente.view.VentRegistro;
import com.ucab.javachat.Cliente.model.Validacion;

/** Clase del controlador para la ventana de inicio de sesion
 * @author Grupo 3
 * */

public class ControladorIniciarSesion implements ActionListener {
	private VentIniciarSesion vista;
	private String usuario;
	private String clave;
	private File imagen = null;
	private Validacion validar = new Validacion();
	
	public ControladorIniciarSesion(final VentIniciarSesion vista) {
		this.vista = vista;
		this.vista.frmInicioDeSesion.setVisible(true);
		
		/*Definicion de botones*/
		this.vista.btnEnviar.addActionListener(this);
		this.vista.btnArchivo.addActionListener(this);
		this.vista.btnContrasena.addActionListener(this);
		this.vista.btnRegistro.addActionListener(this);
	}
	
	/**
	 * Metodo que valida los datos ingresados con el archivo Json de registro.
	 * @return boolean flag. Si los datos son correctos el valor devuelto sera verdadero.
	 * Si alguno de los datos ingresados no coincide con el archivo de registro el valor 
	 * devuelto sera falso.
	 */
	public final boolean validarInicioSesion() {
		boolean flag = true;
		if (this.imagen == null) {
			flag = false;
			vista.lblValidacion.setForeground(Color.RED);
			vista.lblValidacion.setText("       escoja una imagen.");	
		}
		if (validar.validarUsuario(vista.txtUsuario.getText())) {
			setUsuario(vista.txtUsuario.getText());
		}
		else { // devuelve error de usuario
			vista.lblValidacion.setForeground(Color.RED);
			vista.lblValidacion.setText("Usuario no cumple el formato");
			flag = false;
		}
		if(validar.validarContraseña(String.valueOf(vista.txtClave.getPassword()))) {
			setClave(String.valueOf(vista.txtClave.getPassword()));
			
		} else { //devuelve error de clave
			vista.lblValidacion.setForeground(Color.RED);
			vista.lblValidacion.setText("Clave no cumple el formato");
			flag = false;
		}

		return flag;
	}
	
	public void cerrarVentana() {
		this.vista.frmInicioDeSesion.dispose();
	}
	
	public String getUsuario() {
		return this.usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getClave() {
		return this.clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public File getImagen() {
		return imagen;
	}

	public void setImagen(File imagen) {
		this.imagen = imagen;
	}
	
	/**
	 * metodo encargado de la accion de cada boton en la ventana de inicio de sesion.
	 */
	public void actionPerformed(ActionEvent e) {
		if (vista.btnEnviar == e.getSource())	
			if (validarInicioSesion()) {
				VentCliente vistaCliente = new VentCliente(); // Si el inicio de sesion es valido crea la ventana
				new ControladorCliente(vistaCliente, this);
				this.vista.txtClave.setText("");
				this.vista.txtUsuario.setText("");
				this.imagen = null;
				vista.lblValidacion.setText("");
			}
		
		if (vista.btnContrasena == e.getSource()){
			VentRecuperarContraseña ventana = new VentRecuperarContraseña();
			new ControladorRecuperarContraseña(ventana);
		}
		
		if (vista.btnArchivo == e.getSource()){
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filtro = new FileNameExtensionFilter(".jpg", "jpg");
	        chooser.setFileFilter(filtro);
	        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			File archivos = new File ("/home/user");
			chooser.setCurrentDirectory(archivos);
			chooser.setDialogTitle("Seleccione una foto.");
			//Elegiremos archivos del directorio
			chooser.setAcceptAllFileFilterUsed(false);
			//Si seleccionamos algún archivo retornaremos su directorio
			if (chooser.showOpenDialog(vista) == JFileChooser.APPROVE_OPTION) {
				this.imagen = chooser.getSelectedFile();
				vista.lblValidacion.setForeground(new Color (0, 128, 0));
				vista.lblValidacion.setText("     Imagen seleccionada!");	
			}
			else {
				vista.lblValidacion.setForeground(Color.RED);
				vista.lblValidacion.setText("       Escoja una imagen.");	
			}
		}
		
		if (vista.btnRegistro == e.getSource()){
			VentRegistro vistaReg = new VentRegistro();
			new ControladorRegistrarUsuario(vistaReg);
			this.vista.frmInicioDeSesion.dispose();
		}
	}

}
