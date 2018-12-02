package pt.iscte.esi.projeto.form.models;

import java.util.ArrayList;

/**
 * 
 *  @author svbro-iscteiul
 *	This is a thread that fetchs the tweets from the TwitterAPI
 */
public class TwitterThread extends Thread{

	private TwitterAPI twitter= new TwitterAPI();
	private ArrayList<Message> tweets = new ArrayList<Message>();
	public TwitterThread() {
		run();
	}
	@Override
	public void run(){
		try {
			tweets=twitter.getTweets();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Message> getTweets() {
		return tweets;
	}

}
