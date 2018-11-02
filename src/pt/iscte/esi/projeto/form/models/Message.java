package src.pt.iscte.esi.projeto.form.models;

import java.security.Timestamp;

public interface Message {
	
	public String getMessage();
	
	public void setMessage(String message);
	
	public String getTime();
	
	public void setTime(Timestamp time);
	
	public String getSender();
	
	public void setSender(String sender);

}
