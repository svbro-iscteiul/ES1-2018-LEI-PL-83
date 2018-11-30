package pt.iscte.esi.projeto.form.models;

import java.util.ArrayList;

public class TwitterThread extends Thread{
	
	private TwitterAPI twitter= new TwitterAPI();
	private ArrayList<Message> tweets = new ArrayList<Message>();
	public TwitterThread() {
		run();
	}
	@Override
	public void run(){
		
		tweets=twitter.getTweets();
	
	}
	public ArrayList<Message> getTweets() {
		return tweets;
	}

}
