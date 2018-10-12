package src.pt.iscte.esi.projeto.form;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


public class XMLFileEditor {
	/*
	 * This class registers the user in the app
	 * if the username doesn't exist it will save in the config.xml file the password, email and username and returns "New Username added"
	 * If the username already exists it will return "Username taken"
	 * if the email already exists it will return "Email taken"
	 * */
	public String SignIn(String Username, String Email, String Password) 
	{
		String s=ReadFile(Username,Email,Password);
		if(!s.equals("Error"))
			if(s.equals("Username not found"))
			{
				try {
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse("src/pt/iscte/esi/projeto/form/config.xml");
				Element root = document.getDocumentElement();
				Element newUser = document.createElement("User");

				Element name = document.createElement("Username");
				name.appendChild(document.createTextNode(Username));
				newUser.appendChild(name);

				Element email = document.createElement("Password");
				email.appendChild(document.createTextNode(Password));
				newUser.appendChild(email);

				Element password = document.createElement("Email");
				password.appendChild(document.createTextNode(Email));
				newUser.appendChild(password);
				root.appendChild(newUser);

				DOMSource source = new DOMSource(document);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				StreamResult result = new StreamResult("src/pt/iscte/esi/projeto/form/config.xml");
				transformer.transform(source, result);
				return "New Username added";
				} catch (Exception e) {
					e.printStackTrace();
					return "Error";
				}
			}
			else if(s.equals("Password Correct") || s.equals("Password Incorrect"))
				return "Username taken";		
			else
			{
				return "Email taken";
			}
		return "Error";
	}

	/**
	 * Code based on https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
	 * returns a string depending on the follow options:
	 * If username exists and password is correct returns "Password Correct"
	 * If username exists but the password is wrong returns: "Password Incorrect"
	 * If the username does not exist returns: "Username not found"
	 * If the email exists returns: Email taken
	 * If an error occurs returns: "Error"
	 */
	public String ReadFile(String Username,String Email,String Password) {
		try {

			File fXmlFile = new File("src/pt/iscte/esi/projeto/form/config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("User");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);


				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if(eElement.getElementsByTagName("Email").item(0).getTextContent().equals(Email))
						return "Email taken";
					if(eElement.getElementsByTagName("Username").item(0).getTextContent().equals(Username)){
						if(eElement.getElementsByTagName("Password").item(0).getTextContent().equals(Password)) 
							return "Password Correct";
						else
							return "Password Incorrect";
					}


				}
			}
			return "Username not found";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}

}