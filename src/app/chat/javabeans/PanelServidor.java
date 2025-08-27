package app.chat.javabeans;

import java.awt.BorderLayout;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class PanelServidor extends JPanel implements Runnable{
	
	private JTextArea textArea;
	
	
	public PanelServidor() {
		
		setLayout(new BorderLayout());
		textArea = new JTextArea();
		
		add(textArea);
		
		Thread t = new Thread(this);
		t.start();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
		
			//Puerto a la escucha
			ServerSocket server = new ServerSocket(9999);
			
			while(true) {
				
				//Acepta todas las conexiones que llega por el puerto
				Socket sock = server.accept();
				
				//Flujo de entrada
				DataInputStream input = new DataInputStream(sock.getInputStream());
				
				String mensaje = input.readUTF();
				
				textArea.append("\n " + mensaje);
				
				sock.close();
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}