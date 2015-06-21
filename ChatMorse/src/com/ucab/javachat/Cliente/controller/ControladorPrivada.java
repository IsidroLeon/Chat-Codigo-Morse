package com.ucab.javachat.Cliente.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import com.ucab.javachat.Cliente.model.Cliente;
import com.ucab.javachat.Cliente.model.CodigoMorse;
import com.ucab.javachat.Cliente.model.ReproducirSonido;
import com.ucab.javachat.Cliente.model.ThreadSonido;
import com.ucab.javachat.Cliente.view.VentPrivada;

public class ControladorPrivada implements ActionListener, KeyListener {
	private Cliente cliente;
	private VentPrivada ventana;
	private ReproducirSonido sonido;
	private boolean esMorse = false;

	public ControladorPrivada(VentPrivada ventana, Cliente cliente) {
		this.ventana = ventana;
		this.cliente = cliente;
		ventana.txtMensaje.addActionListener(this);
		ventana.btnConvertir.addActionListener(this);
	    ventana.butEnviar.addActionListener(this);
	    ventana.txtMensaje.addKeyListener(this);
	    sonido = new ReproducirSonido();
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
	   }
	
	    @SuppressWarnings("deprecation")
		private void cerrarVentana() {    
	    	if(sonido.isAlive())
				  sonido.stop();
	    	String mensaje = "El usuario "+cliente.getNombre()+" se ha retirado de la conversación";
	    	ventana.amigo.remove(cliente.getNombre());
		    cliente.flujo(ventana.amigo, mensaje);
		    ventana.txtMensaje.setText("");
		    ventana.panMostrar.setText("");
		    ventana.setVisible(false);      
	    }
	    
	    public void mostrarMsg(String msg, String usuario){
	    	sonido = new ReproducirSonido(msg);
			sonido.setPriority(1);
		    sonido.start();
		    ThreadSonido thread = new ThreadSonido(msg, ventana);
		    thread.start();
	    	msg = CodigoMorse.traducirMorse(msg);
	    	msg = ""+usuario+">"+msg;
	        ventana.panMostrar.append(msg+"\n");
	     }
	    
	    @SuppressWarnings("deprecation")
		public void keyPressed (KeyEvent e) {
	        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
	        	if(!esMorse) {
					  if(sonido.isAlive())
					  sonido.stop();
			      	String mensaje = CodigoMorse.traducirAlfabeto(ventana.txtMensaje.getText()); 
			      	System.out.println(mensaje);
			      	cliente.flujo(ventana.amigo, mensaje);
			      	this.ventana.txtMensaje.setText("");
			      	ThreadSonido thread = new ThreadSonido(mensaje, ventana);
				    thread.start();
			      	esMorse = false;
				  } else {
					  if(sonido.isAlive())
						  sonido.stop();
				      String mensaje = ventana.txtMensaje.getText();
				      cliente.flujo(ventana.amigo, mensaje);
				      this.ventana.txtMensaje.setText("");
				      ThreadSonido thread = new ThreadSonido(mensaje, ventana);
					    thread.start();
				      esMorse = false;
				  }
	        }
	    }
	   @SuppressWarnings("deprecation")
	@Override
	   public void actionPerformed(ActionEvent e) 
	   {
		  if(e.getSource()==this.ventana.butEnviar) {
			  if(!esMorse) {
				  if(sonido.isAlive())
				  sonido.stop();
		      	String mensaje = CodigoMorse.traducirAlfabeto(ventana.txtMensaje.getText()); 
		      	System.out.println(mensaje);
		      	cliente.flujo(ventana.amigo, mensaje);
		      	this.ventana.txtMensaje.setText("");
		      	ThreadSonido thread = new ThreadSonido(mensaje, ventana);
			    thread.start();
		      	esMorse = false;
			  } else {
				  if(sonido.isAlive())
					  sonido.stop();
			      String mensaje = ventana.txtMensaje.getText();
			      cliente.flujo(ventana.amigo, mensaje);
			      this.ventana.txtMensaje.setText("");
			      ThreadSonido thread = new ThreadSonido(mensaje, ventana);
				  thread.start();
			      esMorse = false;
			  }
		  } else if (e.getSource() == this.ventana.btnConvertir) {
			  if(!esMorse) {
				  ventana.txtMensaje.setText(CodigoMorse.traducirAlfabeto(ventana.txtMensaje.getText()));
				  sonido = new ReproducirSonido(ventana.txtMensaje.getText());
				  sonido.setPriority(1);
			      sonido.start();
				  ventana.txtMensaje.setEditable(false);
				  esMorse = true;
			  } else {
				  if(sonido.isAlive())
					  sonido.stop();
				  ventana.txtMensaje.setText(CodigoMorse.traducirMorse(ventana.txtMensaje.getText()));
				  ventana.txtMensaje.setEditable(true);
				  ventana.txtMensaje.requestFocus(true);
				  esMorse = false;
			  }
		  }
	   }

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
