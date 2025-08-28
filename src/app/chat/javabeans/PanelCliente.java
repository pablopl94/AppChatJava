package app.chat.javabeans;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;


public class PanelCliente extends JPanel {

    private JTextArea textArea;
    private JTextField nickRem, ipRec;
    

    public PanelCliente() {

        setLayout(new BorderLayout());
        setBackground(Color.decode("#1D2026"));
        
        //---LAMINA SUPERIOR CUADRO TEXTO RECIBIDO--------------

        //Lamina nick remitente y nick receptor
        JPanel lamina = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 10)); 
        lamina.setBackground(Color.decode("#1D2026"));
        
        //Etiqueta y campo nick
        JLabel nick = new JLabel("Nick: ");
        nick.setForeground(Color.WHITE);
        nick.setFont(new Font("Arial", Font.PLAIN, 14));
        
        nickRem = new JTextField(8);
        nickRem.setBackground(Color.decode("#2C2F33"));
        nickRem.setForeground(Color.WHITE);
        nickRem.setCaretColor(Color.WHITE);
        nickRem.setBorder(BorderFactory.createLineBorder(Color.decode("#595959"), 2));
        nickRem.setFont(new Font("Arial", Font.PLAIN, 12));

        //Etiqueta y campo port
        JLabel port = new JLabel("Port: ");
        port.setForeground(Color.WHITE);
        port.setFont(new Font("Arial", Font.PLAIN, 14));
        
        ipRec = new JTextField(8);
        ipRec.setBackground(Color.decode("#2C2F33"));
        ipRec.setForeground(Color.WHITE);
        ipRec.setCaretColor(Color.WHITE);
        ipRec.setBorder(BorderFactory.createLineBorder(Color.decode("#595959"), 2));
        ipRec.setFont(new Font("Arial", Font.PLAIN, 12));
      
        // Añadir en orden: Nick, campo nick, espacio, Port, campo port
        lamina.add(nick);
        lamina.add(nickRem);
        lamina.add(Box.createHorizontalStrut(20)); // Separador entre los dos grupos
        lamina.add(port);
        lamina.add(ipRec);
        
        add(lamina, BorderLayout.NORTH);
        
 
        //---LAMINA CENTRO AREA DE TEXTO---------------
        
        //Lamina area de texto
        JPanel lamina2 = new JPanel(new BorderLayout());
        lamina2.setBackground(Color.decode("#1D2026"));
        lamina2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        textArea = new JTextArea(13, 20);
        textArea.setBackground(Color.decode("#2C2F33"));
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);
        textArea.setBorder(BorderFactory.createLineBorder(Color.decode("#595959"), 2));
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.getViewport().setBackground(Color.decode("#2C2F33"));
        scrollPane.setBackground(Color.decode("#1D2026"));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#595959"), 2));

        lamina2.add(scrollPane, BorderLayout.CENTER);
        add(lamina2, BorderLayout.CENTER); 
        
        
        //---LAMINA INFERIOR BOTON ENVIAR--------------

        //Lamina cuadro de texto 
        JPanel lamina3 = new JPanel(new BorderLayout());
        lamina3.setBackground(Color.decode("#1D2026"));
        lamina3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JTextField cuadroTexto = new JTextField(20);
        cuadroTexto.setBackground(Color.decode("#2C2F33"));
        cuadroTexto.setForeground(Color.WHITE);
        cuadroTexto.setCaretColor(Color.WHITE);
        cuadroTexto.setBorder(BorderFactory.createLineBorder(Color.decode("#595959"), 2));
        cuadroTexto.setFont(new Font("Arial", Font.PLAIN, 14));

        lamina3.add(cuadroTexto, BorderLayout.CENTER);

        //Lamina boton
        JPanel lamina4 = new JPanel(new FlowLayout());
        lamina4.setBackground(Color.decode("#1D2026"));

        JButton enviar = new JButton("Enviar");
        enviar.setBackground(Color.decode("#595959"));
        enviar.setForeground(Color.WHITE);
        enviar.setBorderPainted(false);
        enviar.setFocusPainted(false);
        enviar.setFont(new Font("Arial", Font.BOLD, 14));
        enviar.setPreferredSize(new Dimension(100, 30));
        enviar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    //Creando via de comunicación
                    Socket sock = new Socket("192.168.0.13", 9999);

                    //Creando el flujo de comunicación
                    DataOutputStream dataOutput = new DataOutputStream(sock.getOutputStream());
                    dataOutput.writeUTF(cuadroTexto.getText());

                    // Limpiar el campo después de enviar
                    cuadroTexto.setText("");

                } catch (IOException e1) {
                    
                    e1.printStackTrace();
                }
            }
        });

        lamina4.add(enviar);

        	
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(lamina3, BorderLayout.CENTER);
        panelInferior.add(lamina4, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);

    }
}