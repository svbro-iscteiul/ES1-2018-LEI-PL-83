package src.pt.iscte.esi.projeto.form.models;

import java.security.Timestamp;

public class TwitterMsg implements Message {
	
	private String message;
	private String sender;
	private Timestamp time;
	
	public TwitterMsg() {}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;		
	}

	@Override
	public String getTime() {
		String stringTime = time.toString();
		return stringTime;
	}

	@Override
	public void setTime(Timestamp time) {
		this.time = time;
		
	}

	@Override
	public String getSender() {
		return sender;
	}

	@Override
	public void setSender(String sender) {
		this.sender = sender;		
	}

}
