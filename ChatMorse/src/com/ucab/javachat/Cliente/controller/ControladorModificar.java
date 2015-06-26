package com.ucab.javachat.Cliente.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ucab.javachat.Cliente.model.Cliente;
import com.ucab.javachat.Cliente.model.Criptologia;
import com.ucab.javachat.Cliente.model.Usuario;
import com.ucab.javachat.Cliente.model.Validacion;
import com.ucab.javachat.Cliente.view.VentModificar;

/**
 * 
 * @author Grupo 3 - A. Rodriguez, I. Teixeira, L. Valladares, D. Suarez
 *
 */

public class ControladorModificar  implements ActionListener {
	private Usuario UsuarioModificar;
	VentModificar vista;
	Cliente cliente;
	private boolean flag;
	public ControladorModificar(VentModificar vista, Usuario modelo, Cliente cliente) {
		 this.vista = vista ;
		 this.cliente = cliente;
		 this.vista.dateChooser.setDate((modelo.getFecha()));;
		 this.vista.rdbtnMasculino.setSelected(modelo.isSexo());
		 this.vista.rdbtnFemenino.setSelected(!modelo.isSexo());
		 this.vista.textoNombre.setText(modelo.getNombreCompleto());
		 try {
			this.vista.textoEmail.setText(Criptologia.desencriptar(modelo.getEmail()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.vista.textoEmail.setText(modelo.getEmail());
		 this.vista.textoUsuario.setText(modelo.getNombreDeUsuario());
		 this.vista.botonSalir.addActionListener(this);
	     this.vista.botonGuardar.addActionListener(this);
	     UsuarioModificar = modelo;
	}

	public void actionPerformed(ActionEvent evento) {
		if(vista.botonGuardar == evento.getSource()){
			Validacion validacion = new Validacion();
			 
			flag=true;
			 
			
			 
			 if (validacion.validarUsuario(vista.textoUsuario.getText()) == false){
					vista.textoUsuario.setText("nombre de usuario no valido");
					flag = false;
				}
				else{
					
					 UsuarioModificar.setNombreDeUsuario(vista.textoUsuario.getText());
					 vista.textoUsuario.setText(" ");
				}
			
			 if(validacion.validarNombreCompleto(vista.textoNombre.getText()) == false){
					vista.textoNombre.setText("ingrese nombre completo valido.");
					flag = false;
				}
				else{
		            UsuarioModificar.setNombreCompleto(vista.textoNombre.getText());
		            vista.textoNombre.setText(" ");
				}
			 
			 if (validacion.validarEmail(vista.textoEmail.getText())==false){
				    vista.textoEmail.setText("invalido");
					flag = false;
				}
				else{
					 UsuarioModificar.setEmail(vista.textoEmail.getText());
					vista.textoEmail.setText(" ");	
				}
			
			 if (vista.grupoSexo.isSelected(vista.grupoSexo.getSelection()) == true)
				 UsuarioModificar.setSexo(true);
			else if (vista.grupoSexo.isSelected(vista.grupoSexo.getSelection()) == false)
				 UsuarioModificar.setSexo(false);
			 
			 UsuarioModificar.setFecha(vista.dateChooser.getDate());
				 
			 if (flag) {
				 UsuarioModificar.setEmail(Criptologia.encriptar(UsuarioModificar.getEmail()));
				 cliente.flujo(UsuarioModificar, cliente.getNombre());		
			 }
				
		
			
		}
		else if (vista.botonSalir == evento.getSource()) {
		  vista.setVisible(false);
	      vista.dispose();
	    
		}
	}

	public Usuario getUsuarioModificar() {
		return UsuarioModificar;
	}

	public void setUsuarioModificar(Usuario usuarioModificar) {
		UsuarioModificar = usuarioModificar;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}



	

}
