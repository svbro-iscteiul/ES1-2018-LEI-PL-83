package pt.iscte.esi.projeto.form.models;

import java.util.List;

public class GmailAPITest {
	
	public static void main(String[] args) {
		GmailAPI p = new GmailAPI();
		List<Message> message;
		try {
			message = p.getMails();
			for(Message m: message)
				System.out.println(m.getTime());
				//System.out.println("Sender: "+m.getSender()+" Time: "+m.getTime() + " Message:" +m.getMessage()+ "\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
