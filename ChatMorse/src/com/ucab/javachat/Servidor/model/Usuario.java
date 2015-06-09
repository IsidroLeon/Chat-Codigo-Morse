package com.ucab.javachat.Servidor.model;
	import java.util.Date;
	/* Un año “y” se representa por el entero y – 1.900. 
	 * Por ejemplo el año 1982 se representaría por el entero 1982 – 1900 = 82. 
	 * De este modo, 82 representa 1982 y 92 representa 1992.
	 * @authors Ismael T.
	 * */
	
public class Usuario {
	private int id;
	private String nombreDeUsuario;
	private boolean sexo;
	private Date fecha;
	private String nombreCompleto;
	private String email;
	private String clave;
	//private file imagen; archivo imagen
	
	/* Metodo que valida si la fecha ingresada es valida
	 * El minimo de edad son 10 anios y el maximo es de 90 anios
	 * 25 representa a 1925 y 105 representa a 2005
	 * los metodos de la libreria java.util.Date estan deprecados, por eso el "@SuppressWarnings("deprecation")"
	 * */
	@SuppressWarnings("deprecation")
	public void validarFecha(Date fecha){ 
		if ( (fecha.getYear()>25) && (fecha.getYear()<105) ){
			System.out.println("tiene permiso para usar la aplicacion");
		}
		else 
			System.out.println("Usted debe tener mas de 10 anios o menos de 90 para poder usar la aplicacion");
	}
	
	@SuppressWarnings("deprecation")
	public Date setFecha(int dia, int mes, int anio){
		this.fecha = new Date(anio, mes, dia);
		return fecha;
	}
	
	/*Getters y Setters 
	 * */
	public Date getFecha(){
		return fecha;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}
	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
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
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}

	/*Constructores con y sin paramteros*/
	public Usuario(int id, String nombreDeUsuario, boolean sexo, Date fecha,
			String nombreCompleto, String email, String clave) {
		super();
		this.id = id;
		this.nombreDeUsuario = nombreDeUsuario;
		this.sexo = sexo;
		this.fecha = fecha;
		this.nombreCompleto = nombreCompleto;
		this.email = email;
		this.clave = clave;
	}

	public Usuario() {
		super();
	}

	
	/*metodo toString para imprimir valores de la clase Usuario*/
	public String toString() {
		return "Usuario [id=" + id + ", nombreDeUsuario=" + nombreDeUsuario
				+ ", sexo=" + sexo + ", fecha=" + fecha + ", nombreCompleto="
				+ nombreCompleto + ", email=" + email + ", clave=" + clave
				+ "]";
	}	
}