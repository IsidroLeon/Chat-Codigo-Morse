package com.ucab.javachat.Servidor.model;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Clase encargada de enviar un correo a la direccion de correo de un usuario registrado 
 * en la aplicacion. La clase se utiliza para el envio de la contraseña en Recuperar contraseña  
 * @author Grupo 3
 */

public class EnvioCorreo {
	private String correo; 
	private String asunto;
	private String cuerpo;
	/**
	 * Constructor con parametros
	 * @param correo
	 * @param asunto
	 * @param cuerpo
	 */
	public EnvioCorreo(String correo, String asunto, String cuerpo) {
		this.correo = correo;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
	}
	
	/**
	 * Metodo encargado del envio del correo.
	 * Se creo el correo grupo3program2015@gmail.com para el envio de correos a usuarios 
	 * registrados en la aplicacion.
	 */
	public void enviar () {
		final String username = "grupo3program2015@gmail.com";
		final String password = "clavemorse";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("grupo3program2015@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(correo));
			message.setSubject(asunto);
			message.setText(cuerpo);
 
			Transport.send(message);
 
			System.out.println("Correo enviado");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
