package pt.iscte.esi.projeto.form.models;

import java.util.List;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.Post;
import com.restfb.types.User;

//restfb
public class FacebookMain {
	
	/**
	 * Facebook API Main
	 * @param args
	 */
	public static void main(String[] args) {
		/* 
		 * Facebook API Tutorials in Java # 1 | Setup Development Environment 
		 * https://www.youtube.com/watch?v=m14hYs1T3FA&index=1&list=PLYPFxrXyK0BwiXNe09hTPjFqYbsWv8gxb
		 */
		/* 
		 * Facebook API Tutorials in Java # 2 | Get User Access Token
		 * https://www.youtube.com/watch?v=GwbO_PdwK_4&index=2&list=PLYPFxrXyK0BwiXNe09hTPjFqYbsWv8gxb
		 */
		String accessToken2 = "EAAD0JjeESBwBAHMZAmdkKgkZCnDiJxu1LLnZAONofCxZBlXZBMPnEc1L8TOZA6VjgvW5j7L7wxlAzN5N3UR818hnnO3tzlFn5kDHz4zN7PwIRrl7x6VKdyHfDlPAR7t7IMzDWINbGXo76fHh3xgsMZBHhF0ZCgTbsmGqSal9UwcbRgFpZA7y9VG71Yn9BIQ2EZArwivpUpLrGDOZAWenEEu6wKF";
		FacebookClient fbClient2 = new DefaultFacebookClient(accessToken2);
		User me2 = fbClient2.fetchObject("me", User.class);
		System.out.println("Facebook:");
		System.out.println("Id: " + me2.getId());
		System.out.println("Name: " + me2.getName());

		/* 
		 * Facebook API Tutorials in Java # 4 | Create Your Own Fb APP & Extend User Access Token  
		 * https://www.youtube.com/watch?v=qFZazZ1JXsM&list=PLYPFxrXyK0BwiXNe09hTPjFqYbsWv8gxb&index=5
		 */
		String accessToken4 = "EAAD0JjeESBwBAHMZAmdkKgkZCnDiJxu1LLnZAONofCxZBlXZBMPnEc1L8TOZA6VjgvW5j7L7wxlAzN5N3UR818hnnO3tzlFn5kDHz4zN7PwIRrl7x6VKdyHfDlPAR7t7IMzDWINbGXo76fHh3xgsMZBHhF0ZCgTbsmGqSal9UwcbRgFpZA7y9VG71Yn9BIQ2EZArwivpUpLrGDOZAWenEEu6wKF";
		
		FacebookClient fbClient4 = new DefaultFacebookClient(accessToken4);
		AccessToken extendedAccessToken4 = fbClient4.obtainExtendedAccessToken("268444977350684","75a7638dfa1a64929a408b3955681d0a");
		
		System.out.println("ExtendedAccessToken: "+extendedAccessToken4.getAccessToken());
		System.out.println("Expires: " + extendedAccessToken4.getExpires());

		/* 
		 * Facebook API Tutorials in Java # 5 | Get User Timeline Posts
		 * https://www.youtube.com/watch?v=wiFif4gOdFE&index=6&list=PLYPFxrXyK0BwiXNe09hTPjFqYbsWv8gxb
		*/ 
		String accessToken5 ;//= "EAAGZBEccjciEBAJ37ZAIbHKiL1Mo1HHex2pQTcs41dq8azfBvFGgt4eGgKBq12kSssOof51FKO0niKu7AaVKs3dy8W1ilqp4xcjFD1F9mmjJpVyeDnZAffUXRfh7zXL06BuSwQtfHMJbmJ079qCnkT844brHx966cz73JZBZBFy2Bv1rWu7T1rQddZCVpxywZCO6lDxoWDk2gZDZD";
		accessToken5 = "EAAD0JjeESBwBAHMZAmdkKgkZCnDiJxu1LLnZAONofCxZBlXZBMPnEc1L8TOZA6VjgvW5j7L7wxlAzN5N3UR818hnnO3tzlFn5kDHz4zN7PwIRrl7x6VKdyHfDlPAR7t7IMzDWINbGXo76fHh3xgsMZBHhF0ZCgTbsmGqSal9UwcbRgFpZA7y9VG71Yn9BIQ2EZArwivpUpLrGDOZAWenEEu6wKF";	
		FacebookClient fbClient5 = new DefaultFacebookClient(accessToken5);

		Connection<Post> result = fbClient5.fetchConnection("me/feed",Post.class,  Parameter.with("type", "post"));
		
		//Parameter.with("since", "<timestamp>"); //adicionei
		
//		Connection<Post> messages = fbClient.fetchConnection("search",
//		        Post.class,
//		            Parameter.with("q", keyword),
//		            Parameter.with("limit", limit),
//		            Parameter.with("type", "post"));
		
		System.out.println("\nPosts:");
		int counter5 = 0;
		int counterTotal = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "ISCTE"
				if (aPost.getMessage() != null && aPost.getMessage().contains("ISCTE") ) {  
					System.out.println("---- Post "+ counter5 + " ----");
					System.out.println("Id: "+"fb.com/"+aPost.getId());
					System.out.println("Message: "+aPost.getMessage());
					System.out.println("Created: "+aPost.getCreatedTime());
					counter5++;
				}
				counterTotal++;
			}
		}
		System.out.println("-------------\nNº of Results: " + counter5+"/"+counterTotal);		
	}
}
