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
import javax.swing.JPasswordField;
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


	private XMLFileEditor editor = new XMLFileEditor();

	private boolean twitterOff = false;
	private boolean facebookOff = false;
	private boolean emailOff = false;


	/**
	 * Class constructor.
	 */
	public AccountManageWindow() {
		updateBoolStatus();
		initialize();
		frame.setVisible(true);
	}



	/**
	 * Class constructor.
	 */
	public AccountManageWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		updateBoolStatus();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Updates boolean status from mainwindow
	 */
	private void updateBoolStatus() {
		if(mainWindow != null) {
			twitterOff = mainWindow.isTwitterOff();
			facebookOff = mainWindow.isFacebookOff();
			emailOff = mainWindow.isEmailOff();
		}

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

		JTextField accessTokenFace = new JTextField();
		accessTokenFace.setColumns(10);
		accessTokenFace.setBounds(377, 105, 366, 20);
		contentPane.add(accessTokenFace);


		JTextField AppId = new JTextField();
		AppId.setBounds(377, 132, 121, 20);
		contentPane.add(AppId);
		AppId.setColumns(10);

		JTextField AppSecret = new JTextField();
		AppSecret.setColumns(10);
		AppSecret.setBounds(377, 163, 236, 20);
		contentPane.add(AppSecret);


		JTextField authConsumSecretTwit = new JTextField();
		authConsumSecretTwit.setColumns(10);
		authConsumSecretTwit.setBounds(376, 240, 366, 20);
		contentPane.add(authConsumSecretTwit);

		JTextField mailMail = new JTextField();
		mailMail.setBounds(377, 422, 366, 20);
		contentPane.add(mailMail);


		JTextField authConsumKeyTwit = new JTextField();
		authConsumKeyTwit.setColumns(10);
		authConsumKeyTwit.setBounds(377, 270, 366, 20);
		contentPane.add(authConsumKeyTwit);

		JTextField authAccessTokenSecretTwit = new JTextField();
		authAccessTokenSecretTwit.setColumns(10);
		authAccessTokenSecretTwit.setBounds(376, 333, 366, 20);
		contentPane.add(authAccessTokenSecretTwit);

		JTextField authAccessTokenTwit = new JTextField();
		authAccessTokenTwit.setColumns(10);
		authAccessTokenTwit.setBounds(376, 301, 366, 20);
		contentPane.add(authAccessTokenTwit);

		JPasswordField passMail = new JPasswordField();
		passMail.setBounds(377, 458, 251, 20);
		passMail.setColumns(10);
		passMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(passMail);

		JLabel lblEmail = DefaultComponentFactory.getInstance().createLabel("Access token:");
		lblEmail.setBounds(285, 105, 71, 14);
		contentPane.add(lblEmail);

		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("App Secret:");
		lblNewJgoodiesLabel.setBounds(285, 166, 71, 14);
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

		JLabel lblEmail_1 = DefaultComponentFactory.getInstance().createLabel("E-mail: ");
		lblEmail_1.setBounds(326, 424, 41, 17);
		contentPane.add(lblEmail_1);

		JLabel lblPassword_1 = DefaultComponentFactory.getInstance().createLabel("Password:");
		lblPassword_1.setBounds(307, 463, 71, 14);
		contentPane.add(lblPassword_1);

		JButton btnAdicionarFace = new JButton("Adicionar"); // Botão Adiciona tokens - facebook
		btnAdicionarFace.setBounds(396, 194, 89, 23);
		contentPane.add(btnAdicionarFace);


		JButton btnAdicionarMail = new JButton("Adicionar"); // Botão Adiciona tokens - e-mail
		btnAdicionarMail.setBounds(396, 489, 89, 23);
		contentPane.add(btnAdicionarMail);

		JLabel infoLabel = new JLabel("");
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		infoLabel.setBounds(290, 530, 396, 25);
		contentPane.add(infoLabel);

		JButton btnAdicionarTwitter = new JButton("Adicionar");
		btnAdicionarTwitter.setBounds(396, 360, 89, 23);

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
		rdbtnDesactivarContaFacebbok.setBounds(507, 194, 226, 23);
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

		if(twitterOff)
			rdbtnDesactivarContaTwitter.setSelected(true);
		if(facebookOff)
			rdbtnDesactivarContaFacebbok.setSelected(true);
		if(emailOff)
			rdbtnDesactivarContaMail.setSelected(true);

		// Implementa funcionalidade de registo de e-mail e respetiva pass, no ficheiro
		// de texto
		btnAdicionarMail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					if (mailMail.getText().equals("")
							|| passMail.getText().equals("")) {
						System.out.println("1 caixa texto=" + mailMail.getText()); // não esta a captar info da caixa, problema?
						infoLabel.setText("<html><font color='red'>Error: All input fields are mandatory. Insert all e-mail inputs! </font></html>");

					} else {
						String passText = new String(passMail.getPassword());
						editor.AddAcountsForEmail(mailMail.getText(), passText);
						infoLabel.setText("<html><font color='green'>Sucess.</font></html>");
					}

				} catch (Exception e1) {
					e1.printStackTrace();

				}
			}
		});
		
		btnAdicionarFace.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							if(!accessTokenFace.getText().equals(null) 
									&& !AppId.getText().equals(null) 
									&& !AppSecret.getText().equals(null) ){
								editor.AddAcountsForFacebook(accessTokenFace.getText(),AppId.getText(),AppSecret.getText());
								infoLabel.setText("<html><font color='green'>Sucess.</font></html>");
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		btnAdicionarTwitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							if(!authConsumKeyTwit.getText().equals(null) 
									&& !authConsumSecretTwit.getText().equals(null) 
									&& !authAccessTokenTwit.getText().equals(null) 
									&& !authAccessTokenSecretTwit.getText().equals(null)){
								editor.AddAcountsForTwitter(authConsumKeyTwit.getText(),authConsumSecretTwit.getText(),authAccessTokenTwit.getText(),authAccessTokenSecretTwit.getText());
								infoLabel.setText("<html><font color='green'>Sucess.</font></html>");
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		contentPane.add(btnAdicionarTwitter);
		
		JLabel lblAppId = new JLabel("App Id:");
		lblAppId.setBounds(295, 135, 46, 14);
		contentPane.add(lblAppId);
		
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//new MainWindow();
				if(mainWindow != null) {
					if(rdbtnDesactivarContaFacebbok.isSelected()) {
						mainWindow.setFacebookOff(true);
					}
					if(rdbtnDesactivarContaTwitter.isSelected()) {
						mainWindow.setTwitterOff(true);
					}
					if(rdbtnDesactivarContaMail.isSelected()) {
						mainWindow.setEmailOff(true);
					}
				}

				Thread thread = new Thread() {
					@Override
					public void run() {
						mainWindow.refreshAllTable();
					}
				};

				thread.start();
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
