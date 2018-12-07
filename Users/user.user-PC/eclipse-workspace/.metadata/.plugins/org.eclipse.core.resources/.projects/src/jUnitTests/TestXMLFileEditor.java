package jUnitTests;

import static org.junit.Assert.*;

import javax.swing.JTextArea;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.iscte.esi.projeto.utils.XMLFileEditor;

/**
 * Test class
 *
 */
public class TestXMLFileEditor {

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
		XMLFileEditor xmlFileEditor = new XMLFileEditor();
		
		assertEquals("Password Correct", xmlFileEditor.ReadFile("user", "", "123")); 
		assertEquals("Password Incorrect", xmlFileEditor.ReadFile("user", "", "12233"));
		assertEquals("Username Not Found", xmlFileEditor.ReadFile("usertestest", "", "12233"));
		assertEquals("Email Taken", xmlFileEditor.ReadFile("", "user", ""));
		
		assertEquals("Email Taken", xmlFileEditor.SignIn("UserTestef4f44f4" , "UserTest@ms.com", "UserTestPass"));
		assertEquals("Username Taken", xmlFileEditor.SignIn("UserTeste", "UserTesdeadwdt@ms.com", "UserTestPass"));
		
		JTextArea jTextArea = new JTextArea();
		
		xmlFileEditor.LoadXMLContentRAW(jTextArea);
		
		xmlFileEditor.SaveXMLContent(jTextArea);
		
		
		// não esquecer de apagar as entradas criadas em baixo
		
		assertEquals("New Username Added", xmlFileEditor.SignIn("NewTest" , "NewTest@ms.com", "NewTest"));
		
		assertEquals("New Twitter Tokens Added", xmlFileEditor.addTwittwerTokens("NewTest" , "NewTest", "NewTest", "NewTest"));
		
		assertEquals("AuthConsumerSecret Taken", xmlFileEditor.readFileTokTwiter("NewTest" , "test2testnew", "test2testnew", "test2testnew"));
		assertEquals("AuthConsumerKey Taken", xmlFileEditor.readFileTokTwiter("test2testnew" , "NewTest", "test2testnew", "test2testnew"));
		assertEquals("AuthAccessToken Taken", xmlFileEditor.readFileTokTwiter("test2testnew" , "test2testnew", "NewTest", "test2testnew"));
		assertEquals("AuthAccesTokenSecret Taken", xmlFileEditor.readFileTokTwiter("test2testnew" , "test2testnew", "test2testnew", "NewTest"));
		assertEquals("Tokens not yet inserted", xmlFileEditor.readFileTokTwiter("test2testnew" , "test2testnew", "test2testnew", "test2testnew"));

		
		xmlFileEditor.AddAcountsForTwitter("NewTest", "NewTest", "NewTest", "NewTest");
		xmlFileEditor.AddAcountsForEmail("NewTest", "NewTest");

	}

}
