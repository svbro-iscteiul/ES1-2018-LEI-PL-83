package pt.iscte.esi.projeto.form.models;

import java.util.Comparator;

public class MessageComparator implements Comparator<Message>{

	@Override
	public int compare(Message o1, Message o2) {
		String temp1=o1.getTime();
	    String temp2=o2.getTime();
	    String[] date1=temp1.split("/");
	    String[] date2=temp2.split("/");
		if(Integer.parseInt(date1[2])==Integer.parseInt(date2[2]))
			if(Integer.parseInt(date1[1])==Integer.parseInt(date2[1])) 
				return Integer.parseInt(date2[0])-Integer.parseInt(date1[0]);
			else 
				return Integer.parseInt(date2[1])-Integer.parseInt(date1[1]);
		else
			return Integer.parseInt(date2[2])-Integer.parseInt(date1[2]);
	}
	

}
