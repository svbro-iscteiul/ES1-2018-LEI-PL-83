package pt.iscte.esi.projeto.form.models;


public class Message {
	
	private String message;
	private String sender;
	private String channel;
	private String time;
	
	public Message() {}
	
	public Message(String time, String channel, String sender, String message) {
		this.time=time;
		this.channel=channel;
		this.sender=sender;
		this.message=message;
	}

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

	public void setTime(String time) {
		this.time = time;
		
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;		
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	

}
