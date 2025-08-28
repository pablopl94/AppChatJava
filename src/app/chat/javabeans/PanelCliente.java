package app.chat.javabeans;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class PanelCliente extends JPanel implements Runnable {

    private JTextArea textArea;
    private JTextField ipTextField;


    public PanelCliente() {

        setLayout(new BorderLayout());
        setBackground(Color.decode("#1D2026"));

        //---LAMINA SUPERIOR CUADRO TEXTO RECIBIDO--------------

        //Lamina nick remitente y nick receptor
        JPanel lamina = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 10));
        lamina.setBackground(Color.decode("#1D2026"));

        //Etiqueta y campo nick
        ImageIcon usuarioIcon = new ImageIcon(getClass().getResource("/resources/icons/user.png"));
        String nickText = JOptionPane.showInputDialog(null, "Introduce tu nick");
        JLabel nick = new JLabel(nickText, usuarioIcon, SwingConstants.LEFT);
        nick.setForeground(Color.WHITE);
        nick.setFont(new Font("Arial", Font.BOLD, 12));

        //Etiqueta y campo port
        JLabel ip = new JLabel("IP: ");
        ip.setForeground(Color.WHITE);
        ip.setFont(new Font("Arial", Font.PLAIN, 14));

        ipTextField = new JTextField(8);
        ipTextField.setBackground(Color.decode("#2C2F33"));
        ipTextField.setForeground(Color.WHITE);
        ipTextField.setCaretColor(Color.WHITE);
        ipTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#595959"), 2));
        ipTextField.setFont(new Font("Arial", Font.PLAIN, 12));

        // Añadir en orden: Nick, campo nick, espacio, Port, campo port
        lamina.add(nick);
        lamina.add(Box.createHorizontalStrut(40));
        lamina.add(ip);
        lamina.add(ipTextField);

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
        textArea.setEditable(false);  // No editable que solo aparezca el mensaje del otro cliente

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

                    //Instanciando clase DatosPaquete
                    DatosPaquete datosOutput = new DatosPaquete();

                    //Almacenar en las propiedades, los datos introducidos
                    datosOutput.setNickname(nick.getText());
                    datosOutput.setIp(ipTextField.getText());
                    datosOutput.setTexto(cuadroTexto.getText());

                    //Creamos el stream (flujo de salida)
                    ObjectOutputStream outputStean = new ObjectOutputStream(sock.getOutputStream());
                    outputStean.writeObject(datosOutput);

                    //Cerramos el socket
                    sock.close();

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

		//Creamos el hilo que va a estar a la escucha
		Thread t = new Thread(this);
		t.start();
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {

			//Puerto a la escucha
			ServerSocket server = new ServerSocket(9090);

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

				sock.close();
			}

		} catch (IOException e) {

			e.printStackTrace();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
}