package com.ucab.javachat.Servidor.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ucab.javachat.Servidor.model.EnvioCorreo;
import com.ucab.javachat.Servidor.controller.ServidorController;

/**
 *
 * @author Administrador
 */
public class ServidorModel extends Thread
{
     Socket scli=null;
     Socket scli2=null;
     DataInputStream entrada=null;
     DataOutputStream salida=null;
     DataOutputStream salida2=null;
     public static Vector<ServidorModel> clientesActivos = new Vector<ServidorModel>();	
     private String nameUser;
     private String clave;
     ServidorController serv;
     
     public ServidorModel(Socket scliente,Socket scliente2,ServidorController serv)
     {
        scli=scliente;
        scli2=scliente2;
        this.serv=serv;
        clientesActivos.add(this);        
        serv.mostrar("cliente agregado: "+this);			
     }
     
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
     
	public void run()
     {
		Gson gson = new Gson();
    	serv.mostrar(".::Esperando Mensajes :");
    	
    	try
    	{
          entrada = new DataInputStream(scli.getInputStream());
          salida = new DataOutputStream(scli.getOutputStream());
          salida2 = new DataOutputStream(scli2.getOutputStream());
          int caseInicio = 0;
          caseInicio = entrada.readInt();
          switch(caseInicio) {
          	case 1: // Inicio de sesion
          		this.setNameUser(entrada.readUTF());
          		this.setClave(entrada.readUTF());
          		Autenticacion inicioDeSesion = new Autenticacion(this.getNameUser(), this.getClave());
          		// Envia true o false si el inicio de sesion es valido o invalido
          		boolean flagInicioSesion = inicioDeSesion.autenticar();
          		//boolean flagInicioSesion = true;
                salida.writeBoolean(flagInicioSesion);
                if(flagInicioSesion) {
                	serv.mostrar("Ha iniciado sesion: "+this.getNameUser());
                }
          		break;
          	case 2: // Registro
          		String usuarioRegistroJson = entrada.readUTF();
          		Usuario usuarioRegistro = gson.fromJson(usuarioRegistroJson, new TypeToken<Usuario>() {}.getType());
          		File miDir = new File (".");
          		String ruta = "";
          		BufferedInputStream bis;
          		BufferedOutputStream bos;
          		 
          		byte[] receivedData;
          		int in;
          		String file;
          		try{ 
          			//Buffer de 1024 bytes
          			receivedData = new byte[1024];
          			bis = new BufferedInputStream(scli.getInputStream());
          			//Recibimos el nombre del fichero
          			file = entrada.readUTF();
          			file = file.substring(file.indexOf('\\')+1,file.length());
          			//Para guardar fichero recibido
          			bos = new BufferedOutputStream(new FileOutputStream(file));
          			while ((in = bis.read(receivedData)) != -1){
          				bos.write(receivedData,0,in);
          			}
          			bos.close();
          			ruta = miDir.getCanonicalPath() + "/" + usuarioRegistro.getImagen().getName();
          			File imagen = new File(ruta);
          			 //New path
          			 File fileSendPath = new File(miDir + "/Documentos/Imagenes de Verificacion/" 
          			 + usuarioRegistro.getNombreDeUsuario() + ".jpg");
          			 //Moving the file.
          			 boolean si = imagen.renameTo(fileSendPath);
          			 if (si) usuarioRegistro.setImagen(imagen);
          			           			 
       			 }catch (Exception e ) {
          			 System.err.println(e);
      			 }
          		
 
          		Autenticacion registro = new Autenticacion(usuarioRegistro);  
          		boolean flagRegistro = registro.registrar();
          		salida.writeBoolean(flagRegistro);
          		if (flagRegistro) {
          			serv.mostrar("Nuevo usuario registrado");
          		}
          		scli.close();
          		scli2.close();
          		break;
          	case 3: //Recuperar contraseña 
          		String correo = entrada.readUTF();
				try {
					correo = Criptologia.desencriptar(correo);
				} catch (Exception e) {
					e.printStackTrace();
				}
          		Autenticacion recupera = new Autenticacion(correo);
          		String clave = recupera.comparaContraseña();
          		if (clave != null) {
	          		EnvioCorreo envio = new EnvioCorreo(correo, "Recuperación de contraseña", "La clave es:  "+clave);
					envio.enviar();
					salida.writeBoolean(true);
          		} else {
          			salida.writeBoolean(false);
          		}
          		scli.close();
          		scli2.close();
	          	break;
          }
    	}
    	catch (IOException e) {  
    		e.printStackTrace();     
    	}
    	
        int opcion=0,numUsers=0;
        String mencli = "";
        String amigostring = "";
        Vector<String> amigos = new Vector<String>();
                
    	while(true){
          try{
             opcion = entrada.readInt();
             switch(opcion)
             {
                case 1://envio de mensaje a todos
                   mencli = entrada.readUTF();
                   serv.mostrar("mensaje recibido "+mencli);
                   //enviaMensaje(mencli);
                   break;
                case 2://envio de lista de activos
                   numUsers = clientesActivos.size();
                   salida.writeInt(numUsers);
                   for(int i=0;i<numUsers;i++)
                      salida.writeUTF(clientesActivos.get(i).nameUser);
                   break;
                   // Esta vaina tiene que enviar el usuario por separado
                   // para saber quien lo envia en el cliente y poder traducir solo el mensaje
                   // tambien para que el que lo envie no le suene el sonido
                case 3: // envia mensaje privado
                    mencli=entrada.readUTF();//mensaje enviado
                    amigostring = entrada.readUTF();
                    amigos = gson.fromJson(amigostring, new TypeToken<Vector<String>>() {}.getType()); 
                    enviaMensaje(mencli, amigos, amigostring);
                   	break;
	             }
          }
          	catch (IOException e) {System.out.println("El cliente termino la conexion.");break;}
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
    
     public void enviaUserActivos()
     {
        ServidorModel user=null;
        for(int i=0;i<clientesActivos.size();i++)
        {           
           try
            {
              user=clientesActivos.get(i);
              if(user==this)continue;//ya se lo envie
              user.salida2.writeInt(2);//opcion de agregar 
              user.salida2.writeUTF(this.getNameUser());	
            }catch (IOException e) {e.printStackTrace();}
        }
     }
   
   private void enviaMensaje(String mencli, Vector<String> amigos, String jsonamigos) 
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
                 user.salida2.writeUTF(jsonamigos);
              }
            }catch (IOException e) {e.printStackTrace();}
        }
      }
   }
}