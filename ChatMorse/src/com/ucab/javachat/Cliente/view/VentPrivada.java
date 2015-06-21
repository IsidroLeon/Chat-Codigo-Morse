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
@SuppressWarnings("serial")
public class VentPrivada extends JFrame
{

public JTextArea panMostrar;
   public JTextField txtMensaje;
   public JButton butEnviar;
   
   public Cliente cliente;
   public Vector<String> amigo;
   public JButton btnConvertir = new JButton("Convertir");
   
   public VentPrivada(Cliente cliente)
   {
      super("Amigo");
      this.cliente=cliente;
      txtMensaje = new JTextField(30);
      butEnviar = new JButton("Enviar");
      txtMensaje.requestFocus();
      
      JPanel panAbajo = new JPanel();
      panAbajo.setBounds(0, 229, 401, 40);
             panAbajo.setLayout(new BorderLayout());
             panAbajo.add(new JLabel("  Ingrese mensaje a enviar:"),
                                BorderLayout.NORTH);
             panAbajo.add(txtMensaje, BorderLayout.CENTER);
             panAbajo.add(butEnviar, BorderLayout.EAST);
      getContentPane().setLayout(null);
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(0, 0, 513, 222);
      getContentPane().add(scrollPane);
      panMostrar = new JTextArea();
      panMostrar.setEditable(false);
      panMostrar.setRows(16);
      scrollPane.setColumnHeaderView(panMostrar);
      panMostrar.setColumns(33);
      getContentPane().add(panAbajo);
      btnConvertir.setBounds(402, 244, 111, 25);
      getContentPane().add(btnConvertir);
       
      amigo = new Vector<String>();
      
      setSize(515,300);
      setLocation(570,90);      			      
   }
}
