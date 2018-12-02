package pt.iscte.esi.projeto.form.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Gmail API class, used only for testing
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
				System.out.println("Time: "+m.getTime() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
