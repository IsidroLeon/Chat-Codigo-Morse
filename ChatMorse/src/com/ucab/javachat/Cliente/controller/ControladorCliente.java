package com.ucab.javachat.Cliente.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import com.ucab.javachat.Cliente.model.Cliente;
import com.ucab.javachat.Cliente.model.ThreadActualizarUsuario;
import com.ucab.javachat.Cliente.view.VentCliente;
import com.ucab.javachat.Cliente.view.VentPrivada;

public class ControladorCliente implements ActionListener {
	private VentCliente ventana;
	private Cliente cliente;
	private VentPrivada ventPrivada;
	private ControladorPrivada contPrivada;
	static String nombreUsuario;
	static String clave;
	private ThreadActualizarUsuario actualizarUsuario;

	public ControladorCliente(VentCliente ventana, ControladorIniciarSesion contSesion) {
        nombreUsuario = contSesion.getUsuario();
        clave = contSesion.getClave();
        this.ventana = ventana;
        try {
			cliente = new Cliente(this);
			cliente.conexion(nombreUsuario, clave);
			nombreUsuario = cliente.getNombre();
	        ventana.nomUsers = new Vector<String>();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        this.ventana.setVisible(true);
        this.ventana.butPrivado.addActionListener(this);
        actualizarUsuario = new ThreadActualizarUsuario(cliente);
        actualizarUsuario.start();
        
        ventPrivada = new VentPrivada(cliente);
        contPrivada = new ControladorPrivada(ventPrivada, cliente);
	}
	
	public void setNombreUser(String user)
    {
		this.ventana.lblNomUser.setText("Usuario " + user);
    }
    public void ponerActivos(Vector<String> datos)
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
       if(evt.getSource()==this.ventana.butPrivado)
       {
    	 Vector<Integer> posiciones = new Vector<Integer>();
    	 int[] indices = this.ventana.lstActivos.getSelectedIndices();
    	 for(int indice : indices)
    	 {
    		 posiciones.add(indice);
    	 }
    	 Vector<String> nombres = new Vector<String>();
    	 for (int posicion : posiciones){
    		 try {
    			nombres.add(ventana.nomUsers.get(posicion));
    		 } catch(ArrayIndexOutOfBoundsException ex) {
    			 System.out.println(ex.getMessage());
    		 }
    	 }
    	 nombres.add(nombreUsuario);
    	 contPrivada.setAmigo(nombres);
       }
    }
    
    public void mensajeAmigo(String msg, Vector<String> amigos)
    {
       contPrivada.setAmigo(amigos);           
       contPrivada.mostrarMsg(msg);
    }

}
