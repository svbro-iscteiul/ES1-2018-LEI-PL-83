package DBA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iscte.esi.projeto.form.models.Message;

public class APIDataBase {


	public void WriteEmail(List<Message> list) throws FileNotFoundException{
		URL path = APIDataBase.class.getResource("Gmail.txt");
		File file = new File(path.getFile()); 
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		for(Message m: list)
		{
			String message=m.getChannel()+"&&&&&"+m.getSender()+"&&&&&"+m.getTime()+"&&&&&"+m.getMessage();
			System.out.println(message);
			writer.println(message);
		}

		writer.close();
	}
	public List<Message> ReadEmail() throws IOException {
		URL path = APIDataBase.class.getResource("Gmail.txt");
		File file = new File(path.getFile()); 
		List<Message> list = new ArrayList<Message>();
		Scanner sc = new Scanner(file); 
		while (sc.hasNextLine()) {
			String st=sc.nextLine();
			String[] tmp = st.split("&&&&&");
			Message m = new Message();
			m.setChannel(tmp[0]);
			m.setSender(tmp[1]);
			m.setTime(tmp[2]);
			m.setMessage(tmp[3]);
			list.add(m);
		} 
		sc.close();

		return list;
	}
	
	public void WriteFacebook(List<Message> list) throws FileNotFoundException{
		URL path = APIDataBase.class.getResource("Facebook.txt");
		File file = new File(path.getFile()); 
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		int i=0;
		System.out.println(list.size());
		for(Message m: list)
		{
			System.out.println(i);
			i++;
			String message=m.getChannel()+"&&&&&"+m.getSender()+"&&&&&"+m.getTime()+"&&&&&"+m.getMessage();
			System.out.println(message);
			
			writer.println(message);
		}

		writer.close();

	}
	
	public List<Message> ReadFacebook() throws IOException {
		URL path = APIDataBase.class.getResource("Facebook.txt");
		File file = new File(path.getFile()); 
		List<Message> list = new ArrayList<Message>();
		Scanner sc = new Scanner(file); 

		while (sc.hasNextLine()) {
			String st=sc.nextLine();
			String[] tmp = st.split("&&&&&");
			Message m = new Message();
			m.setChannel(tmp[0]);
			m.setSender(tmp[1]);
			m.setTime(tmp[2]);
			m.setMessage(tmp[3]);
			list.add(m);
		} 
		sc.close();

		return list;
	}

	public void WriteTwitter(List<Message> list) throws FileNotFoundException
	{
		URL path = APIDataBase.class.getResource("Twitter.txt");
		File file = new File(path.getFile()); 
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		for(Message m: list)
		{
			String message=m.getChannel()+"&&&&&"+m.getSender()+"&&&&&"+m.getTime()+"&&&&&"+m.getMessage();
			System.out.println(message);
			writer.println(message);
		}

		writer.close();
	}

	public List<Message> ReadTwitter() throws IOException 
	{
		URL path = APIDataBase.class.getResource("Twitter.txt");
		File file = new File(path.getFile()); 
		List<Message> list = new ArrayList<Message>();
		Scanner sc = new Scanner(file); 

		while (sc.hasNextLine()) {
			String st=sc.nextLine();
			String[] tmp = st.split("&&&&&");
			Message m = new Message();
			m.setChannel(tmp[0]);
			m.setSender(tmp[1]);
			m.setTime(tmp[2]);
			m.setMessage(tmp[3]);
			list.add(m);
		} 
		sc.close();

		return list;
		} 





}
