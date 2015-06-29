package com.ucab.javachat.Cliente.view;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;
import javax.swing.JPasswordField;

/**
 * Clase encargada de la vista de la ventana modificar datos
 * @authors Grupo 3
 */

@SuppressWarnings("serial")
public class VentModificar extends JFrame {

	public JTextField textoUsuario;
	public JTextField textoNombre;
	public JTextField textoEmail;
	public JButton botonGuardar,botonSalir,btnImagen;
	public JRadioButton rdbtnMasculino,rdbtnFemenino;
	public ButtonGroup grupoSexo;
	public JDateChooser dateChooser;
	public JFrame f = new JFrame();
	public JPasswordField textoContraseña;
	public JPasswordField textoRepetirContraseña;
	public JLabel lblUsuarioErr, lblNombreErr, lblEmailErr, lblFechaErr, lblContraseñaErr, lblSexoErr, lblFotoErr;
	public JTextField nombreImagen;

	/**
	 * metodo encargado de cargar todos los componentes visuales de la ventana
	 */
	public VentModificar() {
		getContentPane().setLayout(null);
		setBounds(100, 100, 547, 378);
		
		
		lblUsuarioErr = new JLabel("");
		lblUsuarioErr.setBounds(327, 27, 194, 14);
		getContentPane().add(lblUsuarioErr);
		
		lblNombreErr = new JLabel("");
		lblNombreErr.setBounds(327, 58, 194, 14);
		getContentPane().add(lblNombreErr);
		
		lblEmailErr = new JLabel("");
		lblEmailErr.setBounds(327, 88, 194, 14);
		getContentPane().add(lblEmailErr);
		
		lblFechaErr = new JLabel("");
		lblFechaErr.setBounds(327, 119, 194, 14);
		getContentPane().add(lblFechaErr);
		
		lblContraseñaErr = new JLabel("");
		lblContraseñaErr.setBounds(327, 147, 194, 14);
		getContentPane().add(lblContraseñaErr);
		
		lblSexoErr = new JLabel("");
		lblSexoErr.setBounds(346, 210, 144, 14);
		getContentPane().add(lblSexoErr);
		
		lblFotoErr = new JLabel("");
		lblFotoErr.setBounds(327, 249, 194, 14);
		getContentPane().add(lblFotoErr);
		
		JLabel lblNombre = new JLabel("Usuario:");
		lblNombre.setBounds(29, 27, 90, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Nombre completo: ");
		lblApellido.setBounds(29, 58, 126, 14);
		getContentPane().add(lblApellido);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(29, 210, 35, 14);
		getContentPane().add(lblSexo);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(29, 88, 35, 14);
		getContentPane().add(lblEmail);
		
		JLabel lblImagen = new JLabel("Fecha de nacimiento:");
		lblImagen.setBounds(29, 119, 126, 14);
		getContentPane().add(lblImagen);
		
		textoUsuario = new JTextField();
		textoUsuario.setBounds(165, 24, 152, 20);
		getContentPane().add(textoUsuario);
		textoUsuario.setColumns(10);
		
		textoEmail = new JTextField();
		textoEmail.setBounds(165, 85, 152, 20);
		textoEmail.setColumns(10);
		getContentPane().add(textoEmail);
		
		botonSalir = new JButton("Salir");
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		botonSalir.setBounds(346, 308, 144, 23);
		getContentPane().add(botonSalir );
		
		textoNombre = new JTextField();
		textoNombre.setBounds(165, 55, 152, 20);
		textoNombre.setColumns(10);
		getContentPane().add(textoNombre);
		
		botonGuardar = new JButton("Guardar");
		botonGuardar.setBounds(346, 274, 144, 23);
		getContentPane().add(botonGuardar);
		
		rdbtnFemenino = new JRadioButton("Femenino");
		rdbtnFemenino.setBounds(243, 210, 107, 14);
		getContentPane().add(rdbtnFemenino);
		
		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBounds(146, 210, 90, 14);
		getContentPane().add(rdbtnMasculino);
		
		grupoSexo = new ButtonGroup();
		grupoSexo.add(rdbtnMasculino);
		grupoSexo.add(rdbtnFemenino);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(165, 113, 152, 20);
		getContentPane().add(dateChooser);
		
		JLabel lblFoto = new JLabel("Foto:");
		lblFoto.setBounds(29, 249, 46, 14);
		getContentPane().add(lblFoto);
		
	    btnImagen= new JButton("Seleccionar foto");
		btnImagen.setBounds(165, 271, 152, 23);
		getContentPane().add(btnImagen);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setBounds(29, 144, 70, 20);
		getContentPane().add(lblContrasea);
		
		JLabel lblRepetirContraseña = new JLabel("Repetir contraseña:");
		lblRepetirContraseña.setBounds(29, 175, 101, 14);
		getContentPane().add(lblRepetirContraseña);
		
		textoContraseña = new JPasswordField();
		textoContraseña.setBounds(165, 144, 152, 20);
		getContentPane().add(textoContraseña);
		
		textoRepetirContraseña = new JPasswordField();
		textoRepetirContraseña.setBounds(165, 175, 152, 19);
		getContentPane().add(textoRepetirContraseña);
		
		nombreImagen = new JTextField();
		nombreImagen.setBounds(164, 243, 153, 20);
		getContentPane().add(nombreImagen);
		nombreImagen.setColumns(10);
		
	    setTitle("Modificar datos");
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setResizable(false);
	    setLocationRelativeTo(null);
	    setVisible(false);
	    }
	}