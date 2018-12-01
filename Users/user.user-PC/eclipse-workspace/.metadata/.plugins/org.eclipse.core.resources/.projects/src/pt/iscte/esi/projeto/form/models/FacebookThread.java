package pt.iscte.esi.projeto.form.models;

import java.util.ArrayList;

public class FacebookThread extends Thread{
	
	private FacebookAPI Facebook= new FacebookAPI();
	private ArrayList<Message> posts = new ArrayList<Message>();
	public FacebookThread() {
		run();
	}
	@Override
	public void run(){
		
		posts=Facebook.getPosts();
	
	}
	public ArrayList<Message> getPosts() {
		return posts;
	}
}
