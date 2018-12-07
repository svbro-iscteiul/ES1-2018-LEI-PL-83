package pt.iscte.esi.projeto.form.models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import DBA.APIDataBase;
/**
 * Facebook API class, used only for testing
 * 
 * @author Sérgio Vaz
 */
public class FacebookAPITest {
	public static void main(String[] args)  {

		FacebookAPI f = new FacebookAPI();
		try {
			for(Message m:f.getPosts())
				System.out.println(m.getMessage());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		

		
	}


}
