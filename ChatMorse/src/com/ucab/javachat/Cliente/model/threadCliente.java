package com.ucab.javachat.Cliente.model;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ucab.javachat.Cliente.controller.ControladorCliente;

/**
 * Este hilo es el encargado de notificar a cada usuario conectado cuando 
 * recibe un mensaje y cuando se conecta otro usuario.Tambien se encarga 
 * de enviar los mensajes del usuario al servidor.
 * 
 * @author Grupo 3 - A. Rodriguez, I. Teixeira, L. Valladares, D. Suarez
 * @version 2.0
 */
class threadCliente extends Thread{
   DataInputStream entrada;
   ControladorCliente vcli;
   public threadCliente (DataInputStream entrada,ControladorCliente vcli) throws IOException
   {
      this.entrada=entrada;
      this.vcli=vcli;
   }
   
   public void run()
   {
      String menser=""/*,amigo=""*/;
      Vector<String> amigos = new Vector<String>();
      int opcion=0;
      while(true)
      {         
         try{
            opcion = entrada.readInt();
            switch(opcion)
            {
               case 1: //mensaje enviado          
            	   break;
               case 2://se agrega
            	   menser = entrada.readUTF();
            	   vcli.agregarUser(menser);                  
            	   break;
               case 3://mensaje de amigo
            	   Gson gson = new Gson();
            	   menser = entrada.readUTF();
            	   String amigostring = entrada.readUTF();
            	   amigos = gson.fromJson(amigostring, new TypeToken<Vector<String>>() {}.getType());
            	   vcli.mensajeAmigo(menser, amigos);
            	   System.out.println("ECO del servidor:" +menser);
            	   break;
            }
         }
         catch (Exception e){
            System.out.println("Error en la comunicación "+"Información para el usuario");
            break;
         }
      }
      System.out.println("se desconecto el servidor");
   }

   
}