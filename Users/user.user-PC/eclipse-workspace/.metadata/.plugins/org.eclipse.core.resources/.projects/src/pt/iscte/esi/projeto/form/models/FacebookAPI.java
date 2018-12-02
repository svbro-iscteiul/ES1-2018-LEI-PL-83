package pt.iscte.esi.projeto.form.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.Comment;
import com.restfb.types.Comments;
import com.restfb.types.FacebookType;
import com.restfb.types.MessageTag;
import com.restfb.types.Post;
import com.restfb.types.User;

/**
 * FacebookAPI class
 *
 *
 */
public class FacebookAPI {
	private ArrayList<Message> posts = new ArrayList<Message>();
	private static String Token=
			"EAAD0JjeESBwBADc45Ai73TqZCQi00REggSntendJSgk7jGdZCaT7jYLKqG9Q6NoVMTIze0Ss6bf9ID5XFaDDCEZBYFOpJyTKBlzMCirznZCXlgQ8qL2DZCuVA3R6G49SYio5fE7vZBuQqXDZAP71L0PzY7EC7t1ANVP2cu1xj9wr80vQc0JLFlfFxz7wVEmi7lt9Y51FTymRAZDZD";
	private static String AppId="268444977350684";
	private static String AppSecret="75a7638dfa1a64929a408b3955681d0a";


	/**
	 * @author svbro-iscteiul
	 * @param fbmessage
	 * @throws Exception
	 * Posts fbmessage has a comment on facebook 
	 */
	public void postMessage(String fbmessage) throws Exception{

		String pageID = "305848090029032";

		FacebookClient fbClient = new DefaultFacebookClient(Token);

		FacebookType publishMessageResponse = fbClient.publish(pageID +"/feed", FacebookType.class, Parameter.with("message", fbmessage));


	}

	/**
	 * @author svbro-iscteiul
	 * @return
	 * Gets the post from facebook and adds them to the posts arraylist
	 */
	public ArrayList<Message> getPosts() {

		String accessToken =Token;
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		AccessToken extendedAccessToken4 = fbClient.obtainExtendedAccessToken(AppId,AppSecret);

		Connection<Post> result = fbClient.fetchConnection("me/posts",Post.class);
		//me/posts/?fields=comments
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
	/**
	 * @author svbro-iscteiul
	 * @param date
	 * @return
	 * modifies the string to create a date, e.g Thu Nov 08 16:06:15 GMT 2018 becomes 08/11/2018
	 */
	private String setDate(String date) {
		
		String[] tmp = date.split(" ");
		String month=SetMonth(tmp[1]);
		String day=tmp[2];
		String year=tmp[5];
		String a=day+"/"+month+"/"+year;
		return a;
	}

	/**
	 * @author svbro
	 * @param string 
	 * @return string
	 * Returns the number of the mouth
	 * */
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






}
