package pt.iscte.esi.projeto.form.models;

/**
 * Message model class
 * @author jose.f.santos
 *
 */
public class Message {
	
	private String message;
	private String sender;
	private String channel;
	private String time;
	
	/**
	 * Constructor
	 */
	public Message() {}
	
	/**
	 * Constructor
	 * 
	 * @param time as String
	 * @param channel as String
	 * @param sender as String
	 * @param message as String
	 */
	public Message(String time, String channel, String sender, String message) {
		this.time=time;
		this.channel=channel;
		this.sender=sender;
		this.message=message;
	}

	/**
	 * get method for getting message
	 * 
	 * @return message as String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * set method for setting message
	 * 
	 * @param message as String
	 */
	public void setMessage(String message) {
		this.message = message;		
	}

	/**
	 * get method for getting time
	 * 
	 * @return time as String
	 */
	public String getTime() {
		return time;
	}

	/**
	 * set method for time
	 * 
	 * @param time as String
	 */
	public void setTime(String time) {
		this.time = time;
		
	}

	/**
	 * get method for sender
	 * 
	 * @return sender as String
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * set method for sender
	 * 
	 * @param sender as String
	 */
	public void setSender(String sender) {
		this.sender = sender;		
	}

	/**
	 * get method for channel
	 * 
	 * @return channel as String
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * set method for channel
	 * 
	 * @param channel as String
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	

}
