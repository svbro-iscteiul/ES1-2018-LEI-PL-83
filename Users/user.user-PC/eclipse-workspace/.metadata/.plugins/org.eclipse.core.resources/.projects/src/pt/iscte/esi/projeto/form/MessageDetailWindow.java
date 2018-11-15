package pt.iscte.esi.projeto.form;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import java.awt.Color;

/**
 * Window UI for message details
 * @author jose.f.santos
 *
 */
public class MessageDetailWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessageDetailWindow window = new MessageDetailWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MessageDetailWindow() {
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
		frame.getContentPane().setLayout(null);
		
		ImageIcon image = new ImageIcon(MessageDetailWindow.class.getResource("/pt/iscte/esi/projeto/form/images/Symbol_ISCTE.png"));
		
		
		//Adding photo to the frame "MainFrameMessageView"
		JLabel fotoDois = new JLabel("");
		fotoDois.setBounds(541, -40, 282, 276);
		Image img = image.getImage().getScaledInstance(fotoDois.getWidth(), fotoDois.getHeight(), Image.SCALE_SMOOTH);
		
		fotoDois.setIcon(new ImageIcon(img));
		
		
		frame.getContentPane().add(fotoDois);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(174, 98, 155, 20);
		frame.getContentPane().add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(174, 129, 155, 20);
		frame.getContentPane().add(textPane_1);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(578, 514, 89, 23);
		frame.getContentPane().add(btnOk);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(655, 168, 17, 326);
		frame.getContentPane().add(scrollBar);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setForeground(new Color(0, 0, 128));
		textArea.setBounds(115, 168, 557, 326);
		frame.getContentPane().add(textArea);
		
		JLabel lblCanal = DefaultComponentFactory.getInstance().createLabel("Canal:");
		lblCanal.setBounds(110, 98, 64, 14);
		frame.getContentPane().add(lblCanal);
		
		JLabel lblOrigem = DefaultComponentFactory.getInstance().createLabel("Origem:");
		lblOrigem.setBounds(110, 129, 64, 14);
		frame.getContentPane().add(lblOrigem);
		
		JLabel lblDetalhesDaMensagem = DefaultComponentFactory.getInstance().createTitle("Detalhes da mensagem");
		lblDetalhesDaMensagem.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDetalhesDaMensagem.setBounds(110, 50, 321, 14);
		frame.getContentPane().add(lblDetalhesDaMensagem);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(406, 116, 155, 20);
		frame.getContentPane().add(textPane_2);
		
		JLabel lblData = DefaultComponentFactory.getInstance().createLabel("Data:");
		lblData.setBounds(368, 116, 43, 14);
		frame.getContentPane().add(lblData);
	}
}
