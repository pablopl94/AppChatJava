package app.chat.application;

import app.chat.javabeans.Marco;
import app.chat.javabeans.PanelServidor;

public class AppServidor {

	public static void main (String[]args) {

		new Marco(new PanelServidor(), "App Servidor");
		 //setBackground(Color.decode("#1D2026"));
	}
}