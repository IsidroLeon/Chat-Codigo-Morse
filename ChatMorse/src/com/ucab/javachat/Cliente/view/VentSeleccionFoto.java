package com.ucab.javachat.Cliente.view;

import javax.swing.JFrame;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;

public class VentSeleccionFoto {

	public JFrame frmSeleccioneUnaFoto;
	public JFileChooser fotoSeleccionada;

	public VentSeleccionFoto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSeleccioneUnaFoto = new JFrame();
		frmSeleccioneUnaFoto.setTitle("Seleccione una foto");
		frmSeleccioneUnaFoto.setBounds(100, 100, 500, 345);
		frmSeleccioneUnaFoto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fotoSeleccionada = new JFileChooser();
		frmSeleccioneUnaFoto.getContentPane().add(fotoSeleccionada, BorderLayout.NORTH);
	}

}
