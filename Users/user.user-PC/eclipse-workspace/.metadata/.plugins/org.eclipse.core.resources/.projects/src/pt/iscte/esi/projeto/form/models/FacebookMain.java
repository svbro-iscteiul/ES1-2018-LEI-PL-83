package pt.iscte.esi.projeto.form.models;

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
 * @author 
 *
 */
public class FacebookMain {
	public static String Token="EAAD0JjeESBwBAL8OQ9yVoX5tGOqpgQiWhMyoyOYmtka1QWCVAlxxRzi97QFeUyAxOOb7mnpvwYiId2n6lr58iyqZBinEckQvj4ZBVKLHCfGuF61r1OdQMBidrpC2MNgLmCc2AupdDRGIvjJCMVembyTgm9FdCDX0sp1bld1w5VNLk5zZCKQHp19Gfg5aSXna1qFwZCDzkbnwKuP4KZCwG";
	/**
	 * Facebook API Main
	 * @param args
	 */
	public static void main(String[] args) {
		
		String accessToken=Token;
		FacebookClient fbClient5 = new DefaultFacebookClient(accessToken);

		Connection<Post> result = fbClient5.fetchConnection("me/feed",Post.class,  Parameter.with("type", "post"));
		
		//Parameter.with("since", "<timestamp>"); 
		
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
