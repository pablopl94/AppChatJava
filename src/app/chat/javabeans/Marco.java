package app.chat.javabeans;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Marco extends JFrame {

	public Marco(JPanel panel, String titulo) {

		setTitle(titulo);
		setBounds(600, 100, 300, 400);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		add(panel);
		setVisible(true);
	}
}