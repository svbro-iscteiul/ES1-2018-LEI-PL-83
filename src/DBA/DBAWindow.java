package src.DBA;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import src.pt.iscte.esi.projeto.utils.XMLFileEditor;

public class DBAWindow {

	private JFrame frmDbaEditor;

	/**
	 * Create the application.
	 */
	public DBAWindow() {
		initialize();
		frmDbaEditor.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDbaEditor = new JFrame();
		frmDbaEditor.setTitle("DBA - Editor");
		frmDbaEditor.setResizable(false);
		frmDbaEditor.setBounds(300, 250, 961, 687);
		frmDbaEditor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDbaEditor.getContentPane().setLayout(null);
		
		JButton btnSave = new JButton("Save");
		
		btnSave.setBounds(834, 602, 97, 25);
		frmDbaEditor.getContentPane().add(btnSave);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(UIManager.getColor("info"));
		textArea.setFont(new Font("Monospaced", Font.BOLD, 15));
		textArea.setBounds(2, 2, 914, 571);
				
		JScrollPane panel = new JScrollPane(textArea);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(12, 13, 919, 576);
		
		frmDbaEditor.getContentPane().add(panel);
		
		XMLFileEditor xmlEditor = new XMLFileEditor();

		xmlEditor.LoadXMlContent(textArea);
		
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int result = JOptionPane.showConfirmDialog(frmDbaEditor, "Your about to overwrite config.xml file.\nAre you sure?", "Confirmation",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(result == 1)
					xmlEditor.SaveXMLContent(textArea);
				else
					return;
			}
		});
		
		
	}

}
