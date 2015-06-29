/*
 * Cliente.java
 *
 * Created on 21 de marzo de 2008, 12:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ucab.javachat.Cliente.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ucab.javachat.Cliente.controller.ControladorCliente;
import com.ucab.javachat.Cliente.model.Usuario;

/**
 * Modelo del cliente que utiliza el chat. Crea la comunicar mediante sockets con el sevidor.
 *
 * @author Grupo 3 - A. Rodriguez, I. Teixeira, L. Valladares, D. Suarez
 * @version 2.0
 */
public class Cliente{
   public static String IP_SERVER;
   ControladorCliente vent;
   DataInputStream entrada = null;
   DataOutputStream salida = null;
   DataInputStream entrada2 = null;
   Socket comunication = null;//para la comunicacion
   Socket comunication2 = null;//para recivir msg
   
   /** 
    * Creates a new instance of Cliente.
    */
   public Cliente(ControladorCliente vent) throws IOException
   {      
      this.vent = vent;
   }
   
   public Cliente() throws IOException
   {      
	   
   }
   
   /** Metodo que inicializa los sockets con el servidor y envia el nombre de usuario
    * y la contraseña para la autenticacion en el servidor
    * 
    * @param nombre nombre de usuario
    * @param clave clave del usuario (cifrada)
    * @throws IOException
    */
   public Usuario conexion(String nombre, String clave, File imagen) throws IOException {
	   Gson gson = new Gson();
	   String autenticadoJson = "";
	   Usuario autenticado = new Usuario();
	   try {
		   comunication = new Socket(Cliente.IP_SERVER, 8081); //envia
		   comunication2 = new Socket(Cliente.IP_SERVER, 8082); //recibe
		   entrada = new DataInputStream(comunication.getInputStream()); // envia al cliente
		   salida = new DataOutputStream(comunication.getOutputStream()); // envia al cliente
		   entrada2 = new DataInputStream(comunication2.getInputStream());
		   vent.setLabelUser();
		   salida.writeInt(1);
		   salida.writeUTF(nombre);
		   salida.writeUTF(clave);
		
           BufferedImage image = ImageIO.read(new File(imagen.getCanonicalPath()));
           ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           ImageIO.write(image, "jpg", byteArrayOutputStream);
           byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
           salida.write(size);
           salida.write(byteArrayOutputStream.toByteArray());
           salida.flush();		   
		   
		   autenticadoJson = entrada.readUTF();
		   if (!autenticadoJson.trim().equals("Fallo")) {
			   autenticado = gson.fromJson(autenticadoJson, new TypeToken<Usuario>() {}.getType());
		   }
	   } catch (IOException e) {
		   JOptionPane.showMessageDialog(null, "Imposible conectar con el servidor actualmente", "Problema de conexión", JOptionPane.INFORMATION_MESSAGE);
		   System.out.println("\tEl servidor no esta levantado");
		   System.out.println("\t=============================");
	   }
	   if (autenticadoJson != "Fallo") {
		   new ThreadCliente(entrada2, vent).start();
	   }
	   
	   return autenticado;
   }
   
   	/** Envia los datos del nuevo usuario al servidor para realizar las validaciones necesarias del lado del servidor
   	 * y su almacenamiento en el archivo para el registro
	 * @param nuevoUsuario - Datos del nuevo usuario a registrar en el sistema
	 * @throws IOException
	 */
   public int conexion(Usuario nuevoUsuario) throws IOException {
	   int flag = 4; // Error desconocido
	   try {
		   comunication = new Socket(Cliente.IP_SERVER, 8081); //envia
		   comunication2 = new Socket(Cliente.IP_SERVER, 8082); //recibe
		   entrada = new DataInputStream(comunication.getInputStream()); // envia al cliente
		   salida = new DataOutputStream(comunication.getOutputStream()); // envia al cliente
		   entrada2 = new DataInputStream(comunication2.getInputStream());
		   flag = flujo(nuevoUsuario);
	   } catch (IOException e) {
		   JOptionPane.showMessageDialog(null, "Imposible conectar con el servidor actualmente", "Problema de conexión", JOptionPane.INFORMATION_MESSAGE);
		   System.out.println("\tEl servidor no esta levantado");
		   System.out.println("\t=============================");
	   }
	   return flag;
   }
   
   public boolean conexion(String correo, File imagen) throws IOException {
	   boolean flag = false;
	   try {
		   comunication = new Socket(Cliente.IP_SERVER, 8081); //envia
		   comunication2 = new Socket(Cliente.IP_SERVER, 8082); //recibe
		   entrada = new DataInputStream(comunication.getInputStream()); // envia al cliente
		   salida = new DataOutputStream(comunication.getOutputStream()); // envia al cliente
		   entrada2 = new DataInputStream(comunication2.getInputStream());
		   flag = flujo(correo,imagen);
	   } catch (IOException e) {
		   JOptionPane.showMessageDialog(null, "Imposible conectar con el servidor actualmente", "Problema de conexión", JOptionPane.INFORMATION_MESSAGE);
		   System.out.println("\tEl servidor no esta levantado");
		   System.out.println("\t=============================");
	   }
	   return flag;
   }
   
   public String getNombre(){
      return vent.getUsuario();
   }
   
   public void cerrarSockets() {
	   try {
		comunication.close();
		comunication2.close();
	} catch (IOException e) {
		e.printStackTrace();
	}	   
   }
   
   /**
    * Este metodo al ser invocado se encarga de enviar la lista de todos los usarios conectados.
    * @return Vector con los usuarios conectados.
    */
   public Vector<String> pedirUsuarios(){
      Vector<String> users = new Vector<String>();
      try {         
         salida.writeInt(2);
         int numUsers=entrada.readInt();
         for(int i=0;i<numUsers;i++)
            users.add(entrada.readUTF());
      } catch (Exception ex) {
         ex.getMessage();
      }
      return users;
   }
   
   /**
    * Este metodo envia un mensaje a todos los usuarios que estan en una ventana grupal y privada.
    * @param amigos - Usuarios en la ventana privada.
    * @param mens - Mensaje a enviar.
    */
   public void flujo(Vector<String> amigos, String mens, String emisor) { /*flujo para mensaje privado*/
	  Gson gson = new Gson();
      try {             
         System.out.println("el mensaje enviado desde el cliente es :" + mens);
         salida.writeInt(3); //opcion de mensaje a amigo
         salida.writeUTF(mens);
         salida.writeUTF(emisor);
         String jsonamigos = gson.toJson(amigos);
         salida.writeUTF(jsonamigos);
      } catch (IOException e) {
         System.out.println("error...." + e);
      }
   }
   
   /**
    * Este metodo se encarga de enviar una instancia del objeto usuario al servidor, para esto
    * se crea un objeto json en el que se añaden los datos del usuario.
    * @param usuario - Objeto que contiene los datos de un usuario que esta en el proceso de registro.
    */
   	public int flujo(Usuario usuario) {
       int flag = 3; //Error desconocido 
       Gson gson = new Gson();  
       try {         
           salida.writeInt(2);
           String jsonRegistroUsuario = gson.toJson(usuario);
           salida.writeUTF(jsonRegistroUsuario);
           BufferedImage image = ImageIO.read(new File(usuario.getImagen().getCanonicalPath()));
           ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           ImageIO.write(image, "jpg", byteArrayOutputStream);
           byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
           salida.write(size);
           salida.write(byteArrayOutputStream.toByteArray());
           salida.flush();
           flag = entrada.readInt();
        }
        catch (IOException e) {
            System.out.println("error...." + e);
        }
        return flag;
  }
   
   public boolean flujo(String correo, File imagen){
	   boolean flag = false;
	   try{
		   salida.writeInt(3);
		   salida.writeUTF(correo);
		   BufferedImage image = ImageIO.read(new File(imagen.getCanonicalPath()));
           ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           ImageIO.write(image, "jpg", byteArrayOutputStream);
           byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
           salida.write(size);
           salida.write(byteArrayOutputStream.toByteArray());
           salida.flush();			   
		   flag = entrada.readBoolean();
	   } catch(IOException e){
		   System.out.println("error recuperando contraseña..." + e);
	   }
	   return flag;
   }
   
   /**
    * este metodo se encarga de enviar un objeto de tipo Usuario al servidor para modificar
    * sus datos
    * @param usuario Objeto que contiene los datos de un usuario que esta en el proceso de modificacion
    * @param nombreOriginal  - El nombre anterior a la modificación para la busqueda
    * @param imagenCambia - determina si la imagen fue cambiada en la ventana
    * @return 1 si el correo es repetido, 2 si el usuario es repetido, 3 si el error es desconocido
    * 4 si la imagen es repetida
    */
   public int flujo(Usuario usuario, String nombreOriginal, boolean imagenCambia) {
       int flag = 3; //error desconocido
       Gson gson = new Gson();  
       try {         
    	   String jsonRegistroUsuario = gson.toJson(usuario);
    	   salida.writeInt(1);
           salida.writeUTF(jsonRegistroUsuario);
           salida.writeUTF(nombreOriginal);
           salida.writeBoolean(imagenCambia);
           if (imagenCambia) {
	           BufferedImage image = ImageIO.read(new File(usuario.getImagen().getCanonicalPath()));
	           ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	           ImageIO.write(image, "jpg", byteArrayOutputStream);
	           byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
	           salida.write(size);
	           salida.write(byteArrayOutputStream.toByteArray());
	           salida.flush();
           }
           flag = entrada.readInt();
           if (flag == 0) {
        	   vent.setUsuarioAutenticado(usuario);
        	   vent.setUsuario(usuario.getNombreDeUsuario());
           }
        } catch (IOException e) {
            System.out.println("Error.... " + e);
        }
        return flag;
  }
}
