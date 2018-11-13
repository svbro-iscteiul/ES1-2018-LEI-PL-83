package pt.iscte.esi.projeto.form.models;


import java.sql.Date;

public class Message {
	
	private String message;
	private String sender;
	private String date;
	
	public Message() {}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;		
	}

	public String getTime() {
		return date;
	}

	public void setTime(String date) {
		this.date = date;
		
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;		
	}

}
