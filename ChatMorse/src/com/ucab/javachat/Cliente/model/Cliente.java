/*
 * Cliente.java
 *
 * Created on 21 de marzo de 2008, 12:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ucab.javachat.Cliente.model;

import java.io.*;
import java.net.*;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.JOptionPane;

import com.ucab.javachat.Cliente.controller.ControladorCliente;

/**
 *
 * @author Administrador
 */
public class Cliente{
   public static String IP_SERVER;
   ControladorCliente vent;
   DataInputStream entrada = null;
   DataOutputStream salida = null;
   DataInputStream entrada2 = null;
   Socket comunication = null;//para la comunicacion
   Socket comunication2 = null;//para recivir msg
   
   String nomCliente;
   /** Creates a new instance of Cliente */
   public Cliente(ControladorCliente vent) throws IOException
   {      
      this.vent=vent;
   }
   
   public void conexion() throws IOException {
      try {
         comunication = new Socket(Cliente.IP_SERVER, 8081); //envia
         comunication2 = new Socket(Cliente.IP_SERVER, 8082); //recibe
         entrada = new DataInputStream(comunication.getInputStream()); // envia al cliente
         salida = new DataOutputStream(comunication.getOutputStream()); // envia al cliente
         entrada2 = new DataInputStream(comunication2.getInputStream());
         nomCliente = JOptionPane.showInputDialog("Introducir Nick :");
         vent.setNombreUser(nomCliente);         
         salida.writeUTF(nomCliente);
      } catch (IOException e) {
         System.out.println("\tEl servidor no esta levantado");
         System.out.println("\t=============================");
      }
      new threadCliente(entrada2, vent).start();
   }
   
   public String getNombre(){
      return nomCliente;
   }
   
   public Vector<String> pedirUsuarios(){
      Vector<String> users = new Vector();
      try {         
         salida.writeInt(2);
         int numUsers=entrada.readInt();
         for(int i=0;i<numUsers;i++)
            users.add(entrada.readUTF());
      } catch (IOException ex) {
         ex.getMessage();
      }
      return users;
   }
   
   public void flujo(String mens) {
      try {             
         System.out.println("el mensaje enviado desde el cliente es :" + mens);
         salida.writeInt(1);
         salida.writeUTF(mens);
      } catch (IOException e) {
         System.out.println("error...." + e);
      }
   }
   
   public void flujo(Vector<String> amigos,String mens) { /*flujo para mensaje privado*/
	  Gson gson = new Gson();
      try {             
         System.out.println("el mensaje enviado desde el cliente es :" + mens);
         salida.writeInt(3);//opcion de mensaje a amigo
         salida.writeUTF(mens);
         String jsonamigos = gson.toJson(amigos);
         salida.writeUTF(jsonamigos);
      } catch (IOException e) {
         System.out.println("error...." + e);
      }
   }
   
  
}
