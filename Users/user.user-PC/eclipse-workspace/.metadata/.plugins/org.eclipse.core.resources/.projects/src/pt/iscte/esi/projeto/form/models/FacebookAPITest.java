package pt.iscte.esi.projeto.form.models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import DBA.APIDataBase;

public class FacebookAPITest {
	public static void main(String[] args)  {
		FacebookAPI p = new FacebookAPI();
		FacebookThread t= new FacebookThread();
		APIDataBase a = new APIDataBase();
		t.start();
		try {
			t.join();
			List<Message> message = t.getPosts();
			System.out.println(message.size());
			//a.WriteFacebook(message);
			/*a.WriteFacebook(message);
		List<Message> message = p.getTweets();
			List<Message> message = a.ReadFacebook();*/
		for(Message m: message)
			System.out.println("Sender: "+m.getSender()+" Time: "+m.getTime() + " Message:" +m.getMessage()+ "\n");
		} catch ( InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
	}


}
