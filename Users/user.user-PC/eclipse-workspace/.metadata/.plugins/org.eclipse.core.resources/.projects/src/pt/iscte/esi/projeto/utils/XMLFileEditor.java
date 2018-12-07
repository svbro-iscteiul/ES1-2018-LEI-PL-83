package pt.iscte.esi.projeto.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

/**
 * XML Editor class, all methods for editing or gather information from
 * configs.xml are here.
 *
 */
public class XMLFileEditor {
	/*
	 * This class registers the user in the app if the username doesn't exist it
	 * will save in the config.xml file the password, email and username and returns
	 * "New Username Added" If the username already exists it will return
	 * "Username Taken" if the email already exists it will return "Email Taken"
	 */

	/**
	 * Used to register user in the xml file.
	 * 
	 * @param Username as String
	 * @param Email    as String
	 * @param Password as String
	 * @return "Error", "Username Taken" or "Email Taken" if an error is found. If
	 *         the user is added the returns "New Username Added"
	 */
	public String SignIn(String Username, String Email, String Password) {
		String s = ReadFile(Username, Email, Password);
		if (!s.equals("Error"))
			if (s.equals("Username Not Found")) {
				try {
					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(
							"Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");

					// Element root = document.getDocumentElement();

					Element newUser = document.createElement("User_Login");

					Element name = document.createElement("Username");
					name.appendChild(document.createTextNode(Username));
					newUser.appendChild(name);

					Element email = document.createElement("Password");
					email.appendChild(document.createTextNode(Password));
					newUser.appendChild(email);

					Element password = document.createElement("Email");
					password.appendChild(document.createTextNode(Email));
					newUser.appendChild(password);

					document.getElementsByTagName("Login").item(0).appendChild(newUser);

					DOMSource source = new DOMSource(document);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(
							"Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
					transformer.transform(source, result);
					return "New Username Added";
				} catch (Exception e) {
					e.printStackTrace();
					return "Error";
				}
			} else if (s.equals("Password Correct") || s.equals("Password Incorrect"))
				return "Username Taken";
			else {
				return "Email Taken";
			}
		return "Error";
	}


	/*
	 * Code based on
	 * https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/ returns
	 * a string depending on the follow options: If username exists and password is
	 * correct returns "Password Correct" If username exists but the password is
	 * wrong returns: "Password Incorrect" If the username does not exist returns:
	 * "Username Not Found" If the email exists returns: Email Taken If an error
	 * occurs returns: "Error"
	 */

	/**
	 * Used to read xml file. Username is Found if user exists the password given
	 * match.
	 * 
	 * @param Username as String
	 * @param Email    as String
	 * @param Password as String
	 * @return If error returns "Error", "Username Not Found", "Password Incorrect"
	 *         or "Email Taken". If all is correct then "Email Taken".
	 */
	public String ReadFile(String Username, String Email, String Password) {
		try {
			File fXmlFile = new File(
					"Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("User_Login");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if (eElement.getElementsByTagName("Email").item(0).getTextContent().equals(Email))
						return "Email Taken";
					if (eElement.getElementsByTagName("Username").item(0).getTextContent().equals(Username)) {
						if (eElement.getElementsByTagName("Password").item(0).getTextContent().equals(Password))
							return "Password Correct";
						else
							return "Password Incorrect";
					}

				}
			}
			return "Username Not Found";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}

	
	/**
	 * Retrieve the xml content and then set the content in textArea
	 * 
	 * @param textArea as JTextArea
	 */
	public void LoadXMLContentRAW(JTextArea textArea) {
		try {
			File fXmlFile = new File(
					"Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(fXmlFile);

			String out = "";

			Transformer tform = TransformerFactory.newInstance().newTransformer();
			tform.setOutputProperty(OutputKeys.INDENT, "yes");
			tform.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(document);
			StringWriter strWriter = new StringWriter();
			StreamResult result = new StreamResult(strWriter);

			tform.transform(source, result);

			textArea.setText(strWriter.getBuffer().toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Replaces the xml content with the text in the JTextArea
	 * 
	 * @param textArea as JTextArea
	 */
	public void SaveXMLContent(JTextArea textArea) {
		try {
			File fXmlFile = new File(
					"Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
			BufferedWriter bw = new BufferedWriter(new FileWriter(fXmlFile));
			bw.write(textArea.getText());
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Add account information in XML
	 * @param ConsumerKey
	 * @param ConsumerSecret
	 * @param AccessToken
	 * @param AccessTokenSecret
	 */
	public void AddAcountsForTwitter(String ConsumerKey,String ConsumerSecret,String AccessToken, String AccessTokenSecret)
	{
		try {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse("Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
				
				if(document.getElementsByTagName("Twitter").item(0).getChildNodes().getLength()!=0) {
					document.getElementsByTagName("Twitter").item(0).removeChild(document.getElementsByTagName("Twitter").item(0).getFirstChild());
				}
				Element newUser = document.createElement("User_Twitter");

				Element CK = document.createElement("ConsumerKey");
				CK.appendChild(document.createTextNode(ConsumerKey));
				newUser.appendChild(CK);

				Element CS = document.createElement("ConsumerSecret");
				CS.appendChild(document.createTextNode(ConsumerSecret));
				newUser.appendChild(CS);

				Element AT = document.createElement("AccessToken");
				AT.appendChild(document.createTextNode(AccessToken));
				newUser.appendChild(AT);
				
				Element ATS = document.createElement("AccessTokenSecret");
				ATS.appendChild(document.createTextNode(AccessTokenSecret));
				newUser.appendChild(ATS);
				
				document.getElementsByTagName("Twitter").item(0).appendChild(newUser);

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult("Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
				transformer.transform(source, result);			
				} catch (Exception e) {
					e.printStackTrace();
				}
		
		
	}
	
	/**
	 * 
	 */
	public void AddAcountsForFacebook(String token,String AppId, String AppSecret)
	{
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse("Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
			
			if(document.getElementsByTagName("Facebook").item(0).getChildNodes().getLength()!=0) {
				document.getElementsByTagName("Facebook").item(0).removeChild(document.getElementsByTagName("Facebook").item(0).getFirstChild());
			}
			Element newUser = document.createElement("User_Facebook");

			Element Userfacebook = document.createElement("Token");
			Userfacebook.appendChild(document.createTextNode(token));
			newUser.appendChild(Userfacebook);

			Element Appid = document.createElement("AppId");
			Appid.appendChild(document.createTextNode(AppId));
			newUser.appendChild(Appid);

			Element Appsecret = document.createElement("AppSecret");
			Appsecret.appendChild(document.createTextNode(AppSecret));
			newUser.appendChild(Appsecret);
			
			document.getElementsByTagName("Facebook").item(0).appendChild(newUser);

			DOMSource source = new DOMSource(document);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			StreamResult result = new StreamResult("Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
			transformer.transform(source, result);			
		} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	/**
	 * Add Email account information 
	 * @param usermail
	 * @param password
	 */
	public void AddAcountsForEmail(String usermail,String password)
	{
		try {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse("Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
				
				if(document.getElementsByTagName("Gmail").item(0).getChildNodes().getLength()!=0) {
					document.getElementsByTagName("Gmail").item(0).removeChild(document.getElementsByTagName("Gmail").item(0).getFirstChild());
				}
				Element newUser = document.createElement("User_Email");

				Element Usermail = document.createElement("Mail");
				Usermail.appendChild(document.createTextNode(usermail));
				newUser.appendChild(Usermail);

				Element Pass = document.createElement("Password");
				Pass.appendChild(document.createTextNode(password));
				newUser.appendChild(Pass);

			
				
				document.getElementsByTagName("Gmail").item(0).appendChild(newUser);

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult("Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
				transformer.transform(source, result);			
			} catch (Exception e) {
					e.printStackTrace();
				}
		
		
	}
	
	public String getTwitterTokens(){
		try {
			File fXmlFile = new File(
					"Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			if(doc.getElementsByTagName("Twitter").item(0).getChildNodes().getLength()!=0) {
				Node Twitter=doc.getElementsByTagName("User_Twitter").item(0);
				NodeList TwitterUser=Twitter.getChildNodes();
				String tmp = "";
				for(int i=0;i<TwitterUser.getLength();i++)
					tmp+=TwitterUser.item(i).getTextContent()+"BREAKHERE";
				return tmp;
			}
			else
				return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	public String getFacebookTokens(){
		try {
			File fXmlFile = new File(
					"Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			if(doc.getElementsByTagName("Facebook").item(0).getChildNodes().getLength()!=0) {
				Node Twitter=doc.getElementsByTagName("User_Facebook").item(0);
				NodeList TwitterUser=Twitter.getChildNodes();
				String tmp = "";
				for(int i=0;i<TwitterUser.getLength();i++)
					tmp+=TwitterUser.item(i).getTextContent()+"BREAKHERE";
				return tmp;
			}
			else
				return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
	public String getEmailTokens(){
		try {
			File fXmlFile = new File(
					"Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			//NodeList nList = doc.getElementsByTagName("Twitter");


			if(doc.getElementsByTagName("Gmail").item(0).getChildNodes().getLength()!=0) {
				Node Twitter=doc.getElementsByTagName("User_Email").item(0);
				NodeList TwitterUser=Twitter.getChildNodes();
				String tmp = "";
				for(int i=0;i<TwitterUser.getLength();i++)
					tmp+=TwitterUser.item(i).getTextContent()+"BREAKHERE";
				return tmp;
			}
			else
				return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}

	
	
	
	// (elsa) 18_11_18 - equivalente ao read file, mas para os tokens do twitter (repete codigo - alterar dps)
	/**
	 * Read File Tokens from Twitter
	 * @param AuthConsumerSecret
	 * @param AuthConsumerKey
	 * @param AuthAccessToken
	 * @param AuthAccesTokenSecret
	 * @return
	 */
	public String readFileTokTwiter(String AuthConsumerSecret, String AuthConsumerKey, String AuthAccessToken,
			String AuthAccesTokenSecret) {
		try {
			File fXmlFile = new File(
					"Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("User_Twitter_Token");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if (eElement.getElementsByTagName("AuthConsumerSecret").item(0).getTextContent().equals(AuthConsumerSecret))
						return "AuthConsumerSecret Taken";
					if (eElement.getElementsByTagName("AuthConsumerKey").item(0).getTextContent().equals(AuthConsumerKey))
						return "AuthConsumerKey Taken";
					if (eElement.getElementsByTagName("AuthAccessToken").item(0).getTextContent().equals(AuthAccessToken))
						return "AuthAccessToken Taken";
					if (eElement.getElementsByTagName("AuthAccesTokenSecret").item(0).getTextContent().equals(AuthAccesTokenSecret))
						return "AuthAccesTokenSecret Taken";
				}
			}
			return "Tokens not yet inserted";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}



	// (Elsa)  18_11_18 metodo semelhante ao SignIn para os token do twitter (melhorar dps para evitar codigo repetido)
	/**
	 * Add twitter tokens 
	 * @param AuthConsumerSecret
	 * @param AuthConsumerKey
	 * @param AuthAccessToken
	 * @param AuthAccesTokenSecret
	 * @return
	 */
	public String addTwittwerTokens(String AuthConsumerSecret, String AuthConsumerKey, String AuthAccessToken,
			String AuthAccesTokenSecret) {
		String st = readFileTokTwiter(AuthConsumerSecret, AuthConsumerKey, AuthAccessToken, AuthAccesTokenSecret);
		
	
		if (!st.equals("Error")) {
			if (st.equals("Tokens not yet inserted")) {
				try {
					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(
							"Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");

//					 Element root = document.createElement("Twitter_Tokens"); //adiciona uma raíz ?
//			         document.appendChild(root);
//		            

					Element newUser = document.createElement("User_Twitter_Token");
//					root.appendChild(newUser);
					
					
					Element authConsumerSecret = document.createElement("AuthConsumerSecret");
					authConsumerSecret.appendChild(document.createTextNode(AuthConsumerSecret));
					newUser.appendChild(authConsumerSecret);

					Element authConsumerKey = document.createElement("AuthConsumerKey");
					authConsumerKey.appendChild(document.createTextNode(AuthConsumerKey));
					newUser.appendChild(authConsumerKey);

					Element authAccessToken = document.createElement("AuthAccessToken");
					authAccessToken.appendChild(document.createTextNode(AuthAccessToken));
					newUser.appendChild(authAccessToken);
					
					Element authAccesTokenSecret = document.createElement("AuthAccesTokenSecret");
					authAccesTokenSecret.appendChild(document.createTextNode(AuthAccesTokenSecret));
					newUser.appendChild(authAccesTokenSecret);

					document.getElementsByTagName("Twitter").item(0).appendChild(newUser);

					DOMSource source = new DOMSource(document);

					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					StreamResult result = new StreamResult(
							"Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
					transformer.transform(source, result);
					return "New Twitter Tokens Added";
				} catch (Exception e) {
					e.printStackTrace();
					return "Error";
				}
			}
			else {
				return "Tokens already exists";
			}
		}
		return "Error";
	}


	/**
	 * Used to return email and password from xml
	 * 
	 * @return String[] with email and password
	 */
	public String[] getEmail() {
		String[] mailcredentials = new String[2];
		try {
			File fXmlFile = new File(
					"Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("User_Email");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if (eElement.getElementsByTagName("Mail").item(0).getTextContent() != null)
						mailcredentials[0] = eElement.getElementsByTagName("Mail").item(0).getTextContent();
					if (eElement.getElementsByTagName("Password").item(0).getTextContent() != null)
						mailcredentials[1] = eElement.getElementsByTagName("Password").item(0).getTextContent();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailcredentials;
	}
	
	
	/**
	 * Used to return twitter tokens from xml
	 * 
	 * @return String[] with tokens
	 */
	public String[] getTwitter() {
		String[] mailcredentials = new String[4];
		try {
			File fXmlFile = new File(
					"Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("User_Twitter");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if (eElement.getElementsByTagName("ConsumerKey").item(0).getTextContent() != null)
						mailcredentials[0] = eElement.getElementsByTagName("ConsumerKey").item(0).getTextContent();
					if (eElement.getElementsByTagName("ConsumerSecret").item(0).getTextContent() != null)
						mailcredentials[1] = eElement.getElementsByTagName("ConsumerSecret").item(0).getTextContent();
					if (eElement.getElementsByTagName("AccessToken").item(0).getTextContent() != null)
						mailcredentials[2] = eElement.getElementsByTagName("AccessToken").item(0).getTextContent();
					if (eElement.getElementsByTagName("AccessTokenSecret").item(0).getTextContent() != null)
						mailcredentials[3] = eElement.getElementsByTagName("AccessTokenSecret").item(0).getTextContent();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailcredentials;
	}
	
	
	/**
	 * Used to return twitter tokens from xml
	 * 
	 * @return String[] with tokens
	 */
	public String[] getFacebook() {
		String[] mailcredentials = new String[3];
		try {
			File fXmlFile = new File(
					"Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("User_Facebook");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if (eElement.getElementsByTagName("Token").item(0).getTextContent() != null)
						mailcredentials[0] = eElement.getElementsByTagName("Token").item(0).getTextContent();
					if (eElement.getElementsByTagName("AppId").item(0).getTextContent() != null)
						mailcredentials[1] = eElement.getElementsByTagName("AppId").item(0).getTextContent();
					if (eElement.getElementsByTagName("AppSecret").item(0).getTextContent() != null)
						mailcredentials[2] = eElement.getElementsByTagName("AppSecret").item(0).getTextContent();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailcredentials;
	}
}