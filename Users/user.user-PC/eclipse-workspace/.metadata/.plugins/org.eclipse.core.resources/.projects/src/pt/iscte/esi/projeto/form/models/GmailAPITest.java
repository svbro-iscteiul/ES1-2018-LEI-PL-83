package pt.iscte.esi.projeto.form.models;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

/**
 * Gmail API class, used only for testing
 *
 * @author Sérgio Vaz
 */
public class GmailAPITest {
	/*Test class, its going to be deleted*/
	public static void main(String[] args) {
		GmailAPI p = new GmailAPI();
		try {
			for(Message m :p.getMails())
				System.out.println(m.getChannel());
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
