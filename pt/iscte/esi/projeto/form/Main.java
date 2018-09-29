package pt.iscte.esi.projeto.form;

import java.awt.EventQueue;

public class Main {

	/**
	 * Launch the application.
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
