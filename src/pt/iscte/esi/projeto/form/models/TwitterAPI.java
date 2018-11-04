package pt.iscte.esi.projeto.form.models;

import java.util.ArrayList;
import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterAPI {
	private ArrayList<Message> message = new ArrayList<Message>();
	private final static String user= "ISCTE - IUL";
	
	
	/*
	 * This class uses the API twitter4j to get the tweets of the user 
	 * and creates a object message with the text, time of the tweet 
	 * */
	public ArrayList<Message> getMessage() {
        try {
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	cb.setDebugEnabled(true)
        	  .setOAuthConsumerKey("W1f0VvgWPfT8OBqVxvy4Mw")
        	  .setOAuthConsumerSecret("zKH2yAtRyefwsgOO8h8Szc4kru68iEm95QmIG7svw")
        	  .setOAuthAccessToken("36481851-VhzByC4f9MSsZES1QZQ4e4iBvA9bWGLyv9HKFpy7c")
        	  .setOAuthAccessTokenSecret("OahDuXF2Lhl5xlNYALhYZir6xSflAxKP9Zh89T05po");
        	TwitterFactory tf = new TwitterFactory(cb.build());
        	Twitter twitter = tf.getInstance();        		
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses) {
				if (status.getUser().getName() != null && status.getUser().getName().contains(user)) {
					Message m = new Message();
					m.setSender("Twitter");
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
