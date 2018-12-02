package pt.iscte.esi.projeto.form.models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import DBA.APIDataBase;

public class FacebookAPITest {
	public static void main(String[] args)  {

		FacebookAPI f = new FacebookAPI();
		try {
			f.postMessage("asdasd");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		

		
	}


}
