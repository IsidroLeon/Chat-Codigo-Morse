package com.ucab.javachat.Cliente.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ucab.javachat.Cliente.model.Cliente;
import com.ucab.javachat.Cliente.model.Criptologia;
import com.ucab.javachat.Cliente.model.Usuario;
import com.ucab.javachat.Cliente.model.Validacion;
import com.ucab.javachat.Cliente.view.VentModificar;

/** Clase encargada de la ventana que se muestra cuando se desean hacer 
 * cambios en la informacion de un usuario.
 * @author Grupo 3 - A. Rodriguez, I. Teixeira, L. Valladares, D. Suarez
 */

public class ControladorModificar  implements ActionListener {
	private Usuario usuarioModificar;
	private VentModificar vista;
	private Cliente cliente;
	private boolean flag;
	private boolean flagImagen = false;
	public ControladorModificar(final VentModificar vista, Cliente cliente) {
		 this.vista = vista;
		 this.cliente = cliente;
	}
	
	/**
	 * metodo encargado de completar los campos de textos con la informacion que se 
	 * posee en el archivo de registro.
	 * @param modelo
	 */
	public final void cargarDatos(final Usuario modelo) {
		 this.vista = new VentModificar();
		 this.vista.dateChooser.setDate((modelo.getFecha()));
		 this.vista.rdbtnMasculino.setSelected(modelo.isSexo());
		 this.vista.rdbtnFemenino.setSelected(!modelo.isSexo());
		 this.vista.textoNombre.setText(modelo.getNombreCompleto());
		 try {
			this.vista.textoEmail.setText(Criptologia.desencriptar(modelo.getEmail()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		 this.vista.textoUsuario.setText(modelo.getNombreDeUsuario());
		 this.vista.botonSalir.addActionListener(this);
	     this.vista.botonGuardar.addActionListener(this);
	     this.vista.btnImagen.addActionListener(this);
	     usuarioModificar = modelo;
	     this.vista.setVisible(true);
	}
	
	/**
	 * metodo que muestra un label de error con el mensaje: "Este usuario ya existe"
	 */
	public final void usuarioRepetido() {
		vista.lblUsuarioErr.setText("Este usuario ya existe");
		vista.lblUsuarioErr.setForeground(Color.RED);
	}
	
	/**
	 * metodo que muestra un label de error con el mensaje: "Este correo ya existe"
	 */
	public final void correoRepetido() {
		vista.lblEmailErr.setText("Este correo ya existe");
	    vista.lblEmailErr.setForeground(Color.RED);
	}

	/**
	 * metodo encargado de la accion de los botones y campos de textos en la ventana que se muestra
	 */
	public final void actionPerformed(final ActionEvent evento) {
		Validacion validacion = new Validacion();
		flag = true;		
		if (vista.btnImagen == evento.getSource()) {
			if (vista.textoUsuario.getText() != "") {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter(".jpg", "jpg");
		        chooser.setFileFilter(filtro);
		        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				File archivos = new File("/home/user");
				chooser.setCurrentDirectory(archivos);
				chooser.setDialogTitle("Seleccione una foto.");
				
				//Elegiremos archivos del directorio;
				chooser.setAcceptAllFileFilterUsed(false);
				
				//Si seleccionamos algún archivo retornaremos su directorio
				if (chooser.showOpenDialog(vista) == JFileChooser.APPROVE_OPTION) {
					File fichero = chooser.getSelectedFile();
					usuarioModificar.setImagen(fichero);
					vista.nombreImagen.setText(fichero.getName());
					vista.lblFotoErr.setForeground(new Color(0,128,0));
					vista.lblFotoErr.setText(" imagen seleccionada!");
					flagImagen = true;
				} else {
					vista.lblFotoErr.setForeground(Color.RED);
					vista.lblFotoErr.setText("por favor, seleccione una imagen.");	
				}
			}
		}	
		if (vista.botonGuardar == evento.getSource()) {
			if (!validacion.validarUsuario(vista.textoUsuario.getText())) {
					vista.lblUsuarioErr.setText("Nombre de usuario invalido");
					vista.lblUsuarioErr.setForeground(Color.RED);
					flag = false;	
				} else {
					vista.lblUsuarioErr.setText(" "); 
					usuarioModificar.setNombreDeUsuario(vista.textoUsuario.getText());
				}
			
			 if (!validacion.validarNombreCompleto(vista.textoNombre.getText())) {
					vista.lblNombreErr.setText("Nombre  invalido");
					vista.lblNombreErr.setForeground(Color.RED);
					flag = false;
				} else {
					vista.lblNombreErr.setText(" ");
					usuarioModificar.setNombreCompleto(vista.textoNombre.getText());
				}
			 
			 if (!validacion.validarEmail(vista.textoEmail.getText())) {
				    vista.lblEmailErr.setText("Email invalido");
				    vista.lblEmailErr.setForeground(Color.RED);
					flag = false;
				} else {
					 vista.lblEmailErr.setText(" ");
					 usuarioModificar.setEmail(vista.textoEmail.getText());
					 usuarioModificar.setEmail(Criptologia.encriptar(usuarioModificar.getEmail()));
				}
			
			 if (vista.grupoSexo.isSelected(vista.grupoSexo.getSelection())) {
				usuarioModificar.setSexo(true);
			} else if (vista.grupoSexo.isSelected(vista.grupoSexo.getSelection())) {
				usuarioModificar.setSexo(false);
			}
			 
			 if (!(String.valueOf(vista.textoContraseña.getPassword()).equals(""))) {
				 if (Arrays.equals(vista.textoContraseña.getPassword(), vista.textoRepetirContraseña.getPassword())) {
					 if ((validacion.validarContraseña(String.valueOf(vista.textoContraseña.getPassword())))) {
						 usuarioModificar.setClave(String.valueOf(vista.textoContraseña.getPassword()));
						 usuarioModificar.setClave(Criptologia.encriptar(usuarioModificar.getClave()));
					 } else {
						 flag = false;
						 vista.lblContraseñaErr.setForeground(Color.RED);
						 vista.lblContraseñaErr.setText("La contraseña no cumple el patron");
					 }
				 } else {
					 flag = false;
					 vista.lblContraseñaErr.setForeground(Color.RED);
					 vista.lblContraseñaErr.setText("Las contraseñas no coinciden");
				 }
			 }
			 if (validacion.validarFecha(vista.dateChooser.getDate())) {
				 usuarioModificar.setFecha(vista.dateChooser.getDate());
				 vista.lblFechaErr.setText("");
			 } else {
				 flag = false;
				 vista.lblFechaErr.setForeground(Color.RED);
				 vista.lblFechaErr.setText("Fecha invalida");
			 }
			 if (flag) {
				 int opcion = cliente.flujo(usuarioModificar, cliente.getNombre(), flagImagen);	
				 if (opcion == 0) { // Todo bien
					 this.vista.setVisible(false);
				 } else if (opcion == 1) { // El correo ya existe
					 this.correoRepetido();
				 } else if (opcion == 2) { // EL usuario ya existe
					 this.usuarioRepetido();
				 } else if (opcion == 4) { //Error guardando la imagen
					 JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar modificar la imagen,"
					 		+ " intente con otra.", "Problema de modificacion", JOptionPane.INFORMATION_MESSAGE);
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "La modificación no fue satisfactoria.", 
							 "Problema de modificacion", JOptionPane.INFORMATION_MESSAGE);
				 }
			 }
		}
		else if (vista.botonSalir == evento.getSource()) {
			flag = false;
			  
		vista.setVisible(false);
	    vista.dispose();
		}
	}
	
	/**
	 * getters y setters
	 */
	public final Usuario getUsuarioModificar() {
		return usuarioModificar;
	}

	public final void setUsuarioModificar(final Usuario usuarioModificar) {
		this.usuarioModificar = usuarioModificar;
	}

	public final boolean isFlag() {
		return flag;
	}

	public final void setFlag(final boolean flag) {
		this.flag = flag;
	}
}
