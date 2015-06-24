package com.ucab.javachat.Servidor.model;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EnvioCorreo {
	private String correo; 
	private String asunto;
	private String cuerpo;
	
	public EnvioCorreo(String correo, String asunto, String cuerpo) {
		this.correo = correo;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
	}
	
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
