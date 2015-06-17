package com.ucab.javachat.Cliente.view;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JPasswordField;

import com.toedter.calendar.JDateChooser;


/**
 * Esta clase se encarga de mostrar la vista para cuando sea requerido registrar un usuario.
 * 
 * @author Diego Suarez
 * 
 * @version 1.0
 */

public class VentRegistro {

	public JFrame frmRegistroDeUsuario;
	public JTextField campoUsuario;
	public JTextField campoNombre;
	public JTextField campoEmail;
	public JPasswordField campoContraseña;
	public JRadioButton rdbtnMasculino;
	public JRadioButton rdbtnFemenino;
	public JLabel usuarioValido;
	public JLabel nombreValido;
	public JLabel fechaValida;
	public JLabel sexoValido;
	public JLabel emailValido;
	public JLabel contraseñaValida;
	public JButton btnRegistrar;
	public JButton btnSeleccionarFoto;
	public ButtonGroup grupoSexo;
	public JDateChooser fechaUsuario;
	public JButton btnSalir;

	/**
	 * Create the application.
	 */
	public VentRegistro() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistroDeUsuario = new JFrame();
		frmRegistroDeUsuario.setTitle("Registro de usuario");
		frmRegistroDeUsuario.setBounds(100, 100, 533, 321);
		frmRegistroDeUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistroDeUsuario.getContentPane().setLayout(null);
		
		JLabel labelIntroduccion = new JLabel("Rellene los siguientes campos:");
		labelIntroduccion.setBounds(22, 12, 226, 15);
		frmRegistroDeUsuario.getContentPane().add(labelIntroduccion);
		
		JLabel labelUsuario = new JLabel("Usuario:");
		labelUsuario.setBounds(32, 41, 70, 15);
		frmRegistroDeUsuario.getContentPane().add(labelUsuario);
		
		JLabel labelNombre = new JLabel("Nombre Completo:");
		labelNombre.setBounds(31, 68, 131, 15);
		frmRegistroDeUsuario.getContentPane().add(labelNombre);
		
		JLabel labelFecha = new JLabel("Fecha de nacimiento:\n");
		labelFecha.setBounds(32, 95, 155, 15);
		frmRegistroDeUsuario.getContentPane().add(labelFecha);
		
		JLabel labelEmail = new JLabel("Email:");
		labelEmail.setBounds(32, 122, 50, 15);
		frmRegistroDeUsuario.getContentPane().add(labelEmail);
		
		JLabel labelSexo = new JLabel("Sexo:");
		labelSexo.setBounds(32, 149, 50, 15);
		frmRegistroDeUsuario.getContentPane().add(labelSexo);
		
		JLabel labelContraseña = new JLabel("Clave:");
		labelContraseña.setBounds(32, 176, 94, 15);
		frmRegistroDeUsuario.getContentPane().add(labelContraseña);
		
		
		campoUsuario = new JTextField();
		campoUsuario.setBounds(162, 38, 155, 19);
		frmRegistroDeUsuario.getContentPane().add(campoUsuario);
		campoUsuario.setColumns(10);
		
		campoNombre = new JTextField();
		campoNombre.setBounds(162, 65, 155, 19);
		frmRegistroDeUsuario.getContentPane().add(campoNombre);
		campoNombre.setColumns(10);
		
		fechaUsuario = new JDateChooser();
		fechaUsuario.setBounds(162, 91, 155, 19);
		frmRegistroDeUsuario.getContentPane().add(fechaUsuario);
		
		campoEmail = new JTextField();
		campoEmail.setBounds(162, 119, 155, 19);
		frmRegistroDeUsuario.getContentPane().add(campoEmail);
		campoEmail.setColumns(10);
		
		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBounds(159, 147, 89, 19);
		frmRegistroDeUsuario.getContentPane().add(rdbtnMasculino);
		
		rdbtnFemenino = new JRadioButton("Femenino");
		rdbtnFemenino.setBounds(247, 147, 89, 19);
		frmRegistroDeUsuario.getContentPane().add(rdbtnFemenino);
		
		grupoSexo = new ButtonGroup();
		grupoSexo.add(rdbtnFemenino);
		grupoSexo.add(rdbtnMasculino);
		
		campoContraseña = new JPasswordField();
		campoContraseña.setBounds(162, 173, 155, 19);
		frmRegistroDeUsuario.getContentPane().add(campoContraseña);
		
		usuarioValido = new JLabel("-> 8+ caracteres y solo letras.");
		usuarioValido.setForeground(new Color(178, 34, 34));
		usuarioValido.setFont(new Font("Dialog", Font.PLAIN, 12));
		usuarioValido.setBounds(329, 40, 178, 15);
		frmRegistroDeUsuario.getContentPane().add(usuarioValido);
		
		nombreValido = new JLabel("-> nombre sin numeros y caract.");
		nombreValido.setForeground(new Color(178, 34, 34));
		nombreValido.setFont(new Font("Dialog", Font.PLAIN, 12));
		nombreValido.setBounds(329, 67, 254, 15);
		frmRegistroDeUsuario.getContentPane().add(nombreValido);
		
		fechaValida = new JLabel("-> debes ser mayor de 10 años.");
		fechaValida.setForeground(new Color(178, 34, 34));
		fechaValida.setFont(new Font("Dialog", Font.PLAIN, 12));
		fechaValida.setBounds(329, 94, 254, 15);
		frmRegistroDeUsuario.getContentPane().add(fechaValida);
		
		emailValido = new JLabel("");
		emailValido.setFont(new Font("Dialog", Font.PLAIN, 12));
		emailValido.setForeground(new Color(178, 34, 34));
		emailValido.setBounds(365, 122, 189, 15);
		frmRegistroDeUsuario.getContentPane().add(emailValido);
		
		contraseñaValida = new JLabel("-> 6 a 12 caract., mínimo un num.");
		contraseñaValida.setForeground(new Color(178, 34, 34));
		contraseñaValida.setFont(new Font("Dialog", Font.PLAIN, 12));
		contraseñaValida.setBounds(329, 173, 254, 19);
		frmRegistroDeUsuario.getContentPane().add(contraseñaValida);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(275, 246, 121, 25);
		frmRegistroDeUsuario.getContentPane().add(btnRegistrar);
		
		JLabel lblFotografa = new JLabel("Foto:");
		lblFotografa.setBounds(32, 203, 94, 15);
		frmRegistroDeUsuario.getContentPane().add(lblFotografa);
		
		btnSeleccionarFoto = new JButton("Seleccionar foto");
		btnSeleccionarFoto.setBounds(162, 203, 155, 25);
		frmRegistroDeUsuario.getContentPane().add(btnSeleccionarFoto);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(406, 247, 89, 23);
		frmRegistroDeUsuario.getContentPane().add(btnSalir);
		
		
	}
}
