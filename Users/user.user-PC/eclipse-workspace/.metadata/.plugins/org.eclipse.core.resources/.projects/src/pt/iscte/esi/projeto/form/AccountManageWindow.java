package pt.iscte.esi.projeto.form;


import java.awt.Color;
import java.awt.Cursor;
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



/**
 * Window UI for Account Management
 * 
 * @author Elsa Teixeira, José Santos, Sérgio Ribeiro LEI ISCTE
 *
 */
public class AccountManageWindow extends JFrame {

	private JFrame frame;
	private JPanel contentPane;

	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;

	private XMLFileEditor editor = new XMLFileEditor();


	/**
	 * Class constructor.
	 */
	public AccountManageWindow() {
		initialize();
		frame.setVisible(true);
	}

	
	/**
	 * Creates and initializes Window.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		newLabel.setBounds(87, 105, 119, 421);
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
		lblNewLabel.setBounds(158, 49, 553, 14);
		contentPane.add(lblNewLabel);

		/*
		 * Adding the different "frame components" to the window
		 */
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Desactivar mensagens deste canal");
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		rdbtnNewRadioButton.setBounds(491, 171, 241, 23);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnDesactivarMensagensDeste = new JRadioButton("Desactivar mensagens deste canal");
		rdbtnDesactivarMensagensDeste.setBackground(Color.WHITE);
		rdbtnDesactivarMensagensDeste.setBounds(491, 360, 225, 23);
		contentPane.add(rdbtnDesactivarMensagensDeste);

		JRadioButton radioButton = new JRadioButton("Desactivar mensagens deste canal");
		radioButton.setBackground(Color.WHITE);
		radioButton.setBounds(491, 485, 225, 23);
		contentPane.add(radioButton);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(377, 105, 366, 20);
		contentPane.add(textField_6);

		textField = new JTextField();

		textField = new JTextField();
		textField.setBounds(377, 140, 121, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(507, 140, 236, 20);
		contentPane.add(textField_2);


		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(376, 240, 366, 20);
		contentPane.add(textField_10);

		textField_4 = new JTextField();
		textField_4.setBounds(377, 427, 366, 20);
		contentPane.add(textField_4);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(377, 105, 366, 20);
		contentPane.add(textField_6);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(377, 270, 366, 20);
		contentPane.add(textField_1);

		textField_9 = new JTextField();
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(376, 333, 366, 20);
		contentPane.add(textField_3);

		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(376, 301, 366, 20);
		contentPane.add(textField_9);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(376, 333, 366, 20);
		contentPane.add(textField_3);

		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(377, 397, 366, 20);
		contentPane.add(textField_11);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(377, 427, 366, 20);
		contentPane.add(textField_4);

		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(376, 240, 366, 20);
		contentPane.add(textField_10);

		textField_8 = new JTextField();
		textField_8.setBounds(377, 458, 251, 20);
		textField_8.setColumns(10);
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(textField_8);

		JLabel lblEmail = DefaultComponentFactory.getInstance().createLabel("Access token:");
		lblEmail.setBounds(285, 105, 119, 14);
		contentPane.add(lblEmail);

		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Extended access token:");
		lblNewJgoodiesLabel.setBounds(234, 143, 144, 14);
		contentPane.add(lblNewJgoodiesLabel);

		JLabel lblNewJgoodiesLabel_00 = DefaultComponentFactory.getInstance().createLabel("AuthConsumerSecret:");
		lblNewJgoodiesLabel_00.setBounds(243, 240, 127, 14);
		contentPane.add(lblNewJgoodiesLabel_00);

		JLabel lblNewJgoodiesLabel_0 = DefaultComponentFactory.getInstance().createLabel("AuthConsumerKey:");
		lblNewJgoodiesLabel_0.setBounds(257, 270, 124, 14);
		contentPane.add(lblNewJgoodiesLabel_0);

		JLabel lblNewJgoodiesLabel_2 = DefaultComponentFactory.getInstance().createLabel("Auth. access token:");
		lblNewJgoodiesLabel_2.setBounds(254, 300, 124, 14);
		contentPane.add(lblNewJgoodiesLabel_2);

		JLabel lblUsername = DefaultComponentFactory.getInstance().createLabel("Auth access \r\ntoken secret:");
		lblUsername.setBounds(216, 335, 172, 14);
		contentPane.add(lblUsername);

		JLabel lblUsernameMail = DefaultComponentFactory.getInstance().createLabel("Username: ");
		lblUsernameMail.setBounds(299, 400, 68, 14);
		contentPane.add(lblUsernameMail);

		JLabel lblEmail_1 = DefaultComponentFactory.getInstance().createLabel("E-mail: ");
		lblEmail_1.setBounds(299, 430, 68, 14);
		contentPane.add(lblEmail_1);

		JLabel lblPassword_1 = DefaultComponentFactory.getInstance().createLabel("Password:");
		lblPassword_1.setBounds(285, 461, 82, 14);
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
							if(!textField_1.getText().equals(null) 
									&& !textField_10.getText().equals(null) 
									&& !textField_9.getText().equals(null) 
									&& !textField_3.getText().equals(null)){
								editor.AddAcountsForTwitter(textField_1.getText(),textField_10.getText(),textField_9.getText(),textField_3.getText());
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
									&& !textField_8.getText().equals(null)){
								editor.AddAcountsForEmail(textField_4.getText(), textField_8.getText());
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
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MainWindow();
				frame.dispose();
			}
		});
		btnOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(btnOk);

		// (18_11_18 - elsa)
		// Configuration of the frame, to receive tokens of Twitter from user
		btnAdicionarTwit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (textField_10.getText().toString().equals("") 
						|| textField_1.getText().toString().equals("")
						|| textField_9.getText().toString().equals("") 
						|| textField_3.getText().toString().equals("")) {
					System.out.println("1 caixa texto=" + textField_10.getText().toString()); // não esta a captar info
																								// da caixa, problema?
					infoLabel.setText("<html><font color='red'>Error: All input fields are mandatory. Insert all Twitter tokens! </font></html>");

				} else {
					XMLFileEditor i = new XMLFileEditor();
					System.out.println("chego aqui");

					String s = i.addTwittwerTokens(textField_10.getText().toString(), textField_1.getText().toString(),
							textField_9.getText().toString(), textField_3.getText().toString());

					if (s.equals("Error")) {
						infoLabel.setText("<html><font color='red'>Error</font></html>");
					} else if (s.equals("Tokens already exists")) {
						infoLabel.setText(
								"<html><font color='red'>Error: One or more tokens already in use.</font></html>");

						System.out.println(textField_10.getText().toString());
						System.out.println(textField_9.getText().toString());
						System.out.println(textField_3.getText().toString());
						System.out.println(textField_1.getText().toString());
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
				if (textField_11.getText().toString().equals("") 
						|| textField_4.getText().toString().equals("")
						|| textField_8.getText().toString().equals("")) {
					System.out.println("1 caixa texto=" + textField_4.getText().toString()); // não esta a captar info
																								// da caixa, problema?
					infoLabel.setText(
							"<html><font color='red'>Error: All input fields are mandatory. Insert all e-mail inputs! </font></html>");

				} else {
					XMLFileEditor i = new XMLFileEditor();
					String s = i.SignIn(textField_11.getText().toString(), textField_4.getText().toString(),
							textField_8.getText().toString());

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
	}

	public static void main(String[] args) {

		AccountManageWindow m= new AccountManageWindow();
		m.initialize();
	}
}
