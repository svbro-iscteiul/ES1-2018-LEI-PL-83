package pt.iscte.esi.projeto.form.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;

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
		Session session = Session.getDefaultInstance(new Properties( ));
		Store store = session.getStore("imaps");
		store.connect("imap.googlemail.com", 993, "happyc0d3rtwo@gmail.com", "happy.two");
		Folder inbox = store.getFolder( "INBOX" );
		inbox.open( Folder.READ_ONLY );

		// Fetch unseen messages from inbox folder
		Message[] messages = inbox.getMessages();

		// Sort messages from recent to oldest
		Arrays.sort( messages, ( m1, m2 ) -> {
			try {
				return m2.getSentDate().compareTo( m1.getSentDate() );
			} catch ( MessagingException e ) {
				throw new RuntimeException( e );
			}
		} );

		for ( Message message : messages ) {
			// if the sender is svbro@iscte-iul.pt
			if(InternetAddress.toString(message.getFrom()).equals("=?UTF-8?Q?S=C3=A9rgio_Ribeiro?= <Sergio_Vaz@iscte-iul.pt>")) {
				String body = getTextFromMessage(message);
				AddMessage(message.getSentDate().toString(), "Email", message.getSubject(), body);
			}
		}
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
	private void AddMessage(String time, String channel, String sender, String message) 
	{
		String date=SetDateFormat(time);
		pt.iscte.esi.projeto.form.models.Message a = new pt.iscte.esi.projeto.form.models.Message(date,channel,sender,message);
		
		emails.add(a);
	}
	
	/**
	 * This class gets the body of the email, since the body comes encrypted 
	 * 
	 * @param message as Message
	 * @return result as String
	 * @throws MessagingException
	 * @throws IOException
	 */
	private String getTextFromMessage(Message message) throws MessagingException, IOException {
		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}
		return result;
	}
	
	/**
	 * An auxiliary class to getTextFromMessage
	 * 
	 * @param mimeMultipart as MimeMultipart
	 * @return result as String
	 * @throws MessagingException
	 * @throws IOException
	 */
	private String getTextFromMimeMultipart( MimeMultipart mimeMultipart)  throws MessagingException, IOException{
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				//  result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
			} else if (bodyPart.getContent() instanceof MimeMultipart){
				result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
			}
		}
		return result;
	}
	
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
