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

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * XML Editor class, all methods for editing or gather information from configs.xml are here.
 * @author jose.f.santos
 *
 */
public class XMLFileEditor {
	/*
	 * This class registers the user in the app
	 * if the username doesn't exist it will save in the config.xml file the password, email and username and returns "New Username Added"
	 * If the username already exists it will return "Username Taken"
	 * if the email already exists it will return "Email Taken"
	 * */
	
	/**
	 * Used to register user in the xml file.
	 * 
	 * @param Username as String
	 * @param Email as String
	 * @param Password as String
	 * @return "Error", "Username Taken" or "Email Taken" if an error is found. If the user is added the returns "New Username Added"
	 */
	public String SignIn(String Username, String Email, String Password) 
	{
		String s=ReadFile(Username,Email,Password);
		if(!s.equals("Error"))
			if(s.equals("Username Not Found"))
			{
				try {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse("Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
				
				//Element root = document.getDocumentElement();
				
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
				
				document.getElementsByTagName("User_Login").item(0).appendChild(newUser);

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult("Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
				transformer.transform(source, result);
				return "New Username Added";
				} catch (Exception e) {
					e.printStackTrace();
					return "Error";
				}
			}
			else if(s.equals("Password Correct") || s.equals("Password Incorrect"))
				return "Username Taken";		
			else
			{
				return "Email Taken";
			}
		return "Error";
	}

	/*
	 * Code based on https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
	 * returns a string depending on the follow options:
	 * If username exists and password is correct returns "Password Correct"
	 * If username exists but the password is wrong returns: "Password Incorrect"
	 * If the username does not exist returns: "Username Not Found"
	 * If the email exists returns: Email Taken
	 * If an error occurs returns: "Error"
	 */
	
	/**
	 * Used to read xml file. Username is Found if user exists the password given match.
	 * 
	 * @param Username as String
	 * @param Email as String
	 * @param Password as String
	 * @return If error returns "Error", "Username Not Found", "Password Incorrect" or "Email Taken". If all is correct then "Email Taken".
	 */
	public String ReadFile(String Username,String Email,String Password) {
		try {
			File fXmlFile = new File("Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("User_Login");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);


				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if(eElement.getElementsByTagName("Email").item(0).getTextContent().equals(Email))
						return "Email Taken";
					if(eElement.getElementsByTagName("Username").item(0).getTextContent().equals(Username)){
						if(eElement.getElementsByTagName("Password").item(0).getTextContent().equals(Password)) 
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
			File fXmlFile = new File("Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");
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
			
		}catch(Exception e) {
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
			File fXmlFile = new File("Users/user.user-PC/eclipse-workspace/.metadata/.plugins/org.eclipse.core.resources/.projects/src/DBA/config.xml");		
			BufferedWriter  bw = new BufferedWriter (new FileWriter(fXmlFile));		
			bw.write(textArea.getText());
			bw.close();		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}