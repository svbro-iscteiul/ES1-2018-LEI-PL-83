package pt.iscte.esi.projeto.form.models;
import java.util.List;

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
		t.start();
		try {
			t.join();
			List<Message> message = t.getTweets();
		//List<Message> message = p.getTweets();
		for(Message m: message)
			System.out.println("Sender: "+m.getSender()+" Time: "+m.getTime() + " Message:" +m.getMessage()+ "\n");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
	}

}
