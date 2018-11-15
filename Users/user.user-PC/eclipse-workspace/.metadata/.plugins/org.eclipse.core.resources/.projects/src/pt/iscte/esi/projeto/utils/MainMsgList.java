package pt.iscte.esi.projeto.utils;


import pt.iscte.esi.projeto.form.models.Message;

/**
 * Class that defines list in Main Window.
 * this matrix only supports 100 lines and 4 rows max.
 * 
 * @author jose.f.santos
 *
 */
public class MainMsgList {
	
	private String[] headers;
	private String[][] msgMatrix;
	private int counter;
	
	/**
	 * Constructor
	 */
	public MainMsgList() {
		counter = 0;
	}
	

	public MainMsgList(String[] headers,String[][] msgMatrix) {
		this.headers = headers;
		this.msgMatrix = msgMatrix;
		
		counter = 0;
		
		for(int i=0;i<msgMatrix.length;i++) {
			for(int j=0; j< msgMatrix[i].length;j++) {
				if(msgMatrix[i][j] != null){
					counter++;
					break;
				}
			}
		}
	}
	
	/**
	 * get method for msgMatrix
	 * 
	 * @return Object[][]
	 */
	public String[][] getMsgMatrix() {
		return msgMatrix;
	}
	
	/**
	 * set method for msgMatrix
	 * 
	 * @param msgMatrix as String[][]
	 */
	public void setMsgMatrix(String[][] msgMatrix) {
		this.msgMatrix = msgMatrix;
		
		counter = 0;
		
		for(int i=0;i<msgMatrix.length;i++) {
			for(int j=0; j< msgMatrix[i].length;j++) {
				if(msgMatrix[i][j] != null){
					counter++;
					break;
				}
			}
		}
	}
	
	/**
	 * get method for headers
	 * 
	 * @return String[]
	 */
	public String[] getHeaders() {
		return headers;
	}

	/**
	 * set method for headers
	 * 
	 * @param headers as String[]
	 */
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}
	
	/**
	 * get for stack counter
	 * 
	 * @return counter as int
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Add a message to msg
	 * 
	 * @param msg as Message
	 */
	public void addMessage(Message msg) {
		if(counter >= 100) {
			return;
		}
		else {
			for(int i=0; i<100; i++) {
				for(int j=0; j<msgMatrix[i].length;j++) {
					if(msgMatrix[i][j] == null) {
						switch (j) {
							case 0:
								msgMatrix[i][j] = msg.getTime();
								break;
							case 1:
								msgMatrix[i][j] = msg.getChannel(); 
								break;
							case 2:
								msgMatrix[i][j] = msg.getSender();
								break;
							case 3:
								msgMatrix[i][j] = msg.getMessage();
								break;
							default:
								break;
							}
						counter++;
						return;
					}
				}
			}
		}
	}

}
