package com.ucab.javachat.Cliente.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentRecuperarContraseña {

	public JFrame frameRecuperarContraseña;
	public JTextField textField;
	public JButton btnSeleccionar;
	public JButton btnEnviar;

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
		frameRecuperarContraseña.setBounds(100, 100, 300, 215);
		frameRecuperarContraseña.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameRecuperarContraseña.getContentPane().setLayout(null);
		frameRecuperarContraseña.setTitle("Recuperar contraseña");
		
		JLabel lblRecuperar = new JLabel("Recuperar Contraseña");
		lblRecuperar.setBounds(54, 12, 200, 15);
		frameRecuperarContraseña.getContentPane().add(lblRecuperar);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(12, 55, 63, 20);
		frameRecuperarContraseña.getContentPane().add(lblCorreo);
		
		JLabel lblSubirArchivo = new JLabel("Seleccionar Foto");
		lblSubirArchivo.setBounds(12, 103, 127, 20);
		frameRecuperarContraseña.getContentPane().add(lblSubirArchivo);
		
		textField = new JTextField();
		textField.setBounds(81, 56, 191, 19);
		frameRecuperarContraseña.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(156, 101, 116, 25);
		frameRecuperarContraseña.getContentPane().add(btnSeleccionar);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(81, 158, 135, 25);
		frameRecuperarContraseña.getContentPane().add(btnEnviar);
	}
}
