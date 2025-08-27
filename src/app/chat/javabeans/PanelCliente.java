package app.chat.javabeans;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;

public class PanelCliente extends JPanel {

	private JPanel lamina;
	private JPanel lamina2;

	public PanelCliente() {

		setLayout(new FlowLayout(FlowLayout.CENTER));

		setBackground(Color.decode("#1D2026"));

		JLabel titulo = new JLabel("Cliente");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Arial", Font.BOLD, 18));
		add(titulo);

		JTextField cuadroTexto = new JTextField(20);
		cuadroTexto.setBackground(Color.decode("#2C2F33"));
		cuadroTexto.setForeground(Color.WHITE);
		cuadroTexto.setCaretColor(Color.WHITE);
		cuadroTexto.setBorder(BorderFactory.createLineBorder(Color.decode("#595959"), 2));
		cuadroTexto.setFont(new Font("Arial", Font.PLAIN, 14));
		add(cuadroTexto);

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
					Socket sock = new Socket("192.168.0.13", 9999 );
					
					//Creando el flujo de comunicación
					DataOutputStream dataOutput = new DataOutputStream(sock.getOutputStream());
					dataOutput.writeUTF(cuadroTexto.getText());

					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		
		add(enviar);

	}
}