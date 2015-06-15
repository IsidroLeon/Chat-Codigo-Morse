package com.ucab.javachat.Cliente.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import com.ucab.javachat.Cliente.model.Cliente;
import com.ucab.javachat.Cliente.view.VentPrivada;

public class ControladorPrivada implements ActionListener {
	private Cliente cliente;
	private VentPrivada ventana;

	public ControladorPrivada(VentPrivada ventana, Cliente cliente) {
		this.ventana = ventana;
		this.cliente = cliente;
		ventana.txtMensaje.addActionListener(this);
	    ventana.butEnviar.addActionListener(this);
	  this.ventana.addWindowListener(new WindowListener()
	  {         
	     public void windowClosing(WindowEvent e) {
	        cerrarVentana();
	     }
	     public void windowClosed(WindowEvent e) {}         
	     public void windowOpened(WindowEvent e) {}
	     public void windowIconified(WindowEvent e) {}
	     public void windowDeiconified(WindowEvent e) {}
	     public void windowActivated(WindowEvent e) {}
	     public void windowDeactivated(WindowEvent e) {}
	    
	  });
	}
	
		public void setAmigo(Vector<String> amigos) {
			ventana.setVisible(true);
			ventana.amigo = amigos;
			String usuarios = amigos.toString();
			ventana.setTitle("Conversación con: "+usuarios);
			//this.mostrarMsg("Estas habland con; "+usuarios);
	   }
	
	    private void cerrarVentana() {       
	      ventana.setVisible(false);      
	    }
	    
	    public void mostrarMsg(String msg){
	        ventana.panMostrar.append(msg+"\n");
	     }
	    
	   @Override
	   public void actionPerformed(ActionEvent e) 
	   {
	      String mensaje = ventana.txtMensaje.getText();  
	      //mostrarMsg(cliente.getNombre()+">"+mensaje);
	      cliente.flujo(ventana.amigo, mensaje);
	      ventana.txtMensaje.setText("");	  
	   }

}
