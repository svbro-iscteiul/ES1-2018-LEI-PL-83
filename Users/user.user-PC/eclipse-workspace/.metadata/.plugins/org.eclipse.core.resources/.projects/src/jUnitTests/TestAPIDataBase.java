package jUnitTests;

import static org.junit.Assert.*;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DBA.APIDataBase;
import pt.iscte.esi.projeto.form.models.Message;

public class TestAPIDataBase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String expected = new String();
		String actual = new String();
		
		expected = "Test";
		
		List<Message> messageList = new ArrayList<>();
		List<Message> temp = new ArrayList<>();
		messageList.add(new Message(null, null, null, expected ));
		
		APIDataBase apiDataBase = new APIDataBase();
		
		try {
			apiDataBase.WriteFacebook(messageList);
			
			temp = apiDataBase.ReadFacebook();
			
			for (Message message : temp) {
				if(message.getMessage() != null) {
					actual = message.getMessage();
				}
			}
			assertEquals(expected, actual);
			
			
			apiDataBase.WriteEmail(messageList);
			
			temp = apiDataBase.ReadEmail();
			
			for (Message message : temp) {
				if(message.getMessage() != null) {
					actual = message.getMessage();
				}
			}
			assertEquals(expected, actual);
			
			
			apiDataBase.WriteTwitter(messageList);
			
			temp = apiDataBase.ReadTwitter();
			
			for (Message message : temp) {
				if(message.getMessage() != null) {
					actual = message.getMessage();
				}
			}
			assertEquals(expected, actual);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
