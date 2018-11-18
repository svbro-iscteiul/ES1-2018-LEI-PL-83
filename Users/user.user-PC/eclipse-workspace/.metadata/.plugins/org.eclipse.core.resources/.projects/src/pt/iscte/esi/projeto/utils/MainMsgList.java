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
				msgMatrix[i][0]="";
				msgMatrix[i][1]="";
				msgMatrix[i][2]="";
				msgMatrix[i][3]="";
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
			for(int i=0; i<msgMatrix.length; i++) {
				if(msgMatrix[i][0]=="") {
					msgMatrix[i][0] = msg.getTime();
					msgMatrix[i][1] = msg.getChannel(); 
					msgMatrix[i][2] = msg.getSender();
					msgMatrix[i][3] = msg.getMessage();
					counter++;
					break;
				}
			}

	}
}
