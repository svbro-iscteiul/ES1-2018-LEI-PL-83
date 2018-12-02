package DBA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
			String message=m.getChannel()+"BREAKHERE"+m.getSender()+"BREAKHERE"+m.getTime()+"BREAKHERE"+m.getMessage()+"ENDMESSAGE";

			writer.println(message);
		}

		writer.close();

	}

	public List<Message> ReadFacebook() throws IOException {

		File file = new File("files/Facebook.txt"); 
		List<Message> list = new ArrayList<Message>();
		Scanner sc = new Scanner(file); 
		String st="";
		while (sc.hasNextLine()) {
			st+=sc.nextLine();
		}	
		sc.close();
		String[] tmp = st.split("ENDMESSAGE");
		for(int i=0;i<tmp.length;i++)
		{
			String[] tmp1=tmp[i].split("BREAKHERE");
			Message m = new Message();
			m.setChannel(tmp1[0]);
			m.setSender(tmp1[1]);
			m.setTime(tmp1[2]);
			m.setMessage(tmp1[3]);
			list.add(m);
		}


		return list;
	}
	public void WriteEmail(List<Message> list) throws FileNotFoundException{

		File file = new File("files/Gmail.txt"); 
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		for(Message m: list)
		{
			String message=m.getChannel()+"BREAKHERE"+m.getSender()+"BREAKHERE"+m.getTime()+"BREAKHERE"+m.getMessage()+"ENDMESSAGE";

			writer.println(message);
		}

		writer.close();

	}

	public List<Message> ReadEmail() throws IOException {

		File file = new File("files/Gmail.txt"); 
		List<Message> list = new ArrayList<Message>();
		Scanner sc = new Scanner(file); 
		String st="";
		while (sc.hasNextLine()) {
			st+=sc.nextLine();
		}	
		sc.close();
		String[] tmp = st.split("ENDMESSAGE");
		for(int i=0;i<tmp.length;i++)
		{
			String[] tmp1=tmp[i].split("BREAKHERE");
			Message m = new Message();
			m.setChannel(tmp1[0]);
			m.setSender(tmp1[1]);
			m.setTime(tmp1[2]);
			m.setMessage(tmp1[3]);
			list.add(m);
		}


		return list;
	}
	public void WriteTwitter(List<Message> list) throws FileNotFoundException{

		File file = new File("files/Twitter.txt"); 
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		for(Message m: list)
		{
			String message=m.getChannel()+"BREAKHERE"+m.getSender()+"BREAKHERE"+m.getTime()+"BREAKHERE"+m.getMessage()+"ENDMESSAGE";

			writer.println(message);
		}

		writer.close();

	}

	public List<Message> ReadTwitter() throws IOException {

		File file = new File("files/Twitter.txt"); 
		List<Message> list = new ArrayList<Message>();
		Scanner sc = new Scanner(file); 
		String st="";
		while (sc.hasNextLine()) {
			st+=sc.nextLine();
		}	
		sc.close();
		String[] tmp = st.split("ENDMESSAGE");
		for(int i=0;i<tmp.length;i++)
		{
			String[] tmp1=tmp[i].split("BREAKHERE");
			Message m = new Message();
			m.setChannel(tmp1[0]);
			m.setSender(tmp1[1]);
			m.setTime(tmp1[2]);
			m.setMessage(tmp1[3]);
			list.add(m);
		}


		return list;
	}




}
