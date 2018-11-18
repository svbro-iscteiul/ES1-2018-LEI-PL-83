
package pt.iscte.esi.projeto.form;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import DBA.DBAWindow;
import pt.iscte.esi.projeto.form.models.GmailAPI;
import pt.iscte.esi.projeto.form.models.Message;
import pt.iscte.esi.projeto.form.models.TwitterAPI;
import pt.iscte.esi.projeto.utils.MainMsgList;

/**
 * Window UI with list of tweets, emails and facebook posts
 * 
 * @author Elsa Teixeira, José Santos, Sérgio Ribeiro - LEI ISCTE
 *
 */
public class MainWindow {

	private JFrame frame;
	private JTable table;
	private JLabel image2;
	private JTextField txtPesquisaMensagensPor;
	private MainMsgList msgList;
	private DefaultTableModel defaultTableModel;
	private ArrayList<Message> tweets = new ArrayList<Message>();
	private ArrayList<Message> emails = new ArrayList<Message>();
	private Set<String> TwitterSenders = new HashSet<String>();
	private Set<String> EmailSenders = new HashSet<String>();
	private String ChoosenOrigin="";


	public static void main(String[] args) {
		//MessageDetailWindow window = new MessageDetailWindow(null, null, null, null);
		MainWindow window = new MainWindow();

	}


	/**
	 * Class constructor.
	 */
	public MainWindow() {
		initialize();
		frame.setVisible(true);
	}
	
	
	/**
	 * gets the tweets and adds them to the specific list
	 */
	private void getTweets() {
		TwitterAPI t = new TwitterAPI();
		tweets = t.getTweets();
	}

	/**
	 * gets the emails and adds them to the specific list
	 */
	private void getEmails() {
		GmailAPI g = new GmailAPI();
		try {
			emails = g.getMails();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Updates new information in academic news frame
	 */
	private void refreshTable() {
		Object[][] matrix = msgList.getMsgMatrix();
		if (defaultTableModel.getRowCount() == 0 && matrix == null) {
			matrix = new Object[100][100];
			for (int i = 0; i < 21; i++) {
				for (int j = 0; j < 21; j++) {
					matrix[i][j] = null;
					defaultTableModel.setDataVector(matrix, msgList.getHeaders());
				}
			}
		} else {
			defaultTableModel.setDataVector(msgList.getMsgMatrix(), msgList.getHeaders());
		}
	}
	
	/**
	 * Under work!! used to select an element in the list.
	 * 
	 * @param component
	 * @param popup
	 */
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	
	/**
	 * Create and initialize Window.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 800, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 155, 701, 311);

		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBackground(new Color(255, 255, 204));
		table.setBorder(new LineBorder(new Color(0, 0, 139), 1, true));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		msgList = new MainMsgList();
		msgList.setHeaders(new String[] { "Data", "Canal", "Origem", "Mensagem" });
		String[][] temp = new String[101][4];
		msgList.setMsgMatrix(temp);

			getTweets();
			for (Message m : tweets) {
				msgList.addMessage(m);
				TwitterSenders.add(m.getSender());
			}

			getEmails();
			for (Message m : emails) {
				msgList.addMessage(m);
				EmailSenders.add(m.getSender());
			}
	

		// for(Message f : facebook) fazer depois get messages from face

		defaultTableModel = new DefaultTableModel(msgList.getMsgMatrix(), msgList.getHeaders()) {
			/**
			 * Serializa a informação dada na linha anterior
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

		};

		
		//refreshTable();

		table.setModel(defaultTableModel);
		table.getColumnModel().getColumn(3).setPreferredWidth(402);
		scrollPane.setViewportView(table);
		frame.getContentPane().add(scrollPane);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
					if (arg0.getButton() == MouseEvent.BUTTON1 || arg0.getButton() == MouseEvent.BUTTON2) {
						int row = table.getSelectedRow();
	
						if(table.getModel().getValueAt(row, 0)!=null) {
						new MessageDetailWindow(table.getModel().getValueAt(row, 0).toString(),table.getModel().getValueAt(row, 1).toString(),
								table.getModel().getValueAt(row, 2).toString(),table.getModel().getValueAt(row, 3).toString());
	
						frame.dispose();
	
						if(table.getModel().getValueAt(row, 0)!="") {
							new MessageDetailWindow(table.getModel().getValueAt(row, 0).toString(),table.getModel().getValueAt(row, 1).toString(),
									table.getModel().getValueAt(row, 2).toString(),table.getModel().getValueAt(row, 3).toString());
							frame.dispose();
						}
					}
				}
			}
		});

		// Adds the LogOut option to the window and link it to Login Window
		JLabel lblLogOut = new JLabel("<html><font color='white'>Logout</font></html>");
		lblLogOut.setBounds(709, 98, 61, 14);
		lblLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new LoginWindow();
				frame.dispose();
			}
		});

		lblLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLogOut.setForeground(new Color(240, 255, 255));
		frame.getContentPane().add(lblLogOut);

		/*
		 * Adds the "add accounts" option to the window and link it to
		 * "Account Manage Window".
		 */
		JLabel lblAdd_tokens = new JLabel("<html><font color='white'>Adicionar Contas | </font></html>");
		lblAdd_tokens.setBounds(600, 98, 110, 14);
		lblAdd_tokens.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg1) {
				new AccountManageWindow();
				frame.dispose();
			}
		});
		frame.getContentPane().setLayout(null);
		lblAdd_tokens.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAdd_tokens.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAdd_tokens.setForeground(new Color(240, 255, 255));
		frame.getContentPane().add(lblAdd_tokens);

		/*
		 * Adds filter boxes to the window
		 */
		Choice choice = new Choice();
		choice.setBounds(35, 129, 95, 20);
		choice.add("filtrar data");
		frame.getContentPane().add(choice);

		Choice choice_1 = new Choice();
		choice_1.setBounds(132, 129, 92, 20);
		choice_1.add("All Channels");
		choice_1.add("Twitter");
		choice_1.add("Facebook");
		choice_1.add("E-mail");
		JButton FilterChannel = new JButton("Filtrar Canal");
		FilterChannel.setBounds(324,129,123,20);
		
		frame.getContentPane().add(choice_1);

		Choice choice_2 = new Choice();
		choice_2.setBounds(226, 129, 92, 20);
		choice_2.add("filtrar origem");
		choice_2.setVisible(false);
		frame.getContentPane().add(choice_2);
		JButton FilterSender = new JButton("Filtrar Origem");
		FilterSender.setEnabled(false);
		FilterSender.setBounds(454, 129, 123, 20);
		FilterSender.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				msgList.setHeaders(new String[] { "Data", "Canal", "Origem", "Mensagem" });
				String[][] temp = new String[101][4];
				msgList.setMsgMatrix(temp);
				if(ChoosenOrigin=="Twitter"){
					for (Message m : tweets) 
						if(m.getSender().equals(choice_2.getItem(choice_2.getSelectedIndex())))
							msgList.addMessage(m);
				}
				else if(choice_1.getItem(choice_1.getSelectedIndex()).equals("E-mail")){
					for (Message m : emails)
						if(m.getSender().equals(choice_2.getItem(choice_2.getSelectedIndex())))
							msgList.addMessage(m);
				
				}
				

					defaultTableModel = new DefaultTableModel(msgList.getMsgMatrix(), msgList.getHeaders()) {
						/**
						 * Serializa a informação dada na linha anterior
						 */
						private static final long serialVersionUID = 1L;
						Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}

					};
					
					table.setModel(defaultTableModel);
			}});

		frame.getContentPane().add(FilterSender);

		FilterChannel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				msgList.setHeaders(new String[] { "Data", "Canal", "Origem", "Mensagem" });
				String[][] temp = new String[101][4];
				msgList.setMsgMatrix(temp);
				if(choice_1.getItem(choice_1.getSelectedIndex()).equals("Twitter")){
					for (Message m : tweets)
						msgList.addMessage(m);
					
					ChoosenOrigin="Twitter";
					choice_2.setVisible(true);
					choice_2.removeAll();
					for(String s:TwitterSenders)
						choice_2.add(s);
					FilterSender.setEnabled(true);
					
				}
				else if(choice_1.getItem(choice_1.getSelectedIndex()).equals("E-mail")){
					for (Message m : emails)
						msgList.addMessage(m);
					
					ChoosenOrigin="E-mail";
					choice_2.setVisible(true);
					choice_2.removeAll();
					for(String s:EmailSenders)
						choice_2.add(s);
					FilterSender.setEnabled(true);
				}
				else{
					ChoosenOrigin="All";
					for (Message m : emails)
						msgList.addMessage(m);
					for (Message m : tweets)
						msgList.addMessage(m);
					choice_2.setVisible(false);
					FilterSender.setEnabled(false);
				}

					defaultTableModel = new DefaultTableModel(msgList.getMsgMatrix(), msgList.getHeaders()) {
						/**
						 * Serializa a informação dada na linha anterior
						 */
						private static final long serialVersionUID = 1L;
						Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}

					};
					
					table.setModel(defaultTableModel);
			}});

		frame.getContentPane().add(FilterChannel);
		/*
		 * Adds image to the main window
		 */

		JLabel foto = new JLabel("");
		foto.setBounds(0, 26, 784, 99);

		ImageIcon image = new ImageIcon(
				MainWindow.class.getResource("/pt/iscte/esi/projeto/form/images/ImageMainWindow.png"));
		Image img = image.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_SMOOTH);

		foto.setIcon(new ImageIcon(img));
		frame.getContentPane().add(foto);

		/*
		 * Adds Button "Filtrar" to the window
		 */
	
		txtPesquisaMensagensPor = new JTextField();
		txtPesquisaMensagensPor.setBounds(390, 502, 234, 20);
		txtPesquisaMensagensPor.setForeground(new Color(112, 128, 144));
		txtPesquisaMensagensPor.setText("Filtrar mensagens por palavra-chave");
		frame.getContentPane().add(txtPesquisaMensagensPor);
		txtPesquisaMensagensPor.setColumns(10);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 784, 26);
		frame.getContentPane().add(menuBar);

		JMenu mnTools = new JMenu("Tools");
		mnTools.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		menuBar.add(mnTools);

		JMenuItem mntmDbaEditor = new JMenuItem("DBA - Editor");
		mntmDbaEditor.setSelected(true);
		mntmDbaEditor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							new DBAWindow();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		mntmDbaEditor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnTools.add(mntmDbaEditor);

		/*
		 * Creates Refresh button and his action
		 */
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				refreshTable();
			}
		});
		btnRefresh.setBounds(44, 501, 93, 23);
		frame.getContentPane().add(btnRefresh);

	}
}
