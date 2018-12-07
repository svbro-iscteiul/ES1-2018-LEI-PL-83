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
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import pt.iscte.esi.projeto.form.models.FacebookAPI;
import pt.iscte.esi.projeto.form.models.GmailAPI;
import pt.iscte.esi.projeto.form.models.TwitterAPI;
import twitter4j.TwitterException;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;

/**
 * Window UI for message details
 *
 * @author José F Santos, Elsa Teixeira, Sérgio Vaz
 */
public class MessageDetailWindow {

	private JFrame frame;
	private String channel;
	private String date;
	private String origin;
	private String text;


	/**
	 * Main
	 * @param args inputs
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MessageDetailWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Constructor
	 * 
	 * @param date String
	 * @param channel String
	 * @param origin String
	 * @param text String
	 */
	public MessageDetailWindow(String date, String channel, String origin, String text) {
		this.date=date;
		this.channel=channel;
		this.origin=origin;
		this.text=text;
		initialize();

		frame.setVisible(true);
	}


	/**
	 * Constructor
	 */
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
		frame.setBounds(200, 150, 800, 605);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		ImageIcon image = new ImageIcon(
				MessageDetailWindow.class.getResource("/pt/iscte/esi/projeto/form/images/Symbol_ISCTE.png"));


		// Adding photo to the frame "MainFrameMessageView"
		JLabel fotoDois = new JLabel("");
		fotoDois.setBounds(608, -23, 194, 205);
		Image img = image.getImage().getScaledInstance(fotoDois.getWidth(), fotoDois.getHeight(), Image.SCALE_SMOOTH);

		fotoDois.setIcon(new ImageIcon(img));

		frame.getContentPane().add(fotoDois);



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
		textArea.setBounds(115, 168, 557, 240);


		JScrollPane panel = new JScrollPane(textArea);
		panel.setBorder(new CompoundBorder());
		panel.setBounds(110, 168, 562, 179);
		frame.getContentPane().add(panel);

		//here we can watch the complete message

		JTextArea textAreaResponse = new JTextArea();
		textAreaResponse.setEditable(true);
		textAreaResponse.setLineWrap(true);
		textAreaResponse.setForeground(new Color(0, 0, 128));
		textAreaResponse.setBounds(115, 420, 557, 50);
		frame.getContentPane().add(textAreaResponse);
		
		JLabel ErrorMessage = new JLabel("");
		ErrorMessage.setBounds(120, 481, 311, 14);
		frame.getContentPane().add(ErrorMessage);

		JScrollPane panel2 = new JScrollPane(textAreaResponse);
		panel2.setBorder(new CompoundBorder());
		panel2.setBounds(110, 358, 562, 112);
		frame.getContentPane().add(panel2);
		JButton btOk = new JButton("Responder");
		btOk.setBounds(578, 514, 100, 23);
		btOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!textAreaResponse.getText().equals("")) {
					if(channel.equals("Twitter") || channel.equals("twitter"))
					{
						try {
							new TwitterAPI().ReplyToTweet(text, textAreaResponse.getText());
							ErrorMessage.setText("<html><font color='green'>Sucesso</font></html>");
						} catch (TwitterException e) {
							ErrorMessage.setText("<html><font color='red'>Falha ao responder, tente mais tarde</font></html>");
							e.printStackTrace();
						}
					}
					else if (channel.equals("Facebook") || channel.equals("facebook"))
					{
						try {
							new FacebookAPI().postMessage(textAreaResponse.getText());
							ErrorMessage.setText("<html><font color='green'>Sucesso</font></html>");
						} catch (Exception e) {
							ErrorMessage.setText("<html><font color='red'>Falha ao responder, tente mais tarde</font></html>");
							e.printStackTrace();
						}
					}
					else if(channel.equals("Email") || channel.equals("email")) {
						try {
							
							new GmailAPI().SendEmail("Response to Email",textAreaResponse.getText());
							ErrorMessage.setText("<html><font color='green'>Sucesso</font></html>");
						} catch (Exception e) {
							ErrorMessage.setText("<html><font color='red'>Falha ao responder, tente mais tarde</font></html>");
							e.printStackTrace();
						}
					}
						
				}
				else
					ErrorMessage.setText("<html><font color='red'>Texto da mensagem por preencher</font></html>");


			}
		});
		btOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(btOk);
		
		


	}
}
