package pt.iscte.esi.projeto.form.models;

import java.util.ArrayList;
import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterAPI {
	private ArrayList<Message> message = new ArrayList<Message>();
	private final static String user= "ISCTE";
	
	
	/*
	 * This class uses the API twitter4j to get the tweets of the user 
	 * and creates a object message with the text, time of the tweet 
	 * */
	public ArrayList<Message> getTweets() {
        try {
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
				if (status.getUser().getName() != null && status.getUser().getName().contains(user)) {
					Message m = new Message();
					m.setSender(status.getUser().getName());
					m.setMessage(status.getText());
					String date=SetDateFormat(status.getCreatedAt().toString());
					m.setTime(date);
					message.add(m);
				}
            }     
            return message;
        } catch (Exception e) { System.out.println(e.getMessage()); return null;}
     }
	
	/*
	 * This method recives the date of the Tweet, for example:Fri Oct 26 15:59:50 BST 2018
	 * and return 26/Oct/2018
	 * */
	private String SetDateFormat(String s)
	{
		String[] backup = s.split(" ");
		String date = backup[2] + "/" + backup[1] + "/" + backup[5];
		return date;
		
	}

}
