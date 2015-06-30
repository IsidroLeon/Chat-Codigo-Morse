package com.ucab.javachat.Servidor.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/** Clase encargada de la autenticacion del usuario en el sistema contrastando los datos recibidos
 * con los almacenados en el servidor. Se encarga ademas de realizar las validaciones necesarias del lado del servidor
 * y de comprobar los valores unicos
 * @author Grupo 3 - A. Rodriguez, I. Teixeira, L. Valladares, D. Suarez
 *
 */
public class Autenticacion {
	Usuario user;
	String nombreDeUsuario;
	String clave;
	String correo;
	File imagen;
	ArrayList<Usuario> usuariosArchivo = new ArrayList<Usuario>();
	
	/** Constructor para el registro de un nuevo usuario.
	 * @param user - El usuario a registrar
	 */
	public Autenticacion(Usuario user) {
		ManejoArchivos archivo = new ManejoArchivos();
		this.user = user;
		usuariosArchivo = archivo.getListaUsuarios();
	}
	
	/** Constructor para el inicio de sesion de un usuario.
	 * @param nombreDeUsuario - nombre o correo del usuario que iniciar sesion
	 * @param clave - clave del usuario que iniciara sesion
	 */
	public Autenticacion(String nombreDeUsuario, String clave, File imagen) {
		ManejoArchivos archivo = new ManejoArchivos();
		usuariosArchivo = archivo.getListaUsuarios();
		this.nombreDeUsuario = nombreDeUsuario;
		this.clave = clave;
		this.imagen = imagen;
	}
	
	/** Constructor para la modificación de datos de un usuario
	 * @param datosModificados - un objeto del tipo usuario con los datos del usuario modificados
	 * @param nombreOriginal - el nombre actual del usuario para buscarlo
	 */
	public Autenticacion(Usuario datosModificados, String nombreOriginal) {
		ManejoArchivos archivo = new ManejoArchivos();
		usuariosArchivo = archivo.getListaUsuarios();
		this.nombreDeUsuario = nombreOriginal;
		this.user = datosModificados;
	}
	
	public Autenticacion(String correo, File imagen){
		this.correo = correo;
		this.imagen = imagen;
	}
	
	/** Realiza el proceso de autenticacion del usuario en el sistema contrastando sus datos
	 * con los almacenados en el sistema
	 * @return Verdadero cuando el usuario existe, falso en cualquier otro caso
	 */
	public Usuario autenticar() {
		if(usuariosArchivo != null) {
			for (Usuario usuario : usuariosArchivo) 
				try {
					// comprueba si existe algun usuario con el correo o el nombre de usuario indicado
					if ((usuario.getNombreDeUsuario().trim().equals(nombreDeUsuario.trim())) ||
							(Criptologia.desencriptar(usuario.getEmail().trim()).equals(nombreDeUsuario.trim()))) {
						// comprueba si hay algun usuario con esa clave
						if (Criptologia.desencriptar(usuario.getClave()).trim().equals(Criptologia.desencriptar(clave).trim())) { 
							// comprueba la similitud entre las imagenes.
							if (CompararImagenes.comparar(this.imagen, usuario.getImagen())){
								return usuario;
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return null;
	}
	
	/** Realiza el proceso de registro de usuario, almacenando el nuevo usuario en el archiv con comprobacion previa
	 * si el correo o el nombre de usuario ya estan registrados
	 * @return Verdadero si se registro el usuario, falso en cualquier otro caso
	 */
	public int registrar() {
		if(usuariosArchivo != null) {
			for (Usuario usuario : usuariosArchivo) {
				try {
					if (Criptologia.desencriptar(usuario.getEmail()).trim().equals(Criptologia.desencriptar(user.getEmail().trim()))) {
						return 1;
					}
					if (usuario.getNombreDeUsuario().trim().equals(this.user.getNombreDeUsuario().trim())) {
						return 2;
					}
					if (user.usuarioVacio()) {
						return 3;
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}		
			}
		}
		else
		{
			usuariosArchivo = new ArrayList<Usuario>();
		}
		usuariosArchivo.add(this.user);
		ManejoArchivos archivo = new ManejoArchivos();
		archivo.escribirArchivo(usuariosArchivo);
		return 0;
	}

	public String comparaContraseña() {
		ManejoArchivos archivo = new ManejoArchivos();
		for(Usuario user : archivo.getListaUsuarios()) {
			String email = user.getEmail();
			if (Criptologia.desencriptar(email).trim().equals(correo.trim()))
				try {
					if (CompararImagenes.comparar(user.getImagen(), this.imagen))
						return Criptologia.desencriptar(user.getClave());
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
	}
	
	public int modificar() {
		boolean flag = false;
		Usuario userModificar = new Usuario();
		int correo = existeCorreoYUsuario((Criptologia.desencriptar(user.getEmail())), user.getNombreDeUsuario(), nombreDeUsuario);
		if((usuariosArchivo != null)&&(correo == 0)) {
			for (Usuario usuario : usuariosArchivo) {
				try {
					// comprueba si existe algun usuario con el correo o el nombre de usuario indicado
					if (usuario.getNombreDeUsuario().trim().equals(nombreDeUsuario.trim())) {
						userModificar = usuario;
						flag = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(flag) {
			ManejoArchivos archivo = new ManejoArchivos();
			usuariosArchivo.remove(userModificar);
			usuariosArchivo.add(user);
			archivo.escribirArchivo(usuariosArchivo);
		}
		return correo;
	}
	
	private int existeCorreoYUsuario(String correo, String nombre, String nombreOriginal) {
		if(usuariosArchivo != null) {
			for (Usuario usuario : usuariosArchivo) {
				if ((Criptologia.desencriptar(usuario.getEmail()).trim().equals(correo)&&(!usuario.getNombreDeUsuario().trim().equals(nombreOriginal)))) {
					return 1;
				}
				if (usuario.getNombreDeUsuario().trim().equals(nombre)&&(!usuario.getNombreDeUsuario().trim().equals(nombreOriginal))) {
					return 2;
				}	
			}
		}
		return 0;
	}
	
}
