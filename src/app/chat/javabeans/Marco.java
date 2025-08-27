package app.chat.javabeans;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Marco extends JFrame {

	public Marco(JPanel panel, String titulo) {

		setTitle(titulo);
		setBounds(600, 100, 300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
		setVisible(true);
	}
}