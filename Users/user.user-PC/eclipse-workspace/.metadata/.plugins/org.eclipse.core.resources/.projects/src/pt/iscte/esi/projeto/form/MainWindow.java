
package pt.iscte.esi.projeto.form;


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

import DBA.DBAWindow;
import pt.iscte.esi.projeto.form.models.Message;
import pt.iscte.esi.projeto.form.models.TwitterAPI;
import pt.iscte.esi.projeto.utils.MainMsgList;
	




	public class MainWindow {

		private JFrame frame;
		private JTable table;
		private JLabel image2;
		private JTextField txtPesquisaMensagensPor;
		private MainMsgList msgList;
		private DefaultTableModel defaultTableModel;
		private ArrayList<Message> tweets= new ArrayList<Message>();

		
		public static void main(String[] args) {
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
			initialize();
			frame.setVisible(true);
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
			
			msgList = new MainMsgList(frame);
			msgList.setHeaders(new String[] { "Data", "Canal", "Origem", "Mensagem"});
			getTweets();
			String[][] temp = new String[101][4];
			msgList.setMsgMatrix(temp);
			for(Message m : tweets)
				msgList.addMessage(m, "Twitter");
			
			//for(Message f : facebook)   fazer depois get messages from face
				
			
			defaultTableModel =  new DefaultTableModel(msgList.getMsgMatrix(), msgList.getHeaders()) {
				/**
				 * Serializa a informação dada na linha anterior
				 */
				private static final long serialVersionUID = 1L;
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				
			};
			
			refreshTable();

			table.setModel(defaultTableModel);		
			table.getColumnModel().getColumn(3).setPreferredWidth(402);

			JLabel lblLogOut = new JLabel("<html><font color='white'>Logout</font></html>");
			lblLogOut.setBounds(709, 98, 61, 14);
			lblLogOut.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					new LoginWindow();
					frame.dispose();
				}
			});
			frame.getContentPane().setLayout(null);
			lblLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblLogOut.setForeground(new Color(240, 255, 255));
			frame.getContentPane().add(lblLogOut);

			scrollPane.setViewportView(table);
			frame.getContentPane().add(scrollPane);
			

			//Add the top image to the main window
			
			JLabel foto = new JLabel("");
			foto.setBounds(0, 26, 784, 99);
			
			ImageIcon image = new ImageIcon(MainWindow.class.getResource("/pt/iscte/esi/projeto/form/images/ImageMainWindow.png"));
			Image img = image.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_SMOOTH);
			
			foto.setIcon(new ImageIcon(img));
			frame.getContentPane().add(foto);
			
			//Add the Button "Pesquisar" to the window
			JButton btnNewButton = new JButton("Pesquisar");
			btnNewButton.setBounds(634, 501, 93, 23);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			frame.getContentPane().add(btnNewButton);
			
			txtPesquisaMensagensPor = new JTextField();
			txtPesquisaMensagensPor.setBounds(390, 502, 234, 20);
			txtPesquisaMensagensPor.setForeground(new Color(112, 128, 144));
			txtPesquisaMensagensPor.setText("Pesquisa mensagens por palavra-chave ");
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
			
			JButton btnRefresh = new JButton("Refresh");
			btnRefresh.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					refreshTable();
				}
			});
			btnRefresh.setBounds(44, 501, 93, 23);
			frame.getContentPane().add(btnRefresh);
			
		/*
		 * Para implementação no Sprint 2
		 * 			
			//Add the Button "Adicionar Contas" to the window
			JButton btnNewButton_1 = new JButton("Adicionar contas");
			btnNewButton_1.setBounds(612, 113, 133, 23);
			frame.getContentPane().add(btnNewButton_1);
			
			image2 = new JLabel("");
			image2.setBounds(0, 157, 414, 402);
 		*/
			
		}
		
		private void refreshTable() {
			Object[][] matrix = msgList.getMsgMatrix();
			if(defaultTableModel.getRowCount() == 0 && matrix == null) {
				matrix = new Object[100][100];
				for(int i=0; i<21;i++) {
					for(int j=0; j<21;j++) {
						matrix[i][j] = null;
						defaultTableModel.setDataVector(matrix, msgList.getHeaders());
					}
				}
			}else {
				defaultTableModel.setDataVector(msgList.getMsgMatrix(), msgList.getHeaders());
			}
		}
		
		
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
		
		private void getTweets()
		{
			TwitterAPI t = new TwitterAPI();
			tweets=t.getTweets();
			//System.out.println(tweets.get(0).getMessage());
		}
	}

	
