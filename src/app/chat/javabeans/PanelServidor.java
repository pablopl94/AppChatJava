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
			
			String nick, ip, texto;
			DatosPaquete datosInput;
			
			while(true) {
				
				//Acepta todas las conexiones que llega por el puerto
				Socket sock = server.accept();
				
				//Creamos el flujo de datos de entrada
				ObjectInputStream inputStream = new ObjectInputStream(sock.getInputStream());
				
				datosInput = (DatosPaquete) inputStream.readObject();
				
				nick = datosInput.getNickname();
				ip = datosInput.getIp();
				texto = datosInput.getTexto();
				
				textArea.append(String.format("[%s] (IP: %s): %s%n", nick, ip, texto ));
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
}