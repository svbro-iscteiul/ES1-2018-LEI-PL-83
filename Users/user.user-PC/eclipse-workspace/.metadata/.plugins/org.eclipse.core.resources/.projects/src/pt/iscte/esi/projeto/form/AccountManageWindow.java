package pt.iscte.esi.projeto.form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import pt.iscte.esi.projeto.utils.XMLFileEditor;

import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;
import javax.swing.JTextPane;

/**
 * Window UI for Account Management
 * 
 * @author Elsa Teixeira, José Santos, Sérgio Ribeiro LEI ISCTE
 *
 */
public class AccountManageWindow extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
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
		ImageIcon image = new ImageIcon(
				AccountManageWindow.class.getResource("/pt/iscte/esi/projeto/form/images/LogosContas.png"));
		Image img = image.getImage().getScaledInstance(newLabel.getWidth(), newLabel.getHeight(), Image.SCALE_SMOOTH);
		newLabel.setIcon(new ImageIcon(img));
		getContentPane().add(newLabel);
		contentPane.add(newLabel);

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

		JTextField textField = new JTextField();
		textField.setBounds(377, 140, 121, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(507, 140, 236, 20);
		contentPane.add(textField_2);

		JTextField textField_4 = new JTextField();
		textField_4.setBounds(377, 427, 366, 20);
		contentPane.add(textField_4);

		JTextField textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(377, 105, 366, 20);
		contentPane.add(textField_6);

		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(377, 270, 366, 20);
		contentPane.add(textField_1);

		JTextField textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(376, 333, 366, 20);
		contentPane.add(textField_3);

		JTextField textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(376, 301, 366, 20);
		contentPane.add(textField_9);

		JTextField textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(376, 240, 366, 20);
		contentPane.add(textField_10);

		JTextField textField_8 = new JTextField();
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



		// Inacabado;

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
		button.setBounds(396, 360, 89, 23);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							if(!textField_1.getText().equals(null) && !textField_10.getText().equals(null) && !textField_9.getText().equals(null) && !textField_3.getText().equals(null)){
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

		JButton button_1 = new JButton("Adicionar");
		button_1.setBounds(396, 489, 89, 23);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							if(!textField_4.getText().equals(null) && !textField_8.getText().equals(null)){
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
	}

	public static void main(String[] args) {

		AccountManageWindow m= new AccountManageWindow();
		m.initialize();
	}
}
