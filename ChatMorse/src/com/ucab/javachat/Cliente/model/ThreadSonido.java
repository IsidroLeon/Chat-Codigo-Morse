package com.ucab.javachat.Cliente.model;

import com.ucab.javachat.Cliente.view.VentPrivada;

/**
 * Clase que se encarga de cargar los componentes visuales al enviar un mensaje para 
 * la reproduccion en sonido del mensaje enviado. Bloque el campo de texto para que no se
 * pueda escribir mientras esta la reproduccion del mensaje. Esto evita errores en la 
 * reprodccion del mensaje al ser enviado.
 * @author Grupo 3
 *
 */

public class ThreadSonido extends Thread {
	private long duracion;
	private VentPrivada ventana;
	
	public ThreadSonido(String mensaje, VentPrivada ventana) {
		super();
		ReproducirSonido r = new ReproducirSonido(mensaje);
		this.duracion = r.duracionSonido();
		this.ventana = ventana;
	}
	
	public void run() {
		ventana.txtMensaje.setEditable(false);
		ventana.butEnviar.setEnabled(false);
		ventana.btnConvertir.setEnabled(false);
		ventana.txtMensaje.setText("Espere hasta que se termine de reproducir el mensaje");
		try {
			Thread.sleep(duracion);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ventana.txtMensaje.setEditable(true);
		ventana.butEnviar.setEnabled(true);
		ventana.btnConvertir.setEnabled(true);
		ventana.txtMensaje.setText("");
	}

}
