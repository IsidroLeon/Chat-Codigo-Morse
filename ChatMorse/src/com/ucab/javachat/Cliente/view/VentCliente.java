/*
 * Cliente.java
 *
 * Created on 12 de marzo de 2008, 06:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.ucab.javachat.Cliente.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.JOptionPane.*;

import com.ucab.javachat.Cliente.model.Cliente;

/**
 * 
 * @author Administrador
 */
public class VentCliente extends JFrame{
     public String mensajeCliente;
     public JTextArea panMostrar;
     public JTextField txtMensaje;
     public JButton butEnviar;
     public JLabel lblNomUser;
     public JList lstActivos;
     public JButton butPrivado;	
      
     public JMenuBar barraMenu;
     public JMenu JMAyuda;
     public JMenuItem help;
     public JMenu JMAcerca;
     public JMenuItem acercaD;
      
     public JOptionPane AcercaDe;
     
     public Vector<String> nomUsers;
     /** Creates a new instance of Cliente */
     public VentCliente() throws IOException {
             super("Cliente Chat");
             txtMensaje = new JTextField(30);
             butEnviar = new JButton("Enviar");
             lblNomUser = new JLabel("Usuario <<  >>");
             lblNomUser.setHorizontalAlignment(JLabel.CENTER);
             panMostrar = new JTextArea();             
             panMostrar.setColumns(25);
             lstActivos=new JList();             
             butPrivado=new JButton("Privado");
             
             barraMenu=new JMenuBar();
             JMAyuda=new JMenu("Ayuda");
             help=new JMenuItem("Ayuda");
             
             
             JMAcerca=new JMenu("Acerca de");
             acercaD=new JMenuItem("Creditos");
             acercaD.setActionCommand("Acerca");
             
             JMAyuda.add(help);
             JMAcerca.add(acercaD);
             barraMenu.add(JMAcerca);
             barraMenu.add(JMAyuda);            
             
             
             panMostrar.setEditable(false);            
             panMostrar.setForeground(Color.BLUE);
             panMostrar.setBorder(javax.swing.BorderFactory.createMatteBorder(3,3,3,3,new Color(25,10,80)));		

             JPanel panAbajo = new JPanel();
             panAbajo.setLayout(new BorderLayout());
                panAbajo.add(new JLabel("  Ingrese mensaje a enviar:"),BorderLayout.NORTH);
                panAbajo.add(txtMensaje, BorderLayout.CENTER);
                panAbajo.add(butEnviar, BorderLayout.EAST);
             JPanel panRight = new JPanel();
             panRight.setLayout(new BorderLayout());
                panRight.add(lblNomUser, BorderLayout.NORTH);
                panRight.add(new JScrollPane(panMostrar), BorderLayout.CENTER);
                panRight.add(panAbajo,BorderLayout.SOUTH);
             JPanel panLeft=new JPanel();
             panLeft.setLayout(new BorderLayout());
               panLeft.add(new JScrollPane(this.lstActivos),BorderLayout.CENTER);
               panLeft.add(this.butPrivado,BorderLayout.NORTH);
             JSplitPane sldCentral=new JSplitPane();  
             sldCentral.setDividerLocation(100);
             sldCentral.setDividerSize(7);
             sldCentral.setOneTouchExpandable(true);
               sldCentral.setLeftComponent(panLeft);
               sldCentral.setRightComponent(panRight);
             
             
             setLayout(new BorderLayout());
             add(sldCentral, BorderLayout.CENTER);   
             add(barraMenu,BorderLayout.NORTH);
             
             txtMensaje.requestFocus();//pedir el focus	
                  
             setSize(450, 430);
             setLocation(120, 90);
             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
             setVisible(true);
     }
}
