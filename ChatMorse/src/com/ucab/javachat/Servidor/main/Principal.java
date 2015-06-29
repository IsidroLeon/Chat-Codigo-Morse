package com.ucab.javachat.Servidor.main;
/**
 * Clase que se encarga de ejecutar la vista y el 
 * controlador de la parte del servidor de la aplicacion
 * @author Grupo 3
 */
import java.io.IOException;

import com.ucab.javachat.Servidor.controller.ServidorController;
import com.ucab.javachat.Servidor.view.ServidorView;

public class Principal {

	   public static void main(String abc[]) throws IOException
	   {                
	     ServidorView vista = new ServidorView();
	     new ServidorController(vista);
	   }

}
