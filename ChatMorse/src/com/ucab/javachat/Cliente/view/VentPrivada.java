/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucab.javachat.Cliente.view;
import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.*;

import com.ucab.javachat.Cliente.model.Cliente;
/**
 *
 * @author Administrador
 */
public class VentPrivada extends JFrame
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 588393534461811702L;
public JTextArea panMostrar;
   public JTextField txtMensaje;
   public JButton butEnviar;
   
   public Cliente cliente;
   public Vector<String> amigo;
   
   public VentPrivada(Cliente cliente)
   {
      super("Amigo");
      this.cliente=cliente;
      txtMensaje = new JTextField(30);
      butEnviar = new JButton("Enviar");
      panMostrar = new JTextArea(); 
      panMostrar.setEditable(false);
      txtMensaje.requestFocus();
      
      JPanel panAbajo = new JPanel();
             panAbajo.setLayout(new BorderLayout());
             panAbajo.add(new JLabel("  Ingrese mensage a enviar:"),
                                BorderLayout.NORTH);
             panAbajo.add(txtMensaje, BorderLayout.CENTER);
             panAbajo.add(butEnviar, BorderLayout.EAST);
      
      setLayout(new BorderLayout());
      add(new JScrollPane(panMostrar),BorderLayout.CENTER);
      add(panAbajo,BorderLayout.SOUTH);
       
      amigo = new Vector<String>();
      
      setSize(300,300);
      setLocation(570,90);      			      
   }
}

