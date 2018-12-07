
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
import java.util.List;
import java.util.Collections;
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
import javax.swing.table.DefaultTableModel;

import DBA.APIDataBase;
import DBA.DBAWindow;
import pt.iscte.esi.projeto.form.models.FacebookThread;
import pt.iscte.esi.projeto.form.models.GmailThread;
import pt.iscte.esi.projeto.form.models.Message;
import pt.iscte.esi.projeto.form.models.MessageComparator;
import pt.iscte.esi.projeto.form.models.StringComparator;
import pt.iscte.esi.projeto.form.models.TwitterThread;
import pt.iscte.esi.projeto.utils.MainMsgList;

/**
 * Window UI with list of tweets, emails and facebook posts
 *
 *  @author José F Santos, Elsa Teixeira, Sérgio Vaz
 */
public class MainWindow {

	private boolean twitterOff = false;
	private boolean facebookOff = false;
	private boolean emailOff = false;

	private boolean getEmail=true;
	private boolean getFacebook=true;
	private boolean getTwitter=true;

	private APIDataBase ApiDB= new APIDataBase();
	private MainWindow mainWindow;
	private JFrame frame;
	private JTable table;
	private MainMsgList msgList;
	private DefaultTableModel defaultTableModel;
	private List<Message> tweets = new ArrayList<Message>();
	private List<Message> emails = new ArrayList<Message>();
	private List<Message> posts = new ArrayList<Message>();
	private List<Message> AllMessages = new ArrayList<Message>();
	private List<Message> ShownMessages= new ArrayList<Message>();
	private Set<String> TwitterSenders = new HashSet<String>();
	private Set<String> EmailSenders = new HashSet<String>();
	private Set<String> FacebookSenders = new HashSet<String>();
	private ArrayList<String> Dates = new ArrayList<String>();
	private String ChoosenChannel="All";
	private String ChoosenSender="All";
	private Set<String> PossibleDates = new HashSet<String>();


	/**
	 * Main
	 * @param args Inputs
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
	}*/




	/**
	 * Twitter boolean status
	 * 
	 * @return true if Twitter is Off
	 */
	public boolean isTwitterOff() {
		return twitterOff;
	}

	/**
	 * Sets Off or ON for Twitter
	 * 
	 * @param twitterOff true if twitter is Off
	 */
	public void setTwitterOff(boolean twitterOff) {
		this.twitterOff = twitterOff;
	}

	/**
	 * Facebook boolean status
	 * 
	 * @return true if Facebook is Off
	 */
	public boolean isFacebookOff() {
		return facebookOff;
	}

	/**
	 * Sets Off or ON for Facebook
	 * 
	 * @param facebookOff true if Facebook is Off
	 */
	public void setFacebookOff(boolean facebookOff) {
		this.facebookOff = facebookOff;
	}

	/**
	 * Email boolean status
	 * 
	 * @return true if Email is Off
	 */
	public boolean isEmailOff() {
		return emailOff;
	}

	/**
	 * Sets Off or ON for Email
	 * 
	 * @param emailOff true if Email is Off
	 */
	public void setEmailOff(boolean emailOff) {
		this.emailOff = emailOff;
	}

	/**
	 *  Refresh all data from main table
	 */
	public void refreshAllTable() {
		refreshTable();
		reconstructTable();
		getMatrixElements();


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
	 * Reconstructs the MainWindow's table
	 * 
	 */
	private void reconstructTable() {
		defaultTableModel = new DefaultTableModel(msgList.getMsgMatrix(), msgList.getHeaders()) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		table.setModel(defaultTableModel);
	}

	/**
	 * Under work!! used to select an element in the list.
	 * 
	 * @param component
	 * @param popup
	 */
	@SuppressWarnings("unused")
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


		getMatrixElements(); 

		//Collections.sort(AllMessages, new MessageComparator());

		ShownMessages = new ArrayList<Message>(AllMessages);
		
		showMessages();



		Collections.sort(Dates, new StringComparator());

		reconstructTable();


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
		choice_1.add("Email");
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
				else if(choice_1.getItem(choice_1.getSelectedIndex()).equals("Email")){
					for (Message m : emails)
						if(m.getSender().equals(choice_2.getItem(choice_2.getSelectedIndex()))) {
							msgList.addMessage(m);
							ChoosenSender=m.getSender();
						}
				}
				else if(ChoosenChannel=="Facebook"){
					for (Message m : posts) 
						if(m.getSender().equals(choice_2.getItem(choice_2.getSelectedIndex()))){
							msgList.addMessage(m);
							ChoosenSender=m.getSender();
						}

				}

				reconstructTable();
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
				else if(choice_1.getItem(choice_1.getSelectedIndex()).equals("Email")){
					for (Message m : emails) {
						msgList.addMessage(m);
						ShownMessages.add(m);
						PossibleDates.add(m.getTime());
					}
					ArrayList<String> tmp = new ArrayList<String>(PossibleDates);
					Collections.sort(tmp, new StringComparator());
					ChoosenChannel="Email";
					choice_2.setVisible(true);
					choice_2.removeAll();
					for(String s:EmailSenders)
						choice_2.add(s);
					for(String s:tmp)
						DateChoice.add(s);
					FilterSender.setEnabled(true);
				}
				else if(choice_1.getItem(choice_1.getSelectedIndex()).equals("Facebook")){
					for (Message m : posts) {
						msgList.addMessage(m);
						ShownMessages.add(m);
						PossibleDates.add(m.getTime());
					}
					ArrayList<String> tmp = new ArrayList<String>(PossibleDates);
					Collections.sort(tmp, new StringComparator());
					ChoosenChannel="Facebook";
					choice_2.setVisible(true);
					choice_2.removeAll();
					for(String s:FacebookSenders)
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

				reconstructTable();


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

		JLabel ErrorMessage = new JLabel("");
		ErrorMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ErrorMessage.setBounds(44, 465, 500, 25);

		String error="";
		if(!getEmail || !getFacebook || !getTwitter)
			error+="Unable to get from:";
		if(!getEmail)
			error+="email,";
		if(!getTwitter)
			error+="twitter,";
		if(!getFacebook)
			error+="facebook,";

		if(!error.equals(""))
			ErrorMessage.setText("<html><font color='red'>Error:"+error+"</font></html>");
		frame.getContentPane().add(ErrorMessage);
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
				refreshAllTable();
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
					for (Message m : posts)
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
				else if(ChoosenChannel.equals("Email")) {
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
				else if(ChoosenChannel.equals("Facebook")) {
					if(ChoosenSender.equals("All")) {
						for (Message m : posts)
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
						for (Message m : posts)
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
				reconstructTable();
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
				reconstructTable();
			}
		});
		frame.getContentPane().add(BtnFiltrarData);

	}

	/**
	 * get Elements for Matrix
	 */
	private void getMatrixElements() {
		try {
			GmailThread f = new GmailThread();
			f.join();
			emails=f.getMails();
			if(emails.size()==0) {
				this.getEmail=false;
				emails=ApiDB.ReadEmail();
				for (Message m : emails) {	
					EmailSenders.add(m.getSender());
				}
			}
			else {
				for (Message m : emails) {
					
					EmailSenders.add(m.getSender());
				}
				ApiDB.WriteEmail(emails);
				
			}
		}catch(Exception e1){
			e1.printStackTrace();	
		}
		try {
			TwitterThread f = new TwitterThread();
			f.join();
			tweets=f.getTweets();
			if(tweets.size()==0) {
				this.getTwitter=false;
				tweets=ApiDB.ReadTwitter();
				for (Message m : tweets) {
					TwitterSenders.add(m.getSender());
				}
			}
			else {
				for (Message m : tweets) {
					TwitterSenders.add(m.getSender());
				}
				ApiDB.WriteTwitter(tweets);
			}
		}catch(Exception e1){
			e1.printStackTrace();	
		}
		try {
			FacebookThread f = new FacebookThread();
			f.join();
			posts=f.getPosts();
			if(posts.size()==0) {
				this.getFacebook=false;
				posts=ApiDB.ReadFacebook();
				for (Message m : posts) {
					FacebookSenders.add(m.getSender());
				}
			}
			else {
				for (Message m : posts) {
				

					FacebookSenders.add(m.getSender());
				}
				ApiDB.WriteFacebook(posts);
			}
		}catch(Exception e1){
			e1.printStackTrace();	
		}
		for(Message m:posts)
			AllMessages.add(m);
		for(Message m:emails)
			AllMessages.add(m);
		for(Message m:tweets)
			AllMessages.add(m);

	}

	/**
	 *  show messages from all APIs
	 */
	private void showMessages() {
		Set<String> tmp = new HashSet<String>();

		for(Message m : ShownMessages) {

			if(m.getChannel().equals("Twitter") && !twitterOff) {
				msgList.addMessage(m);
				tmp.add(m.getTime());
			}
			else if(m.getChannel().equals("Facebook") && !facebookOff) {
				msgList.addMessage(m);
				tmp.add(m.getTime());
			}
			else if(m.getChannel().equals("Email") && !emailOff) {
				msgList.addMessage(m);
				tmp.add(m.getTime());
			}			
		}

		tmp.addAll(Dates);
		Dates.clear();
		Dates.addAll(tmp);

	}
}
