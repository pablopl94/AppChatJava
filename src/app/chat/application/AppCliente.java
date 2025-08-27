package app.chat.application;

import app.chat.javabeans.Marco;
import app.chat.javabeans.PanelCliente;

public class AppCliente {

	public static void main(String[] args) {

		new Marco(new PanelCliente(), "App Cliente");
	}
}
