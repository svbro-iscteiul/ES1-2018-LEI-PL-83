package pt.iscte.esi.projeto.utils;


import pt.iscte.esi.projeto.form.models.Message;

/**
 * Class that defines list in Main Window.
 * this matrix only supports 100 lines and 4 rows max.
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

	/**
	 * Constructor 
	 * @param headers String[][]
	 * @param msgMatrix String[][]
	 */
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
	 * @return Object[][] msgMatrix
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
				if(msgMatrix[i][j] != null
						|| msgMatrix[i][j+1] != null
						|| msgMatrix[i][j+2] != null
						|| msgMatrix[i][j+3] != null){
					counter++;
					break;
				}else {
					msgMatrix[i][0]="";
					msgMatrix[i][1]="";
					msgMatrix[i][2]="";
					msgMatrix[i][3]="";
					break;
				}
			}
		}
	}

	/**
	 * get method for headers
	 * 
	 * @return String[] headers
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
			for(int i=counter; i<msgMatrix.length; i++) {
				if(msgMatrix[i][0].equals("")
						&& msgMatrix[i][1].equals("")
						&& msgMatrix[i][2].equals("")
						&& msgMatrix[i][3].equals("")) {
					msgMatrix[i][0] = ((msg.getTime() == null) ? "" : msg.getTime());
					msgMatrix[i][1] = ((msg.getChannel() == null) ? "" : msg.getChannel()); 
					msgMatrix[i][2] = ((msg.getSender() == null) ? "" : msg.getSender());
					msgMatrix[i][3] = ((msg.getMessage() == null) ? "" : msg.getMessage());
					counter++;
					break;
				}
			}

	}
}
