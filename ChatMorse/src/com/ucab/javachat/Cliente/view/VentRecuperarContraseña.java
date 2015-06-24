package com.ucab.javachat.Cliente.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class VentRecuperarContraseña {

	public JFrame frameRecuperarContraseña;
	public JTextField textField;
	public JButton btnSeleccionar;
	public JButton btnEnviar;
	public JTextField nombreImagen;
	public JLabel labelValidacion;

	/**
	 * Create the application.
	 */
	public VentRecuperarContraseña() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameRecuperarContraseña = new JFrame();
		frameRecuperarContraseña.setBounds(100, 100, 300, 246);
		frameRecuperarContraseña.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameRecuperarContraseña.getContentPane().setLayout(null);
		frameRecuperarContraseña.setTitle("Recuperar contraseña");
		
		JLabel lblRecuperar = new JLabel("Recuperar Contraseña");
		lblRecuperar.setBounds(54, 12, 200, 15);
		frameRecuperarContraseña.getContentPane().add(lblRecuperar);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(12, 55, 63, 20);
		frameRecuperarContraseña.getContentPane().add(lblCorreo);
		
		textField = new JTextField();
		textField.setBounds(79, 56, 193, 19);
		frameRecuperarContraseña.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnSeleccionar.setBounds(89, 124, 128, 25);
		frameRecuperarContraseña.getContentPane().add(btnSeleccionar);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(89, 182, 128, 25);
		frameRecuperarContraseña.getContentPane().add(btnEnviar);
		
		JLabel lblImagen = new JLabel("Imagen:");
		lblImagen.setBounds(12, 97, 70, 15);
		frameRecuperarContraseña.getContentPane().add(lblImagen);
		
		nombreImagen = new JTextField();
		nombreImagen.setBounds(79, 93, 193, 19);
		frameRecuperarContraseña.getContentPane().add(nombreImagen);
		nombreImagen.setColumns(10);
		
		labelValidacion = new JLabel("");
		labelValidacion.setForeground(Color.RED);
		labelValidacion.setFont(new Font("Dialog", Font.BOLD, 12));
		labelValidacion.setBounds(12, 161, 274, 15);
		frameRecuperarContraseña.getContentPane().add(labelValidacion);
	}
}
