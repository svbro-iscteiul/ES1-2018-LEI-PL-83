package pt.iscte.esi.projeto.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Color;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class AccountManageWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountManageWindow frame = new AccountManageWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AccountManageWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 605);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		/**
		 * Add Symbol accounts image to the window
		 */
		//Adding the image icons
		JLabel newLabel = new JLabel("");
		newLabel.setBounds(87, 105, 119, 421);
		ImageIcon image = new ImageIcon(AccountManageWindow.class.getResource("/pt/iscte/esi/projeto/form/images/LogosContas.png"));
		Image img = image.getImage().getScaledInstance(newLabel.getWidth(), newLabel.getHeight(), Image.SCALE_SMOOTH);
		newLabel .setIcon(new ImageIcon(img));
		getContentPane().add(newLabel );
		contentPane.add(newLabel);
		
		JLabel label = DefaultComponentFactory.getInstance().createTitle("");
		label.setBounds(318, 84, 88, 14);
		contentPane.add(label);
		
		
		JLabel lblAdicionarContas = DefaultComponentFactory.getInstance().createTitle("ADICIONAR CONTAS");
		lblAdicionarContas.setForeground(new Color(0, 0, 128));
		lblAdicionarContas.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAdicionarContas.setBounds(318, 28, 195, 23);
		contentPane.add(lblAdicionarContas);
		
		
		/**
		 * Adding the different "frame components" to the window
		 */
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Desactivar mensagens deste canal");
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		rdbtnNewRadioButton.setBounds(491, 171, 241, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnDesactivarMensagensDeste = new JRadioButton("Desactivar mensagens deste canal");
		rdbtnDesactivarMensagensDeste.setBackground(Color.WHITE);
		rdbtnDesactivarMensagensDeste.setBounds(491, 326, 225, 23);
		contentPane.add(rdbtnDesactivarMensagensDeste);
		
		textField = new JTextField();
		textField.setBounds(377, 140, 121, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(507, 140, 236, 20);
		contentPane.add(textField_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(377, 427, 366, 20);
		contentPane.add(textField_4);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(377, 105, 366, 20);
		contentPane.add(textField_6);
		
		JLabel lblEmail = DefaultComponentFactory.getInstance().createLabel("Access token:");
		lblEmail.setBounds(285, 105, 119, 14);
		contentPane.add(lblEmail);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Extended access token:");
		lblNewJgoodiesLabel.setBounds(234, 143, 144, 14);
		contentPane.add(lblNewJgoodiesLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(377, 264, 366, 20);
		contentPane.add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(376, 295, 366, 20);
		contentPane.add(textField_3);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(377, 458, 251, 20);
		contentPane.add(textField_8);
		
		JLabel lblNewJgoodiesLabel_2 = DefaultComponentFactory.getInstance().createLabel("Auth. access token:");
		lblNewJgoodiesLabel_2.setBounds(257, 267, 124, 14);
		contentPane.add(lblNewJgoodiesLabel_2);
		
		JLabel lblUsername = DefaultComponentFactory.getInstance().createLabel("Auth access \r\ntoken secret:");
		lblUsername.setBounds(216, 298, 172, 14);
		contentPane.add(lblUsername);
		
		JLabel lblEmail_1 = DefaultComponentFactory.getInstance().createLabel("E-mail: ");
		lblEmail_1.setBounds(299, 430, 68, 14);
		contentPane.add(lblEmail_1);
		
		JLabel lblPassword_1 = DefaultComponentFactory.getInstance().createLabel("Password:");
		lblPassword_1.setBounds(285, 461, 82, 14);
		contentPane.add(lblPassword_1);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(396, 171, 89, 23);
		contentPane.add(btnAdicionar);
		
		JButton button = new JButton("Adicionar");
		button.setBounds(396, 326, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Adicionar");
		button_1.setBounds(396, 489, 89, 23);
		contentPane.add(button_1);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(690, 527, 69, 29);
		contentPane.add(btnOk);
		
		JLabel lblNewLabel = new JLabel("Adiciona os c\u00F3digos de acesso relativos a cada conta e recebe informa\u00E7\u00E3o acad\u00E9mica proveniente de cada canal.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setBounds(158, 49, 553, 14);
		contentPane.add(lblNewLabel);
		
		JRadioButton radioButton = new JRadioButton("Desactivar mensagens deste canal");
		radioButton.setBackground(Color.WHITE);
		radioButton.setBounds(491, 485, 225, 23);
		contentPane.add(radioButton);
		
		

	}
}
