package src.pt.iscte.esi.projeto.form.models;

import java.security.Timestamp;

public class Message {
	
	private String message;
	private String sender;
	private Timestamp time;
	
	public Message() {}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;		
	}

	public String getTime() {
		String stringTime = time.toString();
		return stringTime;
	}

	public void setTime(Timestamp time) {
		this.time = time;
		
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;		
	}

}
