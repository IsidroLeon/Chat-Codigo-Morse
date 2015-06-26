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

@SuppressWarnings("serial")
public class VentModificar extends JFrame {
	
	/**
	 * 
	 */

	public JTextField textoUsuario;
	public JTextField textoNombre;
	public JTextField textoEmail;
	public JButton botonGuardar,  botonSalir;
	public JRadioButton rdbtnMasculino,rdbtnFemenino;
	public ButtonGroup grupoSexo;
	public JDateChooser dateChooser;
	public JFrame f = new JFrame();
	

	
	public VentModificar(com.ucab.javachat.Cliente.model.Usuario usuarioAutenticado) {
		getContentPane().setLayout(null);
		setBounds(100, 100, 361, 277);
		
		JLabel lblNombre = new JLabel("Usuario:");
		lblNombre.setBounds(29, 27, 90, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Nombre completo: ");
		lblApellido.setBounds(29, 58, 126, 14);
		getContentPane().add(lblApellido);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(29, 144, 35, 14);
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
		botonSalir.setBounds(181, 203, 136, 23);
		getContentPane().add(botonSalir );
		
		textoNombre = new JTextField();
		textoNombre.setBounds(165, 55, 152, 20);
		textoNombre.setColumns(10);
		getContentPane().add(textoNombre);
		
		botonGuardar = new JButton("Guardar");
		botonGuardar.setBounds(29, 203, 136, 23);
		getContentPane().add(botonGuardar);
		
		rdbtnFemenino = new JRadioButton("Femenino");
		rdbtnFemenino.setBounds(249, 144, 90, 14);
		getContentPane().add(rdbtnFemenino);
		
		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBounds(160, 144, 90, 14);
		rdbtnMasculino.setSelected(usuarioAutenticado.isSexo());
		getContentPane().add(rdbtnMasculino);
		
		grupoSexo = new ButtonGroup();
		grupoSexo.add(rdbtnMasculino);
		grupoSexo.add(rdbtnFemenino);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(165, 113, 152, 20);
		getContentPane().add(dateChooser);
		
		JLabel lblFoto = new JLabel("Foto:");
		lblFoto.setBounds(29, 169, 46, 14);
		getContentPane().add(lblFoto);
		
		JButton btnNewButton = new JButton("Seleccionar foto");
		btnNewButton.setBounds(165, 165, 152, 23);
		getContentPane().add(btnNewButton);
	
	    setTitle("Modificar datos");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setVisible(false);
	    }
	}
