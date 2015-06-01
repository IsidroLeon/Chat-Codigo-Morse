
package com.ucab.clientechat.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

/**
 * Clase principal del cliente del chat
 * 
 * @author Ivan Salas Corrales <http://programando-o-intentandolo.blogspot.com.es/>
 */
public class VentanaChat extends JFrame {
    public JTextArea mensajesChat;
    public JTextField tfMensaje;
    public JButton btEnviar;
    
    
    public VentanaChat(){
        super("Cliente Chat");
        
        // Elementos de la ventana
        mensajesChat = new JTextArea();
        mensajesChat.setEnabled(false); // El area de mensajes del chat no se debe de poder editar
        mensajesChat.setLineWrap(true); // Las lineas se parten al llegar al ancho del textArea
        mensajesChat.setWrapStyleWord(true); // Las lineas se parten entre palabras (por los espacios blancos)
        JScrollPane scrollMensajesChat = new JScrollPane(mensajesChat);
        tfMensaje = new JTextField("");
        btEnviar = new JButton("Enviar");
        
        
        // Colocacion de los componentes en la ventana
        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(20, 20, 20, 20);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        c.add(scrollMensajesChat, gbc);
        // Restaura valores por defecto
        gbc.gridwidth = 1;        
        gbc.weighty = 0;
        
        gbc.fill = GridBagConstraints.HORIZONTAL;        
        gbc.insets = new Insets(0, 20, 20, 20);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        c.add(tfMensaje, gbc);
        // Restaura valores por defecto
        gbc.weightx = 0;
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        c.add(btEnviar, gbc);
        
        this.setBounds(400, 100, 400, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    }

}