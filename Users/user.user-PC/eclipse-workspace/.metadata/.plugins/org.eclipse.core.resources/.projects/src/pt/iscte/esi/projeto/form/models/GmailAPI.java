package pt.iscte.esi.projeto.form.models;

import java.io.IOException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import com.sun.mail.smtp.SMTPTransport;

import pt.iscte.esi.projeto.utils.XMLFileEditor;

/**
 * Gmail API class
 */
public class GmailAPI {
	
	private ArrayList<pt.iscte.esi.projeto.form.models.Message> emails = new ArrayList<pt.iscte.esi.projeto.form.models.Message>();
	private final static String user= "=?UTF-8?Q?S=C3=A9rgio_Ribeiro?= <Sergio_Vaz@iscte-iul.pt>";
	private XMLFileEditor editor = new XMLFileEditor();
	private String email="happyc0d3rtwo@gmail.com";
	private String password="happy.two";
	
	private void getTokenFromXML() {
		String tmp=editor.getEmailTokens();
		if(!tmp.equals(null)){
			String[] tmp1=tmp.split("BREAKHERE");
			email=tmp1[0];
			password=tmp1[1];
			
		}
		
	}
	
	
	/**
	 * Get a list of emails.
	 * @author svbro-iscteiul
	 * @return ArrayList<Message> 
	 * @throws Exception
	 */
	public ArrayList<pt.iscte.esi.projeto.form.models.Message> getMails() throws Exception {
		getTokenFromXML();
		Session sesion = Session.getInstance(System.getProperties());
		Store store = sesion.getStore("imaps");
		store.connect("pop.googlemail.com", email, password);
		Folder inbox = store.getFolder("Inbox");
		inbox.open(Folder.READ_WRITE);
		Message[] messages = (Message[]) inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
		for ( Message message : messages ) {
			if(InternetAddress.toString(message.getFrom()).equals(user)) {
				String body = getTextFromMessage(message);
				pt.iscte.esi.projeto.form.models.Message m =createMessage(body);
				emails.add(m);
			}
		}
		return emails;
	}

	/**
	 * creates a Message
	 * @author svbro-iscteiul
	 * @param original as String
	 * @return
	 */
	private pt.iscte.esi.projeto.form.models.Message createMessage(String original){
		pt.iscte.esi.projeto.form.models.Message m = new pt.iscte.esi.projeto.form.models.Message();
		
		String sender= original.substring(original.indexOf("De: ")+4, original.indexOf("Enviado"));
		
		String date=original.substring(original.indexOf("Enviado: ")+9,original.indexOf("Para"));
		
		String temp[]=date.split(" ");
		String day=temp[0];
		String month=temp[2];
		String mes =SetMonth(month);
		String year=temp[4];
		date=day+"/"+mes+"/"+year;
		
		String body=original.substring(original.indexOf("Assunto: ")+9);
		m.setSender(sender);
		m.setChannel("Email");
		m.setMessage(body);
		m.setTime(date);
		return m;
	}
	
	/**
	 * Returns text from message 
	 * @author svbro-iscteiul
	 * @param message Message
	 * @return text as String
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
	 * Returns TextFromMimeMultipart
	 * @author svbro-iscteiul
	 * @param mimeMultipart
	 * @return String
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
	 * @author svbro-iscteiul
	 * @param string 
	 * @return string
	 * Returns the number of the mouth
	 * */
	private String SetMonth(String mes) {
		String m= mes.toLowerCase();
		if(m.equals("janeiro") || m.equals("jan"))
			return "01";
		else if(m.equals("fevereiro") || m.equals("fev"))
			return "02";
		else if(m.equals("março") || m.equals("mar"))
			return "03";
		else if(m.equals("abril") || m.equals("abr"))
			return "04";
		else if(m.equals("maio") || m.equals("mai"))
			return "05";
		else if(m.equals("junho") || m.equals("jun"))
			return "06";
		else if(m.equals("julho") || m.equals("jul"))
			return "07";
		else if(m.equals("agosto") || m.equals("ago"))
			return "08";
		else if(m.equals("setembro") || m.equals("set"))
			return "09";
		else if(m.equals("outubro") || m.equals("out"))
			return "10";
		else if(m.equals("novembro") || m.equals("nov"))
			return "11";
		else 
			return "12";
			
	}
	/**
	 * @author svbro-iscteiul
	 * @param title
	 * @param message
	 * @throws AddressException
	 * @throws MessagingException
	 * Respondes to an email
	 */
	public void SendEmail(String title, String message) throws AddressException, MessagingException {
		getTokenFromXML();
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);
        final MimeMessage msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(email));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));

       

        msg.setSubject(title);
        msg.setText(message, "utf-8");
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

        t.connect("smtp.gmail.com", email, password);
        t.sendMessage(msg, msg.getAllRecipients());      
        System.out.println("sucess");
        t.close();
    }
}
