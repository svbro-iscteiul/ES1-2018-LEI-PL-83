package src.pt.iscte.esi.projeto.form;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

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
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logInPanel.add(btnLogIn);
		
		lblUtilizador.setBounds(200,200,100,30);
		lblPassword.setBounds(200,250,100,30);
		
		txtUtilizador.setBounds(300,200,150,30);
		txtPassw.setBounds(300,250,150,30);
		
		btnLogIn.setBounds(350,325,100,40);
		
		JLabel lblNewLabel_1 = new JLabel("<html><font color='blue'>Sign In</font></html>");
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(350, 378, 100, 30);
		logInPanel.add(lblNewLabel_1);
		
		lblNewLabel_1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							new SigninWindow();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}
}
