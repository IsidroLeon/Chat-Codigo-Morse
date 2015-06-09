package com.ucab.javachat.Cliente.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.ucab.javachat.Cliente.model.Cliente;
import com.ucab.javachat.Cliente.view.VentCliente;
import com.ucab.javachat.Cliente.view.VentPrivada;

public class ControladorCliente implements ActionListener {
	private VentCliente ventana;
	private Cliente cliente;
	private VentPrivada ventPrivada;
	private ControladorPrivada contPrivada;

	public ControladorCliente(VentCliente ventana) {
		this.ventana = ventana;
        this.ventana.txtMensaje.addActionListener(this);
        this.ventana.butEnviar.addActionListener(this);
        this.ventana.butPrivado.addActionListener(this);
        this.ventana.acercaD.addActionListener(this);
        try {
			cliente = new Cliente(this);
			cliente.conexion();     
	        ventana.nomUsers = new Vector();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
        ponerActivos(cliente.pedirUsuarios());
        
        ventPrivada = new VentPrivada(cliente);
        contPrivada = new ControladorPrivada(ventPrivada, cliente);
	}
	
	public void setNombreUser(String user)
    {
       ventana.lblNomUser.setText("Usuario " + user);
    }
    public void mostrarMsg(String msg)
    {
       ventana.panMostrar.append(msg+"\n");
    }
    public void ponerActivos(Vector datos)
    {
       ventana.nomUsers = datos;
       ponerDatosList(ventana.lstActivos,ventana.nomUsers);
    }
    public void agregarUser(String user)
    {
       ventana.nomUsers.add(user);
       ponerDatosList(ventana.lstActivos,ventana.nomUsers);
    }
    public void retirraUser(String user)
    {        
       ventana.nomUsers.remove(user);
       ponerDatosList(ventana.lstActivos,ventana.nomUsers);
    }
   @SuppressWarnings("unchecked")
private void ponerDatosList(JList<String> list,final Vector<String> datos)
   {
       list.setModel(new AbstractListModel() {            
           @Override
           public int getSize() { return datos.size(); }
           @Override
           public Object getElementAt(int i) { return datos.get(i); }
       });
   }
    public void actionPerformed(ActionEvent evt) {
        
      String comand=(String)evt.getActionCommand();
      if(comand.compareTo("Acerca")==0)
      {   
          JOptionPane.showMessageDialog(ventana,"Jos� Valdez/Javier Vargas","Desarrollado por",JOptionPane.INFORMATION_MESSAGE);           
      }
       if(evt.getSource()==this.ventana.butEnviar || evt.getSource()==this.ventana.txtMensaje)
       {
          String mensaje = ventana.txtMensaje.getText();        
          cliente.flujo(mensaje);
          ventana.txtMensaje.setText("");
       }
       else if(evt.getSource()==this.ventana.butPrivado)
       {
          int pos=this.ventana.lstActivos.getSelectedIndex();
          if(pos>=0)              
          {
            contPrivada.setAmigo(ventana.nomUsers.get(pos));
          }
       }
    }
    
    public void mensajeAmigo(String amigo,String msg)
    {
       contPrivada.setAmigo(amigo);           
       contPrivada.mostrarMsg(msg);
    }

}
