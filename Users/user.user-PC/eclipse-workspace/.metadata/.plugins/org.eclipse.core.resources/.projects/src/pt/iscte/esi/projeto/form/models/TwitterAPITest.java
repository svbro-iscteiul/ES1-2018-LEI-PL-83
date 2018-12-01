package pt.iscte.esi.projeto.form.models;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import DBA.APIDataBase;
import twitter4j.TwitterException;

/**
 * Twitter API class, used only for testing
 */
public class TwitterAPITest {
	/*
	 * This is just to see if the Twitter api is working, it will be deleted later
	 * */
	public static void main(String[] args)  {
		TwitterAPI p = new TwitterAPI();
		TwitterThread t= new TwitterThread();
		APIDataBase a = new APIDataBase();
		t.start();
		try {
			t.join();
		
			 t.getTweets();
			List<Message> message = t.getTweets();
			a.WriteTwitter(message);
			
				List<Message> message1 =a.ReadTwitter();
			for(Message m: message1)
				System.out.println("Sender: "+m.getSender()+" Time: "+m.getTime() + " Message:" +m.getMessage()+ "\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 




	}

}
