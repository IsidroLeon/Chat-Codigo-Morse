package com.ucab.javachat.Cliente.model;
import java.net.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ucab.javachat.Cliente.controller.ControladorCliente;
import com.ucab.javachat.Cliente.view.VentCliente;

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
      String[] amigo = new String[10];
      int opcion=0;
      while(true)
      {         
         try{
            opcion=entrada.readInt();
            switch(opcion)
            {
               case 1://mensaje enviado
                  menser=entrada.readUTF();
                  System.out.println("ECO del servidor:"+menser);
                  vcli.mostrarMsg(menser);            
                  break;
               case 2://se agrega
                  menser=entrada.readUTF();
                  vcli.agregarUser(menser);                  
                  break;
               case 3://mensaje de amigo
            	  /*for (int i=0; i<11; i++){ 	//lo que pense y esta malo :)
            		  amigo[i]=entrada.readUTF();
                  }*/
            	  amigo=entrada.readUTF();
                  menser=entrada.readUTF();
                  vcli.mensajeAmigo(amigo,menser);
                  System.out.println("ECO del servidor:"+menser);
                  break;
            }
         }
         catch (IOException e){
            System.out.println("Error en la comunicación "+"Información para el usuario");
            break;
         }
      }
      System.out.println("se desconecto el servidor");
   }

   
}