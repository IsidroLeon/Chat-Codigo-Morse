package com.ucab.javachat.Cliente.model;
	import java.util.Date;
	/** Un año “y” se representa por el entero y – 1.900. 
	 * Por ejemplo el año 1982 se representaría por el entero 1982 – 1900 = 82. 
	 * De este modo, 82 representa 1982 y 92 representa 1992.
	 * @authors Ismael T.
	 * */
	
public class Usuario extends ModeloValidacion{
	private String nombreDeUsuario;
	private boolean sexo;
	private Date fecha;
	private String nombreCompleto;
	private String email;
	private String clave;
	//private file imagen; archivo imagen
	
	/** Metodo que valida si la fecha ingresada es valida
	 * El minimo de edad son 10 anios y el maximo es de 90 anios
	 * 25 representa a 1925 y 105 representa a 2005
	 * los metodos de la libreria java.util.Date estan deprecados, por eso el "@SuppressWarnings("deprecation")"
	 * */
	
	@SuppressWarnings("deprecation")
	public boolean setFecha(int dia, int mes, int año) {
		if(validarFecha(dia, mes, año)) {
			this.fecha = new Date(año, mes, dia);
			return true;
		} else {
			return false;
		}
	}
	
	/*Getters y Setters 
	 * */
	public Date getFecha(){
		return fecha;
	}
	
	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}
	public boolean setNombreDeUsuario(String nombreDeUsuario) {
		if(validarUsuario(nombreDeUsuario)) {
			this.nombreDeUsuario = nombreDeUsuario;
			return true;
		} else {
			return false;
		}
	}
	public boolean isSexo() {
		return sexo;
	}
	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public boolean setNombreCompleto(String nombreCompleto) {
		if(validarNombreCompleto(nombreCompleto)) {
			this.nombreCompleto = nombreCompleto;
			return true;
		} else {
			return false;
		}
	}
	public String getEmail() {
		return email;
	}
	public boolean setEmail(String email) {
		if(validarEmail(email)) {
			this.email = email;
			return true;
		} else {
			return false;
		}
		
	}
	public String getClave() {
		return clave;
	}
	public boolean setClave(String clave) {
		if(validarContraseña(clave)) {
			this.clave = clave;
			return true;
		} else {
			return false;
		}
	}

	/**Constructores con y sin paramteros*/
	public Usuario(int id, String nombreDeUsuario, boolean sexo, Date fecha,
			String nombreCompleto, String email, String clave) {
		this.nombreDeUsuario = nombreDeUsuario;
		this.sexo = sexo;
		this.fecha = fecha;
		this.nombreCompleto = nombreCompleto;
		this.email = email;
		this.clave = clave;
	}

	
	public Usuario() {
	}

	/**metodo toString para imprimir valores de la clase Usuario*/
	public String toString() {
		return "Usuario [nombreDeUsuario=" + nombreDeUsuario
				+ ", sexo=" + sexo + ", fecha=" + fecha + ", nombreCompleto="
				+ nombreCompleto + ", email=" + email + ", clave=" + clave
				+ "]";
	}	
}