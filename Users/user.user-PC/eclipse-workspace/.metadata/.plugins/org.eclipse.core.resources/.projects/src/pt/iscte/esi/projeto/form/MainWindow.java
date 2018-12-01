
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
import java.util.Collections;
import java.util.Comparator;
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
import pt.iscte.esi.projeto.form.models.GmailThread;
import pt.iscte.esi.projeto.form.models.Message;
import pt.iscte.esi.projeto.form.models.MessageComparator;
import pt.iscte.esi.projeto.form.models.StringComparator;
import pt.iscte.esi.projeto.form.models.TwitterAPI;
import pt.iscte.esi.projeto.form.models.TwitterThread;
import pt.iscte.esi.projeto.utils.MainMsgList;

/**
 * Window UI with list of tweets, emails and facebook posts
 *
 */
public class MainWindow {

	private boolean twitterOff = true;
	private boolean facebookOff = true;
	private boolean emailOff = true;
	
	
	private MainWindow mainWindow;
	private JFrame frame;
	private JTable table;
	private JLabel image2;
	private MainMsgList msgList;
	private DefaultTableModel defaultTableModel;
	private ArrayList<Message> tweets = new ArrayList<Message>();
	private ArrayList<Message> emails = new ArrayList<Message>();
	private ArrayList<Message> AllMessages = new ArrayList<Message>();
	private ArrayList<Message> ShownMessages= new ArrayList<Message>();
	private Set<String> TwitterSenders = new HashSet<String>();
	private Set<String> EmailSenders = new HashSet<String>();
	private ArrayList<String> Dates = new ArrayList<String>();
	private String ChoosenChannel="All";
	private String ChoosenSender="All";
	private Set<String> PossibleDates = new HashSet<String>();


	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		//MessageDetailWindow window = new MessageDetailWindow(null, null, null, null);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}


	/**
	 * Class constructor.
	 */
	public MainWindow() {
		mainWindow = this;
		
		initialize();
		frame.setVisible(true);
	}


	/**
	 * gets the tweets and adds them to the specific list
	 */
	/*private void getTweets() {
		TwitterAPI t = new TwitterAPI();
		tweets = t.getTweets();
	}

	/**
	 * gets the emails and adds them to the specific list
	 */
	/*private void getEmails() {
		GmailAPI g = new GmailAPI();
		try {
			emails = g.getMails();
			Collections.sort(emails,new MessageComparator());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	/**
	 * Reconstructs the MainWindow's table
	 * 
	 */
	private void ReconstructTable() {
		defaultTableModel = new DefaultTableModel(msgList.getMsgMatrix(), msgList.getHeaders()) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		table.setModel(defaultTableModel);
	}


	public boolean isTwitterOff() {
		return twitterOff;
	}


	public void setTwitterOff(boolean twitterOff) {
		this.twitterOff = twitterOff;
	}


	public boolean isFacebookOff() {
		return facebookOff;
	}


	public void setFacebookOff(boolean facebookOff) {
		this.facebookOff = facebookOff;
	}


	public boolean isEmailOff() {
		return emailOff;
	}


	public void setEmailOff(boolean emailOff) {
		this.emailOff = emailOff;
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
		TwitterThread t= new TwitterThread();
		GmailThread g = new GmailThread();
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

		try {
			t.join();
			tweets=t.getTweets();
			for (Message m : tweets) {
			//	msgList.addMessage(m);
			AllMessages.add(m);
			TwitterSenders.add(m.getSender());
		}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//getTweets();
		
		
		//getEmails();
		try {
			g.join();
			emails=g.getMails();
			for (Message m : emails) {
			//msgList.addMessage(m);
			AllMessages.add(m);
			EmailSenders.add(m.getSender());
		}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		


		Collections.sort(AllMessages, new MessageComparator());
		Set<String> tmp = new HashSet<String>();
		ShownMessages = new ArrayList<Message>(AllMessages);
		for(Message m : ShownMessages) {
			msgList.addMessage(m);
			tmp.add(m.getTime());
		}
		tmp.addAll(Dates);
		Dates.clear();
		Dates.addAll(tmp);
		Collections.sort(Dates, new StringComparator());
		// for(Message f : facebook) fazer depois get messages from face

		ReconstructTable();


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

					if(table.getModel().getValueAt(row, 0)!=null || table.getModel().getValueAt(row, 0)!="") {
						new MessageDetailWindow(table.getModel().getValueAt(row, 0).toString(),table.getModel().getValueAt(row, 1).toString(),
								table.getModel().getValueAt(row, 2).toString(),table.getModel().getValueAt(row, 3).toString());
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
				new AccountManageWindow(mainWindow);
				//frame.dispose();
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
		Choice DateChoice = new Choice();
		DateChoice.setBounds(554, 129, 95, 20);
		DateChoice.add("All Dates");
		for(String m:Dates)
			DateChoice.add(m);


		frame.getContentPane().add(DateChoice);

		Choice choice_1 = new Choice();
		choice_1.setBounds(44, 129, 92, 20);
		choice_1.add("All Channels");
		choice_1.add("Twitter");
		choice_1.add("Facebook");
		choice_1.add("E-mail");
		JButton FilterChannel = new JButton("Filtrar Canal");
		FilterChannel.setBounds(240,129,123,20);

		frame.getContentPane().add(choice_1);

		Choice choice_2 = new Choice();
		choice_2.setBounds(142, 129, 92, 20);
		choice_2.add("filtrar origem");
		choice_2.setVisible(false);
		frame.getContentPane().add(choice_2);
		JButton FilterSender = new JButton("Filtrar Origem");
		FilterSender.setEnabled(false);
		FilterSender.setBounds(373, 129, 123, 20);
		FilterSender.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				msgList.setHeaders(new String[] { "Data", "Canal", "Origem", "Mensagem" });
				String[][] temp = new String[101][4];
				msgList.setMsgMatrix(temp);
				if(ChoosenChannel=="Twitter"){
					for (Message m : tweets) 
						if(m.getSender().equals(choice_2.getItem(choice_2.getSelectedIndex()))){
							msgList.addMessage(m);
							ChoosenSender=m.getSender();
						}

				}
				else if(choice_1.getItem(choice_1.getSelectedIndex()).equals("E-mail")){
					for (Message m : emails)
						if(m.getSender().equals(choice_2.getItem(choice_2.getSelectedIndex()))) {
							msgList.addMessage(m);
							ChoosenSender=m.getSender();
						}
				}

				ReconstructTable();
			}});

		frame.getContentPane().add(FilterSender);

		FilterChannel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				msgList.setHeaders(new String[] { "Data", "Canal", "Origem", "Mensagem" });
				String[][] temp = new String[101][4];
				msgList.setMsgMatrix(temp);
				ShownMessages.clear();
				PossibleDates.clear();
				DateChoice.removeAll();
				DateChoice.add("All Dates");
				if(choice_1.getItem(choice_1.getSelectedIndex()).equals("Twitter")){
					for (Message m : tweets) {
						msgList.addMessage(m);
						ShownMessages.add(m);
						PossibleDates.add(m.getTime());
					}
					ArrayList<String> tmp = new ArrayList<String>(PossibleDates);
					Collections.sort(tmp, new StringComparator());
					ChoosenChannel="Twitter";
					choice_2.setVisible(true);
					choice_2.removeAll();
					for(String s:TwitterSenders)
						choice_2.add(s);
					for(String s:tmp)
						DateChoice.add(s);
					
					FilterSender.setEnabled(true);

				}
				else if(choice_1.getItem(choice_1.getSelectedIndex()).equals("E-mail")){
					for (Message m : emails) {
						msgList.addMessage(m);
						ShownMessages.add(m);
						PossibleDates.add(m.getTime());
					}
					ArrayList<String> tmp = new ArrayList<String>(PossibleDates);
					Collections.sort(tmp, new StringComparator());
					ChoosenChannel="E-mail";
					choice_2.setVisible(true);
					choice_2.removeAll();
					for(String s:EmailSenders)
						choice_2.add(s);
					for(String s:tmp)
						DateChoice.add(s);
					FilterSender.setEnabled(true);
				}
				else{
					ChoosenChannel="All";
					for (Message m : AllMessages) {
						msgList.addMessage(m);
						ShownMessages.add(m);
						PossibleDates.add(m.getTime());
					}
					ArrayList<String> tmp = new ArrayList<String>(PossibleDates);
					Collections.sort(tmp, new StringComparator());
					for(String s:tmp)
						DateChoice.add(s);
					choice_2.setVisible(false);
					FilterSender.setEnabled(false);
				}

				ReconstructTable();


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

		JTextField txtPesquisaMensagensPor = new JTextField();
		txtPesquisaMensagensPor.setBounds(344, 502, 234, 20);
		txtPesquisaMensagensPor.setForeground(new Color(112, 128, 144));
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

		JButton btnFiltrar = new JButton("Filtrar por palavra");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				msgList.setHeaders(new String[] { "Data", "Canal", "Origem", "Mensagem" });
				String[][] temp = new String[101][4];
				msgList.setMsgMatrix(temp);
				ShownMessages.clear();
				DateChoice.removeAll();
				DateChoice.add("All Dates");
				if(ChoosenChannel.equals("All")){
					for (Message m : emails)
						if(m.getMessage().contains(txtPesquisaMensagensPor.getText())) {
							msgList.addMessage(m);
							ShownMessages.add(m);
							PossibleDates.add(m.getTime());

						}
					for (Message m : tweets)
						if(m.getMessage().contains(txtPesquisaMensagensPor.getText())) {
							msgList.addMessage(m);
							ShownMessages.add(m);
							PossibleDates.add(m.getTime());
						}
					ArrayList<String> tmp = new ArrayList<String>(PossibleDates);
					Collections.sort(tmp, new StringComparator());
					for(String s:tmp)
						DateChoice.add(s);
				}
				else if(ChoosenChannel.equals("Twitter")) {
					if(ChoosenSender.equals("All")) {
						for (Message m : tweets)
							if(m.getMessage().contains(txtPesquisaMensagensPor.getText())) {
								msgList.addMessage(m);
								ShownMessages.add(m);
								PossibleDates.add(m.getTime());
							}
						ArrayList<String> tmp = new ArrayList<String>(PossibleDates);
						Collections.sort(tmp, new StringComparator());
						for(String s:tmp)
							DateChoice.add(s);
					}
					else {
						for (Message m : tweets)
							if(m.getSender().equals(ChoosenSender))	
								if(m.getMessage().contains(txtPesquisaMensagensPor.getText())) {
									msgList.addMessage(m);
									ShownMessages.add(m);
									PossibleDates.add(m.getTime());
								}
						ArrayList<String> tmp = new ArrayList<String>(PossibleDates);
						Collections.sort(tmp, new StringComparator());
						for(String s:tmp)
							DateChoice.add(s);
					}


				}
				else if(ChoosenChannel.equals("E-mail")) {
					if(ChoosenSender.equals("All")) {
						for (Message m : tweets)
							if(m.getMessage().contains(txtPesquisaMensagensPor.getText())) {
								msgList.addMessage(m);
								ShownMessages.add(m);
								PossibleDates.add(m.getTime());
							}
					}
					else {
						for (Message m : tweets)
							if(m.getSender().equals(ChoosenSender))	
								if(m.getMessage().contains(txtPesquisaMensagensPor.getText())) {
									msgList.addMessage(m);
									ShownMessages.add(m);
									PossibleDates.add(m.getTime());
								}
					}
					ArrayList<String> tmp = new ArrayList<String>(PossibleDates);
					Collections.sort(tmp, new StringComparator());
					for(String s:tmp)
						DateChoice.add(s);

				}
				ReconstructTable();
			}
		});
		btnFiltrar.setBounds(600, 501, 145, 23);
		frame.getContentPane().add(btnFiltrar);

		JButton BtnFiltrarData = new JButton("Filtrar Data");
		BtnFiltrarData.setBounds(655, 129, 123, 20);
		BtnFiltrarData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				msgList.setHeaders(new String[] { "Data", "Canal", "Origem", "Mensagem" });
				String[][] temp = new String[101][4];
				msgList.setMsgMatrix(temp);
				if(DateChoice.getItem(DateChoice.getSelectedIndex()).equals("All Dates"))
					for(Message m:ShownMessages)
						msgList.addMessage(m);
				else
					for(Message m:ShownMessages)
						if(m.getTime().equals(DateChoice.getItem(DateChoice.getSelectedIndex())))
							msgList.addMessage(m);
				ReconstructTable();
			}
		});
		frame.getContentPane().add(BtnFiltrarData);

	}
}
