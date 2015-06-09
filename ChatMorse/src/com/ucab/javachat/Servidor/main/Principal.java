package com.ucab.javachat.Servidor.main;

import java.io.IOException;

import com.ucab.javachat.Servidor.controller.ServidorController;
import com.ucab.javachat.Servidor.view.ServidorView;

public class Principal {

	   public static void main(String abc[]) throws IOException
	   {                
	     ServidorView vista = new ServidorView();
	     ServidorController controlador = new ServidorController(vista);
	   }

}
