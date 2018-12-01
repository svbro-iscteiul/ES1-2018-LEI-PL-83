package pt.iscte.esi.projeto.form.models;

import java.util.List;

public class FacebookAPITest {
	public static void main(String[] args)  {
		FacebookAPI p = new FacebookAPI();
		FacebookThread t= new FacebookThread();
		t.start();
		try {
			t.join();
			List<Message> message = t.getPosts();
		//List<Message> message = p.getTweets();
		for(Message m: message)
			System.out.println("Sender: "+m.getSender()+" Time: "+m.getTime() + " Message:" +m.getMessage()+ "\n");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
	}


}
