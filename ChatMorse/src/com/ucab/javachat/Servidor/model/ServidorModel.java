package com.ucab.javachat.Servidor.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ucab.javachat.Servidor.model.EnvioCorreo;
import com.ucab.javachat.Servidor.controller.ServidorController;

/**
 * Esta clase se encarga de recibir informacion para poder enviarla a otros metodos y 
 * lograr un objetivo con ellos. Esta clase es un hilo. 
 * @author Grupo 3
 */
public class ServidorModel extends Thread
{
	 Socket scli = null;
	 Socket scli2 = null;
	 DataInputStream entrada = null;
	 DataOutputStream salida = null;
	 DataOutputStream salida2 = null;
	 public static Vector<ServidorModel> clientesActivos = new Vector<ServidorModel>();	
	 private String nameUser;
	 private String clave;
	 private File imagen;
	 ServidorController serv;
	 ArrayList<Usuario> usuariosArchivo = new ArrayList<Usuario>();
	 Gson gson;
     /**
      * Contructor con parametros
      * @param scliente
      * @param scliente2
      * @param serv
      */
     public ServidorModel(Socket scliente,Socket scliente2,ServidorController serv)
     {
        scli=scliente;
        scli2=scliente2;
        this.serv=serv;
        clientesActivos.add(this);        
        serv.mostrar("cliente agregado: "+this);
        gson = new Gson();
     }
     
     /**
      * getters y setters
      */
     public void setNameUser(String user)
     {
       this.nameUser = user;
     }
     
     public String getNameUser() {
    	 return nameUser;
     }
     
     public void setClave(String clave)
     {
       this.clave = clave;
     }
     
     public String getClave() {
    	 return clave;
     }
     
	public File getImagen() {
		return imagen;
	}

	public void setImagen(File imagen) {
		this.imagen = imagen;
	}

	/** Este metodo tiene varias funciones. Se encarga principalmente del inicio de sesion 
	 * de un usuario en la aplicacion, del registro de un usuario en la aplicacion y del envio
	 * de la clave a la dirrecion de correo de usuarios regstrados que hayan olvidado su clave de 
	 * acceso. Ademas de esto el metodo tambien se encarga de modificar datos de usuarios
	 * registrados, actualizar lista de contactos activos en la aplicacion y enviar mensajes
	 * en la aplicacion. Todo esto esta dentro del metodo implementado run, el cual es 
	 * un metodo que se debe implementar pues hereda de Thread.
	 */
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run()
     {
    	serv.mostrar(".::Esperando Mensajes :");
    	{
    	
          try {
			entrada = new DataInputStream(scli.getInputStream());
			salida = new DataOutputStream(scli.getOutputStream());
	        salida2 = new DataOutputStream(scli2.getOutputStream());
	        int caseInicio = 0;
	        caseInicio = entrada.readInt();
	        switch(caseInicio) {
		        case 1: // Inicio de sesion
		        	iniciarSesion();
		          	break;
		        case 2: // Registro
		        	registrarUsuario();
		        	break;
		        case 3: //Recuperar contraseña 
		        	recuperarContraseña();
		        	break;
	          }
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
        int opcion=0;
        int numUsers=0;
        String mencli = "";
        String amigostring = "";
        Vector<String> amigos = new Vector<String>();
                
    	while(true){
          try{
             opcion = entrada.readInt();
             switch(opcion)
             {
                case 1: // Modificar datos
                	modificarDatos();
                   break;
                case 2://envio de lista de activos
                   numUsers = clientesActivos.size();
                   salida.writeInt(numUsers);
                   for(int i=0;i<numUsers;i++)
                      salida.writeUTF(clientesActivos.get(i).nameUser);
                   break;
                case 3: // envia mensaje privado
                    mencli = entrada.readUTF();//mensaje enviado
                    String emisor = entrada.readUTF();
                    amigostring = entrada.readUTF();
                    amigos = gson.fromJson(amigostring, new TypeToken<Vector<String>>() {}.getType()); 
                    enviaMensaje(mencli, emisor, amigos, amigostring);
                   	break;
	             }
          }
          	catch (IOException e) {
          	System.out.println("El cliente termino la conexion.");
          	break;
          }
    	}
    	serv.mostrar("Se removio un usuario.");
    	clientesActivos.removeElement(this);
    	try{
    		serv.mostrar("Se desconecto un usuario.");
    		scli.close();
    	}	
        catch(Exception et){
        	serv.mostrar("No se puede cerrar el socket.");
        	}   
    	}
     }
     
     /**
     * Metodo utilizado para iniciar sesion en el sistea, recibe el nombre la clave y la imagen y lo contrasta
     * con los datos almacenados en el servidor
     * @throws IOException 
     */
    private void iniciarSesion() throws IOException {
    	byte[] sizeAr;
	   	 int size;
	   	 byte[] imageAr;
	   	 BufferedImage image;
	   	 File dir;
		 this.setNameUser(entrada.readUTF());
		 this.setClave(entrada.readUTF());
   		 dir = new File("." + "/Documentos/verificacionDe" + getNameUser() + ".jpg");
         sizeAr = new byte[4];
         entrada.read(sizeAr);
         size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
         imageAr = new byte[size];
         entrada.readFully(imageAr);
         image = ImageIO.read(new ByteArrayInputStream(imageAr));
         ImageIO.write(image, "jpg", new File(dir.getCanonicalPath()));
         this.imagen = dir.getCanonicalFile();
   		Autenticacion inicioDeSesion = new Autenticacion(this.getNameUser(), this.getClave(), this.getImagen());
   		Usuario autenticado = inicioDeSesion.autenticar();
   		if (autenticado != null) {
   			 this.setNameUser(autenticado.getNombreDeUsuario());
       		 String autenticadoJson =  gson.toJson(autenticado);
             salida.writeUTF(autenticadoJson);
             serv.mostrar("Ha iniciado sesion: "+this.getNameUser());
   		} else {
   			salida.writeUTF("Fallo");
   			scli.close();
             scli2.close();
   		}
   		if (this.imagen.delete()) {
   			serv.mostrar("Se ha eliminado la imagen de verificaion.");
   		}
     }
    
    /** recibe un objeto usuario por el stream y lo almacena junto con su imagen en el servidor
     * @throws IOException
     */
    private void registrarUsuario() throws IOException {
    	byte[] sizeAr;
	   	 int size;
	   	 byte[] imageAr;
	   	 BufferedImage image;
    	String usuarioRegistroJson = entrada.readUTF();
        Usuario usuarioRegistro = gson.fromJson(usuarioRegistroJson, new TypeToken<Usuario>() {}.getType());
        File miDir = new File ("." + "/Documentos/Imagenes de Verificacion/" +
                usuarioRegistro.getNombreDeUsuario() + ".jpg");
        sizeAr = new byte[4];
        entrada.read(sizeAr);
        size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
        imageAr = new byte[size];
        entrada.readFully(imageAr);
        image = ImageIO.read(new ByteArrayInputStream(imageAr));
        ImageIO.write(image, "jpg", new File(miDir.getCanonicalPath()));
        usuarioRegistro.setImagen(miDir.getCanonicalFile());
        Autenticacion registro = new Autenticacion(usuarioRegistro); 
        int flagRegistro = registro.registrar();
        salida.writeInt(flagRegistro);
        if (flagRegistro == 0) {
            serv.mostrar("Nuevo usuario registrado");
        }
        scli.close();
        scli2.close();
    }
    
    /** recibe el correo y la imagen del cliente y lo contrasta con los datos
     * almacenados en el servidor, si todo es correcto envia un correo con la contraseña
     * @throws IOException
     */
    private void recuperarContraseña() throws IOException {
    	byte[] sizeAr;
	   	 int size;
	   	 byte[] imageAr;
	   	 BufferedImage image;
	   	 File dir;
    	String correo = entrada.readUTF();
  		dir = new File("." + "/Documentos/verificacionDe" + getNameUser() + ".jpg");
  		sizeAr = new byte[4];
        entrada.read(sizeAr);
        size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
        imageAr = new byte[size];
        entrada.readFully(imageAr);
        image = ImageIO.read(new ByteArrayInputStream(imageAr));
        ImageIO.write(image, "jpg", new File(dir.getCanonicalPath()));
        this.imagen = dir.getCanonicalFile();
		try {
			correo = Criptologia.desencriptar(correo);
		} catch (Exception e) {
			e.printStackTrace();
		}
  		Autenticacion recupera = new Autenticacion(correo, this.imagen);
  		String clave = recupera.comparaContraseña();
  		if (clave != null) {
      		EnvioCorreo envio = new EnvioCorreo(correo, "Recuperación de contraseña", "La clave es:  "+clave);
			envio.enviar();
			salida.writeBoolean(true);
  		} else {
  			salida.writeBoolean(false);
  		}
  		if (this.imagen.delete()) serv.mostrar("Se ha eliminado la imagen de verificaion.");
  		scli.close();
  		scli2.close();
    }
    
    /** recibe un objeto tipo usuario con los datos modificados y una
     * string del nombre anterior del usuario, para buscarlo en el
     * servidor y modificarlo
     * @throws IOException
     */
    private void modificarDatos() throws IOException {
    	byte[] sizeAr;
	   	int size;
	   	byte[] imageAr;
	   	BufferedImage image;
	   	File dir;
    	int flagRegistro = 3;
    	String usuarioRegistroJson = entrada.readUTF();
        Usuario	usuarioRegistro = gson.fromJson(usuarioRegistroJson, new TypeToken<Usuario>() {}.getType());
        String nombreInicial = entrada.readUTF();
        boolean imagenCambia = entrada.readBoolean();
        if (imagenCambia) {
            dir = new File ("." + "/Documentos/Imagenes de Verificacion/" +
            usuarioRegistro.getNombreDeUsuario() + ".jpg");
            sizeAr = new byte[4];
            entrada.read(sizeAr);
            size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
            imageAr = new byte[size];
            entrada.readFully(imageAr);
            System.out.println(imageAr);
            try {
	    		image = ImageIO.read(new ByteArrayInputStream(imageAr));
	        	ImageIO.write(image, "jpg", new File(dir.getCanonicalPath()));
	        	usuarioRegistro.setImagen(dir.getCanonicalFile());
            } catch (IllegalArgumentException exc) {
            	exc.getStackTrace();
            }
        }
        if (flagRegistro == 3) {
            Autenticacion modificarUsuario = new Autenticacion(usuarioRegistro, nombreInicial); 
        	flagRegistro = modificarUsuario.modificar();
            if (flagRegistro == 0) {
            	serv.mostrar("El usuario "+this.getNameUser()+" ha sido modficado");
            	this.setNameUser(usuarioRegistro.getNombreDeUsuario());
            } else {
            	serv.mostrar("Error al modificar al usuario "+this.getNameUser());
            }
        }
        salida.writeInt(flagRegistro);
    }
   
     /**
      * metodo encargado del envio de mensajes en chats individuales y grupales
      * @param mencli
      * @param emisor
      * @param amigos
      * @param jsonamigos
      */
   private void enviaMensaje(String mencli, String emisor, Vector<String> amigos, String jsonamigos) 
   {
      ServidorModel user=null;
      for (String amigo : amigos) {
        for(int i = 0; i < clientesActivos.size(); i++)
        {           
           try
            {
              user = clientesActivos.get(i);
              if(user.nameUser.equals(amigo))
              {
                 user.salida2.writeInt(3);//opcion de mensaje amigo
                 user.salida2.writeUTF(mencli);
                 user.salida2.writeUTF(emisor);
                 user.salida2.writeUTF(jsonamigos);
              }
            }catch (IOException e) {e.printStackTrace();}
        }
      }
   }
}