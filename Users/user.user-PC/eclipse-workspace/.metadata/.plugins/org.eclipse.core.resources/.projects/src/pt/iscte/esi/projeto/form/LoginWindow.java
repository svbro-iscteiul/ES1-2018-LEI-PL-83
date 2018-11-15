package pt.iscte.esi.projeto.form;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.RenderingHints.Key;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import pt.iscte.esi.projeto.utils.XMLFileEditor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Window UI for Application Login
 * @author jose.f.santos
 *
 */
public class LoginWindow {

	private JFrame frame;
	private JTextField txtUtilizador;
	private JPasswordField txtPassw;


	/**
	 * Class constructor.
	 */
	public LoginWindow() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Create and initialize Login Window.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		frame.setTitle("Bom dia Academia - Login Window");
		
		JLabel lblNewLabel = new JLabel("");
		
		ImageIcon imgIcon = new ImageIcon(LoginWindow.class.getResource("/pt/iscte/esi/projeto/form/images/logo_ISCTE-IUL.png"));
		Image img = imgIcon.getImage();
		img = img.getScaledInstance(200, 500, java.awt.Image.SCALE_SMOOTH);
		imgIcon = new ImageIcon(img);
		//frame.getContentPane().setLayout(new GridLayout(4, 5, 0, 0));
		
		lblNewLabel.setIcon(new ImageIcon(LoginWindow.class.getResource("/pt/iscte/esi/projeto/form/images/logo_ISCTE-IUL.png")));
		frame.getContentPane().add(lblNewLabel, BorderLayout.WEST);
		
		//Here starts LogIn Panel
		
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
		
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(48, 330, 290, 25);
		logInPanel.add(lblNewLabel_5);
		
		JButton btnLogIn = new JButton("Log In");
		
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logInPanel.add(btnLogIn);
		btnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							if(txtUtilizador.getText().equals("") || txtPassw.getPassword().length==0)
								lblNewLabel_5.setText("<html><font color='red'>Error: All input fields are mandatory.</font></html>");
							else
							{
								XMLFileEditor i = new XMLFileEditor();
								char[] pass = txtPassw.getPassword();
								String password = new String(pass);
								String s=i.ReadFile(txtUtilizador.getText(),"",password);
								if(s.equals("Error"))
									lblNewLabel_5.setText("<html><font color='red'>Error</font></html>");
								else if(s.equals("Username Not Found")) 
									lblNewLabel_5.setText("<html><font color='red'>Error:That username does not exit.</font></html>");
								else if(s.equals("Password Incorrect"))
									lblNewLabel_5.setText("<html><font color='red'>Error: Password Incorrect.</font></html>");
								else 
								{
									SwingUtilities.invokeLater(new Runnable() {
										public void run() {
											try {
												new MainWindow();
												frame.dispose();
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									});
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		lblUtilizador.setBounds(200,200,100,30);
		lblPassword.setBounds(200,250,100,30);
		
		txtUtilizador.setBounds(300,200,150,30);
		txtPassw.setBounds(300,250,150,30);
		
		btnLogIn.setBounds(350,325,100,40);
		
		JLabel lblNewLabel_1 = new JLabel("<html><font color='blue'>Registar</font></html>");
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(350, 378, 100, 30);
		logInPanel.add(lblNewLabel_1);
		
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							new RegisterWindow();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}
}
