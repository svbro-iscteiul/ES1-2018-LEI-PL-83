package src.pt.iscte.esi.projeto.form;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;

import src.pt.iscte.esi.projeto.utils.XMLFileEditor;

public class RegisterWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;


	/**
	 * Class constructor.
	 */
	public RegisterWindow() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Create and initialize Register Window.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(350, 350, 804, 536);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.setTitle("Bom dia Academia - Signin Window");
		
		frame.getContentPane().setLayout(new BorderLayout());
		
		JLabel lblNewLabel = new JLabel("");
		
		ImageIcon imgIcon = new ImageIcon(RegisterWindow.class.getResource("/src/pt/iscte/esi/projeto/form/images/logo_ISCTE-IUL.png"));;
		Image img = imgIcon.getImage();
		img = img.getScaledInstance(200, 500, java.awt.Image.SCALE_SMOOTH);
		imgIcon = new ImageIcon(img);
		//frame.getContentPane().setLayout(new GridLayout(4, 5, 0, 0));
		
		lblNewLabel.setIcon(new ImageIcon(RegisterWindow.class.getResource("/src/pt/iscte/esi/projeto/form/images/logo_ISCTE-IUL.png")));
		frame.getContentPane().add(lblNewLabel, BorderLayout.WEST);
		
		JPanel logInPanel = new JPanel();
		logInPanel.setLayout(null);
		frame.getContentPane().add(logInPanel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Username: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(99, 126, 146, 16);
		logInPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("E-mail: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(99, 169, 146, 16);
		logInPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password: ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(99, 240, 146, 16);
		logInPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Confirm Password:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(99, 292, 146, 16);
		logInPanel.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(257, 123, 180, 22);
		logInPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setBounds(257, 166, 180, 22);
		logInPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JPasswordField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setBounds(257, 237, 180, 22);
		logInPanel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JPasswordField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(257, 289, 180, 22);
		logInPanel.add(textField_3);
		
		JButton btnSignIn = new JButton("Registar");
		btnSignIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSignIn.setBounds(398, 371, 97, 25);
		logInPanel.add(btnSignIn);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(99, 330, 396, 25);
		logInPanel.add(lblNewLabel_5);
		
		btnSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textField.getText().equals("") 
						|| textField_1.getText().equals("")
						|| textField_2.getText().equals("")
						|| textField_3.getText().equals("")) {
					lblNewLabel_5.setText("<html><font color='red'>Error: All input fields are mandatory.</font></html>");
				}
				else if(!textField_2.getText().equals(textField_3.getText())) {
					lblNewLabel_5.setText("<html><font color='red'>Error: Passwords don't match.</font></html>");
				}
				else {
					XMLFileEditor i = new XMLFileEditor();
					String s=i.SignIn(textField.getText(),textField_1.getText(),textField_2.getText());
					if(s.equals("Error"))
						lblNewLabel_5.setText("<html><font color='red'>Error</font></html>");
					else if(s.equals("Username taken")) 
						lblNewLabel_5.setText("<html><font color='red'>Error: username already taken.</font></html>");
					else if(s.equals("Email taken"))
						lblNewLabel_5.setText("<html><font color='red'>Error: email already taken.</font></html>");
					else {
						lblNewLabel_5.setText("<html><font color='green'>You got registered.</font></html>");
					}
				}
				
			}
		});
		
		
	}
}
