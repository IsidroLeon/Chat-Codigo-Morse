package com.ucab.javachat.Cliente.model;

import com.ucab.javachat.Cliente.view.VentPrivada;

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
		try {
			Thread.sleep(duracion);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ventana.txtMensaje.setEditable(true);
		ventana.butEnviar.setEnabled(true);
	}

}
