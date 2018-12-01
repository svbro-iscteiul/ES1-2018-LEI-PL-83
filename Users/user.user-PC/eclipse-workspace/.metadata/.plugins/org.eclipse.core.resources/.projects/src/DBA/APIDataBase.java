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

	public void WriteFacebook(List<Message> list) throws FileNotFoundException{

		File file = new File("files/Facebook.txt"); 
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		for(Message m: list)
		{
			
			String message=m.getChannel()+"&&&&&"+m.getSender()+"&&&&&"+m.getTime()+"&&&&&"+(m.getMessage().replaceAll("\\n", "+"));
			
			writer.println(message);
		}

		writer.close();

	}
	
	public List<Message> ReadFacebook() throws IOException {

		File file = new File("files/Facebook.txt"); 
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
	public void WriteEmail(List<Message> list) throws FileNotFoundException{

		File file = new File("files/Gmail.txt"); 
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		int i=0;
		for(Message m: list)
		{
			String message=m.getChannel()+"&&&&&"+m.getSender()+"&&&&&"+m.getTime()+"&&&&&"+(m.getMessage().replaceAll("\\n", "+"));
			
			writer.println(message);
		}

		writer.close();

	}
	
	public List<Message> ReadEmail() throws IOException {

		File file = new File("files/Gmail.txt"); 
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
	public void WriteTwitter(List<Message> list) throws FileNotFoundException{

		File file = new File("files/Twitter.txt"); 
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		int i=0;
		for(Message m: list)
		{
			String message=m.getChannel()+"&&&&&"+m.getSender()+"&&&&&"+m.getTime()+"&&&&&"+m.getMessage();
			
			writer.println(message);
		}

		writer.close();

	}
	
	public List<Message> ReadTwitter() throws IOException {

		File file = new File("files/Twitter.txt"); 
		List<Message> list = new ArrayList<Message>();
		Scanner sc = new Scanner(file); 

		while (sc.hasNextLine()) {
			String st=sc.nextLine();
			String[] tmp = st.split("&&&&&");
			System.out.println(tmp.length);
			Message m = new Message();
			m.setChannel(tmp[0]);
			m.setSender(tmp[1]);
			m.setTime(tmp[2]);
			m.setMessage(tmp[3]);
			System.out.println(tmp[0] + " " + tmp[1]+ " "+ tmp[2] + " " +tmp[3]);
			list.add(m);
		} 
		sc.close();

		return list;
	}




}
