package pt.iscte.esi.projeto.form;


import java.awt.EventQueue;


/**
 * Main class
 *
 *  @author José F Santos
 */
public class Main {

	/**
	 * Main
	 * @param args Inputs
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
