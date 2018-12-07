package pt.iscte.esi.projeto.form.models;

import java.util.ArrayList;
/**
 * This Thread fetchs the posts from the FacebookAPI
 * 
 * @author Sérgio Vaz
 */
public class FacebookThread extends Thread{
	
	private FacebookAPI Facebook= new FacebookAPI();
	private ArrayList<Message> posts = new ArrayList<Message>();
	public FacebookThread() {
		run();
	}
	@Override
	public void run(){
		
		try {
			posts=Facebook.getPosts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Message> getPosts() {
		return posts;
	}
}
