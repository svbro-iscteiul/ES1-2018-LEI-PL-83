package pt.iscte.esi.projeto.form.models;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import DBA.APIDataBase;
import twitter4j.TwitterException;

/**
 * Twitter API class, used only for testing
 * 
 * @author Sérgio Vaz
 */
public class TwitterAPITest {
	
	/*
	 * This is just to see if the Twitter api is working, it will be deleted later
	 * */
	public static void main(String[] args)  {
		TwitterThread t= new TwitterThread();
		TwitterAPI a= new TwitterAPI();
		t.start();
		try {
			t.join();
			List<Message> message = t.getTweets();
			for(Message m: message) {
				System.out.println("Sender: "+m.getSender()+" Time: "+m.getTime() + " Message:" +m.getMessage()+ "\n");
				break;
			}
			a.ReplyToTweet(message.get(0).getMessage(), "This is a reply");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 




	}

}
