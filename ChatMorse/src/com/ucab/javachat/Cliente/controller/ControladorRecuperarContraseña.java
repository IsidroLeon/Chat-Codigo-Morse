package com.ucab.javachat.Cliente.controller;

import com.ucab.javachat.Cliente.model.Cliente;
import com.ucab.javachat.Cliente.model.Criptologia;
import com.ucab.javachat.Cliente.model.Validacion;
import com.ucab.javachat.Cliente.view.VentRecuperarContraseña;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase encargada de controlar la ventana de Recuperar contraseña
 * @author Grupo 3
 *
 */

public class ControladorRecuperarContraseña implements ActionListener{

	private VentRecuperarContraseña vista;
	private File imagen = null;
	private boolean flagImagen = false;

	public ControladorRecuperarContraseña (VentRecuperarContraseña vista){
		this.vista = vista;
		this.vista.frameRecuperarContraseña.setVisible(true);
		this.vista.btnEnviar.addActionListener(this);
		this.vista.btnSeleccionar.addActionListener(this);
		this.vista.nombreImagen.setEditable(false);
	}

		
/**
 * metodo encargado de la accion de cada boton y/o campo de texto que se encuentra en la ventana
 */
	public void actionPerformed(ActionEvent e) {	//metodo para enviar la informacion
		boolean flag = true;
		if (vista.btnSeleccionar == e.getSource()){
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
			if (chooser.showOpenDialog(vista.frameRecuperarContraseña) == JFileChooser.APPROVE_OPTION) {
				this.imagen = chooser.getSelectedFile();
				vista.nombreImagen.setText(this.imagen.getName());
				flagImagen = true;
				if (!vista.textField.getText().contains("@")) flag = false;
			}
			else {
				vista.labelValidacion.setText("    por favor, seleccione una imagen.");	
			}
			flag = false;
		}
		String correo = vista.textField.getText();
		if (vista.btnEnviar == e.getSource()){
			Validacion validar = new Validacion();
			if (validar.validarEmail(correo)){
				correo = Criptologia.encriptar(vista.textField.getText());
				vista.labelValidacion.setText("");
			}
			else {
				vista.labelValidacion.setText("   por favor, escriba un correo valido.");
				flag = false;
			}	
		}
		
		if (imagen.length() > 1536000) {
			 JOptionPane.showMessageDialog(null, "La imagen pesa mucho, elija una imagen que"
			 		+ " pese menos de 1.5mb", "Problema de modificacion", JOptionPane.INFORMATION_MESSAGE);
			 flag = false;
		}
		
		if (flag && flagImagen) {
			try {
				Cliente cliente = new Cliente();
				if(cliente.conexion(correo,this.imagen)) {
					vista.frameRecuperarContraseña.dispose();
					 JOptionPane.showMessageDialog(null, "Se ha enviado un correo con su contraseña", "Recuperación", JOptionPane.INFORMATION_MESSAGE);
				} else {
					 JOptionPane.showMessageDialog(null, "Los datos enviados no coinciden con ningún usuario del servidor", "Problema de conexión", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}