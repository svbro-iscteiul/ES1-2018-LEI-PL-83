package pt.iscte.esi.projeto.form.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Used for testing, delete after
 *
 */
public class GmailAPITest {
	/*Test class, its going to be deleted*/
	public static void main(String[] args) {
		GmailAPI p = new GmailAPI();
		List<Message> message;
		try {
			ArrayList<pt.iscte.esi.projeto.form.models.Message> emails = p.getMails();
			for(Message m: emails)
				System.out.println("Sender: "+m.getSender()+"\n"+"Time: "+m.getTime() + "\nMessage:" +m.getMessage()+ "\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
