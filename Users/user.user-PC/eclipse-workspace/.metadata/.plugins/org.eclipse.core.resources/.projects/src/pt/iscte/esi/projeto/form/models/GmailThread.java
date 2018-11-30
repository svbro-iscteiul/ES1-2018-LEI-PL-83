package pt.iscte.esi.projeto.form.models;

import java.util.ArrayList;

public class GmailThread extends Thread{
	
	private GmailAPI gmail= new GmailAPI();
	private ArrayList<Message> emails = new ArrayList<Message>();
	public GmailThread() {
		run();
	}
	@Override
	public void run(){
		
		try {
			emails=gmail.getMails();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public ArrayList<Message> getMails() {
		return emails;
	}

}
