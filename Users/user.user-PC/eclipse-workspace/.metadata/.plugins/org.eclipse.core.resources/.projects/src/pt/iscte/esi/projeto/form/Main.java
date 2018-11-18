package pt.iscte.esi.projeto.form;


import java.awt.EventQueue;


/**
 * Main class
 * @author Elsa Teixeira, José Santos, Sérgio Ribeiro - LEI ISCTE
 *
 */
public class Main {

	/**
	 * Main method for the Main class.
	 * Launches application by starting Login Window.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoginWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
}
