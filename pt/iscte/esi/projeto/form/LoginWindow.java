package pt.iscte.esi.projeto.form;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Font;

public class LoginWindow {

	private JFrame frame;
	private JTextField txtUtilizador;
	private JPasswordField txtPassw;


	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		JLabel lblNewLabel = new JLabel("");
		
		ImageIcon imgIcon = new ImageIcon("C:\\Users\\jose.f.santos\\Pictures\\logo_ISCTE-IUL.png");
		Image img = imgIcon.getImage();
		img = img.getScaledInstance(200, 500, java.awt.Image.SCALE_SMOOTH);
		imgIcon = new ImageIcon(img);
		//frame.getContentPane().setLayout(new GridLayout(4, 5, 0, 0));
		
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\jose.f.santos\\Pictures\\logo_ISCTE-IUL.png"));
		frame.getContentPane().add(lblNewLabel, BorderLayout.WEST);
		
		//Here starts Log In Panel
		
		JPanel logInPanel = new JPanel();
		logInPanel.setLayout(null);
		frame.getContentPane().add(logInPanel, BorderLayout.CENTER);
		
		JLabel lblUtilizador = new JLabel("Utilizador:");
		lblUtilizador.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logInPanel.add(lblUtilizador);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logInPanel.add(lblPassword);
		
		txtUtilizador = new JTextField();
		txtUtilizador.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUtilizador.setText("");
		logInPanel.add(txtUtilizador);
		txtUtilizador.setColumns(10);
		
		txtPassw = new JPasswordField();
		txtPassw.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPassw.setText("");
		logInPanel.add(txtPassw);
		txtPassw.setColumns(10);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logInPanel.add(btnLogIn);
		
		lblUtilizador.setBounds(250,200,100,30);
		lblPassword.setBounds(250,250,100,30);
		
		txtUtilizador.setBounds(350,200,100,30);
		txtPassw.setBounds(350,250,100,30);
		
		btnLogIn.setBounds(400,325,100,40);
	}

}
