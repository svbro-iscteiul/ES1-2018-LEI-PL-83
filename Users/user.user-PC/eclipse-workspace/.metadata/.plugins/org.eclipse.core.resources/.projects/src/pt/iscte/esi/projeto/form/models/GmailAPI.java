package pt.iscte.esi.projeto.form.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;

/**
 * Gmail API class
 * @author Sérgio Ribeiro
 *
 */
public class GmailAPI {
	
	private ArrayList<pt.iscte.esi.projeto.form.models.Message> emails = new ArrayList<pt.iscte.esi.projeto.form.models.Message>();
	private final static String user= "ISCTE";
	
	
	/**
	 * This class uses the Javax to fetch the emails of the user.
	 * For this to happen, the user has to give permitions to access their inbox 
	 * 
	 * @return: emails as ArrayList<pt.iscte.esi.projeto.form.models.Message>
	 * @throws Exception
	 */
	public ArrayList<pt.iscte.esi.projeto.form.models.Message> getMails() throws Exception {
		 Session emailSession = Session.getInstance(System.getProperties());
			Store store = emailSession.getStore("imaps");
			store.connect("pop.googlemail.com", "happyc0d3rtwo@gmail.com", "happy.two");
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);
	      
	      // retrieve the messages from the folder in an array and print it
	      Message[] messages = emailFolder.getMessages();
	      for (int i = 0, n = messages.length; i < n; i++) {
	    	  Message message = messages[i];
		         


		         Object obj = message.getContent();
		         Multipart mp = (Multipart)obj;
		         BodyPart bp = mp.getBodyPart(0);

/*
		         System.out.println("---------------------------------");
		         System.out.println("Email Number " + (i + 1));
		         System.out.println("Subject: " + message.getSubject());
		         System.out.println("From: " + message.getFrom()[0]);
		         System.out.println("To: " + message.getAllRecipients().toString());
		         System.out.println("Received Date:" + message.getReceivedDate());*/
		         //System.out.println("Text: " + );
		         //AddMessageToList(, "Email", message.getFrom().toString(),bp.getContent().toString());
		         String date=SetDateFormat(message.getSentDate().toString());
		 		 pt.iscte.esi.projeto.form.models.Message a = new pt.iscte.esi.projeto.form.models.Message(message.getSentDate().toString(),"Email", message.getFrom().toString(),bp.getContent().toString());
		 		
		 		emails.add(a);
	    		
		      }
		/*for ( Message message : messages ) {
			// if the sender is svbro@iscte-iul.pt
			if(InternetAddress.toString(message.getFrom()).equals("=?UTF-8?Q?S=C3=A9rgio_Ribeiro?= <Sergio_Vaz@iscte-iul.pt>")) {
				String body = getTextFromMessage(message);
				ddMessage(message.getSentDate().toString(), "Email", message.getSubject(), body);
			}
		}
		return emails;
	}
	*/
	return emails;
	}
	/**
	 * This class creates and adds a message to the emails list
	 * 
	 * @param time as String
	 * @param channel as String
	 * @param sender as String
	 * @param message as String
	 */
/*	private void AddMessageToList(String time, String channel, String sender, String message) 
	{
		String date=SetDateFormat(time);
		pt.iscte.esi.projeto.form.models.Message a = new pt.iscte.esi.projeto.form.models.Message(date,channel,sender,message);
		
		emails.add(a);
*/
	
	/**
	 * This class gets the body of the email, since the body comes encrypted 
	 * 
	 * @param message as Message
	 * @return result as String
	 * @throws MessagingException
	 * @throws IOException
	 */
	
	
	/**
	 * An auxiliary class to getTextFromMessage
	 * 
	 * @param mimeMultipart as MimeMultipart
	 * @return result as String
	 * @throws MessagingException
	 * @throws IOException
	 */
	
	
	/**
	 * This method receives the date of the Mail and convert into a simpler date
	 * 
	 * example:
	 * 	receives: Fri Oct 26 15:59:50 BST 2018
	 * 	returns:  26/Oct/2018
	 * 
	 * @param s as String
	 * @return date as String
	 */
	private String SetDateFormat(String s)
	{
		String[] backup = s.split(" ");
		String date = backup[2] + "/" + backup[1] + "/" + backup[5];
		return date;
		
	}

}
