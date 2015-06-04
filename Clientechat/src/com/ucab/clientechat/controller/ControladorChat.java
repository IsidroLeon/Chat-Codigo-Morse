package com.ucab.clientechat.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.ucab.clientechat.view.VentanaChat;
import com.ucab.clientechat.view.VentanaConfiguracion;

public class ControladorChat {
	
	private Logger log = Logger.getLogger(VentanaChat.class);
	private String host;
	private int puerto;
	private String usuario;
	private Socket socket;
	
	/**
     * Recibe los mensajes del chat reenviados por el servidor
     */
    public void recibirMensajesServidor(VentanaChat frame){
        // Obtiene el flujo de entrada del socket
        DataInputStream entradaDatos = null;
        String mensaje;
        try {
			entradaDatos = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            log.error("Error al crear el stream de entrada: " + ex.getMessage());
        } catch (NullPointerException ex) {
            log.error("El socket no se creo correctamente. ");
        }
        
        // Bucle infinito que recibe mensajes del servidor
        boolean conectado = true;
        while (conectado) {
            try {
                mensaje = entradaDatos.readUTF();
                frame.mensajesChat.append(mensaje + System.lineSeparator());
            } catch (IOException ex) {
                log.error("Error al leer del stream de entrada: " + ex.getMessage());
                conectado = false;
            } catch (NullPointerException ex) {
                log.error("El socket no se creo correctamente. ");
                conectado = false;
            }
        }
    }

	public ControladorChat() {	
		// Carga el archivo de configuracion de log4J
        PropertyConfigurator.configure("log4j.properties");        
        VentanaChat vChat = new VentanaChat();
        recibirMensajesServidor(vChat);
        // Ventana de configuracion inicial
        VentanaConfiguracion vConfiguracion = new VentanaConfiguracion(vChat);
        host = vConfiguracion.tfHost.getText();
        puerto = Integer.parseInt(vConfiguracion.tfPuerto.getText());
        usuario = vConfiguracion.tfUsuario.getText();
        
        log.info("Quieres conectarte a " + host + " en el puerto " + puerto + " con el nombre de ususario: " + usuario + ".");
        
        // Se crea el socket para conectar con el Sevidor del Chat
        try {
            socket = new Socket(host, puerto);
        } catch (UnknownHostException ex) {
            log.error("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
        } catch (IOException ex) {
            log.error("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
        }
        
        // Accion para el boton enviar
        vChat.btEnviar.addActionListener(new ConexionServidor(socket, vChat.tfMensaje, usuario));
	}

}
