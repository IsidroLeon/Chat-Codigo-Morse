package com.ucab.javachat.Cliente.model;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Esta clase contiene los metodos para validar el cumplimiento del formato obligatorio de diversos datos del sistema.
 * hace uso de la libreria regex para contrastar los datos con expresiones regulares adecuadas al formato esperado.
 * Esta clase se encargara de toas las validaciones que necesite ejecutar el cliente antes del almacenamiento y formateo
 * de los datos.
 * 
 * @author Luis Valladares
 * @version 1.0
 */

public class ModeloValidacion{
	
	/**
	 * Obtiene un String de usuario y valida que cumpla los siguientes parametros
	 * Mayor a 8 caracteres
	 * Solo pede contener letras mayusculas o minusculas
	 * debe tener al menos dos numeros
	 *
	 * @return      Verdadero en caso de pasar la comprobación, falso de lo contrario
	 * @author		Luis Valladares
	 */
	public boolean validarUsuario(String nombreUsuario){
		Pattern p = Pattern.compile("/(^(?=.*[a-zA-ZñáéíóúÑÁÉÍÓÚ])(?=.*[0-9].*[0-9])\\b.{8,})|(^(?=.*[0-9].*[0-9])\\b.{8,})");
		Matcher m = p.matcher(nombreUsuario);
		return m.matches();
	}
	
	/**
	 * Obtiene un String de contraseña y valida que cumpla los siguientes parametros
	 * Debe tener entre 6 y 12 caracteres
	 * Admite letras mayusculas y minusculas
	 * debe contener al menos un numero y/o caracter grafico
	 *
	 * @return      Verdadero en caso de pasar la comprobación, falso de lo contrario
	 * @author		Luis Valladares
	 */
	public boolean validarContraseña(String contraseña){
		Pattern p = Pattern.compile("(^(?=.*[a-zA-ZñáéíóúÑÁÉÍÓÚ])((?=.*[0-9])|(?=.*[^A-Za-z0-9_ ]))\\b.{6,12})|(^(?=.*[0-9])|(?=.*[^A-Za-z0-9_ ])\\b.{6,12})");
		Matcher m = p.matcher(contraseña);
		return m.matches();
	}
	
	/**
	 * Obtiene un String de nombre completo y valida que cumpla los siguientes parametros
	 * no debe tener numeros ni caracteres graficos
	 * se admiten letras minusculas, mayusculas, acentuadas y espacios
	 *
	 * @return      Verdadero en caso de pasar la comprobación, falso de lo contrario
	 * @author		Luis Valladares
	 */
	public boolean validarNombreCompleto(String nombreCompleto){
		Pattern p = Pattern.compile("[a-z A-Z ñáéíóú]");
		Matcher m = p.matcher(nombreCompleto);
		return m.matches();
	}
	
	/**
	 * Obtiene un String de email y valida que cumpla los siguientes parametros
	 * el correo debe cumplir con el formato nombre@domino.***
	 *
	 * @return      Verdadero en caso de pasar la comprobación, falso de lo contrario
	 * @author		Luis Valladares
	 */
	public boolean validarEmail(String email){
		Pattern p = Pattern.compile("^[-!#$%&'*+/0-9=?A-Z^_a-z{|}~](\\.?[-!#$%&'*+/0-9=?A-Z^_a-z{|}~])*@[a-zA-Z](-?[a-zA-Z0-9])*(\\.[a-zA-Z](-?[a-zA-Z0-9])*)+$");
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	/**
	 * Obtiene tres entero correspondientes al dia mes y año y valida los siguientes parametros
	 * el año debe ser mayor a 1915 (la persona debe tener 100 años o menos)
	 * la fecha dada debe ser menor que la fecha actual, no puede ser una fecha futura
	 *
	 * @return      Verdadero en caso de pasar la comprobación, falso de lo contrario
	 * @author		Luis Valladares
	 */
	@SuppressWarnings("deprecation")
	public boolean validarFecha(int dia, int mes, int año){
		if (año > 1915) {
			Date fecha = new Date(año, mes, dia);
			long nacimiento = fecha.getTime();
			long actual = System.currentTimeMillis();
			if(nacimiento > actual) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
}
