package pt.iscte.esi.projeto.form.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pt.iscte.esi.projeto.utils.XMLFileEditor;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Twitter API class
 *
 */
public class TwitterAPI {

	private ArrayList<Message> message = new ArrayList<Message>();
	private final static String user= "ISCTE";
	private XMLFileEditor editor = new XMLFileEditor();
	private String ConsumerKey="lssQlInMSR48WEhVnhhEpLKlU";
	private String ConsumerSecret="HJoUp0olU7wGYFFSbB6gEMRtfxJBUunM2ZirdOznPRoGpcBBy9";
	private String AccessToken="1056204591581290497-qChkQRfvnqCsNq5fTlJ6kFiaDdOfos";
	private String AccessTokenSecret="Ikwu8aWLHnm7GduV5SCX1rwfOck5FlEyItvEIzYpRkhsd";
	
	
	private void getTokenFromXML() {
		String tmp=editor.getTwitterTokens();
		if(!tmp.equals(null)){
			String[] tmp1=tmp.split("BREAKHERE");
			ConsumerKey=tmp1[0];
			ConsumerSecret=tmp1[1];
			AccessToken=tmp1[2];
			AccessTokenSecret=tmp1[3];
		}
		
	}

	/**
	 * This class uses the API twitter4j to get the tweets of the user 
	 * and creates a object message with the text, time of the tweet 
	 * 
	 * @return message as ArrayList<Message>
	 * @throws TwitterException 
	 */
	public ArrayList<Message> getTweets() throws TwitterException {
		
		getTokenFromXML();
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey(ConsumerKey)
		.setOAuthConsumerSecret(ConsumerSecret)
		.setOAuthAccessToken(AccessToken)
		.setOAuthAccessTokenSecret(AccessTokenSecret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();        		
		List<Status> statuses = twitter.getHomeTimeline();
		for (Status status : statuses) {
			if (status.getUser().getName() != null && status.getUser().getName().contains(user)) {
				Message m = new Message();
				m.setSender(status.getUser().getName());
				m.setMessage(status.getText());
				String date=SetDateFormat(status.getCreatedAt().toString());
				m.setChannel("Twitter");
				m.setTime(date);
				message.add(m);
			}
		}     
		return message;
	}

	/**
	 * This method receives the date of the Tweet, for example:Fri Oct 26 15:59:50 BST 2018
	 * and return 26/Oct/2018
	 * 
	 * @param s as String
	 * @return date as String
	 */
	private String SetDateFormat(String s)
	{
		String[] backup = s.split(" ");
		String mes = SetMonth(backup[1]);
		String date = backup[2] + "/" + mes + "/" + backup[5];
		return date;

	}
	private String SetMonth(String mes) {
		//System.out.println(mes);
		String m= mes.toLowerCase();
		//System.out.println(m);
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
	 *  
	 * */
	public void ReplyToTweet(String text,String Reply) throws TwitterException{
		List<Status> tweets= new ArrayList<Status>();
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("lssQlInMSR48WEhVnhhEpLKlU")
		.setOAuthConsumerSecret("HJoUp0olU7wGYFFSbB6gEMRtfxJBUunM2ZirdOznPRoGpcBBy9")
		.setOAuthAccessToken("1056204591581290497-qChkQRfvnqCsNq5fTlJ6kFiaDdOfos")
		.setOAuthAccessTokenSecret("Ikwu8aWLHnm7GduV5SCX1rwfOck5FlEyItvEIzYpRkhsd");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();        		
		List<Status> statuses = twitter.getHomeTimeline();
		for (Status status : statuses) {
			if (status.getText().equals(text)) {
				tweets.add(status);
				
				break;
			}
		}
		 Status reply = null;
	        for (Status tweet : tweets) {
	            try {
	                reply = twitter.updateStatus(new StatusUpdate("@" + tweet.getUser().getScreenName() + " " + Reply).inReplyToStatusId(tweet.getId()));
	                
	                //System.out.println("Posted reply " + reply.getId() + " in response to tweet " + reply.getInReplyToStatusId());
	            } catch (TwitterException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } 
	        }

	}

}
