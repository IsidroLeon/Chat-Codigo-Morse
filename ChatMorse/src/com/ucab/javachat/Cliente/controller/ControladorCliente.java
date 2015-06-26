package com.ucab.javachat.Cliente.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.ucab.javachat.Cliente.model.Cliente;
import com.ucab.javachat.Cliente.model.Criptologia;
import com.ucab.javachat.Cliente.model.ThreadActualizarUsuario;
import com.ucab.javachat.Cliente.model.Usuario;
import com.ucab.javachat.Cliente.view.VentCliente;
import com.ucab.javachat.Cliente.view.VentIniciarSesion;
import com.ucab.javachat.Cliente.view.VentPrivada;
import com.ucab.javachat.Cliente.view.VentModificar;

/**
 *Esta clase es el controlador de la vista que se le muestra al usuario cuando inicia sesion.
 *
 *@author Grupo 3 - A. Rodriguez, I. Teixeira, L. Valladares, D. Suarez
 *@version 2.0
 */
public class ControladorCliente implements ActionListener {
	private VentCliente ventana;
	private Cliente cliente;
	private VentPrivada ventPrivada;
	private ControladorPrivada contPrivada;
	private VentModificar ventModificar ;
	private String nombreUsuario;
	private String clave;
	private File imagen;
	private Usuario usuarioAutenticado;
	private ThreadActualizarUsuario actualizarUsuario;
    private ControladorModificar modificador;
	/**
	 * Constructor de la clase. Añade los listener a los componentes de la ventana y ejecuta
	 *  un hilo para actualizar la lista que muestra a los usuarios conectados.
	 * @param ventana - Este carga las especificaciones de la ventana y inicializa todos sus componentes.
	 */
	public ControladorCliente(VentCliente ventana, ControladorIniciarSesion contSesion) {
		Usuario autenticado = new Usuario();
        setUsuario(contSesion.getUsuario());
        setClave(Criptologia.encriptar(contSesion.getClave()));
        setImagen(contSesion.getImagen());
        this.ventana = ventana;
        try {
			cliente = new Cliente(this);
			autenticado = cliente.conexion(nombreUsuario, clave, imagen);
	        ventana.nomUsers = new Vector<String>();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        if (!autenticado.usuarioVacio()) {
        	this.usuarioAutenticado = autenticado;
        	contSesion.cerrarVentana();
	        this.ventana.setVisible(true);
	        this.ventana.butPrivado.addActionListener(this);
	        this.ventana.butModificar.addActionListener(this);
	        actualizarUsuario = new ThreadActualizarUsuario(cliente);
	        actualizarUsuario.start();
	        
	        ventPrivada = new VentPrivada(cliente);
	        contPrivada = new ControladorPrivada(ventPrivada, cliente);
	        ventModificar = new VentModificar(getUsuarioAutenticado());
	        modificador = new ControladorModificar(ventModificar, getUsuarioAutenticado(), cliente);
        } else {
        	JOptionPane.showMessageDialog(null, "La autenticación ha fallado", "Problema de conexión", JOptionPane.INFORMATION_MESSAGE);
        }
	}
	
	public ControladorCliente(VentCliente ventana, ControladorRegistrarUsuario contUsuario) {
		boolean flag = false;
		Usuario nuevoUsuario = (contUsuario.getNuevoUsuario());
        this.ventana = ventana;
        try {
			cliente = new Cliente(this);
			flag = cliente.conexion(nuevoUsuario);
		} catch (IOException e) {
			e.printStackTrace();
		}
        if (flag) {
        	contUsuario.cerrarVentana();
        	JOptionPane.showMessageDialog(null, "Registro completado exitosamente", "Registro completado", JOptionPane.INFORMATION_MESSAGE);
        	Cliente.IP_SERVER = "localhost";
            VentIniciarSesion ventIniciar = new VentIniciarSesion();
    		new ControladorIniciarSesion(ventIniciar);
        } else {
        	JOptionPane.showMessageDialog(null, "El registro no fue satisfactorio, asegurese de que el email y el nombre de usuario no estan registrados", "Problema de registro", JOptionPane.INFORMATION_MESSAGE);
        }
	}
	
	public Usuario getUsuarioAutenticado(){
		return this.usuarioAutenticado;
	}
	
	public void setUsuarioAutenticado(Usuario usuarioAutenticado){
		this.usuarioAutenticado = usuarioAutenticado;
	}
	/**
	 * 
	 * @return El nombre del usuario que inicio sesion.
	 */
	public String getUsuario() {
		return this.nombreUsuario;
	}
	
	/**
	 * Guarda el nombre del usuario que quiere iniciar sesion.
	 * @param user - Nombre de usuario.
	 */
	public void setUsuario(String usuario) {
		this.nombreUsuario = usuario;
	}
	
	/**
	 * 
	 * @return La contraseña del usuario que inicio sesion.
	 */
	public String getClave() {
		return this.clave;
	}
	
	
	/**
	 * Guarda la contraseña del usuario que quiere iniciar sesion.
	 * @param clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	/**
	 * 
	 * @return La imagen del usuario que incio sesion.
	 */
	public File getImagen() {
		return imagen;
	}
	
	/**
	 * Guarda la imagen del usuario que quiere iniciar sesion.
	 * @param imagen
	 */
	public void setImagen(File imagen) {
		this.imagen = imagen;
	}

	/**
	 * Muestra en la ventana el nombre de usuario del cliente.
	 */
	public void setLabelUser()
    {
		this.ventana.lblNomUser.setText("Usuario " + getUsuario());
    }
	
	/**
	 * Muestra en la ventana la lista de usuarios conectados.
	 * @param datos - Vector que contiene los nombres de los usuarios conectados.
	 */
    public void ponerActivos(Vector<String> datos)
    {
       ventana.nomUsers = datos;
       ponerDatosList(ventana.lstActivos,ventana.nomUsers);
    }
    
    /**
     * Añade el nombre de usuario del cliente a la lista de usuarios conectados.
     * @param user - NOmbre de usuario del cliente.
     */
    public void agregarUser(String user)
    {
       ventana.nomUsers.add(user);
       ponerDatosList(ventana.lstActivos,ventana.nomUsers);
    }
    
    /**
     * retira el nombre de usuario del cliente.
     * PD: ESTE METODO NUNCA SE USA.
     * @param user
     */
    public void retirraUser(String user)
    {        
       ventana.nomUsers.remove(user);
       ponerDatosList(ventana.lstActivos,ventana.nomUsers);
    }
    
    /**
     * Este metodo actualiza la lista de la ventana con los datos nuevos del vector.
     * @param list - Lista de la vista.
     * @param datos - Vector que contiene el nombre de usuarios conectados.
     */
   @SuppressWarnings({ "unchecked", "rawtypes", "serial" })
   private void ponerDatosList(JList<String> list,final Vector<String> datos)
   {
       list.setModel(new AbstractListModel() {            
           @Override
           public int getSize() { return datos.size(); }
           @Override
           public Object getElementAt(int i) { return datos.get(i); }
       });
   }
   
   /**
    * Controlador de eventos al presionar los botones de la ventana
    */
    public void actionPerformed(ActionEvent evt) {    
    	if (evt.getSource()==this.ventana.butModificar) {
    		boolean flag=false;
    		modificador.vista.setVisible(true);
    		System.out.println(usuarioAutenticado);
			flag = modificador.isFlag();
			if (flag){
			usuarioAutenticado= modificador.getUsuarioModificar();			
			}
		}
    	if(evt.getSource()==this.ventana.butPrivado) {
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
    	 nombres.add(this.getUsuario());
    	 contPrivada.setAmigo(nombres, this.getUsuario());
       }
    }
    
    public void mensajeAmigo(String mensaje, String emisor, Vector<String> amigos)
    {
    	contPrivada.setAmigo(amigos, emisor);
    	contPrivada.mostrarMsg(mensaje);
    }

}
