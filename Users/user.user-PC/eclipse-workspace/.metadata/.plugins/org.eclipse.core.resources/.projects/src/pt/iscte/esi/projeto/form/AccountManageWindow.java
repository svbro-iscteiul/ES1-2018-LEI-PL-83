package pt.iscte.esi.projeto.form;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import pt.iscte.esi.projeto.utils.XMLFileEditor;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



/**
 * Window UI for Account Management
 *
 */
public class AccountManageWindow extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private MainWindow mainWindow;

	private JTextField textField;
	private JTextField extendedAccessTokenFace1;
	private JTextField extendedAccessTokenFace2;
	private JTextField textField_4;
	private JTextField mailMail;
	private JTextField mail;
	private JTextField textField_6;
	private JTextField accessTokenFace;
	private JTextField authConsumKeyTwit;
	private JTextField textField_3;
	private JTextField authAccessTokenSecretTwit;
	private JTextField passMail;
	private JTextField textField_9;
	private JTextField authAccessTokenTwit;
	private JTextField textField_10;
	private JTextField authConsumSecretTwit;
	private JTextField usernameMail;

	private XMLFileEditor editor = new XMLFileEditor();


	/**
	 * Class constructor.
	 */
	public AccountManageWindow() {
		initialize();
		frame.setVisible(true);
	}
	
	/**
	 * Class constructor.
	 */
	public AccountManageWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		initialize();
		frame.setVisible(true);
	}

	
	/**
	 * Creates and initializes Window.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 800, 605);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		/*
		 * Adds image to the window
		 */
		JLabel newLabel = new JLabel("");
		newLabel.setBounds(45, 84, 119, 413);
		ImageIcon image = new ImageIcon(AccountManageWindow.class.getResource("/pt/iscte/esi/projeto/form/images/LogosContas.png"));
		Image img = image.getImage().getScaledInstance(newLabel.getWidth(), newLabel.getHeight(), Image.SCALE_SMOOTH);
		newLabel.setIcon(new ImageIcon(img));
		frame.getContentPane().add(newLabel);

		/*
		 * Creates window titles
		 */
		JLabel label = DefaultComponentFactory.getInstance().createTitle("");
		label.setBounds(318, 84, 88, 14);
		contentPane.add(label);

		JLabel lblAdicionarContas = DefaultComponentFactory.getInstance().createTitle("ADICIONAR CONTAS");
		lblAdicionarContas.setForeground(new Color(0, 0, 128));
		lblAdicionarContas.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAdicionarContas.setBounds(318, 28, 195, 23);
		contentPane.add(lblAdicionarContas);

		JLabel lblNewLabel = new JLabel(
				"Adiciona os c\u00F3digos de acesso relativos a cada conta e recebe informa\u00E7\u00E3o acad\u00E9mica proveniente de cada canal.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setBounds(158, 49, 516, 14);
		contentPane.add(lblNewLabel);

		accessTokenFace = new JTextField();
		accessTokenFace.setColumns(10);
		accessTokenFace.setBounds(377, 105, 366, 20);
		contentPane.add(accessTokenFace);

		textField = new JTextField();

		extendedAccessTokenFace1 = new JTextField();
		extendedAccessTokenFace1.setBounds(377, 140, 121, 20);
		contentPane.add(extendedAccessTokenFace1);
		extendedAccessTokenFace1.setColumns(10);

		extendedAccessTokenFace2 = new JTextField();
		extendedAccessTokenFace2.setColumns(10);
		extendedAccessTokenFace2.setBounds(507, 140, 236, 20);
		contentPane.add(extendedAccessTokenFace2);


		authConsumSecretTwit = new JTextField();
		authConsumSecretTwit.setColumns(10);
		authConsumSecretTwit.setBounds(376, 240, 366, 20);
		contentPane.add(authConsumSecretTwit);

		mailMail = new JTextField();
		mailMail.setBounds(377, 427, 366, 20);
		contentPane.add(mailMail);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(377, 105, 366, 20);
		contentPane.add(textField_6);

		authConsumKeyTwit = new JTextField();
		authConsumKeyTwit.setColumns(10);
		authConsumKeyTwit.setBounds(377, 270, 366, 20);
		contentPane.add(authConsumKeyTwit);

		textField_9 = new JTextField();
		authAccessTokenSecretTwit = new JTextField();
		authAccessTokenSecretTwit.setColumns(10);
		authAccessTokenSecretTwit.setBounds(376, 333, 366, 20);
		contentPane.add(authAccessTokenSecretTwit);

		authAccessTokenTwit = new JTextField();
		authAccessTokenTwit.setColumns(10);
		authAccessTokenTwit.setBounds(376, 301, 366, 20);
		contentPane.add(authAccessTokenTwit);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(376, 333, 366, 20);
		contentPane.add(textField_3);

		usernameMail = new JTextField();
		usernameMail.setColumns(10);
		usernameMail.setBounds(377, 397, 366, 20);
		contentPane.add(usernameMail);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(377, 427, 366, 20);
		contentPane.add(textField_4);

		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(376, 240, 366, 20);
		contentPane.add(textField_10);

		passMail = new JTextField();
		passMail.setBounds(377, 458, 251, 20);
		passMail.setColumns(10);
		passMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(passMail);

		JLabel lblEmail = DefaultComponentFactory.getInstance().createLabel("Access token:");
		lblEmail.setBounds(285, 105, 119, 14);
		contentPane.add(lblEmail);

		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Extended access token:");
		lblNewJgoodiesLabel.setBounds(234, 143, 144, 14);
		contentPane.add(lblNewJgoodiesLabel);

		JLabel lblNewJgoodiesLabel_00 = DefaultComponentFactory.getInstance().createLabel("AuthConsumerSecret:");
		lblNewJgoodiesLabel_00.setBounds(243, 240, 123, 14);
		contentPane.add(lblNewJgoodiesLabel_00);

		JLabel lblNewJgoodiesLabel_0 = DefaultComponentFactory.getInstance().createLabel("AuthConsumerKey:");
		lblNewJgoodiesLabel_0.setBounds(257, 270, 110, 14);
		contentPane.add(lblNewJgoodiesLabel_0);

		JLabel lblNewJgoodiesLabel_2 = DefaultComponentFactory.getInstance().createLabel("Auth. access token:");
		lblNewJgoodiesLabel_2.setBounds(254, 300, 112, 14);
		contentPane.add(lblNewJgoodiesLabel_2);

		JLabel lblUsername = DefaultComponentFactory.getInstance().createLabel("Auth access \r\ntoken secret:");
		lblUsername.setBounds(228, 336, 138, 14);
		contentPane.add(lblUsername);

		JLabel lblUsernameMail = DefaultComponentFactory.getInstance().createLabel("Username: ");
		lblUsernameMail.setBounds(299, 400, 68, 14);
		contentPane.add(lblUsernameMail);

		JLabel lblEmail_1 = DefaultComponentFactory.getInstance().createLabel("E-mail: ");
		lblEmail_1.setBounds(316, 430, 41, 17);
		contentPane.add(lblEmail_1);

		JLabel lblPassword_1 = DefaultComponentFactory.getInstance().createLabel("Password:");
		lblPassword_1.setBounds(299, 463, 71, 14);
		contentPane.add(lblPassword_1);

		JButton btnAdicionarFace = new JButton("Adicionar"); // Botão Adiciona tokens - facebook
		btnAdicionarFace.setBounds(396, 171, 89, 23);
		contentPane.add(btnAdicionarFace);


		JButton btnAdicionarTwit = new JButton("Adicionar"); // Botão Adiciona tokens - Twitter
		btnAdicionarTwit.setBounds(396, 360, 89, 23);
		contentPane.add(btnAdicionarTwit);

		JButton button = new JButton("Adicionar");
		button.setBounds(396, 360, 89, 23);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							if(!authConsumKeyTwit.getText().equals(null) 
									&& !textField_10.getText().equals(null) 
									&& !textField_9.getText().equals(null) 
									&& !textField_3.getText().equals(null)){
								editor.AddAcountsForTwitter(authConsumKeyTwit.getText(),textField_10.getText(),textField_9.getText(),textField_3.getText());
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		contentPane.add(button);



		JButton btnAdicionarMail = new JButton("Adicionar"); // Botão Adiciona tokens - e-mail
		btnAdicionarMail.setBounds(396, 489, 89, 23);
		contentPane.add(btnAdicionarMail);

		JLabel infoLabel = new JLabel("");
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		infoLabel.setBounds(290, 530, 396, 25);
		contentPane.add(infoLabel);

		JButton button_1 = new JButton("Adicionar");
		button_1.setBounds(396, 489, 89, 23);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							if(!textField_4.getText().equals(null) 
									&& !passMail.getText().equals(null)){
								editor.AddAcountsForEmail(textField_4.getText(), passMail.getText());
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		contentPane.add(button_1);


		/*
		 * Adds Ok button to the frame and his action
		 */
		JButton btnOk = new JButton("OK");
	
		btnOk.setBounds(690, 527, 69, 29);
		
		btnOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(btnOk);
		
		JRadioButton rdbtnDesactivarContaFacebbok = new JRadioButton("Desactivar conta Facebook");
		rdbtnDesactivarContaFacebbok.setBackground(SystemColor.text);
		rdbtnDesactivarContaFacebbok.setForeground(Color.BLACK);
		rdbtnDesactivarContaFacebbok.setBounds(517, 171, 226, 23);
		contentPane.add(rdbtnDesactivarContaFacebbok);
		
		JRadioButton rdbtnDesactivarContaTwitter = new JRadioButton("Desactivar conta Twitter");
		rdbtnDesactivarContaTwitter.setForeground(Color.BLACK);
		rdbtnDesactivarContaTwitter.setBackground(Color.WHITE);
		rdbtnDesactivarContaTwitter.setBounds(517, 360, 226, 23);
		contentPane.add(rdbtnDesactivarContaTwitter);
		
		JRadioButton rdbtnDesactivarContaMail = new JRadioButton("Desactivar conta Mail");
		rdbtnDesactivarContaMail.setForeground(Color.BLACK);
		rdbtnDesactivarContaMail.setBackground(Color.WHITE);
		rdbtnDesactivarContaMail.setBounds(517, 489, 226, 23);
		contentPane.add(rdbtnDesactivarContaMail);

		// (18_11_18 - elsa)
		// Configuration of the frame, to receive tokens of Twitter from user
		btnAdicionarTwit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (textField_10.getText().toString().equals("") 
						|| authConsumKeyTwit.getText().toString().equals("")
						|| textField_9.getText().toString().equals("") 
						|| textField_3.getText().toString().equals("")) {
					System.out.println("1 caixa texto=" + textField_10.getText().toString()); // não esta a captar info
																								// da caixa, problema?
					infoLabel.setText("<html><font color='red'>Error: All input fields are mandatory. Insert all Twitter tokens! </font></html>");

				} else {
					XMLFileEditor i = new XMLFileEditor();
					System.out.println("chego aqui");

					String s = i.addTwittwerTokens(textField_10.getText().toString(), authConsumKeyTwit.getText().toString(),
							textField_9.getText().toString(), textField_3.getText().toString());

					if (s.equals("Error")) {
						infoLabel.setText("<html><font color='red'>Error</font></html>");
					} else if (s.equals("Tokens already exists")) {
						infoLabel.setText(
								"<html><font color='red'>Error: One or more tokens already in use.</font></html>");

						System.out.println(textField_10.getText().toString());
						System.out.println(textField_9.getText().toString());
						System.out.println(textField_3.getText().toString());
						System.out.println(authConsumKeyTwit.getText().toString());
					} else {
						infoLabel.setText(
								"<html><font color='green'> Twitter Tokens successfully registered!</font></html>");

					}
				}
			}
		});

		// Implementa funcionalidade de registo de e-mail e respetiva pass, no ficheiro
		// de texto
		btnAdicionarMail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
				if (usernameMail.getText().equals("") 
						|| textField_4.getText().equals("")
						|| passMail.getText().equals("")) {
					System.out.println("1 caixa texto=" + textField_4.getText()); // não esta a captar info da caixa, problema?
					infoLabel.setText("<html><font color='red'>Error: All input fields are mandatory. Insert all e-mail inputs! </font></html>");

				} else {
					XMLFileEditor i = new XMLFileEditor();
					String s = i.SignIn(usernameMail.getText().toString(), textField_4.getText().toString(),
							passMail.getText().toString());

					if (s.equals("Error")) {
						infoLabel.setText("<html><font color='red'>Error</font></html>");

					} else if (s.equals("Email taken")) {
						infoLabel.setText("<html><font color='red'>Error: email already taken.</font></html>");

					} else {
						infoLabel.setText("<html><font color='green'>You got registered.</font></html>");
					}
				}
				} catch (Exception e1) {
				e1.printStackTrace();
				
			}
			}
		});
		
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//new MainWindow();
				
				if(rdbtnDesactivarContaFacebbok.isEnabled()) {
					mainWindow.setFacebookOff(false);
				}
				if(rdbtnDesactivarContaTwitter.isEnabled()) {
					mainWindow.setTwitterOff(false);
				}
				if(rdbtnDesactivarContaMail.isEnabled()) {
					mainWindow.setEmailOff(false);
				}
				
				frame.dispose();
				
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new AccountManageWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
