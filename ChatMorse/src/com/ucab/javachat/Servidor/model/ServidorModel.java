package com.ucab.javachat.Servidor.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
     String nameUser;
     ServidorController serv;
     
     public ServidorModel(Socket scliente,Socket scliente2,ServidorController serv)
     {
        scli=scliente;
        scli2=scliente2;
        this.serv=serv;
        nameUser="";
        clientesActivos.add(this);        
        serv.mostrar("cliente agregado: "+this);			
     }
     
     public String getNameUser()
     {
       return nameUser;
     }
     
     public void setNameUser(String name)
     {
       nameUser=name;
     }
     
     @SuppressWarnings("unchecked")
	public void run()
     {
    	serv.mostrar(".::Esperando Mensajes :");
    	
    	try
    	{
          entrada=new DataInputStream(scli.getInputStream());
          salida=new DataOutputStream(scli.getOutputStream());
          salida2=new DataOutputStream(scli2.getOutputStream());
          this.setNameUser(entrada.readUTF());
          enviaUserActivos();
    	}
    	catch (IOException e) {  e.printStackTrace();     }
    	
        int opcion=0,numUsers=0;
        String amigo = "";
        String mencli="";
        Vector<String> amigos = new Vector<String>();
                
    	while(true)
    	{
          try
          {
             opcion=entrada.readInt();
             switch(opcion)
             {
                case 1://envio de mensaje a todos
                   mencli=entrada.readUTF();
                   serv.mostrar("mensaje recibido "+mencli);
                   enviaMsg(mencli);
                   break;
                case 2://envio de lista de activos
                   numUsers=clientesActivos.size();
                   salida.writeInt(numUsers);
                   for(int i=0;i<numUsers;i++)
                      salida.writeUTF(clientesActivos.get(i).nameUser);
                   break;
                case 3: // envia mensaje a uno solo
                	Gson gson = new Gson();
                    mencli=entrada.readUTF();//mensaje enviado
                    String amigostring = entrada.readUTF();
                    amigos = gson.fromJson(amigostring, new TypeToken<Vector<String>>() {}.getType()); 
                   enviaMsg(mencli, amigos, amigostring);
                   break;
             }
          }
          catch (IOException e) {System.out.println("El cliente termino la conexion");break;}
    	}
    	serv.mostrar("Se removio un usuario");
    	clientesActivos.removeElement(this);
    	try
    	{
          serv.mostrar("Se desconecto un usuario");
          scli.close();
    	}	
        catch(Exception et)
        {serv.mostrar("no se puede cerrar el socket");}   
     }
     
     public void enviaMsg(String mencli2)
     {
        ServidorModel user=null;
        for(int i=0;i<clientesActivos.size();i++)
        {
           serv.mostrar("MENSAJE DEVUELTO:"+mencli2);
           try
            {
              user=clientesActivos.get(i);
              user.salida2.writeInt(1);//opcion de mensaje 
              user.salida2.writeUTF(""+this.getNameUser()+" >"+ mencli2);              
            }catch (IOException e) {e.printStackTrace();}
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
   
   private void enviaMsg(String mencli, Vector<String> amigos, String jsonamigos) 
   {
	  System.out.println(jsonamigos);
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
                 user.salida2.writeUTF(""+this.getNameUser()+">"+mencli);
                 user.salida2.writeUTF(jsonamigos);
              }
            }catch (IOException e) {e.printStackTrace();}
        }
      }
   }
}