package pt.iscte.esi.projeto.form.models;

import java.util.ArrayList;
import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.Post;
import com.restfb.types.User;

/**
 * Facebook Main
 *
 */
public class FacebookAPI {
	private ArrayList<Message> posts = new ArrayList<Message>();
	private static String Token="EAAD0JjeESBwBAJFqEUE3BZC8AqgE5UwIRM5HiWRH6TtWiDGeins0LlXNAZB7Rb22VglN6Nmd9YNBf8H95YmU2BgElasjC5NhhRtqqGS8FfKb3Lc4mKncaeZBz26qFmAGHS2pqZCmVSS2ofPV0OuPuPz0qmXKx5wZCtKAlenkiwJZA1Xev0a5cYfvA3lG49qYWyG0woXJfcpwZDZD";
	private static String AppId="268444977350684";
	private static String AppSecret="75a7638dfa1a64929a408b3955681d0a";
	public ArrayList<Message> getPosts() {

		String accessToken =Token;
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		AccessToken extendedAccessToken4 = fbClient.obtainExtendedAccessToken(AppId,AppSecret);

		


		Connection<Post> result = fbClient.fetchConnection("me/posts",Post.class);
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "Inform"
				if (aPost.getMessage() != null && aPost.getMessage().contains("ISCTE")) {
					String date = setDate(aPost.getCreatedTime().toString());
					Message m = new Message();
					m.setSender("facebook");
					m.setMessage(aPost.getMessage());
					m.setChannel("Facebook");
					m.setTime(date);
					posts.add(m);

				}
				
			}
		}
		return posts;
	
	}

	private static String setDate(String date) {
		//Thu Nov 08 16:06:15 GMT 2018
		String[] tmp = date.split(" ");
		String month=SetMonth(tmp[1]);
		String day=tmp[2];
		String year=tmp[5];
		String a=day+"/"+month+"/"+year;
		return a;
	}
	
	
	private static String SetMonth(String mes) {
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
		else if(m.equals("outobro") || m.equals("out"))
			return "10";
		else if(m.equals("novembro") || m.equals("nov"))
			return "11";
		else 
			return "12";
			
	}
	
	
	
	
	
	
}
