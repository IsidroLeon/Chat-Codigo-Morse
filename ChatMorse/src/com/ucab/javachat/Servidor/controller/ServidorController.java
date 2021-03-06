package com.ucab.javachat.Servidor.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.ucab.javachat.Servidor.model.ServidorModel;
import com.ucab.javachat.Servidor.view.ServidorView;
/**
 * Clase que controla la parte del servidor
 * @author Grupo 3
 */
public class ServidorController {
	private ServidorView vista;
	
	/**
	 * metodo que se encarga de iniciar la vista del servidor y llama al metodo runServer
	 * @param vista
	 */
	public ServidorController(ServidorView vista) {
		this.vista = vista;
		runServer();
	}
	
	/**
	 * metodo necargado de agregar a la ventana del servidor 
	 * el mensaje que recibe como parametro 
	 * @param msg
	 */
 public void mostrar(String msg)
   {
      vista.txaMostrar.append(msg+"\n");
   }
 
 	/**
 	 * metodo encargado de inicializar los sockets y poder admitir usuarios en la aplicacion 
 	 */
   public void runServer()
   {
      ServerSocket serv = null;//para comunicacion
      ServerSocket serv2 = null;//para enviar mensajes
      boolean listening = true;
      try{
         serv=new ServerSocket(8081);
         serv2=new ServerSocket(8082);
         mostrar(".::Servidor activo :");
         while(listening)
         {
            Socket sock=null,sock2=null;
            try {
               mostrar("Esperando Usuarios");
               sock=serv.accept();
               sock2=serv2.accept();
            } catch (IOException e)
            {
               mostrar("Accept failed: " + serv + ", " + e.getMessage());
               continue;
            }
            ServidorModel user= new ServidorModel(sock,sock2, this);
            mostrar("cliente agregado: "+user);	
            user.start();
            mostrar(".::Esperando Mensajes :");
         }
         
      }catch(IOException e){
         mostrar("error :"+e);
      }
   }

}
