package src.pt.iscte.esi.projeto.form;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 945, 581);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(28, 92, 863, 386);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(260, 39, 522, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Procurar");
		btnNewButton.setBounds(794, 38, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnWindow = new JMenu("Window");
		mnWindow.setHorizontalAlignment(SwingConstants.RIGHT);
		mnWindow.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnWindow);
		
		JMenuItem mntmOptions = new JMenuItem("Options");
		mntmOptions.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmOptions.setHorizontalAlignment(SwingConstants.RIGHT);
		mnWindow.add(mntmOptions);
	}
}
