package pt.iscte.esi.projeto.form;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import pt.iscte.esi.projeto.form.models.TwitterAPI;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

/**
 * Window UI for message details
 * 
 * @author Elsa Teixeira, José Santos, Sérgio Ribeiro - LEI ISCTE
 *
 */
public class MessageDetailWindow {

	private JFrame frame;
	private String channel;
	private String date;
	private String origin;
	private String text;


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

	public MessageDetailWindow(String date, String channel, String origin, String text) {
		this.date=date;
		this.channel=channel;
		this.origin=origin;
		this.text=text;
		initialize();

		frame.setVisible(true);
	}


	public MessageDetailWindow() {
		// TODO Auto-generated constructor stub
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

		ImageIcon image = new ImageIcon(
				MessageDetailWindow.class.getResource("/pt/iscte/esi/projeto/form/images/Symbol_ISCTE.png"));


		// Adding photo to the frame "MainFrameMessageView"
		JLabel fotoDois = new JLabel("");
		fotoDois.setBounds(541, -40, 282, 276);
		Image img = image.getImage().getScaledInstance(fotoDois.getWidth(), fotoDois.getHeight(), Image.SCALE_SMOOTH);

		fotoDois.setIcon(new ImageIcon(img));

		frame.getContentPane().add(fotoDois);



		JButton btOk = new JButton("OK");
		btOk.setBounds(578, 514, 89, 23);
		btOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MainWindow();
				frame.dispose();

			}
		});
		btOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(btOk);


		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(655, 168, 17, 326);
		frame.getContentPane().add(scrollBar);

		//only title window

		JLabel lblDetalhesDaMensagem = DefaultComponentFactory.getInstance().createTitle("Detalhes da mensagem");
		lblDetalhesDaMensagem.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDetalhesDaMensagem.setBounds(110, 50, 321, 14);
		frame.getContentPane().add(lblDetalhesDaMensagem);


		//here we can see the date of the message

		JLabel lblData = DefaultComponentFactory.getInstance().createLabel("Data:");
		lblData.setBounds(368, 116, 43, 14);
		frame.getContentPane().add(lblData);

		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(406, 116, 155, 20);
		textPane_2.setText(date);
		textPane_2.setEditable(false);
		frame.getContentPane().add(textPane_2);

		
		//here we can see the channel of the message

		JLabel lblCanal = DefaultComponentFactory.getInstance().createLabel("Canal:");
		lblCanal.setBounds(110, 98, 64, 14);
		frame.getContentPane().add(lblCanal);

		JTextPane textPane = new JTextPane();
		textPane.setText(channel);
		textPane.setEditable(false);
		textPane.setBounds(174, 98, 155, 20);
		frame.getContentPane().add(textPane);



		//here we can see the origin of the message

		JLabel lblOrigem = DefaultComponentFactory.getInstance().createLabel("Origem:");
		lblOrigem.setBounds(110, 129, 64, 14);
		frame.getContentPane().add(lblOrigem);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(174, 129, 155, 20);
		textPane_1.setText(origin);
		textPane_1.setEditable(false);
		frame.getContentPane().add(textPane_1);



		//here we can watch the complete message

		JTextArea textArea = new JTextArea();
		textArea.setText(text);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setForeground(new Color(0, 0, 128));
		textArea.setBounds(115, 168, 557, 326);


		frame.getContentPane().add(textArea);



	}
}
