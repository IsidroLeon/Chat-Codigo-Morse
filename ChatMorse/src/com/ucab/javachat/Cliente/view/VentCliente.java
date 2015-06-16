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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 
 * @author Administrador
 */
public class VentCliente extends JFrame{
     public String mensajeCliente;
     public JLabel lblNomUser;
     public JList<String> lstActivos;
     public JButton butPrivado;	
      
     public JMenuBar barraMenu;
     public JMenu JMAyuda;
     public JMenuItem help;
     public JMenu JMAcerca;
     public JMenuItem acercaD;
     public String nombreusuario;
      
     public JOptionPane AcercaDe;
     
     public Vector<String> nomUsers;
     /** Creates a new instance of Cliente */
     public VentCliente(){
             super("Cliente Chat");
             lblNomUser = new JLabel("Usuario <<  >>");
             lblNomUser.setHorizontalAlignment(JLabel.CENTER);
             lstActivos=new JList<String>();             
             butPrivado=new JButton("Privado");
            
             JPanel panLeft=new JPanel();
             panLeft.setLayout(new BorderLayout());
             	panLeft.add(lblNomUser, BorderLayout.NORTH);
             	panLeft.add(new JScrollPane(this.lstActivos),BorderLayout.CENTER);
             	panLeft.add(this.butPrivado,BorderLayout.NORTH);
             
             setLayout(new BorderLayout());
             add(panLeft, BorderLayout.CENTER);   
             add(lblNomUser,BorderLayout.NORTH);
                  
             setSize(190, 430);
             setLocation(120, 90);
             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
             setVisible(false);
     }
}