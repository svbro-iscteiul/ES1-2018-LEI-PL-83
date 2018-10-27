
package src.pt.iscte.esi.projeto.form;


import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
	

	public class MainWindow {

		private JFrame frame;
		private JTable table;
		private JLabel image2;
		private JTextField txtPesquisaMensagensPor;

		
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
			table.setModel(new DefaultTableModel(
					/*
					 * Pensar noutra maneira para implementar isto..
					 * o acesso por classes exteriores não é permitido. (ex APIs Face, Twitter)
					 * 
					 * além disso a bibliotéca com.jgoodies está desactualizada e deixou de ser OpenSource,
					 * Para atualizar tem de se pagar
					 * 
					 * implementar antes uma lista como a lista usada no projecto de PCD
					 */
				new Object[][] {
					{"01/01/1000", "teste", "teste", "teste"},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
				},
				new String[] {
					"Data", "Canal", "Origem", "Mensagem"
				}
			) {
				/**
				 * Para que serve ?? 
				 */
				private static final long serialVersionUID = 1L;
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				
			});
			table.getColumnModel().getColumn(3).setPreferredWidth(402);
			frame.getContentPane().setLayout(null);
			
	
			
			JLabel lblLogOut = new JLabel("<html><font color='white'>Logout</font></html>");
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
			lblLogOut.setBounds(709, 72, 61, 14);
			frame.getContentPane().add(lblLogOut);

			scrollPane.setViewportView(table);
			frame.getContentPane().add(scrollPane);
			

			//Add the top image to the main window
			
			JLabel foto = new JLabel("");
			foto.setBounds(0, 0, 784, 102);
			
			ImageIcon image = new ImageIcon(MainWindow.class.getResource("/src/pt/iscte/esi/projeto/form/images/ImageMainWindow.png"));
			Image img = image.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_SMOOTH);
			
			foto.setIcon(new ImageIcon(img));
			frame.getContentPane().add(foto);
			
			//Add the Button "Pesquisar" to the window
			JButton btnNewButton = new JButton("Pesquisar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnNewButton.setBounds(634, 501, 93, 23);
			frame.getContentPane().add(btnNewButton);
			
			txtPesquisaMensagensPor = new JTextField();
			txtPesquisaMensagensPor.setForeground(new Color(112, 128, 144));
			txtPesquisaMensagensPor.setText("Pesquisa mensagens por palavra-chave ");
			txtPesquisaMensagensPor.setBounds(390, 502, 234, 20);
			frame.getContentPane().add(txtPesquisaMensagensPor);
			txtPesquisaMensagensPor.setColumns(10);
			
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
		/*
		 * Para que serve isto????
		 
		}
		private static void addPopup(Component component, final JPopupMenu popup) {
		}
		
		*/
		
	}


